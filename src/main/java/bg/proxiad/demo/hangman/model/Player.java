package bg.proxiad.demo.hangman.model;

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

  private Integer score;

  @OneToOne(mappedBy = "player", cascade = CascadeType.ALL)
  private Ranking ranking;

  @OneToMany(mappedBy = "player")
  private Set<Game> games = new HashSet<>();

  public Player(String name) {
    this.name = name;
    this.score = 0;
    this.ranking = new Ranking(this, Rank.UNRANKED);
    this.ranking.setPlayer(this);
  }

  public Player(String name, Ranking ranking, Integer score) {
    this.name = name;
    this.score = score;
    this.ranking = ranking;
    ranking.setPlayer(this);
  }
}
