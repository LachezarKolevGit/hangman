package bg.proxiad.demo.hangman.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Player {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  private String name;

  @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
  private Ranking ranking;

  @OneToMany(mappedBy = "createdBy")
  private Set<Game> created = new HashSet<>();

  @OneToMany(mappedBy = "playedBy")
  private Set<Game> played = new HashSet<>();

  public Player(String name) {
    this.name = name;
    this.ranking = new Ranking(this, Rank.UNRANKED);
    this.ranking.setPlayer(this);
    this.ranking.setScore(0);
    this.ranking.setLastChange(LocalDate.now());
  }

  public Player(String name, Ranking ranking) {
    this.name = name;
    this.ranking = ranking;
    ranking.setPlayer(this);
    this.ranking.setScore(0);
    this.ranking.setLastChange(LocalDate.now());
  }

  public void addCreatedGame(Game game){
    if (game == null){
      throw new IllegalArgumentException("Game can't be null");
    }

    created.add(game);
  }
}
