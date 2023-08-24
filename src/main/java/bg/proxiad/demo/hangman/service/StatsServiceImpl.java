package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Stats;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bg.proxiad.demo.hangman.repository.StatsRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class StatsServiceImpl implements StatsService {

  private final StatsRepository statsRepository;

  @Override
  public void recordTurn(Stats stats, Character characterPlaced) {
    stats.addCharacterPlaced(characterPlaced);

    statsRepository.save(stats);
  }

  public Stats getStats(Long id) {
    Optional<Stats> statsOptional = statsRepository.findById(id);
    statsOptional.orElseThrow(
        () -> new EntityNotFoundException("Stats with id:" + id + " was not found"));
    return statsOptional.get();
  }

  public Stats decrementLives(Stats stats) {
    int livesRemaining = stats.getLivesRemaining();
    stats.setLivesRemaining(livesRemaining - 1);
    statsRepository.save(stats);
    return stats;
  }
}
