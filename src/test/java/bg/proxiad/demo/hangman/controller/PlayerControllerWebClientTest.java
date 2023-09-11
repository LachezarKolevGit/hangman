package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.GameCreationRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PlayerControllerWebClientTest {

    @Autowired
    private WebTestClient client;

    @Test
    void testPostRegisterPlayer() {
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
}
