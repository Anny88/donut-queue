package cake.world.donutqueue.service;

import cake.world.donutqueue.domain.Order;
import cake.world.donutqueue.exceptions.NoOrdersInTheQueue;
import cake.world.donutqueue.exceptions.OrderAlreadyExistsException;
import cake.world.donutqueue.exceptions.OrderNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderFactory {
    @Getter
    private static List<Order> ordersQueue = new ArrayList<>();

    private static Order findOrderByClientId(short clientId) {
        return ordersQueue.stream()
                .filter(order -> clientId == order.getClientId())
                .findAny()
                .orElse(null);
    }

    public static Order getOrderByClientId(short clientId) {
        Order order = findOrderByClientId(clientId);
        if (order == null) {
            throw new OrderNotFoundException();
        }
        return order;
    }

    public static Order getFirstOrder() {
        if (ordersQueue.size() == 0) {
            throw new NoOrdersInTheQueue();
        }
        return ordersQueue.get(0);
    }

    public static void addOrder(Order order) {
        if (findOrderByClientId(order.getClientId()) == null) {
            ordersQueue.add(order);
        } else {
            throw new OrderAlreadyExistsException();
        }
    }

    public static void deleteOrder(short clientId) {
        Order foundOrder = findOrderByClientId(clientId);
        if (foundOrder != null) {
            ordersQueue.remove(foundOrder);
        } else {
            throw new OrderNotFoundException();
        }
    }
}
