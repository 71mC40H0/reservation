package com.zerobase.reservation.domain.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.zerobase.reservation.domain.model.QRestaurant;
import com.zerobase.reservation.domain.model.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Restaurant> searchByName(String name) {
        String search  = "%" + name + "%";

        QRestaurant restaurant = QRestaurant.restaurant;

        return queryFactory.selectFrom(restaurant)
                .where(restaurant.name.like(search))
                .fetch();
    }
}
