package bg.proxiad.demo.hangman.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.GenericJpaDao;

@Service
public class GameServiceImpl implements GameService {

  private final RankingService rankingService;
  // private final GenericJpaDao<T> gameDAO;

  @Autowired
  public GameServiceImpl(RankingService rankingService) {
    this.rankingService = rankingService;
    // this.gameDAO = gameDAO;
  }

  @Override
  public Game getGame(Long id) {
    /*Optional<Game> gameOptional = gameDAO.get(id);
    if (gameOptional.isEmpty()) {
      throw new IllegalArgumentException("Game with that id does not exist");
    }
    return gameOptional.get();*/
    return null;
  }

  @Override
  public void start() {
    new Game();

    // TODO Auto-generated method stub

  }

  @Override
  public boolean placeChar(Long gameId, Integer position, Character character) {
    /* Optional<Game> gameOptional = gameDAO.get(gameId);
    if (gameOptional.isEmpty()) {
      throw new IllegalArgumentException("Game with that id does not exist");
    }
    Game game = gameOptional.get();
    String word = game.getWord();
    boolean[] progress = game.getProgress();

    if (progress[position]) {
      throw new IllegalArgumentException("Position was guessed correctly in earlier turn");
    }
    if (word.charAt(position.intValue()) == character.charValue()) {
      return false;
    } else {
      return true;
    }*/
    return false;
  }

  @Override
  public void end() {
    // TODO Auto-generated method stub

  }

  @Override
  public boolean gameOverCheck(Game game) {
    boolean[] wordProgress = new boolean[game.getWord().length()];
    for (int i = 0; i < wordProgress.length; i++) {
      if (wordProgress[i] != true) {
        return false;
      }
    }

    return true;
  }
}
