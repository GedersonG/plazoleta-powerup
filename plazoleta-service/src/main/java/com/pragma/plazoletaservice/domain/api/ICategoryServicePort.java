package com.pragma.plazoletaservice.domain.api;

import com.pragma.plazoletaservice.domain.model.CategoryModel;

import java.util.List;

public interface ICategoryServicePort {

    CategoryModel saveCategory(CategoryModel categoryModel);

    List<CategoryModel> getAllCategorys();

    CategoryModel getCategoryById(Long id);

    void deleteCategoryById(Long id);

    void updateCategoryById(Long id, CategoryModel categoryModel);

    boolean existsById(Long id);

    boolean existsByName(String name);
}
