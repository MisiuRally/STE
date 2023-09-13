package STE.domain.exeption;

public class NotFoundException extends RuntimeException{

    public NotFoundException(final String message) {
        super(message);
    }
}
