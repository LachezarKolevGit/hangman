package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.Rank;
import bg.proxiad.demo.hangman.model.Ranking;
import bg.proxiad.demo.hangman.model.Stats;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RankingServiceImpl implements RankingService {

  public static final Integer POINTS_ON_WIN = 3;

  public static final Integer BRONZE_SCORE = 25;

  public static final Integer SILVER_SCORE = 50;

  public static final Integer GOLD_SCORE = 75;

  private final StatsService statsService;

  @Autowired
  public RankingServiceImpl(StatsService statsService) {
    this.statsService = statsService;
  }

  public Player evaluateRank(Player player) {
    Ranking ranking = player.getRanking();
    int playerScore = player.getScore();

    if (playerScore > GOLD_SCORE) {
      ranking.setRank(Rank.GOLD);
    } else if (playerScore > SILVER_SCORE) {
      ranking.setRank(Rank.SILVER);
    } else if (playerScore > BRONZE_SCORE) {
      ranking.setRank(Rank.BRONZE);
    }

    return player;
  }

  public Player addBonusPoints(Player player, Stats stats) {
    int score = player.getScore();
    int livesRemaining = stats.getLivesRemaining();
    if (livesRemaining > 0) {
      score += livesRemaining;
      player.setScore(score);
    }

    return player;
  }

  public Player assignPointsToPlayerOnWin(Player player) {
    player.setScore(player.getScore() + POINTS_ON_WIN);

    return player;
  }

  @Override
  public List<Player> getTopPlayers() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Player> getTopPlayersLastMonth() {
    // TODO Auto-generated method stub
    return null;
  }
}
