package com.pragma.plazoletaservice.domain.usecase;

import com.pragma.plazoletaservice.domain.api.ICategoryServicePort;
import com.pragma.plazoletaservice.domain.model.CategoryModel;
import com.pragma.plazoletaservice.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        return categoryPersistencePort.saveCategory(categoryModel);
    }

    @Override
    public List<CategoryModel> getAllCategorys() {
        return categoryPersistencePort.getAllCategorys();
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        return categoryPersistencePort.getCategoryById(id);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryPersistencePort.deleteCategoryById(id);
    }

    @Override
    public void updateCategoryById(Long id, CategoryModel categoryModel) {
        categoryPersistencePort.updateCategoryById(id, categoryModel);
    }

    @Override
    public boolean existsById(Long id) {
        return categoryPersistencePort.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryPersistencePort.existsByName(name);
    }
}
