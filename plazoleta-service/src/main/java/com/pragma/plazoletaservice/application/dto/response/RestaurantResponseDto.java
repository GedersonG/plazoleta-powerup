package com.pragma.plazoletaservice.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RestaurantResponseDto {
    private Long restaurantId;
    private String name;
    private String address;
    private Long ownerId;
    private String telephone;
    private String urlLogo;
    private String nit;
}
