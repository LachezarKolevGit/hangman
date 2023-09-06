package bg.proxiad.demo.hangman.model;

import jakarta.persistence.EntityNotFoundException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(properties = "spring.flyway.clean-disabled=false")
@Transactional
public class JpaGameDaoIT {

    @Autowired
    private GenericJpaDao<Game> gameDao;

    @Autowired
    private Flyway flyway;

    @BeforeEach
    void setup() {
        flyway.clean();
        flyway.migrate();
        gameDao.setClass(Game.class);
    }

    @Test
    void testInsertingGame() {
        Player player = new Player("Player1");
        String expectedWord = "word";
        GameCreationBeanBuilder gameBuilder = new GameCreationBeanBuilder();
        gameBuilder.creator(player)
                .lives(3).word(expectedWord);
        GameCreationBean gameCreationBean = gameBuilder.build();
        Game expectedGame = new Game(gameCreationBean);
        gameDao.create(expectedGame);

        Optional<Game> gameOptional = gameDao.get(expectedGame.getId());
        gameOptional.orElseThrow(() -> new EntityNotFoundException("Expected game's id was not found"));

        Game actualGame = gameOptional.get();
        assertThat(actualGame)
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(expectedGame)
                .as("Expected game is not equal to actual game ignoring the id field");
    }

    @Test
    void test() {

    }


}
