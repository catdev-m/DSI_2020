/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Estudiante;
import org.registrohorasociales.entity.Rol;
import org.registrohorasociales.entity.RolUsuario;
import org.registrohorasociales.entity.RolUsuarioPK;
import org.registrohorasociales.entity.Usuario;
import org.registrohorasociales.repository.EstudianteRepository;
import org.registrohorasociales.repository.IRolUsuarioRepository;
import org.registrohorasociales.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Miguel
 */
@ManagedBean
@ViewScoped
public class MailController implements Serializable {

    @PostConstruct
    public void initialize() {
    }

    public void accept(String due, String nombre, String apellido, int ciclo, int idCarrera) {
        Properties p = new Properties();
        p.setProperty("mail.smtp.host", "smtp.gmail.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.auth", "true");

        Session s = Session.getDefaultInstance(p);
        String from = "proyeccionsocial.ues.sv@gmail.com";
        String pass = "piaqzdvfdwtezrml";
        String clave = generatePass();
        String to = due + "@ues.edu.sv";
        String asunto = "Solicitud aprobada";
        String mensaje = "Bienvenido! Estás autorizado para iniciar tu servicio social.\n"
                + "Puedes iniciar sesión en el sistema con las siguientes credenciales\n"
                + "Carnet: " + due + " \n"
                + "Contraseña: " + clave + "\n";

        MimeMessage mail = new MimeMessage(s);
        try {
            mail.setFrom(new InternetAddress(from));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mail.setSubject(asunto);
            mail.setText(mensaje);

            Transport t = s.getTransport("smtp");
            t.connect(from, pass);
            t.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            t.close();

            addUserAprobbed(due, to, nombre, apellido, clave, ciclo, idCarrera);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno aprobado para su servicio social", ""));

        } catch (MessagingException ex) {
            Logger.getLogger(solicitudController.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Algo ha fallado", ""));
        }

    }

    public void deny(String due, String nombre) {
        Properties p = new Properties();
        p.setProperty("mail.smtp.host", "smtp.gmail.com");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.port", "587");
        p.setProperty("mail.smtp.auth", "true");

        Session s = Session.getDefaultInstance(p);
        String from = "proyeccionsocial.ues.sv@gmail.com";
        String pass = "piaqzdvfdwtezrml";
        String to = due + "@ues.edu.sv";
        String asunto = "Solicitud rechazada";
        String mensaje = nombre + " tu solicitud para iniciar el servicio social ha sido rechazada.\n"
                + "Visita nuestras instalaciones para saber más\n";

        MimeMessage mail = new MimeMessage(s);
        try {
            mail.setFrom(new InternetAddress(from));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mail.setSubject(asunto);
            mail.setText(mensaje);

            Transport t = s.getTransport("smtp");
            t.connect(from, pass);
            t.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            t.close();

            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La solicitud se ha rechazado", ""));
            deleteSolicitud(due);

        } catch (MessagingException ex) {
            Logger.getLogger(solicitudController.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Algo ha fallado", ""));
        }

    }

    public String generatePass() {
        String pass;
        int numero = (int) (Math.random() * 9 + 1);
        pass = String.valueOf(numero);
        for (int i = 0; i < 4; i++) {
            numero = (int) (Math.random() * 9 + 1);
            pass += String.valueOf(numero);
        }
        return pass;
    }

    public void addUserAprobbed(String carnet, String email, String name, String apellido, String password, int c, int carrer) {
        BCryptPasswordEncoder vpass = new BCryptPasswordEncoder(12);
        UsuarioRepository ru = ApplicationContextProvider.getApplicationContext().getBean(UsuarioRepository.class);
        EstudianteRepository er = ApplicationContextProvider.getApplicationContext().getBean(EstudianteRepository.class);
        String pass = vpass.encode(password);
        IRolUsuarioRepository rolUsuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(IRolUsuarioRepository.class);
        
        Estudiante e = new Estudiante();
        e.setNombres(name);
        e.setApellidos(apellido);
        e.setCorreo(email);
        e.setDue(carnet);
        e.setClave(pass);
        e.setCicloInicio(c);
        e.setIdinstitucion(1);
        e.setIdcarrera(carrer);
        er.save(e);
        Usuario usr = new Usuario();
        usr.setUsr(carnet);
        usr.setClave(pass);
        ru.save(usr);        
        
        rolUsuarioRepository = ApplicationContextProvider.getApplicationContext().getBean(IRolUsuarioRepository.class);    
        RolUsuario rolusr = new RolUsuario();
        RolUsuarioPK pkRoleUsuario = new RolUsuarioPK();
        pkRoleUsuario.setIdRol(2);
        pkRoleUsuario.setUsr(carnet);
        rolusr.setRolUsuarioPK(pkRoleUsuario);
        rolUsuarioRepository.save(rolusr);
        
        deleteSolicitud(carnet);
    }

    public void deleteSolicitud(String carnet) {
        solicitudController soli = new solicitudController();
        soli.eliminarCarreraById(carnet);
    }

}
