package com.pragma.plazoletaservice.application.handler.impl;

import com.pragma.plazoletaservice.application.dto.request.DishRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateDishRequestDto;
import com.pragma.plazoletaservice.application.dto.response.DishResponseDto;
import com.pragma.plazoletaservice.application.handler.IDishHandler;
import com.pragma.plazoletaservice.application.mapper.request.IDishRequestMapper;
import com.pragma.plazoletaservice.application.mapper.response.IDishResponseMapper;
import com.pragma.plazoletaservice.domain.api.IDishServicePort;
import com.pragma.plazoletaservice.domain.model.CategoryModel;
import com.pragma.plazoletaservice.domain.model.DishModel;
import com.pragma.plazoletaservice.domain.model.RestaurantModel;
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
public class DishHandler implements IDishHandler {

    private static final Logger logger = LoggerFactory.getLogger(DishHandler.class);
    private final IDishServicePort dishServicePort;
    private final IDishRequestMapper dishRequestMapper;
    private final IDishResponseMapper dishResponseMapper;

    @Override
    public void saveDish(DishRequestDto dishRequestDto) {
        logger.info("Verifying restaurant id and category id");
        RestaurantModel restaurant = getRestaurantByRestaurantId(dishRequestDto.getRestaurantId());
        CategoryModel category = getCategoryByCategoryId(dishRequestDto.getRestaurantId());

        if (dishRequestDto.getDescription() == null) {
            dishRequestDto.setDescription("");
        }

        DishModel dishModel = dishRequestMapper.toDish(dishRequestDto);
        dishModel.setRestaurant(restaurant);
        dishModel.setCategory(category);
        dishModel.setActive(true);

        logger.info("Saving dish...");
        dishServicePort.saveDish(dishModel);
    }

    @Override
    public List<DishResponseDto> getAllDishs() {
        return dishResponseMapper.toResponseList(dishServicePort.getAllDishs());
    }

    @Override
    public DishResponseDto getDishById(Long id) {
        return dishResponseMapper.toResponse(dishServicePort.getDishById(id));
    }

    @Override
    public void deleteDishById(Long id) {
        existsById(id);


        logger.warn("Deleting dish with id {}", id);
        dishServicePort.deleteDishById(id);
    }

    @Override
    public void updateDishById(Long id, UpdateDishRequestDto requestDto) {
        existsById(id);

        DishModel dish = dishRequestMapper.updateToDish(requestDto);
        existsRestaurantAndCategory(dish, requestDto.getRestaurantId(), requestDto.getCategoryId());

        logger.info("Updating restaurant...");
        dishServicePort.updateDishById(id, dish);
    }

    private void existsById(Long id) {
        if (!dishServicePort.existsById(id)) {
            logger.error("The dish with id {} does not exists.", id);
            throw new NoDataFoundException();
        }
    }

    private RestaurantModel getRestaurantByRestaurantId(Long restaurantId) {
        return (RestaurantModel) dishServicePort.findRestaurantByRestaurantId(restaurantId);
    }

    private CategoryModel getCategoryByCategoryId(Long categoryId) {
        return (CategoryModel) dishServicePort.findCategoryByCategoryId(categoryId);
    }

    private void existsRestaurantAndCategory(DishModel dish, Long restaurantId, Long categoryId) {
        if (restaurantId != null) {
            logger.info("Verifying restaurant id");
            dish.setRestaurant(getRestaurantByRestaurantId(restaurantId));
        }
        if (categoryId != null) {
            logger.info("Verifying category id");
            dish.setCategory(getCategoryByCategoryId(categoryId));
        }
    }
}
