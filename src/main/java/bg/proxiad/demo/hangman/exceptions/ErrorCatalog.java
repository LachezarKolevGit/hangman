package bg.proxiad.demo.hangman.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Locale;


@Getter
@Setter
@AllArgsConstructor
public class ErrorCatalog {

    private String namespace;
    private Locale locale;
    private List<ErrorSpec> errors;
}
