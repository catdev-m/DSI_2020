/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Miguel
 */
@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer>{
    
    
    @Query(nativeQuery = true, value ="select * from carrera")
    public List<Carrera> carrerasList();
    
    @Query(nativeQuery = true, value = "Select * from carrera where idcarrera=?")
    public Carrera getCarreraById(int code);
}
