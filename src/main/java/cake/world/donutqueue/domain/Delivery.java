package cake.world.donutqueue.domain;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Delivery {

    private int position;
    private ArrayList<Order> orders;
    private int waitTimeInMinutes;

    public Delivery (int index, ArrayList<Order> orders) {
        this.position = index + 1;
        this.orders = orders;
        this.waitTimeInMinutes = (index + 1) * 5;
    }
}
