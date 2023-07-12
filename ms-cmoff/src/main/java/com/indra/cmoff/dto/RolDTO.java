package com.indra.cmoff.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.indra.cmoff.utils.util.Constantes;
import com.indra.cmoff.utils.util.Util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RolDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2751387798636879614L;
	
	private Long id;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 15, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	@NotBlank(message = Constantes.MENSAJE_CAMPO_VACIO)
	@Pattern(regexp = "^[a-zA-Z0-9-_']+$", message = Constantes.MENSAJE_LETRA_INVALIDA)
	private String codigo;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 100, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	@NotBlank(message = Constantes.MENSAJE_CAMPO_VACIO)
	@Pattern(regexp = "^[a-zA-ZÀ-ÿ\u00f1\u00d1']+(s*[a-zA-ZÀ-ÿ\u00f1\u00d1']* )*[a-zA-ZÀ-ÿ\u00f1\u00d1']+$", message = Constantes.MENSAJE_LETRA_INVALIDA)
	private String descripcion;
	
	private Boolean estado;
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	private List<ModuloDTO> modulosDtoList;
	
	public String getRolDescripcion () {
		return Util.toStringPKDto(this.getCodigo(), this.getDescripcion());
			
	}
}
