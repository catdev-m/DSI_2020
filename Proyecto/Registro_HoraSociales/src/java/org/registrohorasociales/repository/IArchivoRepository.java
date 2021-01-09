/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.util.List;
import org.registrohorasociales.entity.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author denisse_mejia
 */
@Repository
public interface IArchivoRepository extends JpaRepository<Archivo, String>{
    @Query(nativeQuery = true, value = "select id_file, url, descripcion, carnet, fecha, nombres, apellidos from archivo, estudiante \n" +
                                        "where archivo.carnet = estudiante.due and archivo.carnet = ? order by fecha desc ")
    public List<Object[]> ArchivoList(String i);
    
    @Query(nativeQuery = true, value = "select * from archivo")
    public List<Archivo> listaArchivo();
    
    @Query(nativeQuery = true, value = "select * from archivo where id_file =?")
    public Archivo finByIdFile(String id_file);
    
    @Query(nativeQuery = true, value = "select * from archivo where carnet = ?")
    public List<Archivo> listaByCarnet(String due);
}
