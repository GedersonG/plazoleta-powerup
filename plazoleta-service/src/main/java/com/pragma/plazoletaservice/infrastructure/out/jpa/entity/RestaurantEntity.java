package com.pragma.plazoletaservice.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "restaurants")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long restaurantId;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String address;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId; // Set de Usuarios

    @Column(length = 10)
    private String telephone;

    @Column(name = "url_logo")
    private String urlLogo;

    @Column(nullable = false, unique = true)
    private String nit;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private List<DishEntity> dishes;
}
