package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.Stats;

public interface RankingService {
    Player evaluateRank(Player player);
    Player addBonusPoints(Player player, Stats stats);

}
