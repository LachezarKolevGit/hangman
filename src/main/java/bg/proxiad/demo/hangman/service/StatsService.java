package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Stats;
import bg.proxiad.demo.hangman.repository.StatsRepository;

public interface StatsService {
  void recordTurn(Stats stats, Character characterPlaced);

  Stats getStats(Long id);

  Stats decrementLives(Stats stats);
}
