package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsuarioRol extends EntityPathBase<UsuarioRol> {

    private static final long serialVersionUID = -595603141L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsuarioRol usuarioRol = new QUsuarioRol("usuarioRol");

    public final QUsuarioRolPK id;

    public QUsuarioRol(String variable) {
        this(UsuarioRol.class, forVariable(variable), INITS);
    }

    public QUsuarioRol(Path<? extends UsuarioRol> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsuarioRol(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsuarioRol(PathMetadata metadata, PathInits inits) {
        this(UsuarioRol.class, metadata, inits);
    }

    public QUsuarioRol(Class<? extends UsuarioRol> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.id = inits.isInitialized("id") ? new QUsuarioRolPK(forProperty("id"), inits.get("id")) : null;
    }

}

