/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Institucion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balmore
 */
@Repository
public interface InstitucionRepository extends JpaRepository<Institucion, String>{
    
    @Query(nativeQuery = true, value = "select * from institucion where status = 'A' ")
    public List<Institucion> institucionList();
    
    @Query(nativeQuery = true, value = "select * from institucion where idInstitucion = ?")
    public Institucion getInstitucionById(int id);
   
    @Query(nativeQuery = true, value = "delete from 'institucion' where idInstitucion = ?")
    public void delete(int idInstitucion);
}
