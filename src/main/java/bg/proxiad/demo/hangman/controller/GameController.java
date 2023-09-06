package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.*;
import bg.proxiad.demo.hangman.service.PlayerService;
import bg.proxiad.demo.hangman.utils.GameMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bg.proxiad.demo.hangman.service.GameService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1.0")
public class GameController {

    private final GameService gameService;
    private final PlayerService playerService;
    private final GameMapper gameMapper;

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.gameMapper = gameMapper;
    }

    @GetMapping("/games")
    public List<GameDTO> getGames() {
        return gameService.getAllGames().stream().map(gameMapper::gameToGameDTO).collect(Collectors.toList());
    }

    @GetMapping("/game/{id}")
    public GameDTO getGame(@PathVariable(name = "id") Long gameId) {
        Game game = gameService.getGame(gameId);
        GameDTO gameDTO = gameMapper.gameToGameDTO(game);

        return gameDTO;
    }

    @PostMapping("/game")
    public GameDTO createGame(GameCreationRequest gameCreationRequest, HttpSession session) {
        Player creator = playerService.getPlayer(gameCreationRequest.getCreatorId());

        GameCreationBeanBuilder builder = new GameCreationBeanBuilder();
        GameCreationBean gameCreationBean = builder
                .creator(creator)
                .word(gameCreationRequest.getWord())
                .lives(gameCreationRequest.getLives())
                .build();
        Game game = gameService.create(gameCreationBean);

       return gameMapper.gameToGameDTO(game);
    }
}
