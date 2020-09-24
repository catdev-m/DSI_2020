/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.InstructorInfo;
import org.registrohorasociales.entity.Carrera;
import org.registrohorasociales.entity.Instructor;
import org.registrohorasociales.entity.RolUsuario;
import org.registrohorasociales.entity.RolUsuarioPK;
import org.registrohorasociales.entity.Usuario;
import org.registrohorasociales.repository.CarreraRepository;
import org.registrohorasociales.repository.IRolUsuarioRepository;
import org.registrohorasociales.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.registrohorasociales.repository.IInstructorRepository;
import org.registrohorasociales.repository.ParametroRepository;
import org.registrohorasociales.utils.SendMail;

/**
 *
 * @author denisse_mejia
 */

@ViewScoped
@ManagedBean
public class instructorController implements Serializable{
    
    private IInstructorRepository instructorRepository;
    private List<InstructorInfo> instructoresList ;
    private InstructorInfo instructorSelecter;
    
    private String usuario;
    private String password;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
  
    
    private String formCarnet;
    private String formfName;
    private String formsName;
    private String formfApellido;
    private String formsApellido;
    private String formEmail;
    private String formestado;
    private String formescuela;
    
    private UsuarioRepository usuarioRepository;
    private IRolUsuarioRepository rolUsuarioRepository;
    private IInstructorRepository intructorRepository;
    private CarreraRepository facultadRepository;
    private SendMail sendMailService;
    private ParametroRepository parametroRepository;
    
    private Carrera facultadSelector;
    private String idfacultad;
    private String formFacultad;
    private List<Object[]> facultadList;
    private List<SelectItem> listfacultad;
    
    
@PostConstruct    
    public void init(){
        instructorRepository = ApplicationContextProvider.getApplicationContext().getBean(IInstructorRepository.class);
        facultadRepository =ApplicationContextProvider.getApplicationContext().getBean(CarreraRepository.class);   
        facultadList = facultadRepository.facultadList();
        for(Object[] b : facultadList){
            listfacultad = new ArrayList<SelectItem>();
            listfacultad.add(new SelectItem(b[0].toString(),b[1].toString()));
        }
        loadInstructors();
    }
    
    public void loadInstructors(){
        instructoresList = new ArrayList<>();

        instructorRepository.InstructorList().forEach(o->{
            InstructorInfo i = new InstructorInfo();
            i.id = o[0].toString();
            i.firstName = o[1].toString();
            i.secondName = o[2].toString();
            i.lastName = o[3].toString();
            i.slastName = o[4].toString();
            i.estatus = o[5].toString();
            i.facultad = o[6].toString();
            i.email = o[7].toString();
            instructoresList.add(i);
        });
        
    }
    
    public String crearInstructor(){
        try {
            
        usuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(UsuarioRepository.class);
        instructorRepository = ApplicationContextProvider.getApplicationContext().getBean(IInstructorRepository.class);
        sendMailService = ApplicationContextProvider.getApplicationContext().getBean(SendMail.class);
        BCryptPasswordEncoder vpass = new BCryptPasswordEncoder(12);  
        
        Usuario usr = new Usuario();
        Instructor usrInstructor = new Instructor();
        
        if (formCarnet.equals("") || formfName.equals("") || formfApellido.equals("") || formEmail.equals("") ){
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Deben ingresarse todos los datos obligatorios", ""));
            return null;
        }
        
        usrInstructor.setFirstName(formfName);
        usrInstructor.setSecondName(formsName);
        usrInstructor.setLastName(formfApellido);
        usrInstructor.setSecondLastName(formsApellido);
        usrInstructor.setId(formCarnet);
        usrInstructor.setStatus(formestado);
        usrInstructor.setFacultad(getIdfacultad());
        instructorRepository.save(usrInstructor);
        
        usr.setNombre(formfName);
        usr.setUsr(formCarnet);
        usr.setClave(vpass.encode("123")); 
        usr.setEmail(formEmail);
        usr.setStatus(formestado);
        usuarioRepository.save(usr);
        
        parametroRepository = ApplicationContextProvider.getApplicationContext().getBean(ParametroRepository.class);
        Object[] obj = parametroRepository.getValor("msjInstructor");
        
        
        sendMailService.enviar(formEmail,1,obj[0].toString()+"Mensaje adicional o parámetros");
        
        
        /*Guardando en la tabla rol_usuario*/
        rolUsuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(IRolUsuarioRepository.class);    
        RolUsuario rolusr = new RolUsuario();
        RolUsuarioPK pkRoleUsuario = new RolUsuarioPK();
        pkRoleUsuario.setIdRol(3);
        pkRoleUsuario.setUsr(formCarnet);
        rolusr.setRolUsuarioPK(pkRoleUsuario);
        rolUsuarioRepository.save(rolusr);
        
        /*Borrando campos del formulario*/
        formCarnet = null;
        formfName = null;
        formsName = null;
        formfApellido = null;
        formsApellido = null;
        formEmail = null;
        setFormFacultad("");
        setFormestado("");
        
        loadInstructors();
        
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con éxito", ""));
                } catch (Exception e) {
         FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al crear el usuario", ""));
        e.printStackTrace();
        }  
        return null;
    }
    
