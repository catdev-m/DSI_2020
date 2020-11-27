/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.primefaces.model.menu.MenuModel;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.MenuPrincipalDto;
import org.registrohorasociales.entity.RolUsuario;
import org.registrohorasociales.entity.RolUsuarioPK;
import org.registrohorasociales.entity.Usuario;
import org.registrohorasociales.repository.IRolUsuarioRepository;
import org.registrohorasociales.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 *
 * @author denisse_mejia
 */
@SessionScoped
@ManagedBean
public class LoginSecurity implements Serializable{

    private String usuario;
    private String password;
    private String formUser;
    private String formUserName;
    private String formEmail;
    private String formPassword;
    private String formRol;
    private String formEstado;
    
    private UsuarioRepository usuarioRepository;
    private IRolUsuarioRepository rolUsuarioRepository;
    
    private List<MenuPrincipalDto> menuHtml;
    private MenuModel model;
      
      
    public LoginSecurity() {
    }
    
    public String ingresar(){
        try {

            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/j_spring_security_check");
            dispatcher.forward(request, response);
            
           
        System.out.println(getUsuario());
        System.out.println(getPassword());

        } catch (Exception e) {

        }
        return null;
        
    }
    
    @PostConstruct
    public void init(){
        System.out.print("Mensaje de inicio");
    }
    
    public void logOut() {
        try {            
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) context.getRequest();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/logout");
            dispatcher.forward(request, response);
        } catch (ServletException | IOException ex) {
        }
        
    }
    
    public String crearUsuario(){
        try {
        usuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(UsuarioRepository.class);
        BCryptPasswordEncoder vpass = new BCryptPasswordEncoder(12);  
        Usuario usr = new Usuario();
        
        if (formUserName.equals("") || formUser.equals("") || formEmail.equals("") || formPassword.equals("") ){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Deben ingresarse todos los datos", ""));
            return null;
        }
        usr.setNombre(formUserName);
        usr.setUsr(formUser);
        usr.setEmail(formEmail);
        usr.setStatus("A");
        usr.setClave(vpass.encode(formPassword)); 
        usuarioRepository.save(usr);
        
        /*Guardando en la tabla rol_usuario*/
        rolUsuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(IRolUsuarioRepository.class);    
        RolUsuario rolusr = new RolUsuario();
        RolUsuarioPK pkRoleUsuario = new RolUsuarioPK();
        pkRoleUsuario.setIdRol(3);
        pkRoleUsuario.setUsr(formUser);
        rolusr.setRolUsuarioPK(pkRoleUsuario);
        rolUsuarioRepository.save(rolusr);
        
        /*Borrando campos del formulario*/
        formUserName = null;
        formEmail = null;
        formUser = null;
        formPassword= null;
        setFormEstado("");
        

        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con éxito", ""));
                } catch (Exception e) {
         FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al crear el usuario", ""));
        e.printStackTrace();
        }  
        return null;
    }

    public IRolUsuarioRepository getRolUsuarioRepository() {
        return rolUsuarioRepository;
    }

    public void setRolUsuarioRepository(IRolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }
    

 
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFormUser() {
        return formUser;
    }

    public void setFormUser(String formUser) {
        this.formUser = formUser;
    }

    public String getFormUserName() {
        return formUserName;
    }

    public void setFormUserName(String formUserName) {
        this.formUserName = formUserName;
    }

    public String getFormEmail() {
        return formEmail;
    }

    public void setFormEmail(String formEmail) {
        this.formEmail = formEmail;
    }

    public String getFormPassword() {
        return formPassword;
    }

    public void setFormPassword(String formPassword) {
        this.formPassword = formPassword;
    }

    public String getFormRol() {
        return formRol;
    }

    public void setFormRol(String formRol) {
        this.formRol = formRol;
    }

    public String getFormEstado() {
        return formEstado;
    }

    public void setFormEstado(String formEstado) {
        this.formEstado = formEstado;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<MenuPrincipalDto> getMenuHtml() {
        return menuHtml;
    }

    public void setMenuHtml(List<MenuPrincipalDto> menuHtml) {
        this.menuHtml = menuHtml;
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    

    
}