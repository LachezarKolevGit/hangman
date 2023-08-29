package bg.proxiad.demo.hangman.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.annotation.AccessType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Stats {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  private Long id;

  private int livesRemaining;

  @ManyToOne
  @JoinColumn(name = "ranking_id")
  private Ranking ranking;

  @OneToOne(mappedBy = "stats")
  private Game game;

  @Column(name =  "created_at")
  private LocalDateTime createdAt;

  @ElementCollection
  @CollectionTable(name = "input_history")
  List<Character> inputHistory = new ArrayList<>();

  public Stats(Integer livesRemaining, Game game, LocalDateTime createdAt){
    this.livesRemaining = livesRemaining;
    this.game = game;
    this.createdAt = createdAt;
  }

  public List<Character> getInputHistory(){
     return Collections.unmodifiableList( inputHistory);
  }

  public void addCharacterPlaced(Character characterPlaced) {
    inputHistory.add(characterPlaced);
  }
}
