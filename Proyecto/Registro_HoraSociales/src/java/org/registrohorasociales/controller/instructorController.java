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
import java.util.UUID;
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
import org.primefaces.PrimeFaces;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.dto.EscuelaInfoDto;
import org.registrohorasociales.dto.InstructorInfoDto;
import org.registrohorasociales.entity.Carrera;
import org.registrohorasociales.entity.Escuela;
import org.registrohorasociales.entity.Instructor;
import org.registrohorasociales.entity.RolUsuario;
import org.registrohorasociales.entity.RolUsuarioPK;
import org.registrohorasociales.entity.Usuario;
import org.registrohorasociales.repository.CarreraRepository;
import org.registrohorasociales.repository.EscuelaRepository;
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
public class instructorController implements Serializable {

    private IInstructorRepository instructorRepository;
    private List<InstructorInfoDto> instructoresList;
    private InstructorInfoDto instructorSelecter;

    private String usuario;
    private String passBase;
    private String password;

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
    private EscuelaRepository escuelaRepository;
    private SendMail sendMailService;
    private ParametroRepository parametroRepository;

    private Escuela escuelaSelector;
    private String idfacultad;
    private String formFacultad;
    private List<EscuelaInfoDto> escuelaList;
    private List<SelectItem> escuelaSelect;

    @PostConstruct
    public void init() {
        instructorRepository = ApplicationContextProvider.getApplicationContext().getBean(IInstructorRepository.class);
        escuelaRepository = ApplicationContextProvider.getApplicationContext().getBean(EscuelaRepository.class);
        usuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(UsuarioRepository.class);
        sendMailService = ApplicationContextProvider.getApplicationContext().getBean(SendMail.class);
        parametroRepository = ApplicationContextProvider.getApplicationContext().getBean(ParametroRepository.class);
        rolUsuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(IRolUsuarioRepository.class);
        
        escuelaSelect = new ArrayList<>();
        escuelaRepository.findAll().forEach(o -> {
            escuelaSelect.add(new SelectItem(o.getIdEscuela(),o.getEscuela()));
        });
        loadInstructors();
    }

    public void loadInstructors() {
        instructoresList = new ArrayList<>();

        instructorRepository.InstructorList().forEach(o -> {
            InstructorInfoDto i = new InstructorInfoDto();
            i.id = o[0].toString();
            i.firstName = o[1].toString();
            i.secondName = o[2].toString();
            i.lastName = o[3].toString();
            i.slastName = o[4].toString();
            i.estatus = o[5].toString();
            i.escuela = o[6].toString();
            i.email = o[7].toString();
            instructoresList.add(i);
        });

    }

    public void EliminarInstructor() {
        try {
            instructorRepository.delete(formCarnet);
            loadInstructors();
            
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha eliminado el usuario: " + formCarnet, ""));
            clearFormInst();
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un eliminar el usuario", ""));
            e.printStackTrace();
        }
    }

    public String crearInstructor() {
        try {
            passBase = UUID.randomUUID().toString();
            password = passBase.split("-")[0]+passBase.split("-")[1];
            
            System.out.println(password);
            BCryptPasswordEncoder vpass = new BCryptPasswordEncoder(12);

            Usuario usr = new Usuario();
            Instructor usrInstructor = new Instructor();

            if (formCarnet.equals("") || formfName.equals("") || formfApellido.equals("") || formEmail.equals("")) {
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
            usrInstructor.setIdEscuela(formescuela);
            instructorRepository.save(usrInstructor);

            usr.setNombre(formfName);
            usr.setUsr(formCarnet);
            usr.setClave(vpass.encode(password));
            usr.setEmail(formEmail);
            usr.setStatus(formestado);
            usuarioRepository.save(usr);
            

            Object[] obj = parametroRepository.getValor("msjInstructor");
            sendMailService.enviar(formEmail, 1, obj[0].toString() + "\nSu Usuario es: "+formCarnet+"\nSu password: "+password);
             
            /*Guardando en la tabla rol_usuario*/
            RolUsuario rolusr = new RolUsuario();
            RolUsuarioPK pkRoleUsuario = new RolUsuarioPK();
            pkRoleUsuario.setIdRol(3);
            pkRoleUsuario.setUsr(formCarnet);
            rolusr.setRolUsuarioPK(pkRoleUsuario);
            rolUsuarioRepository.save(rolusr);

            loadInstructors();
            clearFormInst();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario creado con éxito", ""));
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Se generó un error al crear el usuario", ""));
            e.printStackTrace();
        }
        return null;
    }

    public void clearFormInst() {
        /*Borrando campos del formulario*/

        formCarnet = null;
        formfName = null;
        formsName = null;
        formfApellido = null;
        formsApellido = null;
        formEmail = null;
        setFormescuela("");
        setFormestado("");
    }
    public void entra(){
        System.out.println("ENTRA AQUI");
    }
    
    public void obtenerDatos() {
        setFormfName(instructorSelecter.getFirstName());
        setFormsName(instructorSelecter.getSecondName());
        setFormfApellido(instructorSelecter.getLastName());
        setFormsApellido(instructorSelecter.getSlastName());
        setFormEmail(instructorSelecter.getEmail());
        setFormestado(instructorSelecter.getEstatus());
        setFormescuela(instructorSelecter.getEscuela());
        setFormCarnet(instructorSelecter.getId());
    }

    public Escuela getEscuelaSelector() {
        return escuelaSelector;
    }

    public void setEscuelaSelector(Escuela escuelaSelector) {
        this.escuelaSelector = escuelaSelector;
    }

    public List<SelectItem> getEscuelaSelect() {
        return escuelaSelect;
    }

    public void setEscuelaSelect(List<SelectItem> escuelaSelect) {
        this.escuelaSelect = escuelaSelect;
    }

    
    public String getPassBase() {
        return passBase;
    }

    public void setPassBase(String passBase) {
        this.passBase = passBase;
    }

    public EscuelaRepository getEscuelaRepository() {
        return escuelaRepository;
    }

    public void setEscuelaRepository(EscuelaRepository escuelaRepository) {
        this.escuelaRepository = escuelaRepository;
    }

    public ParametroRepository getParametroRepository() {
        return parametroRepository;
    }

    public void setParametroRepository(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }

    public List<EscuelaInfoDto> getEscuelaList() {
        return escuelaList;
    }

    public void setEscuelaList(List<EscuelaInfoDto> escuelaList) {
        this.escuelaList = escuelaList;
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


    public String getFormFacultad() {
        return formFacultad;
    }

    public void setFormFacultad(String formFacultad) {
        this.formFacultad = formFacultad;
    }

    public String getIdfacultad() {
        return idfacultad;
    }

    public void setIdfacultad(String idfacultad) {
        this.idfacultad = idfacultad;
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

    public IInstructorRepository getInstructorRepository() {
        return instructorRepository;
    }

    public void setInstructorRepository(IInstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public List<InstructorInfoDto> getInstructoresList() {
        return instructoresList;
    }

    public void setInstructoresList(List<InstructorInfoDto> instructoresList) {
        this.instructoresList = instructoresList;
    }

    public InstructorInfoDto getInstructorSelecter() {
        return instructorSelecter;
    }

    public void setInstructorSelecter(InstructorInfoDto instructorSelecter) {
        this.instructorSelecter = instructorSelecter;
    }

}
