package bg.proxiad.demo.hangman.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlayerInputBean {
    private String playerName;
    private char character;
    private Long gameId;

}
