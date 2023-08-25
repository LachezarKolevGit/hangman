package bg.proxiad.demo.hangman;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class HangmanApplication implements CommandLineRunner {
  @Autowired private WebApplicationContext context;

  public static void main(String[] args) {
    SpringApplication.run(HangmanApplication.class, args);
  }

  @Override
    public void run(String... args) throws Exception {

  }
}
