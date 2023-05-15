package com.pragma.plazoletaservice.application.handler.impl;

import com.pragma.plazoletaservice.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoletaservice.application.handler.IRestaurantHandler;
import com.pragma.plazoletaservice.application.mapper.IRestaurantRequestMapper;
import com.pragma.plazoletaservice.application.mapper.IRestaurantResponseMapper;
import com.pragma.plazoletaservice.domain.api.IRestaurantServicePort;
import com.pragma.plazoletaservice.domain.model.RestaurantModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        restaurantServicePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }
}