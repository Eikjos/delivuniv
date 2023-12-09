package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.dto.deliveryTour.DeliveryTourDto;
import fr.univrouen.delivuniv.services.DeliveryTourService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-tour")
@AllArgsConstructor
@Tag(name = "Delivery tours routes", description = "All related routes of delivery tours")
public class DeliveryTourController {

    private DeliveryTourService deliveryTourService;


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
    public ResponseEntity<DeliveryTourDto> getById(@PathVariable Long id) {
        var deliveryTour = deliveryTourService.getById(id);
        if (deliveryTour == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveryTour);
    }

    @PostMapping
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Delivery tour is succesfully created"
                    )
            }
    )
    public ResponseEntity<DeliveryTourDto> insert(@RequestBody DeliveryTourDto model) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
