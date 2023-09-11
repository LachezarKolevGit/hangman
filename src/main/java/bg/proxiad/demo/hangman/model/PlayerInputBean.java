package bg.proxiad.demo.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerInputBean {
    private String playerName;
    private char character;
    private Long gameId;

    public PlayerInputBean(PlayerGuessRequest playerGuessRequest) {
        this.playerName = playerGuessRequest.getPlayerName();
        this.character = playerGuessRequest.getCharacter();
        this.gameId = playerGuessRequest.getGameId();
    }
}
