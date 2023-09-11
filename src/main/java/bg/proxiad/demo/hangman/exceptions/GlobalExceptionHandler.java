package bg.proxiad.demo.hangman.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        //ErrorCatalog errorCatalog = new ErrorCatalog(); //get it from somewhere, probably from the db
        //ErrorSpec errorSpec = errorCatalog.findError(Exception.class);

        ErrorSpec errorSpec = new ErrorSpec();
        errorSpec.setName("GAME_NOT_FOUND_ERROR");
        errorSpec.setMessage("Game with the specified id was not found");
        errorSpec.setLogLevel(LogLevel.ERROR);


        List<ErrorSpecIssue> issueList = new ArrayList<>();
        ErrorSpecIssue errorSpecIssue = new ErrorSpecIssue("GameNotFound", "Game with that id does not exist");
        issueList.add(errorSpecIssue);
        errorSpec.setIssues(issueList);
        Error error = new Error();
        error.setName(errorSpec.getName());
        error.setDebugId("debugId-777");  //think of a way to create debugIds
        error.setLogLevel(errorSpec.getLogLevel());
        String errorMessageString = String.format(errorSpec.getMessage(), "GUIDELINE: XYZ");
        error.setMessage(errorMessageString);

        List<ErrorDetail> details = new ArrayList<>();

        ErrorDetail detail = ErrorDetail.builder()
                .issue(String.format(errorSpec.getIssues().get(0).getIssue())) //<variables in issue string>
                .build();

        details.add(detail);
        error.setDetails(details);

        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
