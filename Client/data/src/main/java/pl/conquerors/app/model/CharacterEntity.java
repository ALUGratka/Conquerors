package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class CharacterEntity {
    /**
     * variables
     **/

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

    @SerializedName("nickname")
    private String nickname;

    @SerializedName("sex")
    private int sex;

    @SerializedName("characterClass")
    private int characterClass;

    @SerializedName("hair")
    private int hair;

    @SerializedName("hat")
    private int hat;

    @SerializedName("eyeColor")
    private int eyeColor;

    @SerializedName("blouse")
    private int blouse;

    @SerializedName("pants")
    private int pants;

    @SerializedName("shoes")
    private int shoes;

    @SerializedName("userId")
    private int userId;

    @SerializedName("skillPoints")
    private int skillPoints;

    public CharacterEntity(int level, int charisma, int intelligence, int agility,
                           int strength, String nickname, int sex, int characterClass,
                           int hair, int hat, int eyeColor, int blouse, int pants,
                           int shoes, int userId, int skillPoints) {
        this.level = level;
        this.charisma = charisma;
        this.intelligence = intelligence;
        this.agility = agility;
        this.strength = strength;
        this.nickname = nickname;
        this.sex = sex;
        this.characterClass = characterClass;
        this.hair = hair;
        this.hat = hat;
        this.eyeColor = eyeColor;
        this.blouse = blouse;
        this.pants = pants;
        this.shoes = shoes;
        this.userId = userId;
        this.skillPoints = skillPoints;
    }

    /**
     * getters
     **/

    public int getBlouse() {
        return blouse;
    }

    public int getCharacterClass() {
        return characterClass;
    }

    public String getNickname() {
        return nickname;
    }

    public int getSex() {
        return sex;
    }

    public int getEyeColor() {
        return eyeColor;
    }

    public int getLevel() {
        return level;
    }

    public int getId() {
        return id;
    }

    public int getAgility() {
        return agility;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getShoes() {
        return shoes;
    }

    public int getPants() {
        return pants;
    }

    public int getUserId() {
        return userId;
    }

    public int getStrength() {
        return strength;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getHair() {
        return hair;
    }

    public int getHat() {
        return hat;
    }

    public int getSkillPoints() {return skillPoints;}

    /**
     * setters
     **/

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public void setPants(int pants) {
        this.pants = pants;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setCharacterClass(int characterClass) {
        this.characterClass = characterClass;
    }

    public void setHair(int hair) {
        this.hair = hair;
    }

    public void setHat(int hat) {
        this.hat = hat;
    }

    public void setEyeColor(int eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setBlouse(int blouse) {
        this.blouse = blouse;
    }

    public void setShoes(int shoes) {
        this.shoes = shoes;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setSkillPoints(int skillPoints) {this.skillPoints = skillPoints;}
}
