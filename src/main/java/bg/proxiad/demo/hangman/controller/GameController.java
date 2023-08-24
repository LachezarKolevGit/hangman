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

  /*@GetMapping("/game/{id}")
  public String getGame(@PathVariable(name = "id") Long id) {
    gameService.getGame(id);
    return "game";
  }

  @PostMapping("/game")
  public void placeChar(@RequestParam String playerName, @RequestParam String word) {
    gameService.start(playerName, word);
  }*/
}
