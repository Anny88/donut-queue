package cake.world.donutqueue.service;

import cake.world.donutqueue.domain.Order;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFactory {
    @Getter
    private static List<Order> ordersQueue = new ArrayList<>();

    public static void addOrder(final Order order) {
        ordersQueue.add(order);
    }
}
