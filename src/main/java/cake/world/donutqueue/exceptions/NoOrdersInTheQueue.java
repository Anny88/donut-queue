package cake.world.donutqueue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK, reason = "There are no orders in the queue")
    public class NoOrdersInTheQueue extends RuntimeException {
}
