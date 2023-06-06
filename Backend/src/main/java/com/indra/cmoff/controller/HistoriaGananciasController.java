package com.indra.cmoff.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indra.cmoff.dto.HistoriaGananciasDTO;
import com.indra.cmoff.dto.PaginatedFilter;
import com.indra.cmoff.service.IHistoriaGananciaService;
import com.indra.cmoff.utils.util.Constantes;
import com.indra.cmoff.utils.util.ConstantesRolesMP;

@RestController
@RequestMapping(path = "/historiaganancias")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
RequestMethod.OPTIONS })
public class HistoriaGananciasController {


	private HashMap<String, Object> jsonResponse = new HashMap<>();
	String messageResponse = "";
	HttpStatus statusResponse = HttpStatus.BAD_REQUEST;

	private final IHistoriaGananciaService historiaGananciaService;

	public HistoriaGananciasController(IHistoriaGananciaService historiaGananciasService) {
		this.historiaGananciaService =  historiaGananciasService;

	}

	@PreAuthorize("hasRole('"+ ConstantesRolesMP.ROLE_ADM +"') "
			+ "or hasAuthority('"+ ConstantesRolesMP.FCHPERS + ConstantesRolesMP.PRM_L +"')"
			+ "or hasAuthority('"+ ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L +"')")
	@GetMapping("/{codEmpleado}")
	public Optional<HistoriaGananciasDTO> one(@PathVariable Long codEmpleado) {
		return historiaGananciaService.findById(codEmpleado);
	}

	@PreAuthorize("hasRole('"+ ConstantesRolesMP.ROLE_ADM +"') "
			+ "or hasAuthority('"+ ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_C +"') "
			+ "or hasAuthority('"+ ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L +"')"
			)
	@GetMapping("/buscarporcodigo/{codigo}")
	public HistoriaGananciasDTO findByCodigo(@PathVariable Long codigo) {
		return historiaGananciaService.findByCodEmpleado(codigo);
	}

	@PreAuthorize("hasRole('"+ ConstantesRolesMP.ROLE_ADM +"') "
			+ "or hasAuthority('"+ ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L +"')")
	@PostMapping("/filternombres")
	public Page<HistoriaGananciasDTO> filtrarPorNombre(@RequestBody PaginatedFilter<HistoriaGananciasDTO> filtro) {
		return  historiaGananciaService.filtroPagPorNombre(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());
	}

	@PreAuthorize("hasRole('"+ ConstantesRolesMP.ROLE_ADM +"') "
			+ "or hasAuthority('"+ ConstantesRolesMP.FCHPERS + ConstantesRolesMP.PRM_L +"')")

	@PostMapping("/buscar")
	public Page<HistoriaGananciasDTO> buscar(@RequestBody PaginatedFilter<HistoriaGananciasDTO> filtro) {
		return  historiaGananciaService.filterPaginated(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());

	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_C + "')")
	@PostMapping("/crear")
	public Object createNew(@Valid @RequestBody  HistoriaGananciasDTO newHistoriaGanancias) {

		try {
			historiaGananciaService.save(newHistoriaGanancias);
			messageResponse = Constantes.PER_CREADO;
			statusResponse = HttpStatus.CREATED;

		} catch (Exception e) {
			System.out.println(e.toString());
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;
		}

		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);

	}

}
