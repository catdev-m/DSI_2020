/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Proyecto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author balmore
 */
@Repository
public interface ProyectoRepository {
    @Query(nativeQuery = true, value = "select * from proyecto")
    public List<Proyecto> proyectoList();
}
