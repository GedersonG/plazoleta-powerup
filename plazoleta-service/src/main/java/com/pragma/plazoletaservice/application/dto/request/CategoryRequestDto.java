package com.pragma.plazoletaservice.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class CategoryRequestDto {

    @NotEmpty(message = "The name of category is required.")
    @Size(min = 1, max = 50, message = "The name of the category must be between 1 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The name cannot contain special characters")
    private String name;

    @Size(min = 3, max = 100, message = "Invalid description")
    private String description;
}
