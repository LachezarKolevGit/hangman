package bg.proxiad.demo.hangman.utils;

import bg.proxiad.demo.hangman.model.Game;
import bg.proxiad.demo.hangman.model.GameDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GameMapper {
    @Mapping(target = "creatorName", source = "createdBy.name")
    @Mapping(target = "playerName", source = "playedBy.name")
    @Mapping(target = "statsId", source = "stats.id")
    GameDTO gameToGameDTO(Game source);
    Game gameDTOToGame(GameDTO destination);
}
