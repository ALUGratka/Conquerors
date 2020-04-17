package pl.conquerors.app.model.mapper;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.model.CharacterEntity;

public class CharacterEntityMapper {

    public static Character transform (CharacterEntity characterEntity) {
        Character character = null;
        if(characterEntity!=null){
            character = new Character();
            character.setmId(characterEntity.getId());
            character.setmLevel(characterEntity.getLevel());
            character.setmCharisma(characterEntity.getCharisma());
            character.setmIntelligence(characterEntity.getIntelligence());
            character.setmAgility(characterEntity.getAgility());
            character.setmStrength(characterEntity.getStrength());
            character.setmNickname(characterEntity.getNickname());
            character.setmSex(characterEntity.getSex());
            character.setmCharacterClass(characterEntity.getCharacterClass());
            character.setmHair(characterEntity.getHair());
            character.setmEyeColor(characterEntity.getEyeColor());
            character.setmBlouse(characterEntity.getBlouse());
            character.setmPants(characterEntity.getPants());
            character.setmShoes(characterEntity.getShoes());
            character.setmUserId(characterEntity.getUserId());
        }
        return character;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<Character> transform (List<CharacterEntity> characterEntities) {
        final List<Character> characters = new ArrayList<>();
        characterEntities.forEach(characterEntity -> characters.add(transform(characterEntity)));
        return characters;
    }

}