    public void obtenerDatos(){
        setFormfName(instructorSelecter.getFirstName());
        setFormsName(instructorSelecter.getSecondName());
        setFormfApellido(instructorSelecter.getLastName());
        setFormsApellido(instructorSelecter.getSlastName());
        setFormEmail(instructorSelecter.getEmail());
        setFormestado(instructorSelecter.getEstatus());
    }

    public String getFormestado() {
        return formestado;
    }

    public void setFormestado(String formestado) {
        this.formestado = formestado;
    }

    public String getFormescuela() {
        return formescuela;
    }

    public void setFormescuela(String formescuela) {
        this.formescuela = formescuela;
    }

    

    public SendMail getSendMailService() {
        return sendMailService;
    }

    public void setSendMailService(SendMail sendMailService) {
        this.sendMailService = sendMailService;
    }
    
    
    
    public List<Object[]> getFacultadList() {
        return facultadList;
    }

    public void setFacultadList(List<Object[]> facultadList) {
        this.facultadList = facultadList;
    }

    public String getFormFacultad() {
        return formFacultad;
    }

    public void setFormFacultad(String formFacultad) {
        this.formFacultad = formFacultad;
    }

    public Carrera getFacultadSelector() {
        return facultadSelector;
    }

    public void setFacultadSelector(Carrera facultadSelector) {
        this.facultadSelector = facultadSelector;
    }
    
    public String getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(String idfacultad) {
        this.idfacultad = idfacultad;
    }
    
    public CarreraRepository getFacultadRepository() {
        return facultadRepository;
    }

    public void setFacultadRepository(CarreraRepository facultadRepository) {
        this.facultadRepository = facultadRepository;
    }

    public List<SelectItem> getListfacultad() {
        return listfacultad;
    }

    public void setListfacultad(List<SelectItem> listfacultad) {
        this.listfacultad = listfacultad;
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

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFormCarnet() {
        return formCarnet;
    }

    public void setFormCarnet(String formCarnet) {
        this.formCarnet = formCarnet;
    }

    public String getFormfName() {
        return formfName;
    }

    public void setFormfName(String formfName) {
        this.formfName = formfName;
    }

    public String getFormsName() {
        return formsName;
    }

    public void setFormsName(String formsName) {
        this.formsName = formsName;
    }

    public String getFormfApellido() {
        return formfApellido;
    }

    public void setFormfApellido(String formfApellido) {
        this.formfApellido = formfApellido;
    }

    public String getFormsApellido() {
        return formsApellido;
    }

    public void setFormsApellido(String formsApellido) {
        this.formsApellido = formsApellido;
    }

    public String getFormEmail() {
        return formEmail;
    }

    public void setFormEmail(String formEmail) {
        this.formEmail = formEmail;
    }

    public UsuarioRepository getUsuarioRepository() {
        return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public IRolUsuarioRepository getRolUsuarioRepository() {
        return rolUsuarioRepository;
    }

    public void setRolUsuarioRepository(IRolUsuarioRepository rolUsuarioRepository) {
        this.rolUsuarioRepository = rolUsuarioRepository;
    }

    public IInstructorRepository getIntructorRepository() {
        return intructorRepository;
    }

    public void setIntructorRepository(IInstructorRepository intructorRepository) {
        this.intructorRepository = intructorRepository;
    }
    
    
    public IInstructorRepository getInstructorRepository() {
        return instructorRepository;
    }

    public void setInstructorRepository(IInstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorInfo> getInstructoresList() {
        return instructoresList;
    }

    public void setInstructoresList(List<InstructorInfo> instructoresList) {
        this.instructoresList = instructoresList;
    }

    public InstructorInfo getInstructorSelecter() {
        return instructorSelecter;
    }

    public void setInstructorSelecter(InstructorInfo instructorSelecter) {
        this.instructorSelecter = instructorSelecter;
    }
    
    
}
