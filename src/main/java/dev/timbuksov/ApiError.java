package dev.timbuksov;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
@RestControllerAdvice public class ApiError {
 @ExceptionHandler(ResponseStatusException.class)
 @ResponseStatus(HttpStatus.BAD_REQUEST)
 public Map<String,String> handle(ResponseStatusException ex) {return Map.of("error",ex.getReason()==null?"Invalid request":ex.getReason());}
}
