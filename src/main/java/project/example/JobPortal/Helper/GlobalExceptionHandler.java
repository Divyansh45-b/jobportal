package project.example.JobPortal.Helper;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<String> ResourceNotFoundException(ResourceNotFound  ex)
    {

       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
