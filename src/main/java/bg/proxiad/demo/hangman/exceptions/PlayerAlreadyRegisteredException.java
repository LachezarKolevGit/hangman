package bg.proxiad.demo.hangman.exceptions;

public class PlayerAlreadyRegisteredException extends  RuntimeException{

    public PlayerAlreadyRegisteredException(String message) {
        super(message);
    }
}
