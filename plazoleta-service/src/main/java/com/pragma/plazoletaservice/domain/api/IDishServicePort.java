package com.pragma.plazoletaservice.domain.api;

import com.pragma.plazoletaservice.domain.model.DishModel;

import java.util.List;

public interface IDishServicePort {

    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishs();

    DishModel getDishById(Long id);

    void deleteDishById(Long id);

    void updateDishById(Long id, DishModel dishModel);

    boolean existsById(Long id);
}
