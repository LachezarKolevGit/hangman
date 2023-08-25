package bg.proxiad.demo.hangman.model;

import java.time.LocalDate;
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

    private Integer score;

    @Column(name = "last_change")
    private LocalDate lastChange;

    @OneToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @OneToMany(mappedBy = "ranking")
    private List<Stats> stats = new ArrayList<>();

    public Ranking(Player player, Rank rank) {
        this.player = player;
        player.setRanking(this);
        this.rank = rank;
        this.score = 0;
    }

    public Ranking(Player player, Rank rank, List<Stats> stats) { //unnecessary
        this.player = player;
        player.setRanking(this);
        this.rank = rank;
        this.stats = stats;
        this.score = 0;
    }

    @Override
    public String toString() {
        return "Ranking{" +
                "id=" + id +
                ", rank=" + rank +
                ", score=" + score +
                ", lastChange=" + lastChange +" }";
    }
}
