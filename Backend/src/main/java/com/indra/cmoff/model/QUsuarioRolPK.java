package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QUsuarioRolPK extends BeanPath<UsuarioRolPK> {

    private static final long serialVersionUID = -1143965578L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUsuarioRolPK usuarioRolPK = new QUsuarioRolPK("usuarioRolPK");

    public final QUsuario idUsuario;

    public final QRol rol;

    public QUsuarioRolPK(String variable) {
        this(UsuarioRolPK.class, forVariable(variable), INITS);
    }

    public QUsuarioRolPK(Path<? extends UsuarioRolPK> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUsuarioRolPK(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUsuarioRolPK(PathMetadata metadata, PathInits inits) {
        this(UsuarioRolPK.class, metadata, inits);
    }

    public QUsuarioRolPK(Class<? extends UsuarioRolPK> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.idUsuario = inits.isInitialized("idUsuario") ? new QUsuario(forProperty("idUsuario")) : null;
        this.rol = inits.isInitialized("rol") ? new QRol(forProperty("rol")) : null;
    }

}

