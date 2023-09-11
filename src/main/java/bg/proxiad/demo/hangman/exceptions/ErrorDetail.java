package bg.proxiad.demo.hangman.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorDetail {
    private String field;
    private String value;
    private String issue;
    private String location; //change to ENUM?
}
