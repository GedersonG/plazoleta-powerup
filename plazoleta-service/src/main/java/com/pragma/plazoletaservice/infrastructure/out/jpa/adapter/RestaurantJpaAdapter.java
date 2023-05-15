package com.pragma.plazoletaservice.infrastructure.out.jpa.adapter;

import com.pragma.plazoletaservice.domain.model.RestaurantModel;
import com.pragma.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoletaservice.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Override
    public RestaurantModel saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity = restaurantRepository.save(restaurantEntityMapper.toEntity(restaurantModel));
        return restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> entityList = restaurantRepository.findAll();
        if (entityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantModelList(entityList);
    }
}