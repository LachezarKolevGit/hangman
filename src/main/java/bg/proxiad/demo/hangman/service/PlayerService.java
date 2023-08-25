package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.PlayerDTO;
import bg.proxiad.demo.hangman.model.PlayerInput;
import bg.proxiad.demo.hangman.model.TurnOverview;

public interface PlayerService {

  Player getPlayer(Long id);

  void create(PlayerDTO playerDTO);

  void delete(Long id);

  void startGame(Long playerId, String word);

  Long startGame(String playerName, String word);

  TurnOverview play(Long playerId, Long gameId, PlayerInput playerInput);

  Player getPlayerByName(String name);

  void updateRankOnWin(Player player, Long gameId);
}
