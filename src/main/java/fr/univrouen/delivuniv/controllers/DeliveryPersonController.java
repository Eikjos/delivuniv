package fr.univrouen.delivuniv.controllers;

import fr.univrouen.delivuniv.services.DeliveryPersonService;
import fr.univrouen.delivuniv.dto.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchResultsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery-person")
public class DeliveryPersonController {

    private DeliveryPersonService deliveryPersonService;

    public DeliveryPersonController(DeliveryPersonService deliveryPersonServiceservice) {
        this.deliveryPersonService = deliveryPersonServiceservice;
    }

    @GetMapping("{id}")
    public ResponseEntity<DeliveryPersonDto> getByID(@PathVariable Long id) {
        var deliveryPerson = deliveryPersonService.getById(id);
        if (deliveryPerson == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveryPerson);
    }

    @GetMapping("/search")
    public ResponseEntity<SearchResultsDto<DeliveryPersonDto>> search(SearchDeliveryPersonDto model) {
        return ResponseEntity.ok(deliveryPersonService.search(model));
    }

    @PostMapping
    public ResponseEntity<DeliveryPersonDto> insert(@RequestBody InsertDeliveryPersonDto model) {
        return ResponseEntity.ok(deliveryPersonService.insert(model));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeliveryPersonDto> update(@PathVariable Long id, @RequestBody InsertDeliveryPersonDto model) {
        if (deliveryPersonService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(deliveryPersonService.update(id, model));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity Delete(@PathVariable Long id) {
        var deliveryPerson = deliveryPersonService.getById(id);
        if (deliveryPersonService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        deliveryPersonService.delete(deliveryPerson);
        return ResponseEntity.ok(null);
    }

}
