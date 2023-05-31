package com.pragma.plazoletaservice.infrastructure.configuration;

import com.pragma.plazoletaservice.domain.api.ICategoryServicePort;
import com.pragma.plazoletaservice.domain.spi.ICategoryPersistencePort;
import com.pragma.plazoletaservice.domain.usecase.CategoryUseCase;
import com.pragma.plazoletaservice.infrastructure.out.jpa.adapter.CategoryJpaAdapter;
import com.pragma.plazoletaservice.infrastructure.out.jpa.mapper.ICategoryEntityMapper;
import com.pragma.plazoletaservice.infrastructure.out.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBeanConfiguration {
    
    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryJpaAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
