/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel
 */
@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String>{
    
   @Query(nativeQuery = true,value = "select * from solicitud")
   public List<Solicitud> SolicitudList();
   
   @Query(nativeQuery = true , value = "select * from solicitud where due = ?")
   public Solicitud getSolicitud(String due);
   
}
