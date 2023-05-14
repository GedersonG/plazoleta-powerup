package com.pragma.usuariosservice.application.handler.impl;

import com.pragma.usuariosservice.application.dto.request.ObjectRequestDto;
import com.pragma.usuariosservice.application.dto.response.ObjectResponseDto;
import com.pragma.usuariosservice.application.handler.IObjectHandler;
import com.pragma.usuariosservice.application.mapper.IObjectRequestMapper;
import com.pragma.usuariosservice.application.mapper.IObjectResponseMapper;
import com.pragma.usuariosservice.domain.api.IObjectServicePort;
import com.pragma.usuariosservice.domain.model.ObjectModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ObjectHandler implements IObjectHandler {

    private final IObjectServicePort objectServicePort;
    private final IObjectRequestMapper objectRequestMapper;
    private final IObjectResponseMapper objectResponseMapper;

    @Override
    public void saveObject(ObjectRequestDto objectRequestDto) {
        ObjectModel objectModel = objectRequestMapper.toObject(objectRequestDto);
        objectServicePort.saveObject(objectModel);
    }

    @Override
    public List<ObjectResponseDto> getAllObjects() {
        return objectResponseMapper.toResponseList(objectServicePort.getAllObjects());
    }
}