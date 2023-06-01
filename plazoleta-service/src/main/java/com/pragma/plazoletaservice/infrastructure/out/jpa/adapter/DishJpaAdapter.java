package com.pragma.plazoletaservice.infrastructure.out.jpa.adapter;

import com.pragma.plazoletaservice.domain.model.DishModel;
import com.pragma.plazoletaservice.domain.spi.IDishPersistencePort;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.DishEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.mapper.IDishEntityMapper;
import com.pragma.plazoletaservice.infrastructure.out.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public List<DishModel> getAllDishs() {
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
        DishEntity entity = dishEntityMapper.toEntity(dishModel);

        dishRepository.updateDish(
                id,
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getUrlImage(),
                entity.isActive(),
                entity.getCategory(),
                entity.getRestaurant()
        );
    }

    @Override
    public boolean existsById(Long id) {
        return dishRepository.existsById(id);
    }

    @Override
    public Object[] findRestaurantAndCategoryByIds(Long restaurantId, Long categoryId) {
        return dishRepository
                .findRestaurantAndCategoryByIds(restaurantId, categoryId)
                .orElseThrow(NoDataFoundException::new);
    }
}
