package project.example.JobPortal.Helper;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(String message)
    {
        super(message);
    }
}
