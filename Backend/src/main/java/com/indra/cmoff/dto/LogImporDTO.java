package com.indra.cmoff.dto;


import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.indra.cmoff.utils.util.Constantes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class LogImporDTO extends BaseDTO{
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	@DateTimeFormat(pattern = Constantes.FORMATO_FECHA_YYYYMMDDHHMMSS)
	@Temporal(TemporalType.TIMESTAMP)	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date  fechaCarga;
	

	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 4000, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String nombreArchivo;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 10, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String estado;
	
	@NotEmpty(message = Constantes.MENSAJE_CAMPO_REQUERIDO)
	@Size(max = 4000, message = Constantes.MENSAJE_EXCEDE_TAMANO_MAXIMO)
	private String descripcion;
	

}
