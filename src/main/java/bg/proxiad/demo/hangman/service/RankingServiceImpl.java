package bg.proxiad.demo.hangman.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bg.proxiad.demo.hangman.repository.RankingRepository;

@Service
public class RankingServiceImpl implements RankingService {

  private final RankingRepository rankingRepository;

  @Autowired
  public RankingServiceImpl(RankingRepository rankingRepository) {
    this.rankingRepository = rankingRepository;
  }
}
