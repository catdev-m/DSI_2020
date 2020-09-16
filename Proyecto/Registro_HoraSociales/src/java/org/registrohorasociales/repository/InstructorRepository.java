/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denisse_mejia
 */
@Repository
public interface InstructorRepository extends JpaRepository<Instructor, String>{
    
    @Query(nativeQuery = true, value = "select * from instructor where status = 'A'")
    public List<Instructor> InstructorList();
    
}