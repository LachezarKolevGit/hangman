package bg.proxiad.demo.hangman.controller;

import bg.proxiad.demo.hangman.model.PlayerDTO;
import bg.proxiad.demo.hangman.model.Rank;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import java.time.LocalDate;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class PlayerControllerRestAssured {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }

    @Test
    public void testRegisterPlayer() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("test100");
        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playerDTO)
                .when()
                .post("/v1.0/players/register")
                .then()
                .contentType("application/json")
                .body("name", equalTo(playerDTO.getName()))
                .body("rank", equalTo(Rank.UNRANKED.toString()))
                .body("score", equalTo(0))
                .body("lastChange", equalTo(LocalDate.now().toString()))
                .statusCode(200);
    }

    @Test
    public void testRegisterAndLoginPlayer() {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setName("test100");
        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playerDTO)
                .when()
                .post("/v1.0/players/register")
                .then()
                .contentType("application/json")
                .body("name", equalTo(playerDTO.getName()))
                .body("rank", equalTo(Rank.UNRANKED.toString()))
                .body("score", equalTo(0))
                .body("lastChange", equalTo(LocalDate.now().toString()))
                .statusCode(200);

        RestAssuredMockMvc.given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playerDTO)
                .when()
                .post("/v1.0/players/login")
                .then()
                .contentType("application/json")
                .body("name", equalTo(playerDTO.getName()))
                .body("rank", equalTo(Rank.UNRANKED.toString()))
                .body("score", equalTo(0))
                .body("lastChange", equalTo(LocalDate.now().toString()))
                .statusCode(200);
    }
}
