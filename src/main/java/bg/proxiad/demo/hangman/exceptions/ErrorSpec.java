package bg.proxiad.demo.hangman.exceptions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
public class ErrorSpec {
    private String name;
    private String message;
List<ErrorSpecIssue> issues;

    @JsonIgnore
    private LogLevel logLevel;
    private List<HttpStatus> httpStatusCodes;
    private String suggestedUserActions;
}
