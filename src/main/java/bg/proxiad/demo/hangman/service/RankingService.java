package bg.proxiad.demo.hangman.service;

import java.util.List;
import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.Ranking;
import bg.proxiad.demo.hangman.model.Stats;

public interface RankingService {
  Ranking getPlayerRank(Long playerId);

  Ranking evaluateRank(Ranking ranking);

  Ranking addBonusPoints(Ranking ranking, Stats stats);

  List<Player> getTopPlayers(int pageIndex, int pageSize);

  List<Player> getTopPlayersLastMonth(int pageIndex, int pageSize);

  Ranking increaseScoreOnWin(Ranking ranking);

  Ranking updateDate(Ranking ranking);
}
