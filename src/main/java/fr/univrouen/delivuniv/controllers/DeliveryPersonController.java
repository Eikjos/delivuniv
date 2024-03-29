package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
import fr.univrouen.delivuniv.dto.deliveryTour.SearchDeliveryTourDto;
import fr.univrouen.delivuniv.services.DeliveryPersonService;
import fr.univrouen.delivuniv.dto.deliveryPerson.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchResultsDto;
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
@RequestMapping("/api/delivery-persons")
@AllArgsConstructor
@Tag(name = "Delivery Persons routes", description = "All related routes of delivery persons")
public class DeliveryPersonController {

    private final DeliveryPersonService deliveryPersonService;
    private final DeliveryTourService deliveryTourService;
    private final ModelMapper mapper;

    @GetMapping("{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the delivery person who the requested id"),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not delivery person exist with this id"),
            })
    public ResponseEntity<DeliveryPersonDto> findById(@PathVariable UUID id) {
        var deliveryPerson = deliveryPersonService.findById(id).orElse(null);
        if (deliveryPerson == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.map(deliveryPerson, DeliveryPersonDto.class);
        var numberDeliveries = 0;
        for (var deliveryTour : deliveryPerson.getDeliveryTours()) {
            numberDeliveries += deliveryTour.getDeliveries().size();
        }
        dto.setNumberDeliveries(numberDeliveries);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "List of delivery person"
                    )
            }
    )
    public ResponseEntity<List<DeliveryPersonDto>> findAll() {
        return ResponseEntity.ok(deliveryPersonService.findAll().stream()
                .map(deliveryPerson -> mapper.map(deliveryPerson, DeliveryPersonDto.class)).toList());
    }

    @GetMapping("/search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the result of the search for delivery persons "),
            })
    public ResponseEntity<SearchResultsDto<DeliveryPersonDto>> search(SearchDeliveryPersonDto model) {
        return ResponseEntity.ok(SearchResultsDto.from(deliveryPersonService.search(model)
                .map(deliveryPerson -> {
                    var dto = mapper.map(deliveryPerson, DeliveryPersonDto.class);
                    var numberDeliveries = 0;
                    var numberDeliveryTours = 0;
                    for (var deliveryTour : deliveryPerson.getDeliveryTours()) {
                        ++numberDeliveryTours;
                        numberDeliveries += deliveryTour.getDeliveries().size();
                    }
                    dto.setNumberDeliveries(numberDeliveries);
                    dto.setNumberTours(numberDeliveryTours);
                    return dto;
                })));
    }

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The delivery person is successfully created"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Name is null or Available is null"
                    )
            })
    public ResponseEntity<DeliveryPersonDto> insert(@RequestBody InsertDeliveryPersonDto model) {
        return new ResponseEntity<>(mapper.map(deliveryPersonService.insert(model), DeliveryPersonDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse
                            (responseCode = "404",
                            description = "The delivery person to be updated does not exist"),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The delivery person is successfully updated"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Name is null or Available is null"
                    )
            })
    public ResponseEntity<DeliveryPersonDto> update(@PathVariable UUID id, @RequestBody InsertDeliveryPersonDto model) {
        if (deliveryPersonService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(deliveryPersonService.update(id, model), DeliveryPersonDto.class));
    }

    @DeleteMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse
                            (responseCode = "204",
                                    description = "The delivery person to be deleted does not exist"),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The delivery person is successfully deleted")
            })
    public ResponseEntity delete(@PathVariable UUID id) {
        var deliveryPerson = deliveryPersonService.findById(id);
        if (deliveryPerson == null) {
            return ResponseEntity.noContent().build();
        }
        deliveryPersonService.delete(deliveryPerson.get());
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}/delivery-tours")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "The list of delivery tour for a delivery person"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description =  "The delivery person not exist"
                    )
            }
    )
    public ResponseEntity<SearchResultsDto<DeliveryTourDto>> getByDeliveryPerson(@PathVariable UUID id, SearchDeliveryTourDto model) {
        var deliveryPerson = deliveryPersonService.findById(id);
        if (deliveryPerson.isEmpty())
            return ResponseEntity.notFound().build();

        var deliveryTours = SearchResultsDto.from(deliveryTourService.findAllByDeliveryPerson(id, model)
                .map(deliveryTour -> {
                    var dto = mapper.map(deliveryTour, DeliveryTourDto.class);
                    dto.setNumberDeliveries(deliveryTour.getDeliveries().size());
                    return dto;
                }));

        return ResponseEntity.ok(deliveryTours);
    }

}
