package bg.proxiad.demo.hangman.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Game {
  public static final Integer STARTING_LIVES = 3;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "created_by_player_id")
  private Player createdBy;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
  @JoinColumn(name = "played_by_player_id")
  private Player playedBy;

  @Enumerated(EnumType.STRING)
  private State state = State.ONGOING;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "stats_id", referencedColumnName = "id")
  private Stats stats;

  private String word;

  private boolean[] progress;

  @Deprecated
  public Game(Player createdBy, String word) { //probably will cause an exception
    this.createdBy = createdBy;
    createdBy.getCreated().add(this);
    this.word = word;
    this.progress = new boolean[word.length()];
    this.stats = new Stats();
    stats.setLivesRemaining(STARTING_LIVES);
    stats.setGame(this);
  }

  public Game(GameCreationBean gameCreationBean) {
    this.createdBy = gameCreationBean.getCreator();
    this.createdBy.addCreatedGame(this);
    this.word =gameCreationBean.getWord().toUpperCase();
    this.progress = new boolean[word.length()];
    this.progress[0] = true;
    this.progress[word.length() - 1] = true;
    this.stats = new Stats(gameCreationBean.getLives(), this, LocalDateTime.now());
  }
}
