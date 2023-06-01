package com.pragma.plazoletaservice.application.mapper.request;

import com.pragma.plazoletaservice.application.dto.request.DishRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateDishRequestDto;
import com.pragma.plazoletaservice.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishRequestMapper {


    DishModel toDish(DishRequestDto dishRequestDto);

    @Mapping(target = "restaurant.restaurantId", source = "restaurantId")
    @Mapping(target = "category.categoryId", source = "categoryId")
    DishModel updateToDish(UpdateDishRequestDto updateDishRequestDto);
}
