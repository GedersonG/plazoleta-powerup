package com.pragma.plazoletaservice.domain.api;

import com.pragma.plazoletaservice.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}