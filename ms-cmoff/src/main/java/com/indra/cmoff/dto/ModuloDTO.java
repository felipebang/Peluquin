package com.indra.cmoff.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ModuloDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 15, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String codigo;
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 255, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String nombre;
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	private Boolean estado;
	protected ModuloDTO moduloEnlazado;
	private List<PermisoDTO> permisoDTOList;
}
