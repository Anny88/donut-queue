package cake.world.donutqueue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "An order with a given clientId not found")
    public class OrderNotFoundException extends RuntimeException {
}
