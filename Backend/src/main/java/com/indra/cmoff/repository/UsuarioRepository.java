package com.indra.cmoff.repository;

import com.indra.cmoff.model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>, QuerydslPredicateExecutor<Usuario>{
	
	/**
	 * Metodo que permite obtener un Usuario a partir del usuario ingresado y la clave
	 * 
	 * @param usuario
	 * @return Usuario
	 * */
	Usuario findByUsuarioAndClave(String usuario, String clave);
	
	/**
	 * Metodo que permite obtener un Usuario a partir del usuario ingresado
	 * 
	 * @param usuario
	 * @return Usuario
	 * @autor shenao
	 * */
	Optional<Usuario> findByUsuario (String usuario);
	
	@Query("SELECT u.clave FROM Usuario u WHERE u.id = :usuarioId")
	String getPasswordByUsuarioId(@Param("usuarioId") Long usuarioId);
	
	/**
	 * Metodo que permite obtener un usuario a partir del id del empleado
	 * @autor shenao
	 * @param codEmpleado
	 * @return Usuario
	 */
	Usuario findByPersona(Long codEmpleado);
}
