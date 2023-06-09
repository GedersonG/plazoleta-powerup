package com.pragma.plazoletaservice.application.handler;

import com.pragma.plazoletaservice.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateRestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.response.RestaurantResponseDto;

import java.util.List;

public interface IRestaurantHandler {

    void saveRestaurant(RestaurantRequestDto restaurantRequestDto);

    List<RestaurantResponseDto> getAllRestaurants();

    RestaurantResponseDto getRestaurantById(Long id);

    void deleteRestaurantById(Long id);

    void updateRestaurantById(Long id, UpdateRestaurantRequestDto requestDto);
}