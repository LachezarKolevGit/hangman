package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.Rank;
import bg.proxiad.demo.hangman.model.Ranking;
import bg.proxiad.demo.hangman.model.Stats;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import bg.proxiad.demo.hangman.repository.RankingRepository;
import bg.proxiad.demo.hangman.repository.RankingSpecification;
import bg.proxiad.demo.hangman.repository.SearchCriteria;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

    public Ranking getPlayerRank(Long playerId) {
        Optional<Ranking> optionalRanking = rankingRepository.findRankingByPlayerId(playerId);
        optionalRanking.orElseThrow(() -> new EntityNotFoundException("Ranking was not found based on player id"));

        return optionalRanking.get();
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

        rankingRepository.save(ranking);
        return ranking;
    }

    public Ranking addBonusPoints(Ranking ranking, Stats stats) {
        int score = ranking.getScore();
        int livesRemaining = stats.getLivesRemaining();
        if (livesRemaining > 0) {
            score += livesRemaining;
            ranking.setScore(score);
        }

        rankingRepository.save(ranking);
        return ranking;
    }

    public Ranking increaseScoreOnWin(Ranking ranking) {
        int score = ranking.getScore();
        ranking.setScore(score + POINTS_ON_WIN);

        rankingRepository.save(ranking);
        return ranking;
    }

    public Ranking updateDate(Ranking ranking) {
        ranking.setLastChange(LocalDate.now());
        rankingRepository.save(ranking);

        return ranking;
    }

    @Override
    public List<Player> getTopPlayers(int pageIndex, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Ranking> rankings = rankingRepository.findTop10ByOrderByScoreDesc(pageRequest);
        List<Player> players = rankings.stream().map(Ranking::getPlayer).collect(Collectors.toList());

        return players;
    }

    @Override
    public List<Player> getTopPlayersLastMonth(int pageIndex, int pageSize) {
        RankingSpecification spec1 =
                new RankingSpecification(
                        new SearchCriteria("lastChange", ">", LocalDate.now().minusMonths(1)));

        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
        Page<Ranking> results = rankingRepository.findAll(Specification.where(spec1), pageRequest);

        List<Player> players = results.stream().map(Ranking::getPlayer).collect(Collectors.toList());

        return players;
    }
}
