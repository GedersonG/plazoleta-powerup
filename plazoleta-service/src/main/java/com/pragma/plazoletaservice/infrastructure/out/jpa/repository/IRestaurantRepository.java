package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Modifying
    @Query("UPDATE RestaurantEntity r SET " +
            "r.name = COALESCE(:#{#restaurantModel.name}, r.name), " +
            "r.address = COALESCE(:#{#restaurantModel.address}, r.address), " +
            "r.ownerId = COALESCE(:#{#restaurantModel.ownerId}, r.ownerId), " +
            "r.telephone = COALESCE(:#{#restaurantModel.telephone}, r.telephone), " +
            "r.urlLogo = COALESCE(:#{#restaurantModel.urlLogo}, r.urlLogo), " +
            "r.nit = COALESCE(:#{#restaurantModel.nit}, r.nit) " +
            "WHERE r.restaurantId = :restaurantId"
    )
    void updateRestaurant(@Param("restaurantId") Long restaurantId, @Param("restaurantModel") RestaurantEntity restaurantModel);

    boolean existsByNit(String nit);
}