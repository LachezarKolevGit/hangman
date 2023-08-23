package bg.proxiad.demo.hangman.model;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.TestPropertySource;
import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.flyway.clean-disabled=false")
public class JpaPlayerDaoIT {

    @Autowired
    private GenericJpaDao<Player> playerDao;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setup() {
        flyway.clean();
        flyway.migrate();
        playerDao.setClass(Player.class);
    }

    @Test
    void testInsertingPlayer() {
        Player expectedPlayer = new Player("Player1");
        playerDao.create(expectedPlayer);

        Optional<Player> playerOptional = playerDao.get(expectedPlayer.getId());
        playerOptional.orElseThrow(() -> new EntityNotFoundException("Expected player's id was not found"));

        Player actualPlayer = playerOptional.get();
        assertThat(actualPlayer.getName())
                .isEqualTo(expectedPlayer.getName())
                .as("Expected player's name is not matching actual player's name");
    }

    @Test
    @Transactional
    void testEqualsPersistedRankingWhileIgnoringIdField() {
        Ranking expectedRanking = new Ranking(Rank.UNRANKED);
        Player expectedPlayer = new Player("Player1", expectedRanking, 0);
        playerDao.create(expectedPlayer);

        Optional<Player> playerOptional = playerDao.get(expectedPlayer.getId());
        playerOptional.orElseThrow(() -> new EntityNotFoundException("Expected player's id was not found"));

        Player actualPlayer = playerOptional.get();
        assertThat(actualPlayer)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedPlayer)
                .as("Expected player is not matching actual player while ignoring the id field");
    }
}
