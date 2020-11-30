/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.RelacionEstudianteProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel
 */
@Repository
public interface RelacionEstudianteProyectoRepository extends JpaRepository<RelacionEstudianteProyecto, Integer>{
    @Query(nativeQuery = true, value = "select * from relacion_estudiante_proyecto")
    public List<RelacionEstudianteProyecto> relacionesList();
    
    @Query(nativeQuery = true, value = "select * from relacion_estudiante_proyecto")
    public List<Object[]> objectList();
    
}
