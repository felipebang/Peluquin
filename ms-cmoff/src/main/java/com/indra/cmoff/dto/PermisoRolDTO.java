package com.indra.cmoff.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PermisoRolDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3861083196522221333L;
	
	private PermisoDTO permiso;
	private ModuloDTO modulo;
	private RolDTO rol;

}
