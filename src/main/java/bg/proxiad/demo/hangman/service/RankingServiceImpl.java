package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.Rank;
import bg.proxiad.demo.hangman.model.Ranking;
import bg.proxiad.demo.hangman.model.Stats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bg.proxiad.demo.hangman.repository.RankingRepository;

@Service
public class RankingServiceImpl implements RankingService {

    public final static Integer BRONZE_SCORE = 25;

    public final static Integer SILVER_SCORE = 50;

    public final static Integer GOLD_SCORE = 75;

    private final RankingRepository rankingRepository;

    private final StatsService statsService;

    @Autowired
    public RankingServiceImpl(RankingRepository rankingRepository, StatsService statsService) {
        this.rankingRepository = rankingRepository;
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


}
