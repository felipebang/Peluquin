package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;


@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QHistoriaGanancias extends EntityPathBase<HistoriaGanancias> {
	
	private static final long serialVersionUID = 1L;
	
	public static final QHistoriaGanancias historiaGanancias = new QHistoriaGanancias ("historiaGanancias");
	
 public final NumberPath<Long> idHistoriaGanancias = createNumber("idHistoriaGanancias", Long.class);
	 
	 public final NumberPath<Long> idGanancias = createNumber("idGanancias", Long.class);
	
	
	 public final StringPath gananciaValor = createString("gananciaValor");
	 
	 
	  public final DatePath<java.util.Date> createdAt = createDate("createdAt", java.util.Date.class);

	
	  
	  
	  public QHistoriaGanancias(String variable) {
	        super(HistoriaGanancias.class, forVariable(variable));
	    }
	    public QHistoriaGanancias(Path<? extends HistoriaGanancias> path) {
	        super(path.getType(), path.getMetadata());
	    }
	    
	    public QHistoriaGanancias(PathMetadata metadata) {
	        super(HistoriaGanancias.class, metadata);
	    }


	  
	  
	
	

}
