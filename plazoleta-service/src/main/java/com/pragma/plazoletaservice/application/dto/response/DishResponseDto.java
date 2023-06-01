package com.pragma.plazoletaservice.application.dto.response;

import com.pragma.plazoletaservice.domain.model.CategoryModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DishResponseDto {

    private Long dishId;
    private String name;
    private CategoryModel category;
    private String description;
    private int price;
    private String urlImage;
    private boolean active;
}
