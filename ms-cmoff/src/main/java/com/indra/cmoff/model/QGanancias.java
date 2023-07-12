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
public class QGanancias  extends EntityPathBase<Ganancias>  {
	

	private static final long serialVersionUID = 1L;
	
	public static final QGanancias ganancias = new QGanancias("ganancias");
	
	
	 public final NumberPath<Long> idGanancias = createNumber("idGanancias", Long.class);
	 
	 public final NumberPath<Long> id = createNumber("id", Long.class);
	
	
	 public final StringPath gananciaValor = createString("gananciaValor");
	 
	 
	  public final DatePath<java.util.Date> createdAt = createDate("createdAt", java.util.Date.class);


	  
	  public QGanancias(String variable) {
	        super(Ganancias.class, forVariable(variable));
	    }
	    public QGanancias(Path<? extends Ganancias> path) {
	        super(path.getType(), path.getMetadata());
	    }
	    
	    public QGanancias(PathMetadata metadata) {
	        super(Ganancias.class, metadata);
	    }

}
