package com.pragma.plazoletaservice.application.handler;

import com.pragma.plazoletaservice.application.dto.request.ObjectRequestDto;
import com.pragma.plazoletaservice.application.dto.response.ObjectResponseDto;

import java.util.List;

public interface IObjectHandler {

    void saveObject(ObjectRequestDto objectRequestDto);

    List<ObjectResponseDto> getAllObjects();
}