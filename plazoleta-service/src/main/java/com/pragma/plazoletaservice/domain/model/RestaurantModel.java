package com.pragma.plazoletaservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantModel {

    private Long restaurantId;
    private String name;
    private String address;
    private Long ownerId;
    private String telephone;
    private String urlLogo;
    private String nit;
}
