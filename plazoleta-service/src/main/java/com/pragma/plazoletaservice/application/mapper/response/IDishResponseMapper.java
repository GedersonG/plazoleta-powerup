package com.pragma.plazoletaservice.application.mapper.response;

import com.pragma.plazoletaservice.application.dto.response.DishResponseDto;
import com.pragma.plazoletaservice.domain.model.DishModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IDishResponseMapper {

    DishResponseDto toResponse(DishModel dishModel);

    List<DishResponseDto> toResponseList(List<DishModel> dishModelList);
}
