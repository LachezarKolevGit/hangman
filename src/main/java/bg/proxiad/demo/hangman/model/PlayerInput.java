package bg.proxiad.demo.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlayerInput {  //check if a record can be used
    private int position;
    private char character;
}