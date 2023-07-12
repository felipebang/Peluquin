package com.indra.cmoff.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import com.indra.cmoff.model.LogImpor;

public interface LogImporRepository extends JpaRepository<LogImpor, Long>, QuerydslPredicateExecutor<LogImpor> {


	/**
	 * Metodo que realiza el llamado al procedimiento de carga de proyectos almacenado en DB con nombre
	 * pcd_cargar_proyecto_tmp
	 * 
	 * @param pathFileOne
	 * @param pathFileTwo
	 * @param idProyectos
	 * @param idRoles
	 * @return Integer
	 */
	@Query(value = "CALL pcd_cargar_proyecto_tmp(:pathFileOne, :idProyectos, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureProyectos(@Param("pathFileOne") String pathFileOne,
			@Param("idProyectos") int idProyectos,
			@Param("inout") int inout,  @Param("totalLineas") int totalLineas);
	
	/**
	 * Metodo que realiza el llamado al procedimiento de carga de pantilla almacenado en DB con nombre
	 * pcd_cargar_plantilla_offshore_tmp
	 * 
	 * @param pathFileOne
	 * @param pathFileTwo
	 * @param idProyectos
	 * @param idRoles
	 * @return Integer
	 */
	@Query(value = "CALL pcd_cargar_plantilla_offshore_tmp(:pathFileOne, :idProyectos, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedurePlantillaOffshore(@Param("pathFileOne") String pathFileOne,
			@Param("idProyectos") int idProyectos,
			@Param("inout") int inout,  @Param("totalLineas") int totalLineas);
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_seguimiento_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 */
	@Query(value = "CALL pcd_cargar_seguimiento_tmp(:pathFileOne, :idArbol, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureSeguimiento(@Param("pathFileOne") String pathFileOne,
			@Param("idArbol") int idArbol, @Param("inout") int inout, @Param("totalLineas") int totalLineas);
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_candidatos_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 * pcd_cargar_candidatos_tmp( sbarchivo character varying, idfilearcharbo integer, INOUT onuestado integer)
	 */
	@Query(value = "CALL pcd_cargar_candidatos_tmp(:pathFileOne, :idArbol, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureCargarCandidatos(@Param("pathFileOne") String pathFileOne,
			@Param("idArbol") int idArbol, @Param("inout") int inout, @Param("totalLineas") int totalLineas);
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_necesidades_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 *pcd_cargar_necesidades_tmp( sbarchivoconoc character varying, idfilearchivo integer, INOUT onuestado integer)
	 */
	@Query(value = "CALL pcd_cargar_necesidades_tmp(:pathFileOne, :idArbol, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureCargarNecesidades(@Param("pathFileOne") String pathFileOne,
			@Param("idArbol") int idArbol, @Param("inout") int inout, @Param("totalLineas") int totalLineas);
	

	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_necesidades_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 *pcd_cargar_h_dedicaciones_offshore_tmp( sbarchivoconoc character varying, idfilearchivo integer, INOUT onuestado integer)
	 */
	@Query(value = "CALL pcd_cargar_h_dedicaciones_offshore_tmp(:pathFileOne, :idArbol, :inout)", nativeQuery = true)
	Integer executeProcedureCargarDedicaciones(@Param("pathFileOne") String pathFileOne,
			@Param("idArbol") int idArbol, @Param("inout") int inout);
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_necesidades_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 *pcd_cargar_h_dedicaciones_plantilla_ptp_tmp( sbarchivoconoc character varying, idfilearchivo integer, INOUT onuestado integer)
	 */
	@Query(value = "CALL pcd_cargar_dedicaciones_plantilla_ptp_tmp(:pathFileOne, :idArbol, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureCargarDedicacionesPlantillaPtp(@Param("pathFileOne") String pathFileOne,
			@Param("idArbol") int idArbol, @Param("inout") int inout,  @Param("totalLineas") int totalLineas);
	
	
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_h_cambios_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 *pcd_cargar_h_cambios_tmp( sbarchivoconoc character varying, idfilearchivo integer, INOUT onuestado integer)
	 */
	@Query(value = "CALL pcd_cargar_h_cambios_tmp(:pathFileOne, :idArbol, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureCargarCambios(@Param("pathFileOne") String pathFileOne,
			@Param("idArbol") int idArbol, @Param("inout") int inout,  @Param("totalLineas") int totalLineas);
	
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * pcd_cargar_necesidades_tmp
	 * 
	 * @param pathFileOne
	 * @param idArbol
	 * @return Integer
	 *pcd_cargar_h_movimientos_tmp( sbarchivo character varying, sbarchivo integer, INOUT onuestado integer)
	 */
	@Query(value = "CALL pcd_cargar_h_movimientos_tmp(:pathFileOne, :idArchivo, :inout, :totalLineas)", nativeQuery = true)
	Integer executeProcedureCargarMovimientos(@Param("pathFileOne") String pathFileOne,
			@Param("idArchivo") int idArbol, @Param("inout") int inout,  @Param("totalLineas") int totalLineas);
	
	
	
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_candidatos
	 * 
      * @param int
	 * @return Integer
	 *c_candidatos(INOUT estado integer)
	 */
	@Query(value = "CALL c_candidatos(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroCandidatos( @Param("inout") int inout);
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_dedicacion_plantilla_eyp
	 * 
	 * @param int
	 * @return Integer
	 *c_dedicacion_plantilla_eyp(INOUT estado integer)
	 */
	@Query(value = "CALL c_dedicacion_plantilla_eyp(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroDedicacionPlantilla_eyp( @Param("inout") int inout);
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_necesidad
	 * 
	 * @param int
	 * @return Integer
	 *c_necesidad(INOUT estado integer)
	 */
	@Query(value = "CALL c_necesidad(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroNecesidad( @Param("inout") int inout);
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_plantilha_offshore
	 * 
	 * @param int
	 * @return Integer
	 *c_plantilha_offshore(INOUT estado integer)
	 */
	@Query(value = "CALL c_plantilha_offshore(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroPlantilhaOffshore( @Param("inout") int inout);
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_proyectos
	 * 
	 * @param int
	 * @return Integer
	 *c_proyectos(INOUT estado integer)
	 */
	@Query(value = "CALL c_proyectos(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroProyectos( @Param("inout") int inout);


	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_seguimiento
	 * 
	 * @param int
	 * @return Integer
	 *c_seguimiento(INOUT estado integer)
	 */
	@Query(value = "CALL c_seguimiento(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroSeguimiento( @Param("inout") int inout);
	
	
	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_cambios
	 * 
	 * @param int
	 * @return Integer
	 *c_cambios(INOUT estado integer)
	 */
	@Query(value = "CALL c_cambios(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroCambios( @Param("inout") int inout);
	

	/**
	 * Metodo que realiza el llamado al procedimiento almacenado en DB con nombre
	 * c_movimientos
	 * 
	 * @param int
	 * @return Integer
	 *c_movimientos(INOUT estado integer)
	 */
	@Query(value = "CALL c_movimientos(:inout)", nativeQuery = true)
	Integer executeProcedureCargarMaestroMovimientos( @Param("inout") int inout);
	
}
