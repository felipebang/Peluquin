package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.itiformativos.dto.RolDTO;
import com.indra.itiformativos.mapper.RolDTOMapper;
import com.indra.itiformativos.model.QRol;
import com.indra.itiformativos.repository.RolRepository;
import com.indra.itiformativos.repository.custom.IRolDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class RolDAO implements IRolDAO {

	private final RolRepository repository;
	private final RolDTOMapper rolDTOMapper;

	public RolDAO(RolRepository repository, RolDTOMapper rolDTOMapper) {
		this.repository = repository;
		this.rolDTOMapper = rolDTOMapper;
	}

	@Override
	public Optional<RolDTO> findById(long id) {
		return rolDTOMapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<RolDTO> findAll() {
		return rolDTOMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<RolDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return rolDTOMapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<RolDTO> filterPaginated(int page, int size, RolDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return rolDTOMapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity, true),
			 pagina));
	}

	@Override
	public long countFilter(RolDTO entity) {
		return repository.count(getWhere(entity, false));
	}

	@Override
	public RolDTO save(RolDTO entity) {
		return rolDTOMapper.entityToDto(repository.saveAndFlush(rolDTOMapper.dtoToEntity(entity)));
	}

	@Override
	public List<RolDTO> saveList(List<RolDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(RolDTO entity) {

	}

	@Override
	public void deleteById(long entityId) {
		repository.deleteById(entityId);
	}

	public BooleanExpression getWhere(RolDTO filtro, Boolean isFilterPaginated) {
		BooleanExpression where = QRol.rol.isNotNull();
		if (isFilterPaginated) {
			if (StringUtils.isNoneBlank(filtro.getDescripcion())) {
				where = where.and(QRol.rol.descripcion.containsIgnoreCase(filtro.getDescripcion()));
			}
			if (!Strings.isNullOrEmpty(filtro.getCodigo())) {
				where = where.and(QRol.rol.codigo.containsIgnoreCase(filtro.getCodigo()));
			}
		}
		return where;
	}

	@Override
	public List<RolDTO> findRolDescripcionByIdUsuarioRol(Long idUsuario) {
		return null;
		//return repository.findRolDescripcionByIdUsuarioRol(idUsuario);
	}

	@Override
	public List<RolDTO> findByEstado(Boolean estado) {
		return rolDTOMapper.entitiesToDtos(repository.findByEstado(estado));
	}

	@Override
	public RolDTO findByCodigo(String codigo) {
		return rolDTOMapper.entityToDto(repository.findByCodigo(codigo));
	}
	
}
