package com.indra.cmoff.dto;

import java.util.Date;
import java.util.List;

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
public class RegistroCortesDTO extends BaseDTO {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long idPorcentaje;
	private Long codigoEmpleado;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 15, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String numeroCortes;
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 15, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String valorCorte;
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 255, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private Date createdAt;
	private List<RegistroCortesDTO> RegistroCortesDTOList;

}
