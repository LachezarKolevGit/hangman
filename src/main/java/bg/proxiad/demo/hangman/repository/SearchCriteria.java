package bg.proxiad.demo.hangman.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;
}
