package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final GenericJpaDao<Player> playerDao;

    private final GameService gameService;

    private final RankingService rankingService;

    @Autowired
    public PlayerServiceImpl(GenericJpaDao<Player> playerDao, GameService gameService, RankingService rankingService) {
        this.playerDao = playerDao;
        this.gameService = gameService;
        this.rankingService = rankingService;
    }

    @Override
    public Player getPlayer(Long id) {
        Optional<Player> playerOptional = playerDao.get(id);
        playerOptional.orElseThrow(() -> new EntityNotFoundException("Player with id: " + id + " was not found"));
        return playerOptional.get();
    }

    @Override
    public void create(String name) {
        playerDao.create(new Player(name));
    }

    @Override
    public void delete(Long id) {
        Player player = getPlayer(id);
        playerDao.delete(player);
    }

    public void startGame(Long playerId, String word) {
        Player player = getPlayer(playerId);
        gameService.start(player, word);
    }

    public void play(Long playerId, Long gameId, PlayerInput playerInput) {
        Player player = getPlayer(playerId);
        boolean gameWon = gameService.playerTurn(gameId, playerInput);

        if (gameWon) {
            Game game = gameService.getGame(gameId);
            Stats stats = game.getStats();

            player = rankingService.addBonusPoints(player, stats);
            player = rankingService.evaluateRank(player);

            playerDao.update(player);
        }

    }
}
