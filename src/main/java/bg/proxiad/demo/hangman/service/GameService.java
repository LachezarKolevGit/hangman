package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;

public interface GameService {

  void start();

  Game getGame(Long id);

  boolean placeChar(Long gameId, Integer postion, Character character);

  void end();

  boolean gameOverCheck(Game game);
}
