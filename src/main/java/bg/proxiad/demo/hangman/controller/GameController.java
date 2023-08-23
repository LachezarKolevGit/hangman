package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.PlayerInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import bg.proxiad.demo.hangman.service.GameService;

@Controller
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/game")
  public String getGame(@PathVariable(name = "id") Long id) {
    gameService.getGame(id);
    return "game";
  }

  /*@PostMapping("/game/{id}")
  public void placeChar(@PathVariable(name = "id") Long gameId, @ModelAttribute PlayerInput playerInput, @RequestParam Long playerId) {
    gameService.playerTurn(gameId, playerInput);


  }*/
}
