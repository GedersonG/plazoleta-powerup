package com.pragma.plazoletaservice.domain.spi;

import com.pragma.plazoletaservice.domain.model.ObjectModel;

import java.util.List;

public interface IObjectPersistencePort {
    ObjectModel saveObject(ObjectModel objectModel);

    List<ObjectModel> getAllObjects();
}