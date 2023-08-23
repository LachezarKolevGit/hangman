package bg.proxiad.demo.hangman;

public class GameIsFinishedException extends RuntimeException {
    public GameIsFinishedException(String message) {
        super(message);
    }
}
