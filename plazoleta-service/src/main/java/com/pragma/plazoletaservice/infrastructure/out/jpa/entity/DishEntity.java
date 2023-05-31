package com.pragma.plazoletaservice.infrastructure.out.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "dishes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DishEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToOne
    @JoinColumn(nullable = false, name = "category_id")
    private CategoryEntity category;

    @Column(length = 50)
    private String description;

    @Column(nullable = false, length = 6)
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private RestaurantEntity restaurant;

    @Column(name = "url_image")
    private String urlImage;

    @Column(nullable = false)
    private boolean active = false;
}
