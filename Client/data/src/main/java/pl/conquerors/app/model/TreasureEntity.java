package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class TreasureEntity {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

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

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getAgility() {
        return agility;
    }
}
