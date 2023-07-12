package com.indra.cmoff.service;

import com.indra.cmoff.dto.LogImporDTO;

public interface ILogImporService extends ICrudService<LogImporDTO> {

	//llamado procedure cargar tablas tmp

	Integer callProcedureProyectos(String pathFileOne, int idProyectos,  int totalLineas);
	
	Integer callProcedurePlantillaOffshore(String pathFileOne, int idPlantilla,  int totalLineas);
	
	Integer callProcedureCargueSeguimiento(String pathFileOne, int idArchivo, int totalLineas);
	
	Integer callProcedureDedicaciones(String pathFileOne, int idArchivo);
	
	Integer callCargarCandidatos(String pathFileOne, int idArchivo, int totalLineas);
	
	Integer callProcedureCargarNecesidades(String pathFileOne, int idArchivo,  int totalLineas);
	
	Integer callProcedureCargarDedicacionPlantillaPTP(String pathFileOne, int idArchivo, int totalLineas);
	
	Integer callProcedureCargarCambios(String pathFileOne, int idArchivo, int totalLineas);
	
	Integer callProcedureCargarMovimientos(String pathFileOne, int idArchivo, int totalLineas);
	
	//llamado procedure cargar tablas maestras
	Integer callCargarMaestraCandidatos ( int estadoEjecucion);
	
	Integer callCargarMaestraNecesidad ( int estadoEjecucion);
	
	Integer callCargarMaestraDedicacionPlantillaEYP ( int estadoEjecucion);
	
	Integer callCargarMaestraPlantillaOffshore ( int estadoEjecucion);
	
	Integer callCargarMaestraProyectos ( int estadoEjecucion);
	
	Integer callCargarMaestraSeguimiento ( int estadoEjecucion);
	
	Integer callCargarMaestraCambios ( int estadoEjecucion);
	
	Integer callCargarMaestraMovimientos ( int estadoEjecucion);



}
