package fr.univrouen.delivuniv.deliveryperson;

import java.util.List;

import fr.univrouen.delivuniv.dto.DeliveryPersonDto;
import fr.univrouen.delivuniv.dto.InsertDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchDeliveryPersonDto;
import fr.univrouen.delivuniv.dto.SearchResultsDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/delivery-person")
public class DeliveryPersonController {

    private DeliveryPersonService deliveryPersonService;

    public DeliveryPersonController(DeliveryPersonService deliveryPersonServiceservice) {
        this.deliveryPersonService = deliveryPersonServiceservice;
    }

    @GetMapping("search")
    public SearchResultsDto<DeliveryPersonDto> search(SearchDeliveryPersonDto model) {
        return deliveryPersonService.search(model);
    }

    @PostMapping
    public DeliveryPersonDto insert(@RequestBody InsertDeliveryPersonDto model) {
        return deliveryPersonService.insert(model);
    }

    @PatchMapping("/{id}")
    public DeliveryPersonDto update(@PathVariable Long id, @RequestBody InsertDeliveryPersonDto model) {
        return deliveryPersonService.update(id, model);
    }

    @DeleteMapping("/{id}")
    public void Delete(@PathVariable Long id) {
        deliveryPersonService.delete(id);
    }

}
