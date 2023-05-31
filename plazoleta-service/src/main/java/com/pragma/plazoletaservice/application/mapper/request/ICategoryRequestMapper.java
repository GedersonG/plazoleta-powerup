package com.pragma.plazoletaservice.application.mapper.request;

import com.pragma.plazoletaservice.application.dto.request.CategoryRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateCategoryRequestDto;
import com.pragma.plazoletaservice.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {

    CategoryModel toCategory(CategoryRequestDto categoryRequestDto);

    CategoryModel updateToCategory(UpdateCategoryRequestDto updateCategoryRequestDto);
}
