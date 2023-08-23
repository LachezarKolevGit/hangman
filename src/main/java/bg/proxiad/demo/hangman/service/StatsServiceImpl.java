package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Stats;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import bg.proxiad.demo.hangman.repository.StatsRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {

  private final StatsRepository statsRepository;

  @Override
  public void recordTurn(Long statsId, Integer livesRemaining, Character characterPlaced) {
    Optional<Stats> optionalStats = statsRepository.findById(statsId);
    optionalStats.orElseThrow(() -> new EntityNotFoundException("Stats with the entered id was not found"));

    Stats stats = optionalStats.get();
    stats.setLivesRemaining(livesRemaining);
    stats.addCharacterPlaced(characterPlaced);

    statsRepository.save(stats);
  }

  public Stats getStats(Long id){
    Optional<Stats> statsOptional = statsRepository.findById(id);
    statsOptional.orElseThrow(() -> new EntityNotFoundException("Stats with id:" + id + " was not found"));
    return statsOptional.get();
  }

}
