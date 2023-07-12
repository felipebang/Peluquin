package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.indra.itiformativos.dto.LogImporDTO;
import com.indra.itiformativos.mapper.LogImporDTOMapper;
import com.indra.itiformativos.model.QLogImpor;
import com.indra.itiformativos.repository.LogImporRepository;
import com.indra.itiformativos.repository.custom.ILogImporDAO;
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
	public Integer callProcedure(String pathFileOne, int idArbol, String pathFileTwo, int idGestion) {
		return repository.executeProcedureArbolConocGestionConoc(pathFileOne, idArbol, pathFileTwo, idGestion, 0);
	}

	@Override
	public Integer callProcedureProyectos(String pathFileOne, int idProyectos, String pathFileTwo, int idRoles) {
		return repository.executeProcedureProyectosRolesConoc(pathFileOne, idProyectos, pathFileTwo, idRoles, 0);
	}

	@Override
	public Integer callProcedureArbolConocimiento(String pathFileOne, int idArbol) {
		return repository.executeProcedureArbolConoc(pathFileOne, idArbol, 0);
		 
	}

	@Override
	public Integer callCargarArbolConocimientoIndividual(String pathFileOne, int idArbol) {
		// TODO Auto-generated method stub
		return repository.executeProcedureArbolConocIndividual(pathFileOne, idArbol, 0);
	}

	@Override
	public Integer callCargarConocimientosPersonaIndividual(String pathFileOne, int idPersona) {
		// TODO Auto-generated method stub
		return repository.executeProcedureConocimientosPersonIndividual(pathFileOne, idPersona, 0);
	}

}
