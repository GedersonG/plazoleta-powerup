package com.pragma.plazoletaservice.application.handler.impl;

import com.pragma.plazoletaservice.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateRestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoletaservice.application.handler.IRestaurantHandler;
import com.pragma.plazoletaservice.application.mapper.request.IRestaurantRequestMapper;
import com.pragma.plazoletaservice.application.mapper.response.IRestaurantResponseMapper;
import com.pragma.plazoletaservice.domain.api.IRestaurantServicePort;
import com.pragma.plazoletaservice.domain.model.RestaurantModel;
import com.pragma.plazoletaservice.infrastructure.exception.AlreadyExistsException;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandler implements IRestaurantHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantHandler.class);
    private final IRestaurantServicePort restaurantServicePort;
    private final IRestaurantRequestMapper restaurantRequestMapper;
    private final IRestaurantResponseMapper restaurantResponseMapper;

    @Override
    public void saveRestaurant(RestaurantRequestDto restaurantRequestDto) {
        existsByNit(restaurantRequestDto.getNit());
        if (restaurantRequestDto.getUrlLogo() == null) {
            restaurantRequestDto.setUrlLogo("");
        }

        RestaurantModel restaurantModel = restaurantRequestMapper.toRestaurant(restaurantRequestDto);
        logger.info("Saving restaurant...");
        restaurantServicePort.saveRestaurant(restaurantModel);
    }

    @Override
    public List<RestaurantResponseDto> getAllRestaurants() {
        return restaurantResponseMapper.toResponseList(restaurantServicePort.getAllRestaurants());
    }

    @Override
    public RestaurantResponseDto getRestaurantById(Long id) {
        return restaurantResponseMapper.toResponse(restaurantServicePort.getRestaurantById(id));
    }

    @Override
    public void deleteRestaurantById(Long id) {
        existsById(id);

        logger.warn("Deleting restaurant with id {}", id);
        restaurantServicePort.deleteRestaurantById(id);
    }

    @Override
    public void updateRestaurantById(Long id, UpdateRestaurantRequestDto requestDto) {
        existsById(id);

        logger.info("Updating restaurant...");
        restaurantServicePort.updateRestaurantById(id, restaurantRequestMapper.updateToRestaurant(requestDto));
    }

    private void existsById(Long id) {
        if (!restaurantServicePort.existsById(id)) {
            logger.error("The restaurant with id {} does not exists.", id);
            throw new NoDataFoundException();
        }
    }

    private void existsByNit(String nit) {
        if (restaurantServicePort.existsByNit(nit)) {
            logger.error("The restaurant already exists.");
            throw new AlreadyExistsException();
        }
    }
}