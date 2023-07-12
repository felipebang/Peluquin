package com.indra.cmoff.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolProyectoDTO extends BaseDTO {

	private static final long serialVersionUID = 2867032307688593984L;
	
	private Long id;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 240, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String codigo;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 240, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String descripcion;

}
