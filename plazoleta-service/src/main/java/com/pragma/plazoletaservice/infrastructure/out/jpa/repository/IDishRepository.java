package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    @Modifying
    @Query("UPDATE DishEntity d SET " +
           "d.name = COALESCE(:#{#entity.name}, d.name), " +
           "d.description = COALESCE(:#{#entity.description}, d.description), " +
           "d.restaurant = COALESCE(:#{#entity.restaurant}, d.restaurant), " +
           "d.active = COALESCE(:#{#entity.active}, d.active), " +
           "d.urlImage = COALESCE(:#{#entity.urlImage}, d.urlImage), " +
           "d.price = COALESCE(:#{#entity.price}, d.price), " +
           "d.category = COALESCE(:#{#entity.category}, d.category) " +
           "WHERE d.dishId = :dishId"
    )
    void updateDish(@Param("dishId") Long dishId, @Param("entity") DishEntity entity);
}
