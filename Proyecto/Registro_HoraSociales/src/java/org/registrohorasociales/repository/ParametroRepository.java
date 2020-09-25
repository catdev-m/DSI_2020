/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Parametro;
import org.registrohorasociales.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denisse_mejia
 */

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, String>{
    
    @Query(nativeQuery = true, value = "select valor from parametro a where a.parametro =? ")
    public Object[] getValor(String paramName);
    
    @Query(nativeQuery = true, value = "select parametro, valor from parametro a where a.tipo =? ")
    public List<Object[]> getParametros(int type);
}
