package com.pragma.plazoletaservice.domain.model;

public class RestaurantModel {

    private Long restaurantId;
    private String name;
    private String address;
    private Long ownerId;
    private String telephone;
    private String urlLogo;
    private String nit;

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public RestaurantModel(Long restaurantId, String name, String address, Long ownerId, String telephone, String urlLogo, String nit) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.telephone = telephone;
        this.urlLogo = urlLogo;
        this.nit = nit;
    }

    public RestaurantModel() {
    }
}
