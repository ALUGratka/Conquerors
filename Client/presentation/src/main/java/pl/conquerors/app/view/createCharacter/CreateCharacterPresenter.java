package pl.conquerors.app.view.createCharacter;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.domain.model.Character;

public class CreateCharacterPresenter extends BasePresenter<CreateCharacterView> {
    Character.Sex sex;
    Character.CharacterClass characterClass;
    Character.Shoes shoes;
    Character.Pants pants;
    Character.Blouse blouse;
    Character.EyeColor eyeColor;
    Character.Hair hair;
    Character.Hat hat;
    String nickname = "Lamus";
    int userId;

    CreateCharacterUseCase createCharacterUseCase;

    public CreateCharacterPresenter(final CreateCharacterUseCase useCase) { createCharacterUseCase = useCase; }

    public void setSex(Character.Sex sex) {
        this.sex = sex;
    }

    public void setCharacterClass(Character.CharacterClass characterClass) {
        this.characterClass = characterClass;
    }

    public void setShoes(Character.Shoes shoes) {
        this.shoes = shoes;
    }

    public void setPants(Character.Pants pants) {
        this.pants = pants;
    }

    public void setBlouse(Character.Blouse blouse) {
        this.blouse = blouse;
    }

    public void setEyeColor(Character.EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHair(Character.Hair hair) {
        this.hair = hair;
    }

    public void setHat(Character.Hat hat) {
        this.hat = hat;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void performCharacterCreation(){
        createCharacterUseCase.setData(nickname=nickname, sex=sex, characterClass=characterClass, hair=hair, hat=hat,
                eyeColor=eyeColor, blouse=blouse, pants=pants, shoes=shoes, userId=userId);
    }

}
