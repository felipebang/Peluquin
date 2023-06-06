package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QModulo extends EntityPathBase<Modulo> {

    private static final long serialVersionUID = 1462693104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModulo modulo = new QModulo("modulo");

    public final StringPath codigo = createString("codigo");

    public final BooleanPath estado = createBoolean("estado");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QModulo moduloEnlazado;

    public final SetPath<Modulo, QModulo> modulos = this.<Modulo, QModulo>createSet("modulos", Modulo.class, QModulo.class, PathInits.DIRECT2);

    public final StringPath nombre = createString("nombre");

    public QModulo(String variable) {
        this(Modulo.class, forVariable(variable), INITS);
    }

    public QModulo(Path<? extends Modulo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QModulo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QModulo(PathMetadata metadata, PathInits inits) {
        this(Modulo.class, metadata, inits);
    }

    public QModulo(Class<? extends Modulo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.moduloEnlazado = inits.isInitialized("moduloEnlazado") ? new QModulo(forProperty("moduloEnlazado"), inits.get("moduloEnlazado")) : null;
    }

}

