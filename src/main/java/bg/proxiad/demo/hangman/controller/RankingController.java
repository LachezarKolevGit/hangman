package bg.proxiad.demo.hangman.controller;

import java.util.List;
import java.util.stream.Collectors;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.PlayerDTO;
import bg.proxiad.demo.hangman.utils.PlayerMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.service.RankingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/ranks")
public class RankingController {
    private final RankingService rankingService;
    private final PlayerMapper playerMapper;

    @Autowired
    public RankingController(RankingService rankingService, PlayerMapper playerMapper) {
        this.rankingService = rankingService;
        this.playerMapper = playerMapper;
    }

    @Operation(summary = "Get top players all time")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a page containing top players of all time",
                    content = {@Content(mediaType = "application/json",
                            array  = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))})})
    @GetMapping("/leaderboard")
    public List<PlayerDTO> getTopPlayers(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        List<Player> players = rankingService.getTopPlayers(pageIndex, pageSize);
        List<PlayerDTO> playersDTO = players.stream().map(playerMapper::playerToPlayerDTO).collect(Collectors.toList());

        return playersDTO;
    }

    @Operation(summary = "Get top players for last month")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a page containing top players of last month",
                    content = {@Content(mediaType = "application/json",
                            array  = @ArraySchema(schema = @Schema(implementation = PlayerDTO.class)))})})
    @GetMapping("/leaderboard-last-month")
    public List<PlayerDTO> getTopPlayersLastMonth(@RequestParam(defaultValue = "0") int pageIndex, @RequestParam(defaultValue = "10") int pageSize) {
        List<Player> players = rankingService.getTopPlayersLastMonth(pageIndex, pageSize);
        List<PlayerDTO> playersDTO = players.stream().map(playerMapper::playerToPlayerDTO).collect(Collectors.toList());

        return playersDTO;
    }


}
