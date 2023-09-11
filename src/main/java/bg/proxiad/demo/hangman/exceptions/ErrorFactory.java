package bg.proxiad.demo.hangman.exceptions;

import org.springframework.boot.logging.LogLevel;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory {

    public Error getError(String errorType) {
        if(errorType == null){
            return null;
        }
        if(errorType.equalsIgnoreCase("GAME_NOT_FOUND_ERROR")){
            return initGameNotFoundError();

        } else if(errorType.equalsIgnoreCase("INVALID_PLAYER_ERROR")){
            return initInvalidPlayerError();

        } else if(errorType.equalsIgnoreCase("PLAYER_ALREADY_REGISTERED_ERROR")){
            return initPlayerAlreadyRegisteredError();
        }
        return null;
    }

    public Error initGameNotFoundError(){
        ErrorSpec errorSpec =  ErrorSpec.builder()
                .name("GAME_NOT_FOUND_ERROR")
                .message("Game with the specified id was not found")
                .logLevel(LogLevel.ERROR)
                .build();

        ErrorSpecIssue errorSpecIssue = new ErrorSpecIssue("GameNotFound", "Game with that id does not exist");
        errorSpec.setIssues(List.of(errorSpecIssue));

        ErrorDetail detail = ErrorDetail.builder()
                .issue(String.format(errorSpec.getIssues().get(0).getIssue())) //<variables in issue string>
                .build();

        Error error = Error.builder()
                .name(errorSpec.getName())
                .debugId("debugId-777")
                .logLevel(errorSpec.getLogLevel())
                .message(String.format(errorSpec.getMessage(), "GUIDELINE: XYZ"))
                .details(List.of(detail))
                .build();

        return error;
    }

    public Error initInvalidPlayerError(){
        ErrorSpec errorSpec =  ErrorSpec.builder()
                .name("INVALID_PLAYER_ERROR")
                .message("The creator of the game can't play the game")
                .logLevel(LogLevel.ERROR)
                .build();

        ErrorSpecIssue errorSpecIssue = new ErrorSpecIssue("InvalidPlayer", "The game can't be played by the one who created it");
        errorSpec.setIssues(List.of(errorSpecIssue));

        ErrorDetail detail = ErrorDetail.builder()
                .issue(String.format(errorSpec.getIssues().get(0).getIssue())) //<variables in issue string>
                .build();

        Error error = Error.builder()
                .name(errorSpec.getName())
                .debugId("debugId-888")
                .logLevel(errorSpec.getLogLevel())
                .message(String.format(errorSpec.getMessage(), "GUIDELINE: XYZ"))
                .details(List.of(detail))
                .build();

        return error;
    }

    public Error initPlayerAlreadyRegisteredError(){
        ErrorSpec errorSpec =  ErrorSpec.builder()
                .name("PLAYER_ALREADY_REGISTERED_ERROR")
                .message("Game is already being played")
                .logLevel(LogLevel.ERROR)
                .build();

        ErrorSpecIssue errorSpecIssue = new ErrorSpecIssue("PlayerAlreadyRegistered", "The game is already being played by another player");
        errorSpec.setIssues(List.of(errorSpecIssue));

        ErrorDetail detail = ErrorDetail.builder()
                .issue(String.format(errorSpec.getIssues().get(0).getIssue())) //<variables in issue string>
                .build();

        Error error = Error.builder()
                .name(errorSpec.getName())
                .debugId("debugId-999")
                .logLevel(errorSpec.getLogLevel())
                .message(String.format(errorSpec.getMessage(), "GUIDELINE: XYZ"))
                .details(List.of(detail))
                .build();

        return error;
    }
}
