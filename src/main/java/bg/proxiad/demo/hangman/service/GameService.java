package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.GameCreationBean;
import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.PlayerInput;

import java.util.List;
import java.util.Map;

public interface GameService {

    Game start(GameCreationBean gamecreationBean);

    Game getGame(Long id);

    boolean placeChar(Long gameId, PlayerInput playerInput);

    void markAsFinished(Long id);

    boolean gameWonCheck(Long id);

    boolean gameOverCheck(Long id);

    List<Character> getGuessedCharsByProgress(Long gameId);

    List<Character> getAlphabet();

    List<Character> convertWordToCharList(String word);

    Map<Character, Boolean> getAlphabetWithTriedLetters(List<Character> guessedChars);

    void registerPlayer(Long gameId, Player player);

    List<Character> getAllInvalidChars(Long gameId);

    void registerSecondPlayer(Long gameId, Player secondPlayer);
}
