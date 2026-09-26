package project.example.JobPortal.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private LocalDate timestamp;
    private int status;
    private String error;
    private String message;

}
