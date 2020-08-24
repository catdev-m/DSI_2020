/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.io.Serializable;
import java.util.List;
import org.registrohorasociales.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.registrohorasociales.entity.Usuario;
import org.springframework.data.jpa.repository.Query;




/**
 *
 * @author denisse_mejia
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
    
    @Query(nativeQuery = true, value = "select  * from usuario")
    public List<Usuario> UserList();
    
    @Query(nativeQuery = true, value = "SELECT * FROM `usuario` WHERE usr = ?")
    public Usuario getusuario(String usr);
    
    @Query(nativeQuery = true, value = "SELECT B.* FROM rol_usuario A, rol B WHERE A.id_rol = B.id AND usr = ?")
    public List<Object[]> rol(String usuario);
}
