package com.pragma.plazoletaservice.infrastructure.out.jpa.repository;

import com.pragma.plazoletaservice.infrastructure.out.jpa.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {

    @Modifying
    @Query("UPDATE CategoryEntity c SET " +
            "c.name = COALESCE(:#{#categoryModel.name}, c.name), " +
            "c.description = COALESCE(:#{#categoryModel.description}, c.description) " +
            "WHERE c.categoryId = :categoryId"
    )
    void updateCategory(@Param("categoryId") Long categoryId, @Param("categoryModel") CategoryEntity categoryModel);


    boolean existsByName(String name);
}
