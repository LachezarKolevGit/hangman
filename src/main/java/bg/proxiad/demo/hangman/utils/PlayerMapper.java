package bg.proxiad.demo.hangman.utils;

import bg.proxiad.demo.hangman.model.Player;
import bg.proxiad.demo.hangman.model.PlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    @Mapping(target = "rank", source = "ranking.rank")
    @Mapping(target = "score", source = "ranking.score")
    @Mapping(target = "lastChange", source = "ranking.lastChange")
    PlayerDTO playerToPlayerDTO(Player source);
    Player playerDTOToPlayer(PlayerDTO destination);

}
