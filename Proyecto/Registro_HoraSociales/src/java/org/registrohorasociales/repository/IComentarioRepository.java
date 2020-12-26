/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IComentarioRepository extends JpaRepository<Comentario, Integer>{
    
    @Query(nativeQuery = true, value = "select * from comentario where (usuario = ? or usuario = ?) and idArchivo = ?")
    public List<Comentario> comentariosList(String user1, String user2, int id);
}
