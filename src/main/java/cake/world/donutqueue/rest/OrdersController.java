package cake.world.donutqueue.rest;

import cake.world.donutqueue.domain.Order;
import cake.world.donutqueue.service.OrderFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrdersController {

    // An endpoint for adding items to the queue
    @PostMapping(path = "/orders")
    public ResponseEntity<String> postOrder(@Valid @RequestBody Order order) {
        OrderFactory.addOrder(order);
        return new ResponseEntity<>("Your order has been successfully placed!", OK);
    }
     // An endpoint for the client to check his queue position and approximate wait time.
    //  An endpoint which allows his manager to see all entries in the queue with the
    // approximate wait time
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(OrderFactory.getOrdersQueue(), OK);
    }
    // An endpoint to retrieve his next delivery which should be placed in the cart
    //  An endpoint to cancel an order
    @DeleteMapping("/orders")
    public ResponseEntity<String> deleteOrder(@RequestParam short clientId) {
        OrderFactory.deleteOrder(clientId);
        return new ResponseEntity<>("Your order has been successfully deleted!", OK);
    }


}
