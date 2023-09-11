package bg.proxiad.demo.hangman.exceptions;

import lombok.NoArgsConstructor;

public class InvalidPlayerException extends RuntimeException {
    public InvalidPlayerException(String message) {
        super(message);
    }
}
