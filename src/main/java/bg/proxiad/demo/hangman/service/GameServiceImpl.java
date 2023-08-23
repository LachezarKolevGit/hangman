package bg.proxiad.demo.hangman.service;

import java.util.Optional;

import bg.proxiad.demo.hangman.GameIsFinishedException;
import bg.proxiad.demo.hangman.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final RankingService rankingService;
    private final GenericJpaDao<Game> gameDAO;
    private final StatsService statsService;

    @Autowired
    public GameServiceImpl(RankingService rankingService, GenericJpaDao<Game> gameDAO, StatsService statsService) {
        this.rankingService = rankingService;
        this.gameDAO = gameDAO;
        this.statsService = statsService;
    }

    @Override
    public Game getGame(Long id) {
        Optional<Game> gameOptional = gameDAO.get(id);
        if (gameOptional.isEmpty()) {
            throw new IllegalArgumentException("Game with that id does not exist");
        }

        return gameOptional.get();
    }

    @Override
    public void start(Player player, String word) {
        Game game = new Game(player, word);
        gameDAO.create(game);
    }

    @Override
    public boolean placeChar(Long gameId, PlayerInput playerInput) {
        Game game = getGame(gameId);
        if (game.getState() == State.FINISHED){
            throw new GameIsFinishedException("Game with id: " + gameId + " is finished");
        }

        String word = game.getWord();
        boolean[] progress = game.getProgress();
        int actualPosition = playerInput.getPosition() - 1;

        if (progress.length < actualPosition || 0 < actualPosition ){ // extract and test the if conditions
            throw new IllegalArgumentException("Invalid position");
        }
        if (progress[actualPosition]) { //think of a better way
            throw new IllegalArgumentException("Position was guessed correctly in earlier turn");
        }

        if (word.charAt(actualPosition) == playerInput.getCharacter()) {
            progress[actualPosition] = true;
            game.setProgress(progress);
            gameDAO.update(game);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void markAsFinished(Long id) {
        Game game = getGame(id);
        game.setState(State.FINISHED);
        gameDAO.update(game);
    }

    @Override
    public boolean gameOverCheck(Long id) {
        Game game = getGame(id);
        boolean[] wordProgress = game.getProgress();

        for (int i = 0; i < wordProgress.length; i++) {
            if (!wordProgress[i]) {
                return false;
            }
        }

        return true;
    }

    public boolean playerTurn(Long gameId, PlayerInput playerInput){
        boolean turnStatus = placeChar(gameId, playerInput);
        if (turnStatus){
            boolean gameOverStatus = gameOverCheck(gameId);
            if (gameOverStatus){
                markAsFinished(gameId);
                return true;
            }
        }

        return false;
    }
}
