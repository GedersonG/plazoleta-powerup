package com.pragma.plazoletaservice.domain.spi;

import com.pragma.plazoletaservice.domain.model.CategoryModel;
import com.pragma.plazoletaservice.domain.model.DishModel;
import com.pragma.plazoletaservice.domain.model.RestaurantModel;

import java.util.List;

public interface IDishPersistencePort {

    DishModel saveDish(DishModel dishModel);

    List<DishModel> getAllDishes();

    DishModel getDishById(Long id);

    void deleteDishById(Long id);

    void updateDishById(Long id, DishModel dishModel);

    boolean existsById(Long id);

    RestaurantModel findRestaurantByRestaurantId(Long restaurantId);

    CategoryModel findCategoryByCategoryId(Long categoryId);
}
