package cake.world.donutqueue.service;

import cake.world.donutqueue.domain.Delivery;
import cake.world.donutqueue.domain.Order;
import cake.world.donutqueue.exceptions.NoOrdersInTheQueue;
import cake.world.donutqueue.exceptions.OrderAlreadyExistsException;
import cake.world.donutqueue.exceptions.OrderNotFoundException;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class MainService {
    private static final int MAX_PREMIUM_CLIENT_ID = 999;
    private static final int MAX_ORDERS_IN_DELIVERY = 50;

    @Getter
    private static List<Order> orders = new ArrayList<>();
    @Getter
    private static List<Delivery> deliveries = new ArrayList<>();

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

    public static Delivery retrieveFirstDelivery() {
        if (deliveries.size() == 0) {
            throw new NoOrdersInTheQueue();
        }
        Delivery retrievedDelivery = deliveries.get(0);
        deliveries.remove(0);
        orders = orders.subList(retrievedDelivery.getOrders().size(), orders.size());
        createDeliveriesFromOrders();
        return retrievedDelivery;
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
        createDeliveriesFromOrders();
    }

    public static void deleteOrder(short clientId) {
        Order foundOrder = findOrderByClientId(clientId);
        if (foundOrder == null) {
            throw new OrderNotFoundException();
        }
        orders.remove(foundOrder);
        createDeliveriesFromOrders();
    }

    private static void createDeliveriesFromOrders () {
        deliveries = new ArrayList<>();
        int deliveryIndex = 0;
        int donutsSum = 0;
        ArrayList<Order> ordersForDelivery = new ArrayList<>();
        for (Order order : orders){
            int quantity = order.getQuantity();
            if((donutsSum + quantity) > MAX_ORDERS_IN_DELIVERY) {
                deliveries.add(new Delivery(deliveryIndex, ordersForDelivery));
                deliveryIndex ++;
                donutsSum = quantity;
                ordersForDelivery = new ArrayList<>(Collections.singletonList(order));
            } else {
                ordersForDelivery.add(order);
                donutsSum += quantity;
            }
        }
        deliveries.add(new Delivery(deliveryIndex, ordersForDelivery));
    }
}
