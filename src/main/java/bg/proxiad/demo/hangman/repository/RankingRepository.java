package bg.proxiad.demo.hangman.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import bg.proxiad.demo.hangman.model.Ranking;

import java.util.List;
import java.util.Optional;

@Repository
public interface RankingRepository extends JpaRepository<Ranking, Long>, JpaSpecificationExecutor<Ranking> {

    List<Ranking> findTop10ByOrderByScoreDesc();

    Optional<Ranking> findRankingByPlayerId(Long playerId);

    @Query(nativeQuery = true, value = "SELECT * FROM ranking WHERE ranking.last_change > ranking.last_change - INTERVAL '1 month'  ORDER BY SCORE DESC LIMIT 10;")
    List<Ranking> findTop10ByScoreLastMonth();

}
