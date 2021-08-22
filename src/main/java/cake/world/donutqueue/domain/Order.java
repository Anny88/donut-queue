package cake.world.donutqueue.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
@Builder
public class Order {

    @NotNull
    @Min(value = 1, message = "A minimum clientId is 1")
    @Max(value = 20000, message = "A maximum clientId is 20000")
    private short clientId;

    @NotNull
    @Min(value = 1, message = "At least one donut is needed to place the order")
    @Max(value = 50, message = "The maximum size of the order is 50 donuts")
    private byte quantity;
}
