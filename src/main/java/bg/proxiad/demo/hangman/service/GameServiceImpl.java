package bg.proxiad.demo.hangman.service;

import java.util.*;

import bg.proxiad.demo.hangman.exceptions.GameIsFinishedException;
import bg.proxiad.demo.hangman.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final RankingService rankingService;
    private final GenericJpaDao<Game> gameDAO;
    private final StatsService statsService;

    @Autowired
    public GameServiceImpl(
            RankingService rankingService, GenericJpaDao<Game> gameDAO, StatsService statsService) {
        this.rankingService = rankingService;
        this.gameDAO = gameDAO;
        this.statsService = statsService;
        gameDAO.setClass(Game.class);
    }

    @Override
    public Game getGame(Long id) {
        Optional<Game> gameOptional = gameDAO.get(id);
        if (gameOptional.isEmpty()) {
            throw new IllegalArgumentException("Game with that id does not exist");
        }

        return gameOptional.get();
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> list = gameDAO.getAll();
        System.out.println(gameDAO.getAll());
        return list;
    }

    @Override
    public Game create(GameCreationBean gameCreationBean) {
        Game game = new Game(gameCreationBean); //player join game
        gameCreationBean.getCreator().addCreatedGame(game);
        gameDAO.create(game);

        return game;
    }

    @Override
    public boolean placeChar(Long gameId, PlayerInput playerInput) {
        Game game = getGame(gameId);
        if (game.getState() == State.FINISHED) {
            throw new GameIsFinishedException("Game with id: " + gameId + " is finished");
        }

        String word = game.getWord();
        boolean[] progress = game.getProgress();

        statsService.recordTurn(game.getStats(), playerInput.getCharacter());

        boolean correctGuess = false;
        for (int i = 1; i < progress.length - 1; i++) {
            if (word.charAt(i) == playerInput.getCharacter()) {
                progress[i] = true;
                correctGuess = true;
            }
        }

        if (correctGuess) {
            game.setProgress(progress);
            gameDAO.update(game);
            return true;
        } else {
            statsService.decrementLives(game.getStats());
            return false;
        }
    }

    @Override
    public void markAsFinished(Long id) {
        Game game = getGame(id);
        game.setState(State.FINISHED);
        gameDAO.update(game);
    }

    @Override
    public boolean gameWonCheck(Long id) {
        Game game = getGame(id);
        boolean[] wordProgress = game.getProgress();

        for (int i = 0; i < wordProgress.length; i++) {
            if (!wordProgress[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean gameOverCheck(Long id) {
        Game game = getGame(id);
        Stats stats = game.getStats();

        if (stats.getLivesRemaining() <= 0) {
            return true;
        }

        return false;
    }

    public Character[] getFirstAndLastChar(Game game) {
        String word = game.getWord();
        Character[] firstAndLastChar = new Character[2];
        firstAndLastChar[0] = word.charAt(0);
        firstAndLastChar[1] = word.charAt(word.length() - 1);

        return firstAndLastChar;
    }

    public List<Character> convertWordToCharList(String word) {
        List<Character> characterList = new ArrayList<>();
        for (Character c : word.toCharArray()) {
            characterList.add(c);
        }

        return characterList;
    }

    public List<Character> getAlphabet() {
        List<Character> alphabet = new ArrayList<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet.add(c);
        }
        return Collections.unmodifiableList(alphabet);
    }

    public List<Character> getGuessedCharsByProgress(Long gameId) {
        Game game = getGame(gameId);
        List<Character> guessedChars = new ArrayList<>();
        boolean[] progress = game.getProgress();

        for (int i = 0; i < progress.length; i++) {
            if (progress[i]) {
                guessedChars.add(game.getWord().charAt(i));
            } else {
                guessedChars.add(null);
            }
        }

        return Collections.unmodifiableList(guessedChars);
    }



    public Map<Character, Boolean> getAlphabetWithTriedLetters(List<Character> guessedChars) {
        List<Character> alphabet = getAlphabet();
        Map<Character, Boolean> alphabetGuessed = new HashMap<>(alphabet.size());

        for (char c : alphabet) {
            alphabetGuessed.put(c, false);
        }

        for (int i = 0; i < guessedChars.size(); i++) {
            if (guessedChars.get(i) != null) {
                alphabetGuessed.put(Character.toUpperCase(guessedChars.get(i)), true);
            }
        }

        return Collections.unmodifiableMap(alphabetGuessed);
    }

    public void registerPlayer(Long gameId, Player player) {
        Game game = getGame(gameId);
        if (game.getPlayedBy() != null) {
            throw new IllegalArgumentException("А player is already associated with game id: " + gameId);
        }

        game.setPlayedBy(player);
        gameDAO.update(game);
    }

    public List<Character> getAllInvalidChars(Long gameId) {
        List<Character> guessedCharsByProgress = getGuessedCharsByProgress(gameId);
        List<Character> attemptedChars = getGame(gameId).getStats().getInputHistory();

        List<Character> allInvalidChars = new ArrayList<>(attemptedChars);

        for (int i = 0; i < guessedCharsByProgress.size(); i++) {
            Character currentChar = guessedCharsByProgress.get(i);
            if (currentChar != null && !allInvalidChars.contains(currentChar)) {
                allInvalidChars.add(currentChar);
            }
        }

        System.out.println(allInvalidChars);
        return Collections.unmodifiableList(attemptedChars);
    }

    public void registerSecondPlayer(Long gameId, Player secondPlayer) {
        Game game = getGame(gameId);
        if (game.getCreatedBy().getId().equals(secondPlayer.getId())) {
            throw new RuntimeException("Game cant be played by the player who created it");
        } else if (game.getPlayedBy() != null && !secondPlayer.getId().equals(game.getPlayedBy().getId())) {
            throw new RuntimeException("Second player is already registered");
        } else if (game.getPlayedBy() == null){
            registerPlayer(gameId, secondPlayer);
        }
    }

}

