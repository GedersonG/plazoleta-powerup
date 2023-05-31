package com.pragma.plazoletaservice.application.mapper.response;

import com.pragma.plazoletaservice.application.dto.response.CategoryResponseDto;
import com.pragma.plazoletaservice.domain.model.CategoryModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryResponseMapper {

    CategoryResponseDto toResponse(CategoryModel categoryModel);

    List<CategoryResponseDto> toResponseList(List<CategoryModel> categoryModelList);
}
