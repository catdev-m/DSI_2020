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
    
    @Query(nativeQuery = true, value="select e.*, c.nombrecarrera, c.codigocarrera, i.nom_institucion,"
                    + "inst.first_name, inst.second_name, inst.last_name, inst.second_last_name\n" 
                    +"from estudiante e, carrera c, institucion i, instructor inst\n" 
                    +"where e.idcarrera = c.idcarrera and e.idinstitucion = i.id_institucion "
                    + "and e.idInstructor = inst.id")
    public List<Object[]> estudiantesInfoList();
    
}
