package com.pragma.plazoletaservice.application.dto.request;

import com.pragma.plazoletaservice.domain.model.CategoryModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class DishRequestDto {

    @NotEmpty(message = "The name of dish is required.")
    @Size(min = 1, max = 50, message = "The name of the dish must be between 1 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The name cannot contain special characters")
    private String name;

    @Min(value = 1, message = "Category Id is required")
    @NotNull(message = "The category is required") // ??
    private Long categoryId;

    @Size(min = 3, max = 100, message = "Invalid description")
    private String description;

    @Min(value = 1000, message = "The minimum price for the dish is 1000")
    @Max(value = 1000000, message = "The maximum price for the dish is 1000000")
    @NotNull(message = "The price cannot be empty")
    private int price;

    @Min(value = 1, message = "Restaurant is required")
    @NotNull(message = "Mensaje desde empty")
    private Long restaurantId;

    private String urlImage;
}
