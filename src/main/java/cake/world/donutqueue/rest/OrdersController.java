package cake.world.donutqueue.rest;

import cake.world.donutqueue.domain.Order;
import cake.world.donutqueue.service.OrderFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
public class OrdersController {

    @PostMapping(path = "/orders")
    public ResponseEntity<String> postOrders(@Valid @RequestBody Order order) {
        OrderFactory.addOrder(order);
        return new ResponseEntity<>("Order successfully placed!", OK);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(OrderFactory.getOrdersQueue(), OK);
    }

}
