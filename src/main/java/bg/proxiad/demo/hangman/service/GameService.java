package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.PlayerInput;

public interface GameService {

  void start(Player player, String word);

  Game getGame(Long id);

  boolean placeChar(Long gameId, PlayerInput playerInput);

  void markAsFinished(Long id);

  boolean gameOverCheck(Long id);

  boolean playerTurn(Long gameId, PlayerInput playerInput);
}
