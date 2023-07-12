package com.indra.cmoff.controller;

import com.indra.cmoff.dto.ModuloDTO;
import com.indra.cmoff.dto.PaginatedFilter;
import com.indra.cmoff.dto.PermisoDTO;
import com.indra.cmoff.dto.RolDTO;
import com.indra.cmoff.service.IModuloService;
import com.indra.cmoff.service.IPermisoService;
import com.indra.cmoff.service.IRolService;
import com.indra.cmoff.utils.util.Constantes;
import com.indra.cmoff.utils.util.ConstantesRolesMP;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/roles")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE,
		RequestMethod.OPTIONS })
public class RolController {

	private HashMap<String, Object> jsonResponse = new HashMap<>();
	String messageResponse = "";
	HttpStatus statusResponse = HttpStatus.BAD_REQUEST;

	private IRolService rolService;
	private final IModuloService moduloService;
	private final IPermisoService permisoService;

	public RolController(IRolService rolService, IModuloService moduloService, IPermisoService permisoService) {
		this.rolService = rolService;
		this.moduloService = moduloService;
		this.permisoService = permisoService;
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')")
	@GetMapping("/{id}")
	public RolDTO one(@PathVariable Long id) {
		return rolService.obtenerRolPorId(id);
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "') " + "or hasAuthority('" + ConstantesRolesMP.ADMU + ConstantesRolesMP.PRM_L
			+ "')")
	@GetMapping("/listar")
	public List<RolDTO> all() {
		return rolService.findAll();
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')")
	@PostMapping("/buscar")
	public Page<RolDTO> buscar(@RequestBody PaginatedFilter<RolDTO> filtro) {
		return rolService.filterPaginated(filtro.getPage(), filtro.getSizePerPage(), filtro.getFilter(),
				filtro.getColumn(), filtro.getOrder());
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_C + "')")
	@PostMapping("/crear")
	public Object createNew(@Valid @RequestBody RolDTO newRol) {

		try {
			rolService.saveRol(newRol);
			messageResponse = Constantes.ROL_CREADO;
			statusResponse = HttpStatus.CREATED;

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;
		}

		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_A + "')")
	@PutMapping("/actualizar/{id}")
	public Object replaceRol(@Valid @RequestBody RolDTO rolDto, @PathVariable Long id) {
		try {
			if (rolService.saveRol(rolDto)) {
				statusResponse = HttpStatus.CREATED;
				messageResponse = Constantes.ROL_ACTUALIZADO;
			} else {
				statusResponse = HttpStatus.BAD_REQUEST;
				messageResponse = Constantes.ERROR_ROL_ACTUALIZADO;
			}

		} catch (Exception e) {
			statusResponse = HttpStatus.BAD_REQUEST;
			messageResponse = Constantes.ERROR_SOLICITUD;
		}
		jsonResponse.put(Constantes.MESSAGE, messageResponse);
		jsonResponse.put(Constantes.CODE, statusResponse);
		return new ResponseEntity<>(jsonResponse, statusResponse);
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_C
			+ "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_A + "')")
	@GetMapping("/listarModulosActivos")
	public List<ModuloDTO> findAllByEstadoTrue() {
		return moduloService.findAllByEstado(Boolean.TRUE);
	}

	@PreAuthorize("hasRole('" + ConstantesRolesMP.ROLE_ADM + "') " + "or hasAuthority('" + ConstantesRolesMP.ROLPE
			+ ConstantesRolesMP.PRM_L + "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_C
			+ "')" + "or hasAuthority('" + ConstantesRolesMP.ROLPE + ConstantesRolesMP.PRM_A + "')")
	@GetMapping("/permisosListar")
	public List<PermisoDTO> findAll() {
		return permisoService.findAll();
	}

}
