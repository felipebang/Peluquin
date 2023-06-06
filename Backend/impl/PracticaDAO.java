package com.indra.itiformativos.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import com.indra.itiformativos.dto.PracticaDTO;
import com.indra.itiformativos.mapper.PracticaDTOMapper;
import com.indra.itiformativos.repository.PracticaRepository;
import com.indra.itiformativos.repository.custom.IPracticaDAO;

@Repository
public class PracticaDAO implements IPracticaDAO {
	
	private final PracticaRepository repository;
	private final PracticaDTOMapper mapper;
	
	public PracticaDAO(PracticaRepository repository, PracticaDTOMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	public Optional<PracticaDTO> findById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PracticaDTO> findAll() {
		return mapper.entitiesToDtos(repository.findAll());
	}

	@Override
	public Page<PracticaDTO> findAllPaginated(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PracticaDTO> filterPaginated(int page, int size, PracticaDTO entity, String column, String orderType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countFilter(PracticaDTO entity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PracticaDTO save(PracticaDTO entity) {
		return mapper.entityToDto(repository.saveAndFlush(mapper.dtoToEntity(entity)));
	}

	@Override
	public List<PracticaDTO> saveList(List<PracticaDTO> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(PracticaDTO entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(long entityId) {
		repository.deleteById(entityId);
	}

}
