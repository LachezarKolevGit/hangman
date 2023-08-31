package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Stats;
import jakarta.jws.WebService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bg.proxiad.demo.hangman.repository.StatsRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
@Transactional
@WebService(serviceName = "StatsService", portName = "StatsPort",
        targetNamespace = "http://localhost:8080/",
        endpointInterface = "bg.proxiad.demo.hangman.service.StatsService")
public class StatsServiceImpl implements StatsService {

  private static final Logger LOG = Logger.getLogger(StatsServiceImpl.class.getName());
  private final StatsRepository statsRepository;

  @Override
  public void recordTurn(Stats stats, Character characterPlaced) {
    stats.addCharacterPlaced(characterPlaced);

    statsRepository.save(stats);
  }

  public Stats getStats(Long id) {
LOG.info("Get stats from soap endpoint and id being passed is" +  id);

    /*Optional<Stats> statsOptional = statsRepository.findById(id);
    statsOptional.orElseThrow(
        () -> new EntityNotFoundException("Stats with id:" + id + " was not found"));
    return statsOptional.get();*/
    return null;
  }

  public Stats decrementLives(Stats stats) {
    int livesRemaining = stats.getLivesRemaining();
    stats.setLivesRemaining(livesRemaining - 1);
    statsRepository.save(stats);
    return stats;
  }
}
