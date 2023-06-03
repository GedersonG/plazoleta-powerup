package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.Optional;

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

    @Query(value =
                "SELECT r.* " +
                "FROM restaurants r " +
                "LEFT JOIN dishes d " +
                "ON d.restaurant_id = r.restaurant_id " +
                "WHERE r.restaurant_id = :restaurantId",
            nativeQuery = true)
    Optional<Tuple> findRestaurantByRestaurantId(@Param("restaurantId") Long restaurantId);

    @Query(value =
            "SELECT c.* " +
            "FROM categories c " +
            "LEFT JOIN dishes d " +
            "ON d.category_id = c.category_id " +
            "WHERE c.category_id = :categoryId",
            nativeQuery = true)
    Optional<Tuple> findCategoryByCategoryId(@Param("categoryId") Long categoryId);
}
