package bg.proxiad.demo.hangman.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Game {
  public static final int LIVES_PER_GAME = 3;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "player_id")
  private Player player;

  @Enumerated(EnumType.STRING)
  private State state = State.ONGOING;

  private String word;

  private boolean[] progress;

  @Column(name = "lives_remaining")
  private int livesRemaining = LIVES_PER_GAME;

  public Game(Player player, String word, int remainingLives) {
    this.player = player;
    player.getGames().add(this);
    this.word = word;
    this.progress = new boolean[word.length()];
  }
}
