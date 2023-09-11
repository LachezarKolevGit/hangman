package bg.proxiad.demo.hangman.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameDTO {

    private Long id;
    private String creatorName;
    private String playerName;
    private State state;
    private Long statsId;
    private String word;
    private boolean[] progress;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.creatorName = game.getCreatedBy().getName();
        if (game.getPlayedBy() != null) {
            this.playerName = game.getPlayedBy().getName();
        } else {
            this.playerName = null;
        }
        this.state = game.getState();
        this.statsId = game.getStats().getId();
        this.word = game.getWord();
        this.progress = game.getProgress();
    }

}
