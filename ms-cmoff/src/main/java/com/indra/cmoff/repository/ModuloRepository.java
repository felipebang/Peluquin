package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.indra.cmoff.model.Modulo;

import java.util.List;

public interface ModuloRepository extends JpaRepository<Modulo, Long>, QuerydslPredicateExecutor<Modulo> {

	/**
	 * Metodo que permite obtener la lista de modulos apartir del estado
	 * @param estado
	 * @return List<Modulo>
	 */	
	List<Modulo> findAllByEstado(Boolean estado);
	
	/**
	 * Metodo que permite obtener la lista de modulos apartir del estado en orden descendiente
	 * de acuerdo al modulo enlazado
	 * @param estado
	 * @return List<Modulo>
	 */	
	List<Modulo> findAllByEstadoOrderByModuloEnlazadoDescNombreAsc(Boolean estado);
	/**
	 * Metodo que permite obtener modulo por medio del codigo
	 * @autor shenao
	 * @param codigo
	 * @return Modulo
	 */	
	Modulo findByCodigo(String codigo);
	
	@Query(value = "SELECT id FROM modulo WHERE id IN "
			+ "(select id_padre from modulo) and estado= :estado", 
		  nativeQuery = true)
	List<Long> findAllParentsByEstado(@Param("estado") Boolean estado);

}
