package com.gbsys.exotra.entidad;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTarea is a Querydsl query type for Tarea
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTarea extends EntityPathBase<Tarea> {

    private static final long serialVersionUID = 1351640030L;

    public static final QTarea tarea = new QTarea("tarea");

    public final StringPath dscTarea = createString("dscTarea");

    public final EnumPath<com.gbsys.exotra.constante.EstadoTarea> estTarea = createEnum("estTarea", com.gbsys.exotra.constante.EstadoTarea.class);

    public final DateTimePath<java.util.Date> fecTarea = createDateTime("fecTarea", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QTarea(String variable) {
        super(Tarea.class, forVariable(variable));
    }

    public QTarea(Path<? extends Tarea> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTarea(PathMetadata metadata) {
        super(Tarea.class, metadata);
    }

}

