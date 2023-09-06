package bg.proxiad.demo.hangman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.service.RankingService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0")
public class RankingController {
    private final RankingService rankingService;

    @Autowired
    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping("/leaderboard")  //think twice about the endpoint naming
    public List<Player> getTopPlayers() {
        return rankingService.getTopPlayers();
    }

    @GetMapping("/leaderboard-last-month")
    public List<Player> getTopPlayersLastMonth() {
        return rankingService.getTopPlayersLastMonth();
    }


}
