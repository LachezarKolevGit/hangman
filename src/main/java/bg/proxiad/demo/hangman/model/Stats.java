package bg.proxiad.demo.hangman.model;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.data.annotation.AccessType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
  @JoinColumn(name = "ranking_id", nullable = false)
  private Ranking ranking;

  @ElementCollection
  @CollectionTable(name = "input_history")
  List<Character> inputHistory = new ArrayList<>();
}
