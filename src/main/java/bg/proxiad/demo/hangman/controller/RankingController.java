package bg.proxiad.demo.hangman.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.service.RankingService;

@Controller
public class RankingController {
  private final RankingService rankingService;

  @Autowired
  public RankingController(RankingService rankingService) {
    this.rankingService = rankingService;
  }

  @GetMapping("/")
  public String getTopPlayers(Model model) {
    List<Player> playersAllTime = rankingService.getTopPlayers();
    List<Player> playersLastMonth = rankingService.getTopPlayersLastMonth();

    model.addAttribute("playersAllTime", playersAllTime);
    model.addAttribute("playersLastMonth", playersLastMonth);

    return "ranking-page";
  }
}
