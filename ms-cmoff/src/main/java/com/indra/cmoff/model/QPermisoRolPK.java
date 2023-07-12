package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;



@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QPermisoRolPK extends BeanPath<PermisoRolPK> {

    private static final long serialVersionUID = -247262097L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPermisoRolPK permisoRolPK = new QPermisoRolPK("permisoRolPK");

    public final QModulo modulo;

    public final QPermiso permiso;

    public final QRol rol;

    public QPermisoRolPK(String variable) {
        this(PermisoRolPK.class, forVariable(variable), INITS);
    }

    public QPermisoRolPK(Path<? extends PermisoRolPK> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPermisoRolPK(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPermisoRolPK(PathMetadata metadata, PathInits inits) {
        this(PermisoRolPK.class, metadata, inits);
    }

    public QPermisoRolPK(Class<? extends PermisoRolPK> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.modulo = inits.isInitialized("modulo") ? new QModulo(forProperty("modulo"), inits.get("modulo")) : null;
        this.permiso = inits.isInitialized("permiso") ? new QPermiso(forProperty("permiso")) : null;
        this.rol = inits.isInitialized("rol") ? new QRol(forProperty("rol")) : null;
    }

}

