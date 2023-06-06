package com.indra.cmoff.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.indra.cmoff.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona, Long>, QuerydslPredicateExecutor<Persona> {

	/**
	 * Metodo que permite obtener una Persona a partir del codigo empleado ingresado
	 * 
	 * @param codEmpleado
	 * @return Persona
	 * @autor shenao
	 */
	Persona findByCodigoEmpleado(Long codEmpleado);

	/**
	 * Metodo que permite obtener FilterPersonasProyectoDTO apartir de un llamado a
	 * una función fnc_filtra_personas_rol que me filtra las personas que cumplen
	 * con ciertos conocimientos y su respectivo nivel de madurez
	 * (Modulo de mapa de conocimientos por proyecto)
	 *
	 * @param codProy
	 * @param rol
	 * @param predicate
	 * @param pageable
	 * @return List<FilterPersonasProyectoDTO>
	 * @author shenao
	 */
	@Query(value = "SELECT CAST(fnc_filtro_personasxrol('FSCHSE','Software enginner 1') as text)", nativeQuery = true)
	List<String> filterPersonasMapaConocimientoProyecto(@Param("codProy") String codProy, @Param("rol") String rol);

}
