package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.indra.cmoff.model.PermisoRol;

import java.util.List;

public interface PermisoRolRepository extends JpaRepository<PermisoRol, Long>, QuerydslPredicateExecutor<PermisoRol> {

	//1. Consulta que permite obtener los modulos en PermisoRol apartir del identificador del rol
	@Query("Select distinct per.id.modulo.id FROM PermisoRol per "
		 + "WHERE per.id.rol.id = :idRol")
	List<String> moduloAsignadosRol(@Param("idRol") String idRol);

	//1. Consulta que permite obtener los PermisoRol apartir de idRol
	List<PermisoRol> findByIdRolId(Long idRol);
	
	List<PermisoRol> findByIdModuloIdAndIdRolId(Long idModulo, Long idRol);
	
	@Modifying
	@Query("DELETE FROM PermisoRol pr WHERE pr.id.rol.id = :idRol")
	void eliminarPermisosRolPorIdRol(@Param("idRol") Long idRol);
}
