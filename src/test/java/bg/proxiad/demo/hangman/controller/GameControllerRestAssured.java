package bg.proxiad.demo.hangman.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;

import static io.restassured.RestAssured.given;

import bg.proxiad.demo.hangman.model.*;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import bg.proxiad.demo.hangman.service.GameService;
import bg.proxiad.demo.hangman.service.PlayerService;
import bg.proxiad.demo.hangman.utils.GameMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import java.util.Locale;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GameControllerRestAssured {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void testGetGame() {
        get("/v1.0/game-library/game/2")
                .then()
                .statusCode(200)
                .body("id", equalTo(2));
    }

    @Test
    @Transactional
    public void testPostGame() {
        GameCreationRequest gameCreationRequest = new GameCreationRequest("test", "test10", 3);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(gameCreationRequest)
                .when()
                .post("/v1.0/game-library/game")
                .then()
                .contentType("application/json")
                .body("id", notNullValue())
                .body("creatorName", equalTo(gameCreationRequest.getCreatorUsername()))
                .body("state", equalTo(State.ONGOING.toString()))
                .body("word", equalTo(gameCreationRequest.getWord().toUpperCase(Locale.ROOT)))
                .statusCode(200);
    }
}
