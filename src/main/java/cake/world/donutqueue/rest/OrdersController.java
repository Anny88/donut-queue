package cake.world.donutqueue.rest;

import cake.world.donutqueue.domain.Order;
import cake.world.donutqueue.service.OrdersService;
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
        OrdersService.addOrder(order);
        return new ResponseEntity<>("Your order has been successfully placed!", OK);
    }

     // An endpoint for the client to check his queue position and approximate wait time.
     @GetMapping("/{clientId}")
     public ResponseEntity<Order> getOrderByClientId(@PathVariable short clientId) {
         return new ResponseEntity<>(OrdersService.getOrderByClientId(clientId), OK);
     }

    //  An endpoint which allows his manager to see all entries in the queue with the
    // approximate wait time
    @GetMapping
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(OrdersService.getOrders(), OK);
    }

    // An endpoint to retrieve his next delivery which should be placed in the cart
    @GetMapping("/next")
    public ResponseEntity<Order> getNextOrder() {
        return new ResponseEntity<>(OrdersService.getFirstOrder(), OK);
    }

    //  An endpoint to cancel an order
    @DeleteMapping
    public ResponseEntity<String> deleteOrder(@RequestParam short clientId) {
        OrdersService.deleteOrder(clientId);
        return new ResponseEntity<>("Your order has been successfully deleted!", OK);
    }


}
