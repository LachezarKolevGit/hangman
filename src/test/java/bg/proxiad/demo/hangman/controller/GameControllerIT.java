package bg.proxiad.demo.hangman.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;
import static io.restassured.module.mockmvc.matcher.RestAssuredMockMvcMatchers.*;

import static io.restassured.RestAssured.given;

import org.assertj.core.api.Assert;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.GenericJpaDao;
import bg.proxiad.demo.hangman.model.JpaGameDaoIT;
import bg.proxiad.demo.hangman.service.GameService;
import bg.proxiad.demo.hangman.service.PlayerService;
import bg.proxiad.demo.hangman.utils.GameMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@SpringBootTest
public class GameControllerIT {

  @Autowired private WebApplicationContext webApplicationContext;

  // @MockBean private GenericJpaDao<Game> gameDao;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    // gameDao.setClass(Game.class);
  }

  @Test
  public void testGetGame() {
    get("/v1.0/game/2").then().statusCode(200).assertThat().body("id", equalTo(2));
  }

  @Test
  public void testGetGames() {
    RestAssuredMockMvc.given()
        .when()
        .get("/v1.0/game/2")
        .then()
        .contentType("application/json")
        .body("id", equalTo(2))
        .statusCode(200);
  }
}
