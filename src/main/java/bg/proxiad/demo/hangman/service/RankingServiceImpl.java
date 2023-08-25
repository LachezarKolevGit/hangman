package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.Rank;
import bg.proxiad.demo.hangman.model.Ranking;
import bg.proxiad.demo.hangman.model.Stats;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import bg.proxiad.demo.hangman.repository.RankingRepository;
import bg.proxiad.demo.hangman.repository.RankingSpecification;
import bg.proxiad.demo.hangman.repository.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class RankingServiceImpl implements RankingService {

    public static final Integer POINTS_ON_WIN = 3;

    public static final Integer BRONZE_SCORE = 25;

    public static final Integer SILVER_SCORE = 50;

    public static final Integer GOLD_SCORE = 75;

    private final StatsService statsService;

    private final RankingRepository rankingRepository;

    @Value("${ranking.service.page-size}")
    private int pageSize;

    @Autowired
    public RankingServiceImpl(StatsService statsService, RankingRepository rankingRepository) {
        this.statsService = statsService;
        this.rankingRepository = rankingRepository;
    }

    public Ranking evaluateRank(Ranking ranking) {
        int playerScore = ranking.getScore();

        if (playerScore > GOLD_SCORE) {
            ranking.setRank(Rank.GOLD);
        } else if (playerScore > SILVER_SCORE) {
            ranking.setRank(Rank.SILVER);
        } else if (playerScore > BRONZE_SCORE) {
            ranking.setRank(Rank.BRONZE);
        }

        return ranking;
    }

    public Ranking addBonusPoints(Ranking ranking, Stats stats) {
        int score = ranking.getScore();
        int livesRemaining = stats.getLivesRemaining();
        if (livesRemaining > 0) {
            score += livesRemaining;
            ranking.setScore(score);
        }

        return ranking;
    }

    public Ranking increaseScoreOnWin(Ranking ranking) {
        int score = ranking.getScore();
        ranking.setScore(score + POINTS_ON_WIN);

        return ranking;
    }

    public Ranking updateDate(Ranking ranking) {
        ranking.setLastChange(LocalDate.now());
        return ranking;
    }

    @Override
    public List<Player> getTopPlayers() {
        List<Ranking> rankings = rankingRepository.findTop10ByOrderByScoreDesc();
        List<Player> players = rankings.stream().map(Ranking::getPlayer).collect(Collectors.toList());

        return players;
    }

    @Override
    public List<Player> getTopPlayersLastMonth() {
        RankingSpecification spec1 =
                new RankingSpecification(new SearchCriteria("lastChange", ">", LocalDate.now().minusMonths(1)));

        List<Ranking> results = rankingRepository.findAll(Specification.where(spec1));
        //List<Ranking> rankings = rankingRepository.findTop10ByScoreLastMonth();

        List<Player> players = results.stream().map(Ranking::getPlayer).collect(Collectors.toList());

        return players;
    }
}
