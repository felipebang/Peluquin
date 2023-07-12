package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QPermiso extends EntityPathBase<Permiso> {

    private static final long serialVersionUID = 487753819L;

    public static final QPermiso permiso = new QPermiso("permiso");

    public final StringPath codigo = createString("codigo");

    public final StringPath descripcion = createString("descripcion");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QPermiso(String variable) {
        super(Permiso.class, forVariable(variable));
    }

    public QPermiso(Path<? extends Permiso> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPermiso(PathMetadata metadata) {
        super(Permiso.class, metadata);
    }

}

