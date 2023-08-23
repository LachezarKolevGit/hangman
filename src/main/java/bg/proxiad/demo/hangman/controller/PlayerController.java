package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.PlayerInput;
import bg.proxiad.demo.hangman.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/play")
    public String getPlayView(Model model) {
        model.addAttribute("playerInput", new PlayerInput());
        model.addAttribute("list", List.of("S", "A", "b"));

        return "game";
    }

    @PostMapping("/play/{id}")
    public void play(@PathVariable(name = "id") Long gameId, @ModelAttribute PlayerInput playerInput, @RequestParam Long playerId) {
        playerService.play(playerId, gameId, playerInput);
    }
}
