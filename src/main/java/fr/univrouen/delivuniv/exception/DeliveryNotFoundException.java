package fr.univrouen.delivuniv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "DELIVERY_NOT_FOUND")
public class DeliveryNotFoundException extends RuntimeException{
}
