package com.pragma.usuariosservice.domain.spi;

import com.pragma.usuariosservice.domain.model.ObjectModel;

import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}