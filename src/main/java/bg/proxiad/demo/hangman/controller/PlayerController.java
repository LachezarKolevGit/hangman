package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.exceptions.Error;
import bg.proxiad.demo.hangman.model.*;
import bg.proxiad.demo.hangman.service.GameService;
import bg.proxiad.demo.hangman.service.PlayerService;
import bg.proxiad.demo.hangman.utils.GameMapper;
import bg.proxiad.demo.hangman.utils.PlayerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1.0/players")
public class PlayerController {
    private final PlayerService playerService;
    private final PlayerMapper mapper;
    private final GameService gameService;
    private final GameMapper gameMapper;

    @Autowired
    public PlayerController(PlayerService playerService, PlayerMapper mapper, GameService gameService, GameMapper gameMapper) {
        this.playerService = playerService;
        this.mapper = mapper;
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @Operation(summary = "Register a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player successfully registered",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "There is already a player registered with the same name",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping("/register")
    public PlayerDTO registerPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = playerService.create(playerDTO);

        return mapper.playerToPlayerDTO(player);
    }

    @Operation(summary = "Login player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player successfully logged in",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid player name",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping("/login")
    public PlayerDTO loginPlayer(@RequestBody PlayerDTO playerDTO) {
        Player player = playerService.getPlayerByName(playerDTO.getName());

        return mapper.playerToPlayerDTO(player);
    }

    @Operation(summary = "Make a guess")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player's guess is processed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = TurnResultDetails.class))}),
            @ApiResponse(responseCode = "400", description = "Player's identity is invalid",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Error.class))})})
    @PostMapping("/{gameId}/play")
    public TurnResultDetails play(@PathVariable Long gameId, @RequestBody PlayerGuessRequest playerGuessRequest) {
        PlayerInputBean playerInputBean = PlayerInputBean.builder()
                .playerName(playerGuessRequest.getPlayerName())
                .character(playerGuessRequest.getCharacter())
                .gameId(gameId)
                .build();

        TurnOverview turnOverview = playerService.play(playerInputBean);
        Game game = gameService.getGame(gameId);
        GameDTO gameDTO = gameMapper.gameToGameDTO(game);

        return new TurnResultDetails(turnOverview, gameDTO);
    }

}
