package com.indra.cmoff.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PersonaDTO extends BaseDTO {

	private static final long serialVersionUID = 8015696859109025461L;

	private Long codigoEmpleado;

	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 255, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String apellido;

	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 240, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String nombres;

	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 240, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	@Email(message = Constantes.EMAIL_INVALIDO)
	private String email;

	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 450, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String celular;

	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 45, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String direccion;



//	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
//	private String dominioTecnologico;
//	
//	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
//	private String deliveryCenter;

	public String getNombreCompleto() {
		StringBuilder nombreCompleto = new StringBuilder("");
		nombreCompleto.append(nombres).append(" ").append(apellido);
		if (StringUtils.isNotEmpty(apellido)) {
			nombreCompleto.append(" ").append(apellido);
		}
		return nombreCompleto.toString();
	}

	public void setId(long id) {
		// TODO Auto-generated method stub

	}

}
