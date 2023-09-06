package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.*;
import bg.proxiad.demo.hangman.service.GameService;
import bg.proxiad.demo.hangman.service.PlayerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@RestController
@RequestMapping("/v1.0")
public class PlayerController {
    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/play/{id}")
    public TurnOverview play(@PathVariable(name = "id") Long gameId, PlayerInput playerInput) {
        Player player = playerService.getPlayer(playerInput.getPlayerId());
        TurnOverview turnOverview = playerService.play(player, gameId, playerInput);

        return turnOverview;
    }

    @PostMapping("/register")
    public Long registerPlayer(PlayerDTO playerDTO) {
        Player player = playerService.create(playerDTO);

        return player.getId();
    }

    @PostMapping("/login")
    public Long loginPlayer(@ModelAttribute PlayerDTO playerDTO) {
        Player player = playerService.getPlayerByName(playerDTO.getName());
        //use cookie to store authentication
        return player.getId();
    }

    /*@GetMapping("/login")
    public String loginPlayer(Model model) {
        model.addAttribute("playerDto", new PlayerDTO());

        return "login-player";
    }*/

    /*public boolean checkSessionAttributePlayerNotNull(HttpSession session) { // use AspectJ to redirect
        if (session.getAttribute("player") == null) {
            return false;
        }

        return true;
    }*/

      /* @GetMapping("/register")
    public String registerPlayer(Model model) {
        model.addAttribute("playerDto", new PlayerDTO());

        return "register-player";
    }*/

}
