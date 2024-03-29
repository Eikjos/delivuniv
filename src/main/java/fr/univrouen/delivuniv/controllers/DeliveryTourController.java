package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.dto.SearchResultsDto;
import fr.univrouen.delivuniv.dto.delivery.DeliveryDto;
import fr.univrouen.delivuniv.dto.delivery.SearchDeliveryDto;
import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
import fr.univrouen.delivuniv.dto.deliveryTour.InsertDeliveryTourDto;
import fr.univrouen.delivuniv.dto.deliveryTour.SearchDeliveryTourDto;
import fr.univrouen.delivuniv.services.DeliveryService;
import fr.univrouen.delivuniv.services.DeliveryTourService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/delivery-tours")
@AllArgsConstructor
@Tag(name = "Delivery tours routes", description = "All related routes of delivery tours")
public class DeliveryTourController {

    private final DeliveryTourService deliveryTourService;
    private final DeliveryService deliveryService;
    private final ModelMapper mapper;

    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the list of all the delivery tours"
                    )
            }
    )
    public ResponseEntity<List<DeliveryTourDto>> findAll() {
        return ResponseEntity.ok(deliveryTourService.findAll().stream().map(dt -> mapper.map(dt, DeliveryTourDto.class)).toList());
    }

    @GetMapping("search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the result of the search for delivery tours"
                    )
            }
    )
    public ResponseEntity<SearchResultsDto<DeliveryTourDto>> search(SearchDeliveryTourDto model) {var deliveryTours =  SearchResultsDto.from(deliveryTourService.findAll(model)
                .map(deliveryTour -> {
                    var dto = mapper.map(deliveryTour, DeliveryTourDto.class);
                    dto.setNumberDeliveries(deliveryTour.getDeliveries().size());
                    return dto;
                }));
        return ResponseEntity.ok(deliveryTours);
    }

    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the delivery tour who the requested id"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not delivery tour exist with this id"),
            })
    public ResponseEntity<DeliveryTourDto> getById(@PathVariable UUID id) {
        var deliveryTour = deliveryTourService.findById(id);
        if (deliveryTour == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(deliveryTour, DeliveryTourDto.class));
    }

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Delivery tour is succesfully created"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description =  "endDate or startDate is null or endDate is before startDate or name is null or delivery-person is not valid"
                    )
            }
    )
    public ResponseEntity<DeliveryTourDto> insert(@RequestBody InsertDeliveryTourDto model) {
        var deliveryTour = deliveryTourService.create(model);
        return new ResponseEntity<DeliveryTourDto>(mapper.map(deliveryTour, DeliveryTourDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Delivery tour is succesfully updated"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description =  "Bad request if endDate is before startDate or name is null or delivery-person is not valid"
                    )
            }
    )
    public ResponseEntity<DeliveryTourDto> update(@PathVariable UUID id, @RequestBody InsertDeliveryTourDto model) {
        var deliveryTour = deliveryTourService.findById(id);
        if (deliveryTour == null) {
            return ResponseEntity.notFound().build();
        }
        deliveryTour = deliveryTourService.update(id, model);
        return ResponseEntity.ok(mapper.map(deliveryTour, DeliveryTourDto.class));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Delivery Tour is successfully deleted"
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Not delivery tour exist with this id"
                    )
            }
    )
    public ResponseEntity delete(@PathVariable UUID id) {
        var deliveryTour = deliveryTourService.findById(id);
        if (deliveryTour == null)
            return ResponseEntity.noContent().build();
        deliveryTourService.delete(deliveryTour);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/deliveries")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode =  "200",
                            description = "The list of deliveries for the delivery-tour's id"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "The delivery tour requested not exist"
                    )
            }
    )
    public ResponseEntity<SearchResultsDto<DeliveryDto>> getDeliveriesByDeliveryTourId(@PathVariable UUID id, SearchDeliveryDto model) {
        var deliveryTour = deliveryTourService.findById(id);
        if (deliveryTour == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(SearchResultsDto.from(deliveryService.findAllByDeliveryTour(id, model).map(d -> mapper.map(d, DeliveryDto.class))));
    }
}
