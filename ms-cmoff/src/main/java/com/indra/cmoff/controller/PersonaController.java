package com.indra.cmoff.controller;

import java.util.ArrayList;
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


import com.indra.cmoff.dto.PaginatedFilter;
import com.indra.cmoff.dto.PersonaDTO;
import com.indra.cmoff.dto.RegistroCortesDTO;
import com.indra.cmoff.dto.UsuarioDTO;
import com.indra.cmoff.service.ICrudService;
import com.indra.cmoff.service.IPersonaService;
import com.indra.cmoff.service.IRegistroCorteService;
import com.indra.cmoff.service.IUsuarioService;
import com.indra.cmoff.utils.util.Constantes;
import com.indra.cmoff.utils.util.ConstantesRolesMP;


@RestController
@RequestMapping(path = "/persona")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.OPTIONS })
public class PersonaController {

	private HashMap<String, Object> jsonResponse = new HashMap<>();
	String messageResponse = "";
	HttpStatus statusResponse = HttpStatus.BAD_REQUEST;

	private final IPersonaService personaService;

	private final IRegistroCorteService registroCorteService;
	
	private final IUsuarioService usuarioService;

	public PersonaController(IPersonaService personaService, IRegistroCorteService registroCorteService, IUsuarioService usuarioService) {
		this.personaService = personaService;
		this.registroCorteService = registroCorteService;
		this.usuarioService = usuarioService;

	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.FCHPERS
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L
			+ "')")*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@GetMapping("/{codEmpleado}")
	public Optional<PersonaDTO> one(@PathVariable Long codEmpleado) {
		return personaService.findById(codEmpleado);
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L
			+ "')")*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@GetMapping("/buscarporcodigo/{codigo}")
	public PersonaDTO findByCodigo(@PathVariable Long codigo) {
		return personaService.findByCodEmpleado(codigo);
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU
			+ ConstantesRolesMP.PRM_L + "')")*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PostMapping("/filternombres")
	public Page<PersonaDTO> filtrarPorNombre(@RequestBody PaginatedFilter<PersonaDTO> filtro) {
		return personaService.filtroPagPorNombre(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());
	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.FCHPERS
			+ ConstantesRolesMP.PRM_L + "')")*/

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PostMapping("/buscar")
	public Page<PersonaDTO> buscar(@RequestBody PaginatedFilter<PersonaDTO> filtro) {
		return personaService.filterPaginated(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());

	}

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_C + "')")*/
	
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PostMapping("/crear")
	public Object createNew(@Valid @RequestBody PersonaDTO newPersona) {

		try {
			personaService.save(newPersona);
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

	
	
	
	
	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_C
			+ "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_A + "')")*/
	 //actualizar update partica set subprati='nbnb' wh
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@PutMapping(path = { "/actualizar/{id}" })
	public Object replaceReg(@Valid @RequestBody PersonaDTO personaDto, @PathVariable Long id) {
		personaDto.setId((long) id);
		return personaService.editar(personaDto);
	}
	
	
	// eliminar

	/*
	 * public colaborador delete(@PathVariable("id") int id) { return
	 * registroCorteService.deleteRegistroCortes(id); }
	 */

	/*@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_C
			+ "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_A + "')")*/
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	@DeleteMapping(path = { "/delete/{id}" })
	public Object delete(@PathVariable("id") long codigoEmpleado) {
		try {

			// llamo y borro en REGISTROCORTES,
			List<RegistroCortesDTO> lista = new ArrayList<>();
			lista = listarRegistroCortes();
			for (int i = 0; i < lista.size(); i++) {
				if (lista.get(i).getCodigoEmpleado() == codigoEmpleado) {
					registroCorteService.deleteById(codigoEmpleado);

				}

			}

			List<UsuarioDTO> list = new ArrayList<>();
			list = listarUsuario();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getId() == codigoEmpleado) {
					usuarioService.deleteById(codigoEmpleado);

				}

			}
			//

			personaService.deleteById(codigoEmpleado);
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

	private void alert(String string) {
		// TODO Auto-generated method stub
		
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	private List<RegistroCortesDTO> listarRegistroCortes() {
		// TODO Auto-generated method stub
		return  registroCorteService.findAll();
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	public  IRegistroCorteService getRegistroCorteService() {
		// TODO Auto-generated method stub
		return registroCorteService;
	}
	
	
	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	private List<UsuarioDTO> listarUsuario() {
		
		// TODO Auto-generated method stub
		return  usuarioService.findAll();
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "')" + "or hasAuthority('" + ConstantesRolesMP.RRHH
			+ ConstantesRolesMP.PRM_C + "') " + "or hasAuthority('" + ConstantesRolesMP.RRHH + ConstantesRolesMP.PRM_L
			+ "')")
	public  IUsuarioService getUsuarioService() {
		// TODO Auto-generated method stub
		return usuarioService;
	}
	
	

}
