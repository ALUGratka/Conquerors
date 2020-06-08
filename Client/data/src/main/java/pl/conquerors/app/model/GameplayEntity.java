package pl.conquerors.app.model;

import com.google.gson.annotations.SerializedName;

import pl.conquerors.app.domain.model.Gameplay;

public class GameplayEntity {
    @SerializedName("id")
    private int id;

    @SerializedName("player1id")
    private int player1id;

    @SerializedName("player2id")
    private int player2id;

    @SerializedName("character1id")
    private int character1id;

    @SerializedName("character2id")
    private int character2id;

    @SerializedName("turn")
    private int turn;

    @SerializedName("round")
    private int round; //liczba tur

    @SerializedName("player1PositionX")
    private int player1PositionX;

    @SerializedName("player1PositionY")
    private int player1PositionY;

    @SerializedName("player2PositionX")
    private int player2PositionX;

    @SerializedName("player2PositionY")
    private int player2PositionY;

    @SerializedName("canPlay1")
    private boolean canPlay1;

    @SerializedName("canPlay2")
    private boolean canPlay2;

    @SerializedName("canAccept1")
    private boolean canAccept1;

    @SerializedName("canAccept2")
    private boolean canAccept2;

    public GameplayEntity(int id, int player1id, int player2id, int character1id, int character2id,
                          int turn, int round, int player1PositionX, int player1PositionY,
                          int player2PositionX, int player2PositionY, boolean canPlay1,
                          boolean canPlay2, boolean canAccept1, boolean canAccept2) {
        this.id = id;
        this.player1id = player1id;
        this.player2id = player2id;
        this.character1id = character1id;
        this.character2id = character2id;
        this.turn = turn;
        this.round = round;
        this.player1PositionX = player1PositionX;
        this.player1PositionY = player1PositionY;
        this.player2PositionX = player2PositionX;
        this.player2PositionY = player2PositionY;
        this.canPlay1 = canPlay1;
        this.canPlay2 = canPlay2;
        this.canAccept1 = canAccept1;
        this.canAccept2 = canAccept2;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer1id() {
        return player1id;
    }

    public void setPlayer1id(int player1id) {
        this.player1id = player1id;
    }

    public int getPlayer2id() {
        return player2id;
    }

    public void setPlayer2id(int player2id) {
        this.player2id = player2id;
    }

    public int getCharacter1id() {
        return character1id;
    }

    public void setCharacter1id(int character1id) {
        this.character1id = character1id;
    }

    public int getCharacter2id() {
        return character2id;
    }

    public void setCharacter2id(int character2id) {
        this.character2id = character2id;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getPlayer1PositionX() {
        return player1PositionX;
    }

    public void setPlayer1PositionX(int player1PositionX) {
        this.player1PositionX = player1PositionX;
    }

    public int getPlayer1PositionY() {
        return player1PositionY;
    }

    public void setPlayer1PositionY(int player1PositionY) {
        this.player1PositionY = player1PositionY;
    }

    public int getPlayer2PositionX() {
        return player2PositionX;
    }

    public void setPlayer2PositionX(int player2PositionX) {
        this.player2PositionX = player2PositionX;
    }

    public int getPlayer2PositionY() {
        return player2PositionY;
    }

    public void setPlayer2PositionY(int player2PositionY) {
        this.player2PositionY = player2PositionY;
    }

    public boolean isCanPlay1() {
        return canPlay1;
    }

    public void setCanPlay1(boolean canPlay1) {
        this.canPlay1 = canPlay1;
    }

    public boolean isCanPlay2() {
        return canPlay2;
    }

    public void setCanPlay2(boolean canPlay2) {
        this.canPlay2 = canPlay2;
    }

    public boolean isCanAccept1() {
        return canAccept1;
    }

    public void setCanAccept1(boolean canAccept1) {
        this.canAccept1 = canAccept1;
    }

    public boolean isCanAccept2() {
        return canAccept2;
    }

    public void setCanAccept2(boolean canAccept2) {
        this.canAccept2 = canAccept2;
    }
}
