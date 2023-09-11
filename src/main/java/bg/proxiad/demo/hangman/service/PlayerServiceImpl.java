package bg.proxiad.demo.hangman.service;

import bg.proxiad.demo.hangman.model.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Player create(PlayerDTO playerDTO) {
        Player player = convertPlayerDto(playerDTO);
        playerDao.create(player);
        return player;
    }

    @Override
    public void delete(Long id) {
        Player player = getPlayer(id);
        playerDao.delete(player);
    }

    @Override
    @Deprecated
    public Game startGame(GameCreationBean gameCreationBean) {
        /*Optional<Player> playerOptional = playerDao.getCreatedGamesByPlayer(gameCreationBean.getCreator().getId());
        playerOptional.orElseThrow(() ->
                new EntityNotFoundException("Entity with id: " + gameCreationBean.getCreator().getId() + "was not found")
        );
        gameCreationBean.setCreator(playerOptional.get());
        Game game = gameService.create(gameCreationBean);*/

        return null;
    }

    public TurnOverview play(PlayerInputBean playerInputBean) {
        Long gameId = playerInputBean.getGameId();
        Player player = getPlayerByName(playerInputBean.getPlayerName());
        gameService.registerSecondPlayer(gameId, player);

        boolean charPlaced = gameService.placeChar(gameId, playerInputBean);
        if (charPlaced && gameService.gameWonCheck(gameId)) {
            updateRankOnWin(player, gameId);
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

    public void updateRankOnWin(Player player, Long gameId) {
        Game game = gameService.getGame(gameId);
        Stats stats = game.getStats();
        Ranking playerRank = player.getRanking();

        playerRank = rankingService.addBonusPoints(playerRank, stats);
        playerRank = rankingService.increaseScoreOnWin(playerRank);
        playerRank = rankingService.evaluateRank(playerRank);
        playerRank = rankingService.updateDate(playerRank);

        stats.setRanking(player.getRanking());
        playerDao.update(player);
    }

}
