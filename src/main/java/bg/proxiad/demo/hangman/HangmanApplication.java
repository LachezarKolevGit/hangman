package bg.proxiad.demo.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.context.WebApplicationContext;
import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.GenericJpaDao;

@SpringBootApplication
public class HangmanApplication implements CommandLineRunner {
  @Autowired private WebApplicationContext context;

  public static void main(String[] args) {
    SpringApplication.run(HangmanApplication.class, args);
  }

  @Override
    public void run(String... args) throws Exception {
     /* GenericJpaDao<Game> gameDao = context.getBean(GenericJpaDao.class);
      gameDao.setClass(Game.class);
      System.out.println("Saving game");
    gameDao.create(new Game());*/
  }
}
