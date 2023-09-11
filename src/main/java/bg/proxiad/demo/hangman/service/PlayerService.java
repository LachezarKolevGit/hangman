package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.*;

public interface PlayerService {

  Player getPlayer(Long id);

  Player create(PlayerDTO playerDTO);

  void delete(Long id);

  Game startGame(GameCreationBean gameCreationBean);

  TurnOverview play(PlayerInputBean playerInputBean);

  Player getPlayerByName(String name);

  void updateRankOnWin(Player player, Long gameId);

}
