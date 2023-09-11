package bg.proxiad.demo.hangman.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorSpecIssue {

    private String id;
    private String issue;

}
