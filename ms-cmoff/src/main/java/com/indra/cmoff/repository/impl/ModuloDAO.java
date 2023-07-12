package com.indra.cmoff.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.google.common.base.Strings;
import com.indra.cmoff.dto.ModuloDTO;
import com.indra.cmoff.mapper.ModuloDTOMapper;
import com.indra.cmoff.model.QModulo;
import com.indra.cmoff.repository.ModuloRepository;
import com.indra.cmoff.repository.custom.IModuloDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class ModuloDAO implements IModuloDAO {

	private final ModuloRepository repository;
	private final ModuloDTOMapper moduloDTOMapper;

	public ModuloDAO(ModuloRepository repository, ModuloDTOMapper moduloDTOMapper) {
		this.repository = repository;
		this.moduloDTOMapper = moduloDTOMapper;
	}

	@Override
	public Optional<ModuloDTO> findById(long id) {
		return moduloDTOMapper.optionalEntityToDto(repository.findById(id));
	}

	@Override
	public List<ModuloDTO> findAll() {
		return moduloDTOMapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<ModuloDTO> findAllPaginated(int page, int size) {
		PageRequest pagina = PageRequest.of(page, size);
		return moduloDTOMapper.entityPageToDtoPage(pagina, repository.findAll(pagina));
	}

	@Override
	public Page<ModuloDTO> filterPaginated(int page, int size, ModuloDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return moduloDTOMapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity),
				pagina));
	}

	@Override
	public long countFilter(ModuloDTO modulo) {
		return repository.count(getWhere(modulo));
	}

	@Override
	public ModuloDTO save(ModuloDTO modulo) {
		return moduloDTOMapper.entityToDto(repository.saveAndFlush(moduloDTOMapper.dtoToEntity(modulo)));
	}

	@Override
	public void delete(ModuloDTO modulo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(long moduloId) {
		repository.deleteById(moduloId);
	}

	public BooleanExpression getWhere(ModuloDTO filtro) {
		BooleanExpression where = QModulo.modulo.isNotNull();
			if (!Strings.isNullOrEmpty(filtro.getNombre())) {
				where = where.and(QModulo.modulo.nombre.containsIgnoreCase(filtro.getNombre()));
			}
			if (!Strings.isNullOrEmpty(filtro.getCodigo())) {
				where = where.and(QModulo.modulo.codigo.containsIgnoreCase(filtro.getCodigo()));
			}
		return where;
	}

	@Override
	public List<ModuloDTO> saveList(List<ModuloDTO> entities) {
		return null;
	}

	@Override
	public List<ModuloDTO> findAllByEstadoOrderByModuloEnlazadoDesc(Boolean estado) {
		return moduloDTOMapper.entitiesToDtos(repository.findAllByEstadoOrderByModuloEnlazadoDescNombreAsc(estado));
	}

	@Override
	public ModuloDTO findByCodigo(String codigo) {
		return moduloDTOMapper.entityToDto(repository.findByCodigo(codigo));
	}

	@Override
	public List<Long> findAllParentsByEstadoTrue() {
		return repository.findAllParentsByEstado(Boolean.TRUE);
	}

}
