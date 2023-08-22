package bg.proxiad.demo.hangman.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import bg.proxiad.demo.hangman.model.Ranking;

@Repository
public interface RankingRepository extends CrudRepository<Ranking, Long> {}
