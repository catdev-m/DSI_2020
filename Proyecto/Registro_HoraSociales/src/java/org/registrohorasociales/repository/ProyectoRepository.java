/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balmore
 */
@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Integer>{
    @Query(nativeQuery = true, value = "select * from proyecto")
    public List<Proyecto> proyectoList();
    
    @Query(nativeQuery = true, value = "select * from proyecto where cupos_proyecto > 0")
    public List<Proyecto> proyectoConCupo();
    
    @Query(nativeQuery = true, value = "select * from proyecto where id_proyecto = ?")
    public Proyecto proyectoById(int id);
}
