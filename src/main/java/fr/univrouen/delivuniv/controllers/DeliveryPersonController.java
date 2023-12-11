package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
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

@RestController
@RequestMapping("/api/delivery-person")
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
    public ResponseEntity<DeliveryPersonDto> findById(@PathVariable Long id) {
        var deliveryPerson = deliveryPersonService.findById(id);
        if (deliveryPerson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.map(deliveryPerson, DeliveryPersonDto.class));
    }

    @GetMapping
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
                    dto.setNumberTours(deliveryTourService.countAllByDeliveryPerson(dto.getId()));
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

    @PatchMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse
                            (responseCode = "404",
                            description = "The delivery person to be updated does not exist"),
                    @ApiResponse(
                            responseCode = "200",
                            description = "The delivery person is successfully updated")
            })
    public ResponseEntity<DeliveryPersonDto> update(@PathVariable Long id, @RequestBody InsertDeliveryPersonDto model) {
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
    public ResponseEntity delete(@PathVariable Long id) {
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
                            description =  "The delivery person not exisit"
                    )
            }
    )
    public ResponseEntity<List<DeliveryTourDto>> getByDeliveryPerson(@PathVariable Long id) {
        var deliveryPerson = deliveryPersonService.findById(id);
        if (deliveryPerson.isEmpty())
            return ResponseEntity.notFound().build();

        var deliveryTours = deliveryTourService.findBAllyDeliveryPerson(id)
                .stream().map(deliveryTour -> mapper.map(deliveryTour, DeliveryTourDto.class)).toList();

        return ResponseEntity.ok(deliveryTours);
    }

}
