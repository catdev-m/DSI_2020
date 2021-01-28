/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.controller;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import org.registrohorasociales.config.ApplicationContextProvider;
import org.registrohorasociales.entity.Anuncio;
import org.registrohorasociales.repository.IAnuncioRepository;

/**
 *
 * @author denisse_mejia
 */
@Named(value = "anunciosCarrousel")
@SessionScoped
public class AnunciosCarrousel implements Serializable {
    private List<Anuncio> listAnuncio;
    private IAnuncioRepository anuncioRepository;
    /**
     * Creates a new instance of AnunciosCarrousel
     */
    public AnunciosCarrousel() {
    }
    
    @PostConstruct
    public void init(){
        if(anuncioRepository != null){
            anuncioRepository = ApplicationContextProvider.getApplicationContext().getBean(IAnuncioRepository.class);
            ObtenerAnuncio();
        }else{}
    }
    
    public void ObtenerAnuncio(){
         listAnuncio = anuncioRepository.findAll();
     }

    public List<Anuncio> getListAnuncio() {
        return listAnuncio;
    }

    public void setListAnuncio(List<Anuncio> listAnuncio) {
        this.listAnuncio = listAnuncio;
    }
    
    
    
}
