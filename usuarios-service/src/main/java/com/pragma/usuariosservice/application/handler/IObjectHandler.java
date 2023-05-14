package com.pragma.usuariosservice.application.handler;

import com.pragma.usuariosservice.application.dto.request.ObjectRequestDto;
import com.pragma.usuariosservice.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}