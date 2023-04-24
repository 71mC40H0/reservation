package com.zerobase.reservation.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QKiosk is a Querydsl query type for Kiosk
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QKiosk extends EntityPathBase<Kiosk> {

    private static final long serialVersionUID = -515290972L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QKiosk kiosk = new QKiosk("kiosk");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRestaurant restaurant;

    public QKiosk(String variable) {
        this(Kiosk.class, forVariable(variable), INITS);
    }

    public QKiosk(Path<? extends Kiosk> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QKiosk(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QKiosk(PathMetadata metadata, PathInits inits) {
        this(Kiosk.class, metadata, inits);
    }

    public QKiosk(Class<? extends Kiosk> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.restaurant = inits.isInitialized("restaurant") ? new QRestaurant(forProperty("restaurant"), inits.get("restaurant")) : null;
    }

}

