package bg.proxiad.demo.hangman.model;

import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@Scope("prototype")
@Transactional
public class GameGenericJpaDao extends GenericJpaDao<Game> {

    public GameGenericJpaDao() {
        this.setClass(Game.class);
    }

    public Set<Game> getGamesCreated(Long playerId) {
        List<Game> gamesCreated =
                entityManager
                        .createQuery(
                                "SELECT g FROM Game g JOIN FETCH g.createdBy WHERE g.createdBy.id=:playerId", Game.class)
                        .setParameter("playerId", playerId)
                        .getResultList();

        for (Game game: gamesCreated) {
            System.out.println(game.getId());
        }

        return new HashSet<>(gamesCreated);
    }

    public Set<Game> getGamesPlayed(Long playerId) {
        List<Game> gamesPlayed =
                entityManager
                        .createQuery(
                                "SELECT g FROM Game g LEFT JOIN FETCH g.playedBy WHERE p.id=:playerId", Game.class)
                        .setParameter("playerId", playerId)
                        .getResultList();

        for (Game game: gamesPlayed) {
            System.out.println(game.getId());
        }

        return new HashSet<>(gamesPlayed);
    }

}
