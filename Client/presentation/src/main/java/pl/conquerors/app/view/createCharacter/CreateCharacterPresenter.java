package pl.conquerors.app.view.createCharacter;

import android.util.Log;

import pl.conquerors.app.base.BasePresenter;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.model.mapper.CharacterEntityMapper;
import pl.conquerors.app.rest.RestClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCharacterPresenter extends BasePresenter<CreateCharacterView> {
    private int sex;
    private int characterClass;
    private int shoes;
    private int pants;
    private int blouse;
    private int eyeColor;
    private int hair;
    private int hat;
    private String nickname = "Lamus";
    private int userId;
    private int skillPoints;

    public CreateCharacterPresenter() {
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setCharacterClass(int characterClass) {
        this.characterClass = characterClass;
    }

    public void setShoes(int shoes) {
        this.shoes = shoes;
    }

    public void setPants(int pants) {
        this.pants = pants;
    }

    public void setBlouse(int blouse) {
        this.blouse = blouse;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSkillPoints(int skillPoints) {this.skillPoints = skillPoints;}

    public void performCharacterCreation() {

        int level = 0;
        int skillPoints = 0;
        int strength;
        int charisma;
        int agility;
        int intelligence;

        if (characterClass == 0) {
            strength = 2;
            charisma = 10;
            agility = 5;
            intelligence = 3;
        } else if (characterClass == 1) {
            strength = 2;
            charisma = 5;
            agility = 10;
            intelligence = 3;
        } else if (characterClass == 2) {
            strength = 10;
            charisma = 3;
            agility = 5;
            intelligence = 2;
        } else { //3
            strength = 5;
            charisma = 3;
            agility = 2;
            intelligence = 10;
        }

        Call<CharacterEntity> call = RestClient.getInstance().createCharacter(new CharacterEntity(level, charisma, intelligence, agility,
                strength, nickname, sex, characterClass, hair, hat, eyeColor, blouse, pants,
                shoes, userId, skillPoints));

        call.enqueue(new Callback<CharacterEntity>() {
            @Override
            public void onResponse(Call<CharacterEntity> call, Response<CharacterEntity> response) {
                if (!response.isSuccessful()) {
                    Log.e("Character creation", "Code: " + response.code());
                } else {
                    Log.e("Character created!", "Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<CharacterEntity> call, Throwable t) {
                Log.e("Create character", t.getMessage());
            }
        });

    }
}

