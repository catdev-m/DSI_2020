/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Comentario;
import org.registrohorasociales.repository.IComentarioRepository;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Miguel
 */
@ViewScoped
@ManagedBean
@SessionScoped
public class ComentarioController implements Serializable{
    private List<Comentario> comentarios;
    private Comentario comentSelect;
    private IComentarioRepository repo;
    static String user1, user2;
    static int idArchivo;
    private String formComentario;
    
    @PostConstruct
    public void initialize(){
        repo = ApplicationContextProvider.getApplicationContext().getBean(IComentarioRepository.class);
        cargarComentariosTutor(user1, user2, idArchivo);
    }
    
    public void cargarComentariosTutor(String user1, String user2, int id){
        comentarios = new ArrayList<>();
        comentarios = repo.comentariosList(user1, user2, id);
    }
    
    public void crearComentario(){
        String value = FacesContext.getCurrentInstance().
        getExternalContext().getRequestParameterMap().get("idArchivo");
        
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        Comentario com = new Comentario();
        com.setUsuario(user);
        com.setComentario(formComentario);
        com.setIdArchivo(Integer.parseInt(value));
        try {
            repo.save(com);
        } catch (Exception e) {
        }
        System.out.println("Id que falla: "+value);
        
    }
      
    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public Comentario getComentSelect() {
        return comentSelect;
    }

    public void setComentSelect(Comentario comentSelect) {
        this.comentSelect = comentSelect;
    }

    public String getFormComentario() {
        return formComentario;
    }

    public void setFormComentario(String formComentario) {
        this.formComentario = formComentario;
    }

}
