package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.itiformativos.dto.PermisoDTO;
import com.indra.itiformativos.mapper.PermisoDTOMapper;
import com.indra.itiformativos.model.QPermiso;
import com.indra.itiformativos.repository.PermisoRepository;
import com.indra.itiformativos.repository.custom.IPermisoDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class PermisoDAO implements IPermisoDAO {

	private final PermisoRepository repository;
	private final PermisoDTOMapper permisoMapper;

	public PermisoDAO(PermisoRepository repository, PermisoDTOMapper permisoMapper) {
		this.repository = repository;
		this.permisoMapper = permisoMapper;
	}

	@Override
	public Optional<PermisoDTO> findById(long id) {
		return permisoMapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<PermisoDTO> findAll() {
		return permisoMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<PermisoDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return permisoMapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<PermisoDTO> filterPaginated(int page, int size, PermisoDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return permisoMapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity),
				pagina));
	}

	@Override
	public long countFilter(PermisoDTO entity) {
		return repository.count(getWhere(entity));
	}

	@Override
	public PermisoDTO save(PermisoDTO entity) {
		return permisoMapper.entityToDto(repository.saveAndFlush(permisoMapper.dtoToEntity(entity)));
	}

	@Override
	public List<PermisoDTO> saveList(List<PermisoDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PermisoDTO entity) {
		repository.delete(permisoMapper.dtoToEntity(entity));
		
	}

	@Override
	public void deleteById(long entityId) {
		repository.deleteById(entityId);
	}
	
	public BooleanExpression getWhere(PermisoDTO entity) {
		BooleanExpression where = QPermiso.permiso.isNotNull();
		if (!Strings.isNullOrEmpty(entity.getCodigo())) {
			where = where.and(QPermiso.permiso.codigo.containsIgnoreCase(entity.getCodigo()));
		}
		if (!Strings.isNullOrEmpty(entity.getDescripcion())) {
			where = where.and(QPermiso.permiso.descripcion.containsIgnoreCase(entity.getDescripcion()));
		}
		return where;
	}

}
