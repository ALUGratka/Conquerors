package pl.conquerors.app.model.mapper;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.Gameplay;
import pl.conquerors.app.model.GameplayEntity;

public class GameplayEntityMapper {
    public static Gameplay transform (GameplayEntity gameplayEntity){
        Gameplay gameplay = null;
        if(gameplayEntity!=null){
            gameplay = new Gameplay();
            gameplay.setId(gameplayEntity.getId());
            gameplay.setPlayer1id(gameplayEntity.getPlayer1id());
            gameplay.setPlayer2id(gameplayEntity.getPlayer2id());
            gameplay.setCharacter1id(gameplayEntity.getCharacter1id());
            gameplay.setCharacter2id(gameplayEntity.getCharacter2id());
            gameplay.setTurn(gameplayEntity.getTurn());
            gameplay.setRound(gameplayEntity.getRound());
            gameplay.setPlayer1PositionX(gameplayEntity.getPlayer1PositionX());
            gameplay.setPlayer1PositionX(gameplayEntity.getPlayer1PositionY());
            gameplay.setPlayer2PositionX(gameplayEntity.getPlayer2PositionX());
            gameplay.setPlayer2PositionY(gameplayEntity.getPlayer2PositionY());
            gameplay.setCanPlay1(gameplayEntity.isCanPlay1());
            gameplay.setCanPlay2(gameplayEntity.isCanPlay2());
            gameplay.setCanAccept1(gameplayEntity.isCanAccept1());
            gameplay.setCanAccept2(gameplayEntity.isCanAccept2());
        }
        return gameplay;
    }

    public static List<Gameplay> transform (List<GameplayEntity> gameplayEntities){
        final List<Gameplay> gameplays = new ArrayList<>();
        for(GameplayEntity gameplayEntity : gameplayEntities){
            gameplays.add(transform(gameplayEntity));
        }
        return gameplays;
    }
}
