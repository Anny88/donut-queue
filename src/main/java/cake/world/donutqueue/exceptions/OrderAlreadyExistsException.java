package cake.world.donutqueue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "An order with the given clientId already exists")
    public class OrderAlreadyExistsException extends RuntimeException {
}
