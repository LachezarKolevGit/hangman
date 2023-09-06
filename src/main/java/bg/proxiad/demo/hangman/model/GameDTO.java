package bg.proxiad.demo.hangman.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GameDTO {

    private Long id;
    private Long creatorId;
    private Long playerId;
    private State state;
    private Long statsId;
    private String word;
    private boolean[] progress;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.creatorId = game.getCreatedBy().getId();
        if (game.getPlayedBy() != null) {
            this.playerId = game.getPlayedBy().getId();
        } else {
            this.playerId = null;
        }
        this.state = game.getState();
        this.statsId = game.getStats().getId();
        this.word = game.getWord();
        this.progress = game.getProgress();
    }

}
