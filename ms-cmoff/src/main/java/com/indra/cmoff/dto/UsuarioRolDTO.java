package com.indra.cmoff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UsuarioRolDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8984124401989761319L;
	
	private Long idUsuario;
	private RolDTO rol;
	
}
