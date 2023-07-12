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
public class QPorcentaje  extends EntityPathBase<Porcentaje> {


	private static final long serialVersionUID = -5916331976805354195L;


	 public static final QPorcentaje porcentaje = new QPorcentaje("porcentaje");


	    public final NumberPath<Long> idPorcentaje = createNumber("idPorcentaje", Long.class);

	    public final StringPath  porcentajeEmpl = createString("porcentajeEmpl");
	    
		public final DatePath<java.util.Date> createdAt = createDate("createdAt", java.util.Date.class);

	    
	    
	    public QPorcentaje(String variable) {
	        super(Porcentaje.class, forVariable(variable));
	    }
	    public QPorcentaje(Path<? extends Porcentaje> path) {
	        super(path.getType(), path.getMetadata());
	    }
	    
	    public QPorcentaje(PathMetadata metadata) {
	        super(Porcentaje.class, metadata);
	    }

	
	
}
