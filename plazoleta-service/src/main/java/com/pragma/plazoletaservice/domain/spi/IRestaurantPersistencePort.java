package com.pragma.plazoletaservice.domain.spi;

import com.pragma.plazoletaservice.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantPersistencePort {
    RestaurantModel saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();
}