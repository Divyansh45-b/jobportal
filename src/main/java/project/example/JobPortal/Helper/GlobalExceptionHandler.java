package project.example.JobPortal.Helper;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import project.example.JobPortal.Response.ErrorResponse;

import java.time.LocalDate;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFound e)
    {

        //making object of error response and passing it as a constructor
        ErrorResponse errorResponse = new ErrorResponse(    LocalDate.now(),
                                                            HttpStatus.NOT_FOUND.value(),
                                                            HttpStatus.NOT_FOUND.getReasonPhrase(),
                                                            e.getMessage()
                                                        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
