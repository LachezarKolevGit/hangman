package bg.proxiad.demo.hangman.model;

public class  GameCreationBeanBuilder {

    private Player creator;
    private String word;
    private Integer lives;

    public GameCreationBeanBuilder creator(Player player) {
        this.creator = player;
        return this;
    }

    public GameCreationBeanBuilder word(String word) {
        this.word = word;
        return this;
    }

    public GameCreationBeanBuilder lives(Integer lives) {
        this.lives = lives;
        return this;
    }

    public GameCreationBean build() {
        return new GameCreationBean(creator, word, lives);
    }

}
