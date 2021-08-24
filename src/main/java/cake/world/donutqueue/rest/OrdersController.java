package cake.world.donutqueue.rest;

import cake.world.donutqueue.domain.ClientResponse;
import cake.world.donutqueue.domain.Order;
import cake.world.donutqueue.service.MainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(value = "/orders")
public class OrdersController {

    // An endpoint for adding items to the queue
    @PostMapping
    public ResponseEntity<String> postOrder(@Valid @RequestBody Order order) {
        MainService.addOrder(order);
        return new ResponseEntity<>("Your order has been successfully placed!", OK);
    }

     // An endpoint for the client to check his queue position and approximate wait time.
     @GetMapping("/{clientId}")
     public ResponseEntity<ClientResponse> getOrderByClientId(@PathVariable short clientId) {
         return new ResponseEntity<>(MainService.getClientResponseByClientId(clientId), OK);
     }

    //  An endpoint to cancel an order
    @DeleteMapping
    public ResponseEntity<String> deleteOrder(@RequestParam short clientId) {
        MainService.deleteOrder(clientId);
        return new ResponseEntity<>("Your order has been successfully deleted!", OK);
    }


}
