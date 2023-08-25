package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.GameCreationRequest;
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

    // playerService.play(playerId, gameId, playerInput) da priema kato parameter nqkakvo property
    return modelAndView;
  }

  @GetMapping("/new-game")
  public String getNewGame(Model model) {
    model.addAttribute("gameCreationRequest", new GameCreationRequest());
    return "create-game";
  }

  @PostMapping("/new-game")
  public String createNewGame(
      @ModelAttribute GameCreationRequest gameCreationRequest, Model model) {
    // startGame(new GameCreationBean(, word));
    // GameCreationRequest kato param na tozi metod
    // GameCreationBean.playerName   da e s builder
    // GameCreationBean.word
    // GameCreationBean.lives

    Long gameId = playerService.startGame(gameCreationBean);
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
