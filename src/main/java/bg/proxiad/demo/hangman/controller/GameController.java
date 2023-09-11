package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.exceptions.Error;
import bg.proxiad.demo.hangman.model.*;
import bg.proxiad.demo.hangman.service.PlayerService;
import bg.proxiad.demo.hangman.utils.GameMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import bg.proxiad.demo.hangman.service.GameService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1.0/game-library")
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

    @Operation(summary = "Get a game by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "Non-existing id supplied",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @GetMapping("/game/{id}")
    public GameDTO getGame(@PathVariable(name = "id") Long gameId) {
        Game game = gameService.getGame(gameId);
        GameDTO gameDTO = gameMapper.gameToGameDTO(game);

        return gameDTO;
    }

    @Operation(summary = "Create a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Game successfully created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = GameDTO.class)) }),
            @ApiResponse(responseCode = "400", description = "",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping("/game")
    public GameDTO createGame(@RequestBody GameCreationRequest gameCreationRequest) {
        System.out.println(gameCreationRequest);
        Player creator = playerService.getPlayerByName(gameCreationRequest.getCreatorUsername());

        GameCreationBeanBuilder builder = new GameCreationBeanBuilder();
        GameCreationBean gameCreationBean = builder
                .creator(creator)
                .word(gameCreationRequest.getWord())
                .lives(gameCreationRequest.getLives())
                .build();
        Game game = gameService.create(gameCreationBean);

       return gameMapper.gameToGameDTO(game);
    }

    @Operation(summary = "Get list of games")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List games",
                    content = { @Content(mediaType = "application/json",
                            array  = @ArraySchema(schema = @Schema(implementation = GameDTO.class))) })})
    @GetMapping("/games")
    public List<GameDTO> getGames() {
        return gameService.getAllGames().stream().map(gameMapper::gameToGameDTO).collect(Collectors.toList());
    }
}
