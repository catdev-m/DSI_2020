package org.registrohorasociales.config;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import org.registrohorasociales.controller.loginSecurity;
import org.registrohorasociales.dto.MenuPrincipalDto;
import org.registrohorasociales.entity.Rol;
import org.registrohorasociales.entity.Usuario;
import org.registrohorasociales.repository.MenuRepository;
import org.registrohorasociales.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author denisse_mejia
 */
public class AuthenticationProviderHs implements AuthenticationProvider{
    
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private MenuRepository menuRepository;
    
    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
    
    MenuModel  modelRender = new DefaultMenuModel();    
    List<MenuPrincipalDto> menuPpal = new ArrayList<>();
    BCryptPasswordEncoder vpass = new BCryptPasswordEncoder(12);
    FacesContext facesContext = FacesContext.getCurrentInstance();
        loginSecurity sec = (loginSecurity) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "loginSecurity");
        Authentication authRet = null;
        List<GrantedAuthority> roles = new ArrayList<>();
        GrantedAuthority role = null;
        String usuario = auth.getPrincipal().toString().trim();  
        
        System.out.println(usuario);
       String password = auth.getCredentials().toString().trim();
        if (usuario.isEmpty() && password.isEmpty()) {
            throw new BadCredentialsException("Usuario o Contraseña Invalidos");
        }
        
        //List<Usuario> listUsuarios = usuarioRepository.UserList();
        Usuario usr = usuarioRepository.getusuario(usuario);
        
        if (vpass.matches(password, usr.getClave())) {
            //System.out.println("Cantidad de usuarios "+usr.getClave()+" "+usr.getUsr());
            List<Object[]> rolList= usuarioRepository.rol(usr.getUsr());
            if (rolList.isEmpty()){
                throw new BadCredentialsException("Usuario o Contraseña Invalidos");
            }else{
                Object[] rol =  rolList.get(0) ;
                role = new SimpleGrantedAuthority(rol[1].toString());
                roles.add(role);
                List<Object[]> menuPpalDb = menuRepository.menuPpal(usr.getUsr(), role.toString());
                System.out.println("MENU PRINCIPAL "+menuPpal);
                
                for(Object[] obt: menuPpalDb){
                  /*  MenuPrincipalDto mp = new MenuPrincipalDto();
                    mp.setIdMenu(obt[0].toString());
                    mp.setMenuName(obt[1].toString());
                    mp.setIcono(obt[2].toString());
                    menuPpal.add(mp);*/
                  DefaultSubMenu firstSubmenu = new DefaultSubMenu();
                  firstSubmenu.setLabel(obt[1].toString());
                  firstSubmenu.setIcon(obt[2].toString());
                  modelRender.getElements().add(firstSubmenu);
                }
                DefaultMenuItem item = new DefaultMenuItem();
                item.setValue("Cerrar sesión");
                item.setIcon("ui-icon ui-icon-power-settings-new");
                item.setAjax(false);
                item.setCommand("#{loginSecurity.logOut()}" );
                modelRender.getElements().add(item);
                sec.setModel(modelRender);
                
                authRet = new UsernamePasswordAuthenticationToken(usuario, password, roles);
            }
        }else{
             throw new BadCredentialsException("Usuario o Contraseña Invalidos");
        }
        return authRet;
    }

    public void crearUsuarios(Usuario usr){
        
        usuarioRepository.save(usr);
        
    }
    
    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
    
}
