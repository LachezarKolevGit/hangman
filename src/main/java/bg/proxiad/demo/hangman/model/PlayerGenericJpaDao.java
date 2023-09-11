package bg.proxiad.demo.hangman.model;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

@Repository
@Scope("prototype")
@Transactional
public class PlayerGenericJpaDao extends GenericJpaDao<Player> {

  public PlayerGenericJpaDao() {
    this.setClass(Player.class);
  }

  public Optional<Player> getPlayerByName(String name) {
    System.out.println("Passed name is " + name);
    Player player =
        entityManager
            .createQuery(
                "SELECT p FROM Player p LEFT JOIN p.ranking r WHERE p.name=:name", Player.class)
            .setParameter("name", name)
            .getSingleResult();
    System.out.println(player);

    return Optional.ofNullable(player);
  }

  /*public Set<Game> getFullyFetchedPlayerByName(String name) {
    Player player =
            entityManager
                    .createQuery(
                            "SELECT p FROM Player p LEFT JOIN FETCH p.played WHERE p.id=:playerId", Player.class)
                    .setParameter("playerId", playerId)
                    .getSingleResult();


   return player;
  }*/

}
