package com.indra.cmoff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.indra.cmoff.model.UsuarioRol;
import com.indra.cmoff.model.UsuarioRolPK;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, UsuarioRolPK>, QuerydslPredicateExecutor<UsuarioRol> {
	
	//1. Metodo que permite obtener la lista de Usuarios-Roles a partir de un id de usuario
	List<UsuarioRol> findByIdIdUsuarioId (Long idUsuario);
	
	@Modifying
	@Query("DELETE FROM UsuarioRol usr WHERE usr.id.idUsuario.id = :idUser")
	void eliminarRolesPorIdUsuario(@Param("idUser") Long idUser);

}
