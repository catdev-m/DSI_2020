/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.utils;

/**
 *
 * @author denisse_mejia
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import org.registrohorasociales.repository.ParametroRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class SendMail {
        private String asunto;
        private String servermail;
        private String port;
        private String username;
        private String password;
        
        @Autowired
        ParametroRepository param ;
    public void enviar(String correo, int tipo, String msj) {        
        
        for(Object[] obj : param.getParametros(tipo)){
            if(obj[0].toString().equals("username")){
                username = obj[1].toString();
            }
            if(obj[0].toString().equals("password")){
                password = obj[1].toString();
            }
            if(obj[0].toString().equals("mailServer")){
                servermail = obj[1].toString();
            }
            if(obj[0].toString().equals("puerto")){
                port = obj[1].toString();
            }if(obj[0].toString().equals("asunto")){
                asunto = obj[1].toString();
            }
        }
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", servermail);
        prop.put("mail.smtp.port", port);
        prop.put("mail.smtp.starttls.enable","true");
        prop.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("proyeccionsocial.ues.sv@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(correo)
            );
            message.setSubject(asunto);
            message.setText(msj);

            Transport.send(message);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getServermail() {
        return servermail;
    }

    public void setServermail(String servermail) {
        this.servermail = servermail;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ParametroRepository getParam() {
        return param;
    }

    public void setParam(ParametroRepository param) {
        this.param = param;
    }
    

}
