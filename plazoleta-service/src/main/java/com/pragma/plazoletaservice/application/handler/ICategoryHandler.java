package com.pragma.plazoletaservice.application.handler;

import com.pragma.plazoletaservice.application.dto.request.CategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateCategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.response.CategoryResponseDto;

import java.util.List;

public interface ICategoryHandler {

    void saveCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> getAllCategorys();

    CategoryResponseDto getCategoryById(Long id);

    void deleteCategoryById(Long id);

    void updateCategoryById(Long id, UpdateCategoryRequestDto requestDto);
}
