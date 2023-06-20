package com.indra.cmoff.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.indra.cmoff.dto.GananciasDTO;
import com.indra.cmoff.dto.PaginatedFilter;
import com.indra.cmoff.dto.PorcentajeDTO;
import com.indra.cmoff.dto.RegistroCortesDTO;
import com.indra.cmoff.dto.RegistroPagoDTO;
import com.indra.cmoff.service.IGananciaService;
import com.indra.cmoff.service.IPorcentajeService;
import com.indra.cmoff.service.IRegistroCorteService;
import com.indra.cmoff.service.IRegistroPagoService;
import com.indra.cmoff.utils.util.Constantes;
import com.indra.cmoff.utils.util.ConstantesRolesMP;

/**
 * * Controlador encargado de permitir gestionar creacion persona
 * 
 * @author <br>
 *         <br>
 *         Email: <br>
 * 
 * @version 1.0
 * 
 */
@RestController
@RequestMapping(path = "/registrocortes")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.OPTIONS })
public class RegistroCortesController {

	private HashMap<String, Object> jsonResponse = new HashMap<>();
	String messageResponse = "";
	HttpStatus statusResponse = HttpStatus.BAD_REQUEST;

	private final IRegistroCorteService registroCorteService;

	private final IPorcentajeService porcentajeService;

	private final IGananciaService gananciaService;

	private final IRegistroPagoService registroPagoService;

	public RegistroCortesController(IRegistroCorteService registroCortesService, IGananciaService gananciaService,
			IPorcentajeService porcentajeService, IRegistroCorteService registroCorteService,
			IRegistroPagoService registroPagoService) {
		this.registroCorteService = registroCorteService;
		this.porcentajeService = porcentajeService;
		this.gananciaService = gananciaService;
		this.registroPagoService = registroPagoService;

	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.FCHPERS
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L
			+ "')" +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"     )*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@GetMapping("/{codEmpleado}")
	public Optional<RegistroCortesDTO> one(@PathVariable Long codEmpleado) {
		return registroCorteService.findById(codEmpleado);
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L
			+ "')"  +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"     )*/
	
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@GetMapping("/buscarporcodigo/{codigo}")
	public RegistroCortesDTO findByCodigo(@PathVariable Long codigo) {
		return registroCorteService.findByCodEmpleado(codigo);
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU
			+ ConstantesRolesMP.PRM_L + "')" +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"   )*/
	
	
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PostMapping("/filternombres")
	public Page<RegistroCortesDTO> filtrarPorNombre(@RequestBody PaginatedFilter<RegistroCortesDTO> filtro) {
		return registroCorteService.filtroPagPorNombre(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.FCHPERS
			+ ConstantesRolesMP.PRM_L + "')" +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"   )*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PostMapping("/buscar")
	public Page<RegistroCortesDTO> buscar(@RequestBody PaginatedFilter<RegistroCortesDTO> filtro) {
		return registroCorteService.filterPaginated(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());

	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_C + "')" +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"   )*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PostMapping("/crear")
	public Object createNew(@Valid @RequestBody RegistroCortesDTO newRegistroCortes) {

		try {

			// newRegistroCortes.setNuCodEmpleado((long)
			// newRegistroCortes.getNuCodEmpleado());

			// aplicamos porcentaje al colaborador
			List<PorcentajeDTO> lista = new ArrayList<>();
			lista = listarPorcentaje();
			// operacion de el aplicativo
			
			int getValorCortes = Integer.parseInt(newRegistroCortes.getValorCorte());
			int getPorcentajeEmpl = Integer.parseInt(lista.get(0).getPorcentajeEmpl());
		//operacion
			
			int valorPagoColaborador = getValorCortes * getPorcentajeEmpl / 100;
			int valorGananciaAdministrador = getValorCortes - valorPagoColaborador;
            
			PorcentajeDTO newPorcentaje = new PorcentajeDTO();
			Date fecha = new Date();
			newRegistroCortes.setIdPorcentaje((long) 6);
			newRegistroCortes.setCreatedAt(fecha);
			newRegistroCortes.setValorCorte("" + newRegistroCortes.getValorCorte());
			Long idRegistroCortes;
			idRegistroCortes = registroCorteService.save(newRegistroCortes).getId();

			

			// creao una instancia registro ganancia
			GananciasDTO newGanciancia = new GananciasDTO();
			newGanciancia.setId(idRegistroCortes);
			newGanciancia.setCreatedAt(fecha);
			newGanciancia.setGananciaValor("" + valorGananciaAdministrador);
			gananciaService.save(newGanciancia);


			// creao una instancia registro ganancia
			RegistroPagoDTO newRegistroPago = new RegistroPagoDTO();

			newRegistroPago.setCreatedAt(fecha);
			newRegistroPago.setId(idRegistroCortes);
			newRegistroPago.setValorPago(""+valorPagoColaborador);
			// crear una instancia registropago
			registroPagoService.save(newRegistroPago);
			
			
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
	
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	private List<GananciasDTO> listarGanancias() {
		// TODO Auto-generated method stub
		return gananciaService.findAll();
	}
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	private List<RegistroPagoDTO> listarRegistroPago() {
		// TODO Auto-generated method stub
		return registroPagoService.findAll();
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_C
			+ "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_A + "')")*/
	
	
	
	 //actualizar update partica set subprati='nbnb' wh
	
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PutMapping(path = { "/actualizar/{id}" })
	public Object replaceReg(@Valid @RequestBody RegistroCortesDTO registrDto, @PathVariable Long id) {
		registrDto.setId((long) id);
		return registroCorteService.editar(registrDto);
	}
	
	// eliminar

	/*
	 * public RegistroCortes delete(@PathVariable("id") int id) { return
	 * registroCorteService.deleteRegistroCortes(id); }
	 */

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_C
			+ "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_A + "')")*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@DeleteMapping(path = { "/delete/{id}" })
	public Object delete(@PathVariable("id") long id) {
		try {

			// llamo y borro en ganancias, registro_pago
			
			List<GananciasDTO> lista = new ArrayList<>();
			lista = listarGanancias();
			// System.out.printf( " tamaño "+lista.size());
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getId() == id) {
					gananciaService.deleteById(lista.get(i).getIdGanancias());
				}
			}
			//

			List<RegistroPagoDTO> result = new ArrayList<>();
			result = listarRegistroPago();
			for (int i = 0; i < result.size(); i++) {
				if (result.get(i).getId() == id) {
					registroPagoService.deleteById(result.get(i).getIdPago());

				}

			}

			registroCorteService.deleteById(id);
			statusResponse = HttpStatus.OK;
			jsonResponse.put(Constantes.CODE, statusResponse);
		} catch (Exception e) {
			System.out.printf("   " + e.getMessage() + "" + e.toString());
			// TODO: handle exception
			statusResponse = HttpStatus.OK;
			jsonResponse.put(Constantes.CODE, statusResponse);
			return new ResponseEntity<>(jsonResponse, statusResponse);

		}
		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

		
		
	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_C + "')" +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"   )*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@GetMapping("/listarValorPorcentaje")
	public List<PorcentajeDTO> listarPorcentaje() {
		return porcentajeService.findAll();
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_C + "')" +  "or hasAuthority('" + ConstantesRolesMP.IMPO
			+ ConstantesRolesMP.PRM_L + ConstantesRolesMP.PRM_C +   "')"   )*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	public IRegistroPagoService getRegistroPagoService() {
		return registroPagoService;
	}
}
















