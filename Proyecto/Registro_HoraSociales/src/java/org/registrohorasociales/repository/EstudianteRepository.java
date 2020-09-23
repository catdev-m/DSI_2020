/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Miguel
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, String>{
    @Query(nativeQuery = true, value="select * from estudiante")
    public List<Estudiante> estudiantesList();
    
    @Query(nativeQuery = true, name = "select * from estudiante where due = ?")
    public Estudiante findByDue(String due);
}
