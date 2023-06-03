package com.pragma.plazoletaservice.infrastructure.out.jpa.adapter;

import com.pragma.plazoletaservice.domain.model.CategoryModel;
import com.pragma.plazoletaservice.domain.model.DishModel;
import com.pragma.plazoletaservice.domain.model.RestaurantModel;
import com.pragma.plazoletaservice.domain.spi.IDishPersistencePort;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoletaservice.infrastructure.exception.RestaurantDoesNotExistException;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.plazoletaservice.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Tuple;
import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private static final Logger logger = LoggerFactory.getLogger(DishJpaAdapter.class);
    private final IDishRepository dishRepository;
    private final IDishEntityMapper dishEntityMapper;

    @Override
    public DishModel saveDish(DishModel dishModel) {
        DishEntity dishEntity =
                dishRepository.save(dishEntityMapper.toEntity(dishModel));

        logger.info("New dish saved: {} with id -> {}",
                dishEntity.getName(),
                dishEntity.getDishId());
        return dishEntityMapper.toDishModel(dishEntity);
    }

    @Override
    public List<DishModel> getAllDishes() {
        List<DishEntity> dishList = dishRepository.findAll();
        if (dishList.isEmpty()) {
            logger.error("Dish list it's empty.");
            throw new NoDataFoundException();
        }
        return dishEntityMapper.toDishModelList(dishList);
    }

    @Override
    public DishModel getDishById(Long id) {
        DishEntity dish =
                dishRepository
                        .findById(id)
                        .orElseThrow(NoDataFoundException::new);
        return dishEntityMapper.toDishModel(dish);
    }

    @Override
    public void deleteDishById(Long id) {
        dishRepository.deleteById(id);
    }

    @Override
    public void updateDishById(Long id, DishModel dishModel) {
        dishRepository.updateDish(id, dishEntityMapper.toEntity(dishModel));
    }

    @Override
    public boolean existsById(Long id) {
        return dishRepository.existsById(id);
    }

    @Override
    public RestaurantModel findRestaurantByRestaurantId(Long restaurantId) {
        logger.info("Searching restaurant with id {}", restaurantId);
        Tuple result = dishRepository
                .findRestaurantByRestaurantId(restaurantId)
                .orElseThrow(RestaurantDoesNotExistException::new);

        String address = result.get("address", String.class);
        String name = result.get("name", String.class);
        String nit = result.get("nit", String.class);
        BigInteger ownerIdBigInteger = result.get("owner_id", BigInteger.class);
        Long ownerId = ownerIdBigInteger.longValue();
        String telephone = result.get("telephone", String.class);
        String urlLogo = result.get("url_logo", String.class);

        return new RestaurantModel(restaurantId, name, address, ownerId, telephone, urlLogo, nit);
    }

    @Override
    public CategoryModel findCategoryByCategoryId(Long categoryId) {
        logger.info("Searching restaurant with id {}", categoryId);
        Tuple result = dishRepository
                .findCategoryByCategoryId(categoryId)
                .orElseThrow(RestaurantDoesNotExistException::new);

        String name = result.get("name", String.class);
        String description = result.get("description", String.class);

        return new CategoryModel(categoryId, name, description);
    }
}
