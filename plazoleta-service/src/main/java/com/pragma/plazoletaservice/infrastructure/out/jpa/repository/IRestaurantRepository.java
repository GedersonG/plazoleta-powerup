package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Long> {

}