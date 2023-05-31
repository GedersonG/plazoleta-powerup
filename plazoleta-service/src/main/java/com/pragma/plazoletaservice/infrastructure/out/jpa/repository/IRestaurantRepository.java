package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {
    @Modifying
    @Query(
            "UPDATE RestaurantEntity r SET " +
                    "r.name = COALESCE(:name, r.name), " +
                    "r.address = COALESCE(:address, r.address), " +
                    "r.ownerId = COALESCE(:ownerId, r.ownerId), " +
                    "r.telephone = COALESCE(:telephone, r.telephone), " +
                    "r.urlLogo = COALESCE(:urlLogo, r.urlLogo), " +
                    "r.nit = COALESCE(:nit, r.nit) " +
                    "WHERE r.restaurantId = :restaurantId"
    )
    void updateRestaurant(
            @Param("restaurantId") Long restaurantId,
            @Param("name") String name,
            @Param("address") String address,
            @Param("ownerId") Long ownerId,
            @Param("urlLogo") String urlLogo,
            @Param("nit") String nit
    );
}