package com.indra.cmoff.repository.custom;

import com.indra.cmoff.dto.LogImporDTO;

public interface ILogImporDAO extends ICrudDAO<LogImporDTO> {
	/**
	 * Metodo que permite obtener el log filtrado por el nombre de archivo
	 *
	 * @autor afgarciag
	 * @param archivo,nombre de archivo por el que se quiere filtrar
	 * @return LogImporDTO filtrado por nombre de archivo
	 */
	LogImporDTO findByArchivo(String archivo);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de proyectos y roles
	 * de la base de datos
	 * 
	 * @param idProyectos
	 * @param idRoles
	 * @param pathFileOne
	 * @param pathFileTwo
	 * 
	 * @return Integer
	 */
	Integer callProcedureProyectos(String pathFileOne, int idArchivo, int totalLineas);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * apcd_cargar_candidatos_tmpl
	 *
	 * @param idArbol
	 * @param pathFileOne pcd_cargar_candidatos_tmp( sbarchivoarrbol character
	 *                    varying, idfilearcharbo integer, INOUT onuestado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarCandidatos(String pathFileOne, int idArchivo, int totalLineas);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_necesidades_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_necesidades_tmp( sbarchivoconoc character
	 *                    varying, idfilearchivo integer, INOUT onuestado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarCargarNecesidades(String pathFileOne, int idArchivo, int totalLineas);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_seguimiento_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_seguimiento_tmp( sbarchivoconoc character
	 *                    varying, idfilearchivo integer, INOUT onuestado integer)
	 * 
	 * @return Integer
	 */
	Integer callProcedureCargueSeguimiento(String pathFileOne, int idPersona, int totalLineas);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_seguimiento_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_seguimiento_tmp( sbarchivoconoc character
	 *                    varying, idfilearchivo integer, INOUT onuestado integer)
	 * 
	 * @return Integer
	 */
	Integer callProcedureCarguePlantillaoffhore(String pathFileOne, int idArchivo,  int totalLineas);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_seguimiento_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_h_dedicaciones_offshore_tmp( sbarchivoconoc
	 *                    character varying, idfilearchivo integer, INOUT onuestado
	 *                    integer)
	 * 
	 * @return Integer
	 */
	Integer callProcedureCargueDedicaciones(String pathFileOne, int idArchivo);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_dedicaciones_plantilla_ptp_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_dedicaciones_plantilla_ptp_tmp( sbarchivoconoc
	 *                    character varying, idfilearchivo integer, INOUT onuestado
	 *                    integer)
	 * 
	 * @return Integer
	 */
	Integer callProcedureCargarDedicacionPlantillaPTP(String pathFileOne, int idArchivo,  int totalLineas);

	
	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_h_cambios_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_h_cambios_tmp( sbarchivoconoc
	 *                    character varying, idfilearchivo integer, INOUT onuestado
	 *                    integer)
	 * 
	 * @return Integer
	 */
	Integer callProcedureCargarCambios(String pathFileOne, int idArchivo,  int totalLineas);
	
	
	
	
	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * pcd_cargar_h_movimientos_tmp
	 *
	 * @param idConoPerso
	 * @param pathFileOne pcd_cargar_h_movimientos_tmp( sbarchivoconoc
	 *                    character varying, idfilearchivo integer, INOUT onuestado
	 *                    integer)
	 * 
	 * @return Integer
	 */
	Integer callProcedureCargarMovimientos(String pathFileOne, int idArchivo,  int totalLineas);
	
	
	/**
	 * Metodo que permite llamar a un procedimiento de cargue de c_candidatos( INOUT
	 * estado integer)
	 *
	 * @param int c_candidatos(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraCandidatos(int estadoEjecucion);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de c_necesidad(INOUT
	 * estado integer)
	 *
	 * @param int c_necesidad(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraNecesidad(int estadoEjecucion);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * c_dedicacion_plantilla_eyp( INOUT estado integer)
	 *
	 * @param int c_dedicacion_plantilla_eyp(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraDedicacionPlantillaEYP(int estadoEjecucion);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de
	 * c_plantilha_offshore( INOUT estado integer)
	 *
	 * @param int c_plantilha_offshore(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraPlantillaOffshore(int estadoEjecucion);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de c_proyectos( INOUT
	 * estado integer)
	 *
	 * @param int c_proyectos(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraProyectos(int estadoEjecucion);

	/**
	 * Metodo que permite llamar a un procedimiento de cargue de c_seguimiento(
	 * INOUT estado integer)
	 *
	 * @param int c_seguimiento(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraSeguimiento(int estadoEjecucion);
	
	
	/**
	 * Metodo que permite llamar a un procedimiento de cargue de c_cambios(
	 * INOUT estado integer)
	 *
	 * @param int c_cambios(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraCambios(int estadoEjecucion);
	
	
	/**
	 * Metodo que permite llamar a un procedimiento de cargue de c_movimientos(
	 * INOUT estado integer)
	 *
	 * @param int c_movimientos(INOUT estado integer)
	 * 
	 * @return Integer
	 */
	Integer callCargarMaestraMovimientos(int estadoEjecucion);

}
