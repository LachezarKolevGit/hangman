package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.PlayerInput;

public interface PlayerService {

   Player getPlayer(Long id);
   void create(String name);
   void delete(Long id);
   void startGame(Long playerId, String word);
   void play(Long playerId, Long gameId, PlayerInput playerInput);
}
