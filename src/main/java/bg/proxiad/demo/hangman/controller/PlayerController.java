package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.exceptions.Error;
import bg.proxiad.demo.hangman.model.*;
import bg.proxiad.demo.hangman.service.PlayerService;
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

    @Autowired
    public PlayerController(PlayerService playerService, PlayerMapper mapper) {
        this.playerService = playerService;
        this.mapper = mapper;
    }

    @Operation(summary = "Register a new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player successfully registered",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDTO.class))}),
            @ApiResponse(responseCode = "400", description = "There is already a player registered with the same name",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = Error.class)))})
    @PostMapping("/register")
    public PlayerDTO registerPlayer(PlayerDTO playerDTO) {
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
    public PlayerDTO loginPlayer(PlayerDTO playerDTO) {
        Player player = playerService.getPlayerByName(playerDTO.getName());

        return mapper.playerToPlayerDTO(player);
    }

    //think of a better representation for post to play

    @Operation(summary = "Make a guess")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player's guess is processed successfully",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = PlayerDTO.class))})})
     @PostMapping("/play")
    public TurnOverview play(PlayerGuessRequest playerGuessRequest) {
        PlayerInputBean playerInputBean = new PlayerInputBean(playerGuessRequest);
        TurnOverview turnOverview = playerService.play(playerInputBean);

        return turnOverview;
    }

}
