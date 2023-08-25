package bg.proxiad.demo.hangman.exceptions;

public class GameIsFinishedException extends RuntimeException {
    public GameIsFinishedException(String message) {
        super(message);
    }
}
