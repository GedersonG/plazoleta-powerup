package com.pragma.plazoletaservice.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryResponseDto {

    private Long categoryId;
    private String name;
    private String description;
}
