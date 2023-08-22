package bg.proxiad.demo.hangman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import bg.proxiad.demo.hangman.service.GameService;

@Controller
public class GameController {

  private final GameService gameService;

  @Autowired
  public GameController(GameService gameService) {
    this.gameService = gameService;
  }

  @GetMapping("/game/{id}")
  public String getGame(@PathVariable(name = "id") Long id) {
    gameService.getGame(id);
    return "game";
  }

  @PostMapping("/game/{id}")
  public void placeChar(
      @PathVariable(name = "id") String symbol, @RequestParam Character character) {}
}
