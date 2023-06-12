package com.pragma.usuariosservice.clients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "plazoleta-service", path = "/plazoleta")
public interface IPlazoletaClient {
}
