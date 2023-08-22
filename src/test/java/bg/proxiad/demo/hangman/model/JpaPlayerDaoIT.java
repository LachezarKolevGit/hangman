package bg.proxiad.demo.hangman.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import jakarta.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;

@DataJpaTest(
    includeFilters =
        @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Repository.class))
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.flyway.clean-disabled=false")
public class JpaPlayerDaoIT {

  @Autowired private GenericJpaDao<Player> playerDao;

  @Autowired private Flyway flyway;

  @Autowired private EntityManager manager;

  @BeforeEach
  void setup() {
    flyway.clean();
    flyway.migrate();
  }

  private void flushAndClear() {
    manager.flush();
    manager.clear();
  }

  @Test
  void test() {
    Player expectedPlayer = new Player("Player1");
    Game game = new Game();
    // Ranking ranking = new Ranking();
    manager.persist(game);
    flushAndClear();
    // manager.persist(expectedPlayer);
    // flushAndClear();
    /*playerDao.create(expectedPlayer);
      Optional<Player> playerOptional = playerDao.get(expectedPlayer.getId());
      playerOptional.ifPresent(
          (actualPlayer) ->
              assertEquals(
                  expectedPlayer, actualPlayer, "Expected player is not matching actual player"));
    */
  }
}
