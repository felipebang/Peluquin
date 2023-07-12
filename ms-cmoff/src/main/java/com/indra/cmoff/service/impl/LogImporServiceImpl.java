package com.indra.cmoff.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.indra.cmoff.dto.LogImporDTO;
import com.indra.cmoff.repository.custom.ILogImporDAO;
import com.indra.cmoff.service.ILogImporService;

@Service
public class LogImporServiceImpl implements ILogImporService{
	
	private final ILogImporDAO logImporDAO;

	public LogImporServiceImpl(ILogImporDAO logImporDAO) {
		this.logImporDAO = logImporDAO;
	}

	@Override
	public Optional<LogImporDTO> findById(long id) {
		// TODO Auto-generated method stub
		return logImporDAO.findById(id); 
	}

	@Override
	public List<LogImporDTO> findAll() {
		// TODO Auto-generated method stub
		return logImporDAO.findAll();
	}

	@Override
	public List<LogImporDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LogImporDTO> filterPaginated(int page, int size, LogImporDTO entity, String column, String orderType) {
		return logImporDAO.filterPaginated(page, size, entity, column, orderType);
	}
	

	@Override
	public long countFilter(LogImporDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LogImporDTO save(LogImporDTO entity) {
		return logImporDAO.save(entity);
	}

	@Override
	public List<LogImporDTO> saveList(List<LogImporDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(LogImporDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Integer callProcedureProyectos(String pathFileOne, int idProyectos,  int totalLineas) {
		return logImporDAO.callProcedureProyectos(pathFileOne, idProyectos, totalLineas);
	}

	@Override
	public Integer callProcedureCargueSeguimiento(String pathFileOne, int idArbol, int totalLineas) {
		// TODO Auto-generated method stub
	return	logImporDAO.callProcedureCargueSeguimiento(pathFileOne, idArbol, totalLineas);
	
	}

	@Override
	public Integer callCargarCandidatos(String pathFileOne, int idArbol, int totalLineas ) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarCandidatos(pathFileOne, idArbol, totalLineas);
		
	}

	@Override
	public Integer callProcedureCargarNecesidades(String pathFileOne, int idArbol, int totalLineas) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarCargarNecesidades(pathFileOne, idArbol, totalLineas);
	}

	@Override
	public Integer callProcedurePlantillaOffshore(String pathFileOne, int idPlantilla, int totalLineas) {
		// TODO Auto-generated method stub
		return logImporDAO.callProcedureCarguePlantillaoffhore(pathFileOne, idPlantilla, totalLineas);
	}
	
	@Override
	public Integer callProcedureDedicaciones(String pathFileOne, int idPlantilla) {
		// TODO Auto-generated method stub
		return logImporDAO.callProcedureCargueDedicaciones(pathFileOne, idPlantilla);
	}

	@Override
	public Integer callProcedureCargarDedicacionPlantillaPTP(String pathFileOne, int idArchivo, int totalLineas) {
		// TODO Auto-generated method stub
		return logImporDAO.callProcedureCargarDedicacionPlantillaPTP(pathFileOne, idArchivo, totalLineas);
	}
	
	@Override
	public Integer callProcedureCargarCambios(String pathFileOne, int idArchivo, int totalLineas) {
		// TODO Auto-generated method stub
		return logImporDAO.callProcedureCargarCambios(pathFileOne, idArchivo, totalLineas);
	}

	@Override
	public Integer callProcedureCargarMovimientos(String pathFileOne, int idArchivo, int totalLineas) {
		// TODO Auto-generated method stub
		return logImporDAO.callProcedureCargarMovimientos(pathFileOne, idArchivo, totalLineas);
	}

	@Override
	public Integer callCargarMaestraCandidatos(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraCandidatos(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraNecesidad(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraNecesidad(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraDedicacionPlantillaEYP(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraDedicacionPlantillaEYP(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraPlantillaOffshore(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraPlantillaOffshore(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraProyectos(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraProyectos(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraSeguimiento(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraSeguimiento(estadoEjecucion);
	}
	
	@Override
	public Integer callCargarMaestraCambios(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraCambios(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraMovimientos(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return logImporDAO.callCargarMaestraMovimientos(estadoEjecucion);
	}
	


	
	
}
