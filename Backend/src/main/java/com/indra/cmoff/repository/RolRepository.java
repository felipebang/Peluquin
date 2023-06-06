package com.indra.cmoff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.indra.cmoff.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long>, QuerydslPredicateExecutor<Rol> {
	
	/**
	 * Metodo que permite obtener lista de roles apartir del id del usuario
	 * @autor shenao
	 * @param idUsuario
	 * @return List<Rol>
	 * */
	 
	@Query("SELECT r FROM Rol r INNER JOIN UsuarioRol ur "
			+ "ON ur.id.rol.id = r.id WHERE ur.id.idUsuario = :idusuario ")
	List<Rol> findRolDescripcionByIdUsuarioRol(@Param("idusuario") Long idUsuario);
	
	//1. Metodo que permite obtener los Roles apartir de un estado
	List<Rol> findByEstado(Boolean estado);
	
	/**
	 * Metodo que permite obtener un rol a partir del codigo
	 * 
	 * @param codigo
	 * @return Rol
	 * @autor shenao
	 * */
	Rol findByCodigo (String codigo);

}
