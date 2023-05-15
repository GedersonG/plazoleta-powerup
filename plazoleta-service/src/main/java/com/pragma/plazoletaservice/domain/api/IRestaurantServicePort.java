package com.pragma.plazoletaservice.domain.api;

import com.pragma.plazoletaservice.domain.model.RestaurantModel;

import java.util.List;

public interface IRestaurantServicePort {

    void saveRestaurant(RestaurantModel restaurantModel);

    List<RestaurantModel> getAllRestaurants();
}