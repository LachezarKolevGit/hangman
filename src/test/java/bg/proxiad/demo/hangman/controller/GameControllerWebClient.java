package bg.proxiad.demo.hangman.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GameControllerWebClient {

  @Autowired private WebTestClient client;

  @Test
  void test() {
    this.client
        .get()
        .uri("/v1.0/game/2")
        .exchange()
        .expectStatus()
        .is2xxSuccessful()
        .expectHeader()
        .contentType()
        .expectBody()
        .jsonPath("$.length()")
        .isEqualTo(3)
        .jsonPath("$[0].id")
        .isEqualTo(1)
        .jsonPath("$[0].name")
        .isEqualTo("duke")
        .jsonPath("$[0].tags")
        .isNotEmpty();

    client.get().uri("http://localhost:8080/v1.0/game/2").exchange();

    String responseBody = responseSpec.bodyToMono(String.class).block();
    System.out.println(responseBody);
  }
}
