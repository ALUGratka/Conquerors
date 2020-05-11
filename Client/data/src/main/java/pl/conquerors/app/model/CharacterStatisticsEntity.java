package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

import pl.conquerors.app.domain.model.CharacterStatistics;

public class CharacterStatisticsEntity {
    @SerializedName("id")
    private int id;

    @SerializedName("level")
    private int level;

    @SerializedName("charisma")
    private int charisma;

    @SerializedName("intelligence")
    private int intelligence;

    @SerializedName("agility")
    private int agility;

    @SerializedName("strength")
    private int strength;

    @SerializedName("skillPoints")
    private int skillPoints;

    @SerializedName("characterClass")
    private String characterClass;

    @SerializedName("nickname")
    private String nickname;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public CharacterStatisticsEntity(int level, int charisma, int intelligence, int agility,
                                     int strength, int skillPoints, String characterClass, String nickname) {
        this.level = level;
        this.charisma = charisma;
        this.intelligence = intelligence;
        this.agility = agility;
        this.strength = strength;
        this.skillPoints = skillPoints;
        this.nickname = nickname;
        this.characterClass = characterClass;
    }
}
