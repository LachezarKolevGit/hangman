package bg.proxiad.demo.hangman.model;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class GameCreationRequest {
  private String creatorUsername;
  private String word;
  private Integer lives;
}
