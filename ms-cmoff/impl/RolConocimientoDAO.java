package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.indra.itiformativos.dto.RolConocimientoDTO;
import com.indra.itiformativos.mapper.RolConocimientoDTOMapper;
import com.indra.itiformativos.model.QRolConocimiento;
import com.indra.itiformativos.repository.RolConocimientoRepository;
import com.indra.itiformativos.repository.custom.IRolConocimientoDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class RolConocimientoDAO implements IRolConocimientoDAO {
	
	private final RolConocimientoRepository repository;
	private final RolConocimientoDTOMapper mapper;
	
	public RolConocimientoDAO(RolConocimientoRepository repository, RolConocimientoDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<RolConocimientoDTO> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolConocimientoDTO> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<RolConocimientoDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<RolConocimientoDTO> filterPaginated(int page, int size, RolConocimientoDTO entity, String column,
			String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}
	
	private BooleanExpression getWhere(RolConocimientoDTO entity) {
		BooleanExpression where = QRolConocimiento.rolConocimiento.isNotNull();
		if (Objects.nonNull(entity.getProyecto()) && !Strings.isNullOrEmpty(entity.getProyecto().getCodigo())) {
			where = where.and(QRolConocimiento.rolConocimiento.proyecto.codigo.containsIgnoreCase(entity.getProyecto().getCodigo()));
		}
		if (StringUtils.isNotEmpty(entity.getRolProyecto())) {
			where = where.and(QRolConocimiento.rolConocimiento.rolProyecto.containsIgnoreCase(entity.getRolProyecto()));
		}
		return where;
	}

	@Override
	public long countFilter(RolConocimientoDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RolConocimientoDTO save(RolConocimientoDTO entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RolConocimientoDTO> saveList(List<RolConocimientoDTO> entities) {
		return mapper.entitiesToDtos(repository.saveAll(mapper.dtosToentities(entities)));
	}

	@Override
	public void delete(RolConocimientoDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<String> findDistinctRolesByProyectoCodigo (String codigo) {
		return repository.findDistinctRolesByProyectoCodigo(codigo);
	}
	
	@Override
	public List<RolConocimientoDTO> findRolConocimientoByProyectoCodigoAndRolProyecto (String codigo, String rol) {
		return mapper.entitiesToDtos(repository.findByProyectoCodigoAndRolProyecto(codigo, rol));
	}

	@Override
	public List<RolConocimientoDTO> findByProyectoCodigo (String codigo) {
		return mapper.entitiesToDtos(repository.findByProyectoCodigo(codigo));
	}

	@Override
	@Transactional
	public void deleteByProyectoCodigo(String codigo) {
		repository.deleteByProyectoCodigo(codigo);
	}

}
