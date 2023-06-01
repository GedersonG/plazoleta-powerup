package com.pragma.plazoletaservice.domain.usecase;

import com.pragma.plazoletaservice.domain.api.IDishServicePort;
import com.pragma.plazoletaservice.domain.model.DishModel;
import com.pragma.plazoletaservice.domain.spi.IDishPersistencePort;

import java.util.List;

public class DishUseCase implements IDishServicePort {

    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public DishModel saveDish(DishModel dishModel) {
        return dishPersistencePort.saveDish(dishModel);
    }

    @Override
    public List<DishModel> getAllDishs() {
        return dishPersistencePort.getAllDishs();
    }

    @Override
    public DishModel getDishById(Long id) {
        return dishPersistencePort.getDishById(id);
    }

    @Override
    public void deleteDishById(Long id) {
        dishPersistencePort.deleteDishById(id);
    }

    @Override
    public void updateDishById(Long id, DishModel dishModel) {
        dishPersistencePort.updateDishById(id, dishModel);
    }

    @Override
    public boolean existsById(Long id) {
        return dishPersistencePort.existsById(id);
    }

    @Override
    public Object[] findRestaurantAndCategoryByIds(Long restaurantId, Long categoryId) {
        return dishPersistencePort.findRestaurantAndCategoryByIds(restaurantId, categoryId);
    }
}
