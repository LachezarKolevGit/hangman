package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.PlayerDTO;
import bg.proxiad.demo.hangman.model.PlayerInput;
import bg.proxiad.demo.hangman.model.TurnOverview;
import bg.proxiad.demo.hangman.service.PlayerService;
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PlayerController {

  private final PlayerService playerService;

  @Autowired
  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @GetMapping("/game")
  public String getGameView(Model model) {
    model.addAttribute("playerInput", new PlayerInput());

    return "game";
  }

  @PostMapping("/game")
  public ModelAndView play(
      @RequestParam(name = "gameId") Long gameId,
      @ModelAttribute PlayerInput playerInput,
      @RequestParam Long playerId) {
    ModelAndView modelAndView = new ModelAndView("turn-result");
    TurnOverview turnOverview = playerService.play(playerId, gameId, playerInput);
    modelAndView.addObject("turnOverview", turnOverview.toString());

    return modelAndView;
  }

  @GetMapping("/new-game")
  public String getNewGame() {
    return "create-game";
  }

  @PostMapping("/new-game")
  public String createNewGame(
      @RequestParam String playerName, @RequestParam String word, Model model) {
    Long gameId = playerService.startGame(playerName, word);
    model.addAttribute("gameId", gameId);

    return "game-succesfully-created";
  }

  @GetMapping("/register")
  public String getRegisterPlayerPage(Model model) {
    model.addAttribute("playerDto", new PlayerDTO());

    return "register-player";
  }

  @PostMapping("/register")
  public void createNewPlayer(@ModelAttribute PlayerDTO playerDTO) {
    playerService.create(playerDTO);
  }
}
