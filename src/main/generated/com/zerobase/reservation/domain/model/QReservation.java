package com.zerobase.reservation.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QReservation is a Querydsl query type for Reservation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservation extends EntityPathBase<Reservation> {

    private static final long serialVersionUID = -809347257L;

    public static final QReservation reservation = new QReservation("reservation");

    public final BooleanPath approved = createBoolean("approved");

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final BooleanPath finished = createBoolean("finished");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> numberOfPeople = createNumber("numberOfPeople", Integer.class);

    public final NumberPath<Long> restaurantId = createNumber("restaurantId", Long.class);

    public final StringPath verificationCode = createString("verificationCode");

    public final DateTimePath<java.time.LocalDateTime> verifyExpiredAt = createDateTime("verifyExpiredAt", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> visitDateTime = createDateTime("visitDateTime", java.time.LocalDateTime.class);

    public QReservation(String variable) {
        super(Reservation.class, forVariable(variable));
    }

    public QReservation(Path<? extends Reservation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QReservation(PathMetadata metadata) {
        super(Reservation.class, metadata);
    }

}

