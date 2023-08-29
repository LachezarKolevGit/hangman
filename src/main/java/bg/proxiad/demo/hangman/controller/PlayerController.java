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

@Controller
public class PlayerController {
    public static final String NOT_LOGGED_IN_USER_EXCEPTION_MESSAGE = "Please log in";

    private final PlayerService playerService;

    private final GameService gameService;

    @Autowired
    public PlayerController(PlayerService playerService, GameService gameService) {
        this.playerService = playerService;
        this.gameService = gameService;
    }

    @GetMapping("/play/{id}")
    public String getGame(@PathVariable(name = "id") Long gameId, Model model, HttpSession session, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (session.getAttribute("player") == null) {
            redirectAttributes.addAttribute("errorMessage", NOT_LOGGED_IN_USER_EXCEPTION_MESSAGE);
            redirectAttributes.addAttribute("link", request.getRequestURL());

            return "redirect:/login";
        }

        Player currentlyLoggedInPlayer = (Player) session.getAttribute("player");

        gameService.registerSecondPlayer(gameId, currentlyLoggedInPlayer); //not working properly
        List<Character> guessedChars = gameService.getGuessedCharsByProgress(gameId);
        List<Character> allInvalidChars = gameService.getAllInvalidChars(gameId);
        Map<Character, Boolean> alphabetWithTriedLetters = gameService.getAlphabetWithTriedLetters(allInvalidChars);

        Game game = gameService.getGame(gameId);
        model.addAttribute("game", gameService.getGame(gameId));
        model.addAttribute("currentPlayer", currentlyLoggedInPlayer);
        model.addAttribute("guessedChars", guessedChars);
        model.addAttribute("alphabet", alphabetWithTriedLetters);
        //model.addAttribute("lives", game.getStats().getLivesRemaining());
        model.addAttribute("playerInput", new PlayerInput());

        return "active-game";
    }

    @PostMapping("/play/{id}")
    public ModelAndView play(
            @PathVariable(name = "id") Long gameId,
            @ModelAttribute PlayerInput playerInput, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("redirect:/play/" + gameId);

        Player player = (Player) session.getAttribute("player");
        TurnOverview turnOverview = playerService.play(player, gameId, playerInput);

        return modelAndView;
    }

    @GetMapping("/new-game")
    public String getNewGame(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("player") == null) {
            redirectAttributes.addAttribute("errorMessage", NOT_LOGGED_IN_USER_EXCEPTION_MESSAGE);
            return "redirect:/login";
        }

        model.addAttribute("gameCreationRequest", new GameCreationRequest());
        return "create-game";
    }

    @PostMapping("/new-game")
    public ModelAndView createNewGame(
            @ModelAttribute GameCreationRequest gameCreationRequest, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("player") == null) {
            redirectAttributes.addAttribute("errorMessage", NOT_LOGGED_IN_USER_EXCEPTION_MESSAGE);
            return new ModelAndView("redirect:/login");
        }

        GameCreationBeanBuilder builder = new GameCreationBeanBuilder();
        GameCreationBean gameCreationBean = builder
                .creator((Player) session.getAttribute("player"))
                .word(gameCreationRequest.getWord())
                .lives(gameCreationRequest.getLives())
                .build();

        System.out.println(gameCreationBean);

        Game game = playerService.startGame(gameCreationBean);
        session.setAttribute("game", game);

        return new ModelAndView("game-successfully-created", "gameId", game.getId());
    }

    @GetMapping("/register")
    public String registerPlayer(Model model) {
        model.addAttribute("playerDto", new PlayerDTO());

        return "register-player";
    }

    @PostMapping("/register")
    public String registerPlayer(@ModelAttribute PlayerDTO playerDTO) {  //research
        Player player = playerService.create(playerDTO);

        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginPlayer(Model model) {
        model.addAttribute("playerDto", new PlayerDTO());

        return "login-player";
    }

    @PostMapping("/login")
    public String loginPlayer(@ModelAttribute PlayerDTO playerDTO, HttpSession session) {  //research
        Player player = playerService.getPlayerByName(playerDTO.getName());

        session.setAttribute("player", player);
        return "redirect:/";
    }

    public boolean checkSessionAttributePlayerNotNull(HttpSession session) {
        if (session.getAttribute("player") == null) {
            return false;
        }

        return true;
    }


}
