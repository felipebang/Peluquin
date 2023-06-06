package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QRegistroPago extends EntityPathBase<RegistroPago> {

	private static final long serialVersionUID = 1L;
	
	public static final QRegistroPago  registroPago = new QRegistroPago("registroPago");

	public final NumberPath<Long> idPago = createNumber("idPago", Long.class);

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final StringPath valorPago = createString("valorPago");

	public final DatePath<java.util.Date> createdAt = createDate("createdAt", java.util.Date.class);

	  public QRegistroPago(String variable) {
	        super(RegistroPago.class, forVariable(variable));
	    }
	    public QRegistroPago(Path<? extends RegistroPago> path) {
	        super(path.getType(), path.getMetadata());
	    }
	    
	    public QRegistroPago(PathMetadata metadata) {
	        super(RegistroPago.class, metadata);
	    }


}
