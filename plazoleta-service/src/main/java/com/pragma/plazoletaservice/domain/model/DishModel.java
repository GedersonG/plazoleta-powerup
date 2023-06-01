package com.pragma.plazoletaservice.domain.model;

public class DishModel {

    private Long dishId;
    private String name;
    private CategoryModel category;
    private String description;
    private RestaurantModel restaurant;
    private int price;
    private String urlImage;
    private boolean active;

    public DishModel() {
    }

    public DishModel(Long dishId, String name, CategoryModel category, String description, RestaurantModel restaurant, int price, String urlImage, boolean active) {
        this.dishId = dishId;
        this.name = name;
        this.category = category;
        this.description = description;
        this.restaurant = restaurant;
        this.price = price;
        this.urlImage = urlImage;
        this.active = active;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public RestaurantModel getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantModel restaurant) {
        this.restaurant = restaurant;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
