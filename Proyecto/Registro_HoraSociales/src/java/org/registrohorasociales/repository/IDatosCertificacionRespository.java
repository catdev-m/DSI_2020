/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Datoscertificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel
 */
@Repository
public interface IDatosCertificacionRespository extends JpaRepository<Datoscertificacion,Integer> {
    
    @Query(nativeQuery = true, value = "select * from datosCertificacion")
    public List<Datoscertificacion> datosCertificacionList();
    
    @Query(nativeQuery = true, value = "select * from datosCertificacion where id = ?")
    public Datoscertificacion getById(int i);
}
