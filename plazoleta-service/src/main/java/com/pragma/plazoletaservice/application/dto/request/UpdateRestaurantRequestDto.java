package com.pragma.plazoletaservice.application.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class UpdateRestaurantRequestDto {

    @Size(min = 1, max = 50, message = "The name of the restaurant must be between 1 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "The name cannot contain special characters")
    private String name;

    @Size(min = 1, max = 50, message = "The address must be between 1 and 50 characters.")
    private String address;

    private Long ownerId;

    @Pattern(regexp = "^[0-9]{10}+$", message = "Invalid phone number.")
    private String telephone;

    private String urlLogo;

    @Pattern(regexp = "^[0-9]+$", message = "Invalid nit.")
    private String nit;
}
