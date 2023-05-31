package com.pragma.plazoletaservice.infrastructure.out.jpa.adapter;

import com.pragma.plazoletaservice.domain.model.CategoryModel;
import com.pragma.plazoletaservice.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoletaservice.infrastructure.exception.NoDataFoundException;
import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.CategoryEntity;
import com.pragma.plazoletaservice.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.plazoletaservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RequiredArgsConstructor
public class CategoryJpaAdapter implements ICategoryPersistencePort {

    private static final Logger logger = LoggerFactory.getLogger(CategoryJpaAdapter.class);
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    
    @Override
    public CategoryModel saveCategory(CategoryModel categoryModel) {
        CategoryEntity categoryEntity =
                categoryRepository.save(categoryEntityMapper.toEntity(categoryModel));

        logger.info("New category saved: {} with id -> {}",
                categoryEntity.getName(),
                categoryEntity.getCategoryId());
        return categoryEntityMapper.toCategoryModel(categoryEntity);
    }

    @Override
    public List<CategoryModel> getAllCategorys() {
        List<CategoryEntity> categoryList = categoryRepository.findAll();
        if (categoryList.isEmpty()) {
            logger.error("Category list it's empty.");
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toCategoryModelList(categoryList);
    }

    @Override
    public CategoryModel getCategoryById(Long id) {
        CategoryEntity category =
                categoryRepository
                        .findById(id)
                        .orElseThrow(NoDataFoundException::new);
        return categoryEntityMapper.toCategoryModel(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategoryById(Long id, CategoryModel categoryModel) {
        categoryRepository.updateCategory(
                id,
                categoryModel.getName(),
                categoryModel.getDescription()
        );
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }
}
