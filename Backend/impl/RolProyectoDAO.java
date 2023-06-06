package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.itiformativos.dto.RolProyectoDTO;
import com.indra.itiformativos.mapper.RolProyectoDTOMapper;
import com.indra.itiformativos.model.QRolProyecto;
import com.indra.itiformativos.repository.RolProyectoRepository;
import com.indra.itiformativos.repository.custom.IRolProyectoDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class RolProyectoDAO implements IRolProyectoDAO {
	
	private final RolProyectoRepository repository;
	private final RolProyectoDTOMapper mapper;
	
	public RolProyectoDAO(RolProyectoRepository repository, RolProyectoDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<RolProyectoDTO> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolProyectoDTO> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<RolProyectoDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RolProyectoDTO> filterPaginated(int page, int size, RolProyectoDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	private BooleanExpression getWhere(RolProyectoDTO entity) {
		BooleanExpression where = QRolProyecto.rolProyecto.isNotNull();
		if (!Strings.isNullOrEmpty(entity.getCodigo())) {
			where = where.and(QRolProyecto.rolProyecto.codigo.containsIgnoreCase(entity.getCodigo()));
		}
		if (!Strings.isNullOrEmpty(entity.getDescripcion())) {
			where = where.and(QRolProyecto.rolProyecto.descripcion.containsIgnoreCase(entity.getDescripcion()));
		}
		return where;
	}

	@Override
	public long countFilter(RolProyectoDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RolProyectoDTO save(RolProyectoDTO entity) {
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}

	@Override
	public List<RolProyectoDTO> saveList(List<RolProyectoDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RolProyectoDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}

}
