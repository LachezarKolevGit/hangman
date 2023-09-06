package bg.proxiad.demo.hangman.utils;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.GameDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "creatorId", source = "createdBy.id")
    @Mapping(target = "playerId", source = "playedBy.id")
    @Mapping(target = "statsId", source = "stats.id")
    GameDTO gameToGameDTO(Game source);
    Game gameDTOToGame(GameDTO destination);
}
