package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.services.DeliveryPersonService;
import fr.univrouen.delivuniv.dto.deliveryPerson.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.deliveryPerson.SearchResultsDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-person")
@Tag(name = "Delivery Persons routes", description = "All related routes of delivery persons")
public class DeliveryPersonController {

    private DeliveryPersonService deliveryPersonService;

    public DeliveryPersonController(DeliveryPersonService deliveryPersonServiceservice) {
        this.deliveryPersonService = deliveryPersonServiceservice;
    }

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
    public ResponseEntity<DeliveryPersonDto> getByID(@PathVariable Long id) {
        var deliveryPerson = deliveryPersonService.getById(id);
        if (deliveryPerson == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveryPerson);
    }

    @GetMapping("/search")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Return the result of the search for delivery persons "),
            })
    public ResponseEntity<SearchResultsDto<DeliveryPersonDto>> search(SearchDeliveryPersonDto model) {
        return ResponseEntity.ok(deliveryPersonService.search(model));
    }

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "The delivery person is successfully created"),
            })
    public ResponseEntity<DeliveryPersonDto> insert(@RequestBody InsertDeliveryPersonDto model) {
        try {
            return new ResponseEntity<>(deliveryPersonService.insert(model), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.ok(null);
        }
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
        if (deliveryPersonService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveryPersonService.update(id, model));
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
    public ResponseEntity Delete(@PathVariable Long id) {
        var deliveryPerson = deliveryPersonService.getById(id);
        if (deliveryPersonService.getById(id) == null) {
            return ResponseEntity.noContent().build();
        }
        deliveryPersonService.delete(deliveryPerson);
        return ResponseEntity.ok(null);
    }

}
