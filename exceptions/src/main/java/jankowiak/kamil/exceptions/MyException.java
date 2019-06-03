package jankowiak.kamil.exceptions;


public class MyException extends RuntimeException {
    private String message;

    public MyException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

