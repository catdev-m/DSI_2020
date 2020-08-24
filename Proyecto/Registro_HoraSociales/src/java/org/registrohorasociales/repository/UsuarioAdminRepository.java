/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import org.registrohorasociales.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denisse_mejia
 */
@Repository
public interface UsuarioAdminRepository extends JpaRepository<Usuario, String>{
   
}
