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
public class QRegistroCortes extends EntityPathBase<RegistroCortes> {

	private static final long serialVersionUID = 1L;

	 public static final QRegistroCortes registroCortes = new QRegistroCortes("registroCortes");

	public final NumberPath<Long> id = createNumber("id", Long.class);

	public final NumberPath<Long> idPorcentaje = createNumber("idPorcentaje", Long.class);

	public final NumberPath<Long> codigoEmpleado = createNumber("codigoEmpleado", Long.class);

	public final StringPath numeroCortes = createString("numeroCortes");

	public final StringPath valorCorte = createString("valorCorte");

	public final DatePath<java.util.Date> createdAt = createDate("createdAt", java.util.Date.class);




	
    
    public QRegistroCortes(String variable) {
        super(RegistroCortes.class, forVariable(variable));
    }
    public QRegistroCortes(Path<? extends RegistroCortes> path) {
        super(path.getType(), path.getMetadata());
    }
    
    public QRegistroCortes(PathMetadata metadata) {
        super(RegistroCortes.class, metadata);
    }
	/**
	 * 
	 */

}
