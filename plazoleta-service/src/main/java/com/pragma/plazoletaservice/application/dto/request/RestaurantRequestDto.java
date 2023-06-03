package com.pragma.plazoletaservice.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class RestaurantRequestDto {

    @NotEmpty(message = "The name of the restaurant is required")
    @Size(min = 1, max = 50, message = "The name of the restaurant must be between 1 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The name cannot contain special characters")
    private String name;

    @NotEmpty(message = "The address of the restaurant is mandatory")
    @Size(min = 1, max = 50, message = "The address must be between 1 and 50 characters.")
    private String address;

    @Min(value = 1, message = "The owner ID is invalid")
    @NotNull(message = "The owner ID is required")
    private Long ownerId;

    @NotEmpty(message = "Please enter a number")
    @Pattern(regexp = "^[0-9]{10}+$", message = "Invalid phone number.")
    private String telephone;

    private String urlLogo;

    @NotEmpty(message = "The nit is mandatory")
    @Pattern(regexp = "^[0-9]+$", message = "Invalid nit.")
    private String nit;
}
