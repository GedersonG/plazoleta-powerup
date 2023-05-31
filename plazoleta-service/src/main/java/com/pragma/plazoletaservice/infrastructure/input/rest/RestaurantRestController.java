package com.pragma.plazoletaservice.infrastructure.input.rest;

import com.pragma.plazoletaservice.application.dto.request.RestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.request.UpdateRestaurantRequestDto;
import com.pragma.plazoletaservice.application.dto.response.RestaurantResponseDto;
import com.pragma.plazoletaservice.application.handler.IRestaurantHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurant")
@RequiredArgsConstructor
public class RestaurantRestController {

    private final IRestaurantHandler restaurantHandler;

    @Operation(summary = "Add a new restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Restaurant created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Restaurant already exists", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Void> saveRestaurant(@Valid @RequestBody RestaurantRequestDto restaurantRequestDto) {
        restaurantHandler.saveRestaurant(restaurantRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Get all restaurants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All restaurants returned",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = RestaurantResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<RestaurantResponseDto>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants());
    }

    @Operation(summary = "Get restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant returned",
                    content = @Content(mediaType = "application/json",
                             schema = @Schema(implementation = RestaurantResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponseDto> getRestaurantById(@PathVariable Long id) {
        return ResponseEntity.ok(restaurantHandler.getRestaurantById(id));
    }

    @Operation(summary = "Delete restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad id restaurant", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurantById(@PathVariable Long id) {
        restaurantHandler.deleteRestaurantById(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Restaurant updated", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad updated", content = @Content),
            @ApiResponse(responseCode = "404", description = "Bad id restaurant", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRestaurantById(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRestaurantRequestDto requestDto
    ) {
        restaurantHandler.updateRestaurantById(id, requestDto);
        return ResponseEntity.ok().build();
    }
}