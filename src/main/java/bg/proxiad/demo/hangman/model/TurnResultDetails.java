package bg.proxiad.demo.hangman.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurnResultDetails {
    @JsonProperty("Turn result")
    private TurnOverview turnOverview;
    @JsonProperty("Game Details")
    private GameDTO gameDTO;

}
