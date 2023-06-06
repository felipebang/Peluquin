package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.indra.cmoff.dto.LogImporDTO;
import com.indra.cmoff.mapper.LogImporDTOMapper;
import com.indra.cmoff.model.QLogImpor;
import com.indra.cmoff.repository.LogImporRepository;
import com.indra.cmoff.repository.custom.ILogImporDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class LogImporDAO implements ILogImporDAO{
	
	private final LogImporRepository repository;
	private final LogImporDTOMapper logImporDTOMapper;

	public LogImporDAO(LogImporRepository repository, LogImporDTOMapper logImporDTOMapper) {
		this.repository = repository;
		this.logImporDTOMapper = logImporDTOMapper;
	}
	
	@Override
	public Optional<LogImporDTO> findById(long id) {
		return logImporDTOMapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<LogImporDTO> findAll() {
		return logImporDTOMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<LogImporDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<LogImporDTO> filterPaginated(int page, int size, LogImporDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return logImporDTOMapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	private BooleanExpression getWhere(LogImporDTO entity) {
		BooleanExpression where = QLogImpor.logImpor.isNotNull();
		if (entity.getNombreArchivo() != null) {
			where = where.and(QLogImpor.logImpor.nombreArchivo.containsIgnoreCase(entity.getNombreArchivo()));
		}
		if (entity.getEstado() != null) {
			where = where.and(QLogImpor.logImpor.estado.containsIgnoreCase(entity.getEstado()));
		}
		if (entity.getDescripcion() != null) {
			where = where.and(QLogImpor.logImpor.descripcion.containsIgnoreCase(entity.getDescripcion()));
		}
		return where;
	}
	

	@Override
	public long countFilter(LogImporDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LogImporDTO save(LogImporDTO entity) {
		return logImporDTOMapper.entityToDto(repository.saveAndFlush(logImporDTOMapper.dtoToEntity(entity)));
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
	public LogImporDTO findByArchivo(String archivo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Integer callProcedureProyectos(String pathFileOne, int idProyectos, int totalLineas) {
		return repository.executeProcedureProyectos(pathFileOne, idProyectos,  0, totalLineas);
	}

	
	@Override
	public Integer callCargarCandidatos(String pathFileOne, int idArbol, int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarCandidatos(pathFileOne, idArbol, 0, totalLineas);
	}

	@Override
	public Integer callCargarCargarNecesidades(String pathFileOne, int idPersona, int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarNecesidades(pathFileOne, idPersona, 0, totalLineas);
	}

	@Override
	public Integer callProcedureCargueSeguimiento(String pathFileOne, int idArbol, int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedureSeguimiento(pathFileOne, idArbol, 0, totalLineas);
	}

	@Override
	public Integer callProcedureCarguePlantillaoffhore(String pathFileOne, int idArbol,  int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedurePlantillaOffshore(pathFileOne, idArbol, 0, totalLineas);
	}
	
	@Override
	public Integer callProcedureCargueDedicaciones(String pathFileOne, int idArbol) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarDedicaciones(pathFileOne, idArbol, 0);
	}

	@Override
	public Integer callProcedureCargarDedicacionPlantillaPTP(String pathFileOne, int idArchivo, int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarDedicacionesPlantillaPtp(pathFileOne, idArchivo, 0, totalLineas);
	}
	
	
	@Override
	public Integer callProcedureCargarCambios(String pathFileOne, int idArchivo, int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarCambios(pathFileOne, idArchivo, 0, totalLineas);
	}
	
	@Override
	public Integer callProcedureCargarMovimientos(String pathFileOne, int idArchivo, int totalLineas) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMovimientos(pathFileOne, idArchivo, 0, totalLineas);
	}
	
	@Override
	public Integer callCargarMaestraCandidatos(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroCandidatos(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraNecesidad(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroNecesidad(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraDedicacionPlantillaEYP(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroDedicacionPlantilla_eyp(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraPlantillaOffshore(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroPlantilhaOffshore(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraProyectos(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroProyectos(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraSeguimiento(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroSeguimiento(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraCambios(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return  repository.executeProcedureCargarMaestroCambios(estadoEjecucion);
	}

	@Override
	public Integer callCargarMaestraMovimientos(int estadoEjecucion) {
		// TODO Auto-generated method stub
		return repository.executeProcedureCargarMaestroMovimientos(estadoEjecucion);
	}


	
}
