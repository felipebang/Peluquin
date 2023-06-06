package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.assertj.core.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.indra.itiformativos.dto.ParametroDTO;
import com.indra.itiformativos.mapper.ParametroDTOMapper;
import com.indra.itiformativos.model.QParametro;
import com.indra.itiformativos.repository.ParametroRepository;
import com.indra.itiformativos.repository.custom.IParametroDAO;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
public class ParametroDAO implements IParametroDAO {
	
	private final ParametroRepository repository;
	private final ParametroDTOMapper mapper;
	
	public ParametroDAO(ParametroRepository repository, ParametroDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<ParametroDTO> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ParametroDTO> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<ParametroDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ParametroDTO> filterPaginated(int page, int size, ParametroDTO entity, String column, String orderType) {
		PageRequest pagina = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(orderType), column));
		return mapper.entityPageToDtoPage(pagina, repository.findAll(getWhere(entity), pagina));
	}

	private BooleanExpression getWhere(ParametroDTO entity) {
		BooleanExpression where = QParametro.parametro.isNotNull();
		if (Objects.nonNull(entity.getGrupoParametro())) {
			if (!Strings.isNullOrEmpty(entity.getGrupoParametro().getCodigo())) {
				where = where
						.and(QParametro.parametro.grupoParametro.codigo.containsIgnoreCase(entity.getGrupoParametro().getCodigo()));
			}
		}
		if (!Strings.isNullOrEmpty(entity.getValorOrigen())) {
			where = where.and(QParametro.parametro.valorOrigen.containsIgnoreCase(entity.getValorOrigen()));
		}
		if (!Strings.isNullOrEmpty(entity.getValorDestino())) {
			where = where.and(QParametro.parametro.valorDestino.containsIgnoreCase(entity.getValorDestino()));
		}
		return where;
	}

	@Override
	public long countFilter(ParametroDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ParametroDTO save(ParametroDTO entity) {
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}

	@Override
	public List<ParametroDTO> saveList(List<ParametroDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ParametroDTO entity) {
		repository.delete(mapper.dtoToEntity(entity));
	}

	@Override
	public void deleteById(long entityId) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<ParametroDTO> findByGrupoParametroCodigoOrderAsc (String codigo) {
		return mapper.entitiesToDtos(repository.findByGrupoParametroCodigoOrderByValorDestino(codigo));
	}

}
