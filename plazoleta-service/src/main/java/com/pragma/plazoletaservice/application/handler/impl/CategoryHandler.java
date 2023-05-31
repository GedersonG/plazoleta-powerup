package com.pragma.plazoletaservice.application.handler.impl;

import com.pragma.plazoletaservice.application.dto.request.CategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateCategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.response.CategoryResponseDto;
import com.pragma.plazoletaservice.application.handler.ICategoryHandler;
import com.pragma.plazoletaservice.application.mapper.request.ICategoryRequestMapper;
import com.pragma.plazoletaservice.application.mapper.response.ICategoryResponseMapper;
import com.pragma.plazoletaservice.domain.api.ICategoryServicePort;
import com.pragma.plazoletaservice.domain.model.CategoryModel;
import com.pragma.plazoletaservice.infrastructure.exception.AlreadyExistsException;
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
public class CategoryHandler implements ICategoryHandler {

    private static final Logger logger = LoggerFactory.getLogger(CategoryHandler.class);
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;
    
    @Override
    public void saveCategory(CategoryRequestDto categoryRequestDto) {
        existsByName(categoryRequestDto.getName());
        if (categoryRequestDto.getDescription() == null) {
            categoryRequestDto.setDescription("");
        }

        CategoryModel categoryModel = categoryRequestMapper.toCategory(categoryRequestDto);
        logger.info("Saving category...");
        categoryServicePort.saveCategory(categoryModel);
    }

    @Override
    public List<CategoryResponseDto> getAllCategorys() {
        return categoryResponseMapper.toResponseList(categoryServicePort.getAllCategorys());
    }

    @Override
    public CategoryResponseDto getCategoryById(Long id) {
        return categoryResponseMapper.toResponse(categoryServicePort.getCategoryById(id));
    }

    @Override
    public void deleteCategoryById(Long id) {
        existsById(id);

        logger.warn("Deleting category with id {}", id);
        categoryServicePort.deleteCategoryById(id);
    }

    @Override
    public void updateCategoryById(Long id, UpdateCategoryRequestDto requestDto) {
        existsById(id);

        logger.info("Updating restaurant...");
        categoryServicePort.updateCategoryById(id, categoryRequestMapper.updateToCategory(requestDto));
    }

    private void existsByName(String name) {
        if (categoryServicePort.existsByName(name)) {
            logger.error("The category already exists.");
            throw new AlreadyExistsException();
        }
    }

    private void existsById(Long id) {
        if (!categoryServicePort.existsById(id)) {
            logger.error("The category with id {} does not exists.", id);
            throw new NoDataFoundException();
        }
    }
}
