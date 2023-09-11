package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.GameCreationRequest;
import org.hibernate.sql.ast.tree.Statement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class GameControllerWebClientTest {

    @Autowired
    private WebTestClient client;

    @Test
    void testGetGameReturnedJSONSchema() {
        this.client
                .get()
                .uri("/v1.0/game-library/game/2")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isEqualTo(2)
                .jsonPath("$.creatorId")
                .isEqualTo(17)
                .jsonPath("$.state")
                .isEqualTo("ONGOING")
                .jsonPath("$.word")
                .isEqualTo("test1");
    }

    @Test
    void testCorrectPostForGame() {
        GameCreationRequest gameCreationRequest = new GameCreationRequest("test", "test10", 3);

        this.client.post().uri("/v1.0/game-library/game")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(gameCreationRequest)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectHeader()
                .contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.creatorName")
                .isEqualTo("test")
                .jsonPath("$.state")
                .isEqualTo("ONGOING")
                .jsonPath("$.word")
                .isEqualTo("TEST10");
    }

    @Test
    void registerPlayer() {


    }
}