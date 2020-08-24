/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.registrohorasociales.repository;

import java.io.Serializable;
import java.util.List;
import org.registrohorasociales.entity.OpcionPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author denisse_mejia
 */
public interface MenuRepository extends JpaRepository<OpcionPrincipal, Integer> {

    @Query(nativeQuery = true, value = " SELECT DISTINCT AOP.ID, AOP.DESCRIPCION, AOP.MENU_ICON FROM opcion_principal AOP INNER JOIN opcion AO\n"
            + "ON AOP.ID = AO.ID_OPC_PPAL\n"
            + "INNER JOIN opcion_rol AOR ON AO.ID = AOR.id_opcion\n"
            + "INNER JOIN ROL AR ON AR.id = AOR.id_rol\n"
            + "INNER JOIN rol_usuario ARU ON ARU.id_rol = AR.id\n"
            + "INNER JOIN usuario AU ON AU.usr = ARU.usr\n"
            + "WHERE AU.usr = ?1 AND AR.desc = ?2 ")
    public List<Object[]> menuPpal(String usuario, String rol);

    @Query(nativeQuery = true, value = " SELECT AO.* FROM opcion_principal AOP INNER JOIN opcion AO\n"
            + "ON AOP.ID = AO.ID_OPC_PPAL\n"
            + "INNER JOIN opcion_rol AOR ON AO.ID = AOR.id_opcion\n"
            + "INNER JOIN ROL AR ON AR.id = AOR.id_rol\n"
            + "INNER JOIN rol_usuario ARU ON ARU.id_rol = AR.id\n"
            + "INNER JOIN usuario AU ON AU.usr = ARU.usr\n"
            + "WHERE AU.usr = ?1 AND AR.desc = ?2\n"
            + "and AO.id_opc_ppal = ?3 ")
    public List<Object[]> subMenuopc(String usuario, String rol, String opc);

}
