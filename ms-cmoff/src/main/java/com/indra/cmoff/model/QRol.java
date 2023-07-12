package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QRol extends EntityPathBase<Rol> {

    private static final long serialVersionUID = 243701141L;

    public static final QRol rol = new QRol("rol");

    public final StringPath codigo = createString("codigo");

    public final StringPath descripcion = createString("descripcion");

    public final BooleanPath estado = createBoolean("estado");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QRol(String variable) {
        super(Rol.class, forVariable(variable));
    }

    public QRol(Path<? extends Rol> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRol(PathMetadata metadata) {
        super(Rol.class, metadata);
    }

}

