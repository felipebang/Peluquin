package com.indra.cmoff.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.indra.cmoff.utils.util.Constantes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = Constantes.TABLE_PERSONA, schema = Constantes.SCHEMA_DB_IF)
public class Persona implements Serializable {

	private static final long serialVersionUID = 2721819147043330397L;
	
	@Id
	@Column(name = "nu_cod_empleado")
	private Long codigoEmpleado;
	
	@Column(name = "sb_nombre", nullable = false, length = 240)
	private String nombres;
	
	@Column(name = "sb_apellido_1", nullable = false, length = 45)
	private String apellido1;
	
	@Column(name = "sb_apellido_2", length = 45)
	private String apellido2;
	
	@Column(name = "sb_email", nullable = false, length = 240)
	private String email;
	
	@Column(name = "sb_empresa", nullable = false, length = 450)
	private String empresa;
	
	@Column(name = "sb_funcion_principal", nullable = false, length = 450)
	private String funcionPrincipal;
	
	
	@Column(name = "sb_pais", nullable = false, length = 45)
	private String pais;
	
	@Column(name = "sb_provincia", nullable = false, length = 45)
	private String provincia;
	

	

	


	

	
	

	



	
//	@Column(name = "sb_dominio_tecnologico", nullable = false)
//	private String dominioTecnologico;
//	
//	@Column(name = "sb_delivery_center", nullable = false)
//	private String deliveryCenter;

}
