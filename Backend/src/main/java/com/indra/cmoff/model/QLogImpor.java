package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QLogImpor extends EntityPathBase<LogImpor> {

    private static final long serialVersionUID = -503756347L;

    public static final QLogImpor logImpor = new QLogImpor("logImpor");

    public final StringPath descripcion = createString("descripcion");

    public final StringPath estado = createString("estado");

    public final DatePath<java.util.Date> fechaCarga = createDate("fechaCarga", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath nombreArchivo = createString("nombreArchivo");

    public QLogImpor(String variable) {
        super(LogImpor.class, forVariable(variable));
    }

    public QLogImpor(Path<? extends LogImpor> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLogImpor(PathMetadata metadata) {
        super(LogImpor.class, metadata);
    }

}

