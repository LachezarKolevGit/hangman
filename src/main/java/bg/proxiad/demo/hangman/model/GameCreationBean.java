package bg.proxiad.demo.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GameCreationBean {

  private Long playerId;
  private String word;
}
