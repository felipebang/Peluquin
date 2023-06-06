package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QPermisoRol extends EntityPathBase<PermisoRol> {

    private static final long serialVersionUID = 799741812L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermisoRol permisoRol = new QPermisoRol("permisoRol");

    public final QPermisoRolPK id;

    public QPermisoRol(String variable) {
        this(PermisoRol.class, forVariable(variable), INITS);
    }

    public QPermisoRol(Path<? extends PermisoRol> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPermisoRol(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPermisoRol(PathMetadata metadata, PathInits inits) {
        this(PermisoRol.class, metadata, inits);
    }

    public QPermisoRol(Class<? extends PermisoRol> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QPermisoRolPK(forProperty("id"), inits.get("id")) : null;
    }

}

