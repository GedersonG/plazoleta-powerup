package com.pragma.usuariosservice.domain.api;

import com.pragma.usuariosservice.domain.model.ObjectModel;

import java.util.List;

public interface IObjectServicePort {

    void saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}