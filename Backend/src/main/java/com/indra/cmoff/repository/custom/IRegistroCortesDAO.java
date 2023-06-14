package com.indra.cmoff.repository.custom;

import java.util.Optional;

import com.indra.cmoff.dto.RegistroCortesDTO;
import com.indra.cmoff.model.RegistroCortes;

public interface IRegistroCortesDAO extends ICrudDAO<RegistroCortesDTO> {

	RegistroCortesDTO editar(RegistroCortesDTO p);

	RegistroCortesDTO findByCodEmpleado(Long codigo);


	

	

}
