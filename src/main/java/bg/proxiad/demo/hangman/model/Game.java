package bg.proxiad.demo.hangman.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Value;

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
  @JoinColumn(name = "player_id")
  private Player player;

  @Enumerated(EnumType.STRING)
  private State state = State.ONGOING;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "stats_id", referencedColumnName = "id")
  private Stats stats;

  private String word;

  private boolean[] progress;

  public Game(Player player, String word) {
    this.player = player;
    player.getGames().add(this);
    this.word = word;
    this.progress = new boolean[word.length()];
    this.stats = new Stats();
    stats.setLivesRemaining(STARTING_LIVES);
    stats.setGame(this);
  }
}
