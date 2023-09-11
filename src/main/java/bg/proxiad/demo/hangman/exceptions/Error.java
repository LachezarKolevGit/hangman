package bg.proxiad.demo.hangman.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.logging.LogLevel;

import java.util.List;

@Getter
@Setter

public class Error {
    private String name;
    private List<ErrorDetail> details;
    private String debugId;
    private String message;

    @JsonIgnore
    private LogLevel logLevel;

}
