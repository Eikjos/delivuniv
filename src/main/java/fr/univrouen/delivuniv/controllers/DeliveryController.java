package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.dto.SearchResultsDto;
import fr.univrouen.delivuniv.dto.delivery.DeliveryDto;
import fr.univrouen.delivuniv.dto.delivery.InsertDeliveryDto;
import fr.univrouen.delivuniv.dto.delivery.SearchDeliveryDto;
import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
import fr.univrouen.delivuniv.services.DeliveryService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/deliveries")
@AllArgsConstructor
@Tag(name = "Deliveries routes", description = "All related routes of deliveries")
public class DeliveryController {

    private final ModelMapper mapper;
    private final DeliveryService deliveryService;

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The delivery is successfully created"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid delivery tour. Or, Pick up address or delivery address is null"
                    )
            }
    )
    public ResponseEntity<DeliveryDto> create(@RequestBody InsertDeliveryDto model) {
        return new ResponseEntity<DeliveryDto>(mapper.map(deliveryService.create(model), DeliveryDto.class), HttpStatus.CREATED);
    }

    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of deliveries"
                    )
            }
    )
    public ResponseEntity<SearchResultsDto<DeliveryDto>> findAll(SearchDeliveryDto model) {
        return ResponseEntity.ok(SearchResultsDto.from(deliveryService.findAll(model).map(d -> mapper.map(d, DeliveryDto.class))));
    }

    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the delivery who the requested id"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not delivery exist with this id"),
            })
    public ResponseEntity<DeliveryDto> findById(@PathVariable UUID id) {
        var delivery = deliveryService.findById(id);
        if (delivery == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(delivery, DeliveryDto.class));
    }

    @PutMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The delivery is successfully updated"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The delivery not exist"
                    )
            }
    )
    public ResponseEntity<DeliveryDto> update(@PathVariable UUID id, InsertDeliveryDto model) {
        return ResponseEntity.ok(mapper.map(deliveryService.update(id, model), DeliveryDto.class));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The delivery is successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Not delivery exist with this id"
                    )
            }
    )
    public ResponseEntity delete(@PathVariable UUID id) {
        if (deliveryService.findById(id) == null) {
            return ResponseEntity.noContent().build();
        }
        deliveryService.delete(id);
        return ResponseEntity.ok(null);
    }
}
