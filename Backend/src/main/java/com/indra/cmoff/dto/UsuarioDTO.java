package com.indra.cmoff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.indra.cmoff.utils.util.Constantes;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UsuarioDTO extends BaseDTO{
	
	private static final long serialVersionUID = 7156459006392751851L;
	
	private Long id;
	private Long persona;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 50, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	@NotBlank(message = Constantes.MENSAJE_CAMPO_VACIO)
	@Pattern(regexp = "^[a-z0-9]+$", message=Constantes.MENSAJE_LETRA_INVALIDA)
	private String usuario;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@NotBlank(message = Constantes.MENSAJE_CAMPO_VACIO)
	@Size(min = 4, max = 16, message = Constantes.MENSAJE_CLAVE_INVALIDA)
	private String clave;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	private List<Long> roles;
	private String rolesUsuario;
	
	/**
	 *Atributo creado para el estado de la persona para acceso al sistema,
	 *si en DB hay un usuario con una persona que no existe en base de datos, el estado es igualado a false
	 *para así restringir el acceso.
	 */
	private Boolean estadoPersona;
	/**
	 *Atributo creado para manejo en el Front si la persona logueada existe en DB
	 */
	private PersonaDTO personaDTO;

}
