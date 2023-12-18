package fr.univrouen.delivuniv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "DELIVERY_PERSON_NOT_FOUND")
public class DeliveryPersonNotFoundException extends RuntimeException {
}
