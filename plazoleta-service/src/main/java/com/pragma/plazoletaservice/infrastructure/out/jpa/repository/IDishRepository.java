package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IDishRepository extends JpaRepository<DishEntity, Long> {

    @Modifying
    @Query("UPDATE DishEntity d SET " +
           "d.name = COALESCE(:name, d.name), " +
           "d.description = COALESCE(:description, d.description), " +
           "d.restaurant = COALESCE(:restaurant, d.restaurant), " +
           "d.active = COALESCE(:active, d.active), " +
           "d.urlImage = COALESCE(:urlImage, d.urlImage), " +
           "d.price = COALESCE(:price, d.price), " +
           "d.category = COALESCE(:category, d.category) " +
           "WHERE d.dishId = :dishId"
    )
    void updateDish(@Param("dishId") Long dishId,
                    @Param("name") String name,
                    @Param("description") String description,
                    @Param("price") int price,
                    @Param("urlImage") String urlImage,
                    @Param("active") boolean active,
                    @Param("category") CategoryEntity category,
                    @Param("restaurant") RestaurantEntity restaurant
    );

    @Query("SELECT d.restaurant " +
           "FROM DishEntity d " +
           "WHERE d.restaurant.restaurantId = :restaurantId"
    )
    Optional<RestaurantEntity> findRestaurantByRestaurantId(@Param("restaurantId") Long restaurantId);

    @Query("SELECT d.category " +
            "FROM DishEntity d " +
            "WHERE d.category.categoryId = :categoryId"
    )
    Optional<CategoryEntity> findCategoryByCategoryId(@Param("categoryId") Long categoryId);
}
