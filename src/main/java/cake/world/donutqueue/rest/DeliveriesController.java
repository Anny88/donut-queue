package cake.world.donutqueue.rest;

import cake.world.donutqueue.domain.Delivery;
import cake.world.donutqueue.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/deliveries")
public class DeliveriesController {

    //  An endpoint which allows his manager to see all entries in the queue with the
    // approximate wait time
    @GetMapping
    public ResponseEntity<List<Delivery>> getDeliveries() {
        return new ResponseEntity<>(MainService.getDeliveries(), OK);
    }

    // An endpoint to retrieve his next delivery which should be placed in the cart
    @GetMapping("/next")
    public ResponseEntity<Delivery> getNextDelivery() {
        return new ResponseEntity<>(MainService.retrieveFirstDelivery(), OK);
    }
}
