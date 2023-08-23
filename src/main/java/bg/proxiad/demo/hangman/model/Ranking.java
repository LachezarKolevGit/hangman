package bg.proxiad.demo.hangman.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Ranking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  @Enumerated(EnumType.STRING)
  private Rank rank;

  @OneToOne
  @JoinColumn(name = "player_id")
  private Player player;

  @OneToMany(mappedBy = "ranking")
  private List<Stats> stats = new ArrayList<>();

  public Ranking(Rank rank) {
    this.rank = rank;
    player = null;
  }

  public Ranking(Player player, Rank rank) {
    this.player = player;
    player.setRanking(this);
    this.rank = rank;
  }

  public Ranking(Player player, Rank rank, List<Stats> stats) {
    this.player = player;
    player.setRanking(this);
    this.rank = rank;
    this.stats = stats;
  }
}
