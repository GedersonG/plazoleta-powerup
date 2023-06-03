package com.pragma.plazoletaservice.infrastructure.out.jpa.adapter;

import com.pragma.plazoletaservice.domain.model.RestaurantModel;
import com.pragma.plazoletaservice.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.RestaurantEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.mapper.IRestaurantEntityMapper;
import com.pragma.plazoletaservice.infrastructure.out.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantJpaAdapter.class);
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;

    @Override
    public void saveRestaurant(RestaurantModel restaurantModel) {
        RestaurantEntity restaurantEntity =
                restaurantRepository.save(restaurantEntityMapper.toEntity(restaurantModel));

        logger.info("New restaurant saved: {} with id -> {}",
                    restaurantEntity.getName(),
                    restaurantEntity.getRestaurantId());
        restaurantEntityMapper.toRestaurantModel(restaurantEntity);
    }

    @Override
    public List<RestaurantModel> getAllRestaurants() {
        List<RestaurantEntity> restaurantList = restaurantRepository.findAll();
        if (restaurantList.isEmpty()) {
            logger.error("Restaurant list it's empty.");
            throw new NoDataFoundException();
        }
        return restaurantEntityMapper.toRestaurantModelList(restaurantList);
    }

    @Override
    public RestaurantModel getRestaurantById(Long id) {
        RestaurantEntity restaurant =
                restaurantRepository
                .findById(id)
                .orElseThrow(NoDataFoundException::new);
        return restaurantEntityMapper.toRestaurantModel(restaurant);
    }

    @Override
    public void deleteRestaurantById(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public void updateRestaurantById(Long id, RestaurantModel restaurantModel) {
        restaurantRepository.updateRestaurant(
                id, restaurantEntityMapper.toEntity(restaurantModel)
        );
    }

    @Override
    public boolean existsById(Long id) {
        return restaurantRepository.existsById(id);
    }

    @Override
    public boolean existsByNit(String nit) {
        return restaurantRepository.existsByNit(nit);
    }
}