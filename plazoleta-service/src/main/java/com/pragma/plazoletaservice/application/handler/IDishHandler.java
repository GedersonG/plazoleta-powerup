package com.pragma.plazoletaservice.application.handler;

import com.pragma.plazoletaservice.application.dto.request.DishRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateDishRequestDto;
import com.pragma.plazoletaservice.application.dto.response.DishResponseDto;

import java.util.List;

public interface IDishHandler {

    void saveDish(DishRequestDto dishRequestDto);

    List<DishResponseDto> getAllDishs();

    DishResponseDto getDishById(Long id);

    void deleteDishById(Long id);

    void updateDishById(Long id, UpdateDishRequestDto requestDto);
}
