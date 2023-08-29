package bg.proxiad.demo.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameCreationBean {

    private Player creator;
    private String word;
    private Integer lives;

    GameCreationBean(Player creator, String word, Integer lives) {
        this.creator = creator;
        this.word = word;
        this.lives = lives;
    }

}
