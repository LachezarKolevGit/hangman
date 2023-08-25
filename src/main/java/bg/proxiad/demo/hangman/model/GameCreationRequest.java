package bg.proxiad.demo.hangman.model;

import org.springframework.web.bind.annotation.RequestParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GameCreationRequest {

  private String playerName;
  private String word;
}
