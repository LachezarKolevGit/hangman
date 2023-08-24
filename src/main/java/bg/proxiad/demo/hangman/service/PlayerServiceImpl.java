package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.eclipse.tags.shaded.org.apache.bcel.generic.RETURN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

  private final PlayerGenericJpaDao playerDao;

  private final GameService gameService;

  private final RankingService rankingService;

  @Autowired
  public PlayerServiceImpl(
      PlayerGenericJpaDao playerDao, GameService gameService, RankingService rankingService) {
    this.playerDao = playerDao;
    this.gameService = gameService;
    this.rankingService = rankingService;
  }

  @Override
  public Player getPlayer(Long id) {
    Optional<Player> playerOptional = playerDao.get(id);
    playerOptional.orElseThrow(
        () -> new EntityNotFoundException("Player with id: " + id + " was not found"));
    return playerOptional.get();
  }

  @Override
  public Player getPlayerByName(String name) {
    Optional<Player> playerOptional = playerDao.getPlayerByName(name);
    playerOptional.orElseThrow(
        () -> new EntityNotFoundException("Player with name: " + name + " was not found"));
    return playerOptional.get();
  }

  @Override
  public void create(PlayerDTO playerDTO) {
    Player player = convertPlayerDto(playerDTO);
    playerDao.create(player);
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

  @Override
  public Long startGame(String playerName, String word) {
    Player player = getPlayerByName(playerName);
    Long gameId = gameService.start(player, word);
    return gameId;
  }

  public TurnOverview play(Long playerId, Long gameId, PlayerInput playerInput) {
    Player player = getPlayer(playerId);

    boolean charPlaced = gameService.placeChar(gameId, playerInput);
    if (charPlaced && gameService.gameWonCheck(gameId)) {
      updatePlayerOnWin(player, gameId);
      gameService.markAsFinished(gameId);
      return TurnOverview.GAME_WON;
    } else if (gameService.gameOverCheck(gameId)) {
      gameService.markAsFinished(gameId);
      return TurnOverview.GAME_LOST;
    } else if (charPlaced) {
      return TurnOverview.CORRECT_GUESS;
    }

    return TurnOverview.INCORRECT_GUESS;
  }

  public Player convertPlayerDto(PlayerDTO playerDTO) { // refactor
    if (playerDTO == null) {
      throw new IllegalArgumentException("PlayerDto is null");
    }
    if (playerDTO.getName() == null || playerDTO.getName().isBlank()) {
      throw new IllegalArgumentException("PlayerDto's name is null or blank");
    }

    return new Player(playerDTO.getName());
  }

  public void updatePlayerOnWin(Player player, Long gameId) {
    Game game = gameService.getGame(gameId);
    Stats stats = game.getStats(); // in stats ranking is null and lives remaining is 0

    player = rankingService.addBonusPoints(player, stats);
    player = rankingService.assignPointsToPlayerOnWin(player);
    player = rankingService.evaluateRank(player);

    stats.setRanking(player.getRanking());
    playerDao.update(player);
  }
}
