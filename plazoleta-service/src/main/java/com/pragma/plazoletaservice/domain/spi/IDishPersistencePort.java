package com.pragma.plazoletaservice.domain.spi;

import com.pragma.plazoletaservice.domain.model.DishModel;

import java.util.List;

public interface IDishPersistencePort {

    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDishById(Long id);

    void deleteDishById(Long id);

    void updateDishById(Long id, DishModel dishModel);

    boolean existsById(Long id);
}
