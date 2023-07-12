package com.indra.cmoff.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.indra.cmoff.utils.util.Constantes;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = Constantes.TABLE_REGISTRO_PAGO, schema = Constantes.SCHEMA_DB_IF)
public class RegistroPago  implements Serializable {
	
	
private static final long serialVersionUID = 2721819147043330397L;
	



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pago")
	private Long idPago;
	

	@Column(name = "id")
	private Long id;
	
	
	@Column(name = "valor_pago", nullable = false, length = 240)
	private String valorPago;
	
	@Column(name = "created_at", nullable = false, length = 240)
	private Date createdAt;


}
