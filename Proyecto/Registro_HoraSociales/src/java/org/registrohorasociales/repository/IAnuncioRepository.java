/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import org.registrohorasociales.entity.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denisse_mejia
 */

@Repository
public interface IAnuncioRepository extends JpaRepository<Anuncio, String>{
    @Query(nativeQuery = true, value = "SELECT ifnull(max(ID)+1,1) FROM anuncio ")
     public int nextId();
    
}
