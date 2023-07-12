package com.indra.cmoff.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;



@Generated("com.querydsl.codegen.EntitySerializer")
public class QPersona extends EntityPathBase<Persona> {

    private static final long serialVersionUID = 487938162L;

    public static final QPersona persona = new QPersona("persona");

    public final StringPath apellido1 = createString("apellido1");

    public final StringPath apellido2 = createString("apellido2");

    public final NumberPath<Long> codigoEmpleado = createNumber("codigoEmpleado", Long.class);

    public final StringPath email = createString("email");

    public final StringPath empresa = createString("empresa");

    public final StringPath funcionPrincipal = createString("funcionPrincipal");


    public final StringPath nombres = createString("nombres");

    public final StringPath pais = createString("pais");


    public final StringPath provincia = createString("provincia");


    public QPersona(String variable) {
        super(Persona.class, forVariable(variable));
    }

    public QPersona(Path<? extends Persona> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPersona(PathMetadata metadata) {
        super(Persona.class, metadata);
    }

}

