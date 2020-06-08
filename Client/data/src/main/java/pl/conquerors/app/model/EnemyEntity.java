package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

public class EnemyEntity {
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

    public String getDescription() {
        return description;
    }

    public void setStrength(int strength) {
        this.strength = strength;
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

    public int getIntelligence() {
        return intelligence;
    }

    public int getId() {
        return id;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getAgility() {
        return agility;
    }
}
