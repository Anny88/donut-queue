package cake.world.donutqueue.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientResponse {
    private int position;
    private int waitTimeInMinutes;
}
