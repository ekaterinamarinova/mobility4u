package exception;

import java.io.IOException;

public class InvalidPathException extends IOException {
    public InvalidPathException(String message) {
        super(message);
    }
}
