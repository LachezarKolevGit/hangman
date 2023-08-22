package bg.proxiad.demo.hangman.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import bg.proxiad.demo.hangman.model.Stats;

@Repository
public interface StatsRepository extends CrudRepository<Stats, Long> {}
