package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.Stats;
import bg.proxiad.demo.hangman.repository.StatsRepository;

public interface StatsService {
    void recordTurn(Long statsId, Integer livesRemaining, Character characterPlaced);
    Stats getStats(Long id);
}
