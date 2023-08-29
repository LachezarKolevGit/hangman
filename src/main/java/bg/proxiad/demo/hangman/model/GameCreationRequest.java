package bg.proxiad.demo.hangman.model;

import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameCreationRequest {

  private String word;
  private Integer lives;
}
