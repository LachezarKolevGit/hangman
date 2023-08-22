package bg.proxiad.demo.hangman.service;

import org.springframework.stereotype.Service;
import bg.proxiad.demo.hangman.repository.StatsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {

  private final StatsRepository statsRepository;
}
