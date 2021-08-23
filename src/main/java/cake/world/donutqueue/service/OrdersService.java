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
public class OrdersService {
    private static int MAX_PREMIUM_CLIENT_ID = 999;

    @Getter
    private static List<Order> orders = new ArrayList<>();


    private static Order findOrderByClientId(short clientId) {
        return orders.stream()
                .filter(order -> clientId == order.getClientId())
                .findFirst()
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
        if (orders.size() == 0) {
            throw new NoOrdersInTheQueue();
        }
        return orders.get(0);
    }

    public static void addOrder(Order order) {
        if (findOrderByClientId(order.getClientId()) != null) {
            throw new OrderAlreadyExistsException();
        }
        if (order.getClientId() <= MAX_PREMIUM_CLIENT_ID) {
            int premiumOrdersCount = (int) orders
                    .stream()
                    .filter((tmpOrder) -> tmpOrder.getClientId() <= MAX_PREMIUM_CLIENT_ID)
                    .count();
            orders.add(premiumOrdersCount, order);
        } else {
            orders.add(order);
        }
    }

    public static void deleteOrder(short clientId) {
        Order foundOrder = findOrderByClientId(clientId);
        if (foundOrder != null) {
            orders.remove(foundOrder);
        } else {
            throw new OrderNotFoundException();
        }
    }
}
