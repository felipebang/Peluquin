package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QUsuario extends EntityPathBase<Usuario> {

    private static final long serialVersionUID = 1033534452L;

    public static final QUsuario usuario1 = new QUsuario("usuario1");

    public final StringPath clave = createString("clave");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> persona = createNumber("persona", Long.class);

    public final StringPath usuario = createString("usuario");

    public QUsuario(String variable) {
        super(Usuario.class, forVariable(variable));
    }

    public QUsuario(Path<? extends Usuario> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsuario(PathMetadata metadata) {
        super(Usuario.class, metadata);
    }

}

