package pl.conquerors.app.view.gameplay;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.EnemiesAchievement;
import pl.conquerors.app.domain.model.Gameplay;
import pl.conquerors.app.domain.model.TreasureAchievement;
import pl.conquerors.app.model.EnemiesAchievementEntity;
import pl.conquerors.app.model.TreasureAchievementEntity;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

import pl.conquerors.app.model.mapper.TreasureAchievementMapper;
import pl.conquerors.app.model.mapper.EnemiesAchievementEntityMapper;

public class Map extends View {
    private final int TILE_SIZE = 100;
    public static int[][] tileMap;
    public static int[][] overlay;
    private static int rows, columns;
    private Drawable box, enemy1, enemy2, enemy3, enemy4, enemy5, enemy6, enemy7, enemy8, enemy9, enemy10, enemy11, enemy12, enemy13, enemy14, enemy15;
    private Drawable treasure1, treasure2, treasure3, treasure4, treasure5, treasure6, treasure7, treasure8;
    private Drawable treasure9, treasure10, treasure11, treasure12, treasure13, treasure14, treasure15;
    private Drawable popupBackground;
    Paint strokePaint, fillPaint, namePaint, description1Paint, description2Paint;
    Paint agilityPaint, charismaPaint, strengthPaint, intelligencePaint;
    Paint cancelTextPaint, collectTextPaint, runTextPaint, fightTextPaint;
    List<TreasureAchievement> treasureAchievements;
    List<EnemiesAchievement> enemiesAchievements;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Map(Context context) {
        super(context);
        box = getResources().getDrawable(R.mipmap.skarb, null);
        box.mutate().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
        enemy1 = getResources().getDrawable(R.drawable.enemy1, null);
        enemy2 = getResources().getDrawable(R.drawable.enemy2, null);
        enemy3 = getResources().getDrawable(R.drawable.enemy3, null);
        enemy4 = getResources().getDrawable(R.drawable.enemy4, null);
        enemy5 = getResources().getDrawable(R.drawable.enemy5, null);
        enemy6 = getResources().getDrawable(R.drawable.enemy6, null);
        enemy7 = getResources().getDrawable(R.drawable.enemy7, null);
        enemy8 = getResources().getDrawable(R.drawable.enemy8, null);
        enemy9 = getResources().getDrawable(R.drawable.enemy9, null);
        enemy10 = getResources().getDrawable(R.drawable.enemy10, null);
        enemy11 = getResources().getDrawable(R.drawable.enemy11, null);
        enemy12 = getResources().getDrawable(R.drawable.enemy12, null);
        enemy13 = getResources().getDrawable(R.drawable.enemy13, null);
        enemy14 = getResources().getDrawable(R.drawable.enemy14, null);
        enemy15 = getResources().getDrawable(R.drawable.enemy15, null);

        treasure1 = getResources().getDrawable(R.drawable.treasure1, null);
        treasure2 = getResources().getDrawable(R.drawable.treasure2, null);
        treasure3 = getResources().getDrawable(R.drawable.treasure3, null);
        treasure4 = getResources().getDrawable(R.drawable.treasure4, null);
        treasure5 = getResources().getDrawable(R.drawable.treasure5, null);
        treasure6 = getResources().getDrawable(R.drawable.treasure6, null);
        treasure7 = getResources().getDrawable(R.drawable.treasure7, null);
        treasure8 = getResources().getDrawable(R.drawable.treasure8, null);
        treasure9 = getResources().getDrawable(R.drawable.treasure9, null);
        treasure10 = getResources().getDrawable(R.drawable.treasure10, null);
        treasure11 = getResources().getDrawable(R.drawable.treasure11, null);
        treasure12 = getResources().getDrawable(R.drawable.treasure12, null);
        treasure13 = getResources().getDrawable(R.drawable.treasure13, null);
        treasure14 = getResources().getDrawable(R.drawable.treasure14, null);
        treasure15 = getResources().getDrawable(R.drawable.treasure15, null);

        popupBackground = getResources().getDrawable(R.drawable.popup_background, null);

        fillPaint = new Paint();
        strokePaint = new Paint();
        namePaint = new Paint();
        description1Paint = new Paint();
        description2Paint = new Paint();
        agilityPaint = new Paint();
        charismaPaint = new Paint();
        strengthPaint = new Paint();
        intelligencePaint = new Paint();
        cancelTextPaint = new Paint();
        collectTextPaint = new Paint();
        runTextPaint = new Paint();
        fightTextPaint = new Paint();

        namePaint.setColor(Color.BLACK);
        namePaint.setTextSize(50);
        description1Paint.setColor(Color.BLACK);
        description1Paint.setTextSize(40);
        description2Paint.setColor(Color.BLACK);
        description2Paint.setTextSize(40);

        agilityPaint.setColor(Color.BLACK);
        agilityPaint.setTextSize(35);
        charismaPaint.setColor(Color.BLACK);
        charismaPaint.setTextSize(35);
        strengthPaint.setColor(Color.BLACK);
        strengthPaint.setTextSize(35);
        intelligencePaint.setColor(Color.BLACK);
        intelligencePaint.setTextSize(35);

        cancelTextPaint.setColor(Color.WHITE);
        cancelTextPaint.setTextSize(50);
        collectTextPaint.setColor(Color.WHITE);
        collectTextPaint.setTextSize(50);
        runTextPaint.setColor(Color.WHITE);
        runTextPaint.setTextSize(50);
        fightTextPaint.setColor(Color.WHITE);
        fightTextPaint.setTextSize(50);

        create_map();
        Gameplay game = SharedPreferenceUtil.getGameplayId(this.getContext());

        getTreasureAchievement(game.getId());
        getEnemiesAchievement(game.getId());
    }

    public void create_map() {
        tileMap = new int[30][20];
        rows = tileMap.length;
        columns = tileMap[1].length;
        Random randomValue = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int r = randomValue.nextInt(10);
                if (r % 10 != 1) {
                    tileMap[i][j] = 1;
                } else {
                    tileMap[i][j] = 0;
                }
            }
        }
        overlay = tileMap;
        overlay[2][15] = 3;
    }

    public void makeTreasures() {
        for (TreasureAchievement treasureAchievement : treasureAchievements) {
            int x = Integer.parseInt(treasureAchievement.getmObjectPositionX());
            int y = Integer.parseInt(treasureAchievement.getmObjectPositionY());
            int treasureId = treasureAchievement.getmTreasureId();
            overlay[x][y] = 10 + treasureId;
        }
    }

    public void makeEnemies() {
        for (EnemiesAchievement enemiesAchievement : enemiesAchievements) {
            int x = Integer.parseInt(enemiesAchievement.getmObjectPositionX());
            int y = Integer.parseInt(enemiesAchievement.getmObjectPositionY());
            int enemyId = enemiesAchievement.getmEnemyId();
            overlay[x][y] = 30 + enemyId;
        }
    }

    public void showTreasurePopUp(Canvas canvas, Character character) {
        if (character.currentTreasure != null && !character.currentTreasure.equals(character.pastTreasure)) {
            //character.pastTreasure = character.currentTreasure;
            int id = character.currentTreasure.getmId();
            String name = character.currentTreasure.getmName();
            String description = character.currentTreasure.getmDescription();
            int agility = character.currentTreasure.getmAgility();
            int charisma = character.currentTreasure.getmCharisma();
            int intelligence = character.currentTreasure.getmIntelligence();
            int strength = character.currentTreasure.getmStrength();
            int skillPoints = character.currentTreasure.getmSkillPoints();

            int size = 40;
            List<String> descriptionParts = new ArrayList<String>((description.length() + size - 1) / size);

            for (int start = 0; start < description.length(); start += size) {
                descriptionParts.add(description.substring(start, Math.min(description.length(), start + size)));
            }

            popupBackground.setBounds(1 * TILE_SIZE, 1 * TILE_SIZE, 9 * TILE_SIZE + TILE_SIZE, 10 * TILE_SIZE + TILE_SIZE);
            popupBackground.draw(canvas);
            canvas.drawText(name, 2 * TILE_SIZE, 6 * TILE_SIZE, namePaint);
            if (descriptionParts.size() >= 1) {
                canvas.drawText(descriptionParts.get(0), 2 * TILE_SIZE, (float) 6.5 * TILE_SIZE, description1Paint);
            }
            if (descriptionParts.size() >= 2) {
                canvas.drawText(descriptionParts.get(1), 2 * TILE_SIZE, (float) 7.0 * TILE_SIZE, description2Paint);
            }
            canvas.drawText("Zrecznosc: " + String.valueOf(agility), 2 * TILE_SIZE, (float) 8.0 * TILE_SIZE, agilityPaint);
            canvas.drawText("Sila: " + String.valueOf(strength), 2 * TILE_SIZE, (float) 8.5 * TILE_SIZE, agilityPaint);
            canvas.drawText("Inteligencja: " + String.valueOf(intelligence), 6 * TILE_SIZE, (float) 8.0 * TILE_SIZE, agilityPaint);
            canvas.drawText("Charyzma: " + String.valueOf(charisma), 6 * TILE_SIZE, (float) 8.5 * TILE_SIZE, agilityPaint);

            canvas.drawRect(2*TILE_SIZE, (float)9.0*TILE_SIZE, 5*TILE_SIZE, (float)10*TILE_SIZE,character.cancelButtonPaint);
            canvas.drawRect(6*TILE_SIZE, (float)9.0*TILE_SIZE, 9*TILE_SIZE, (float)10*TILE_SIZE,character.collectButtonPaint);
            canvas.drawText("ZREZYGNUJ", (float) 2.1 * TILE_SIZE, (float) 9.5 * TILE_SIZE, cancelTextPaint);
            canvas.drawText("WEZ", (float) 7 * TILE_SIZE, (float) 9.5 * TILE_SIZE, cancelTextPaint);

            switch (id) {
                case 1:
                    treasure1.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure1.draw(canvas);
                    break;
                case 2:
                    treasure2.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure2.draw(canvas);
                    break;
                case 3:
                    treasure3.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure3.draw(canvas);
                    break;
                case 4:
                    treasure4.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure4.draw(canvas);
                    break;
                case 5:
                    treasure5.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure5.draw(canvas);
                    break;
                case 6:
                    treasure6.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure6.draw(canvas);
                    break;
                case 7:
                    treasure7.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure7.draw(canvas);
                    break;
                case 8:
                    treasure8.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure8.draw(canvas);
                    break;
                case 9:
                    treasure9.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure9.draw(canvas);
                    break;
                case 10:
                    treasure10.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure10.draw(canvas);
                    break;
                case 11:
                    treasure11.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure11.draw(canvas);
                    break;
                case 12:
                    treasure12.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure12.draw(canvas);
                    break;
                case 13:
                    treasure13.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure13.draw(canvas);
                    break;
                case 14:
                    treasure14.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure14.draw(canvas);
                    break;
                case 15:
                    treasure15.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    treasure15.draw(canvas);
                    break;
            }

        }

    }

    public void showEnemyPopUp(Canvas canvas, Character character) {
        if (character.currentEnemy != null && !character.currentEnemy.equals(character.pastEnemy)) {
            //character.pastEnemy = character.currentEnemy;
            int id = character.currentEnemy.getmId();
            String name = character.currentEnemy.getmName();
            String description = character.currentEnemy.getmDescription();
            int agility = character.currentEnemy.getmAgility();
            int charisma = character.currentEnemy.getmCharisma();
            int intelligence = character.currentEnemy.getmIntelligence();
            int strength = character.currentEnemy.getmStrength();

            int size = 40;
            List<String> descriptionParts = new ArrayList<String>((description.length() + size - 1) / size);

            for (int start = 0; start < description.length(); start += size) {
                descriptionParts.add(description.substring(start, Math.min(description.length(), start + size)));
            }

            popupBackground.setBounds(1 * TILE_SIZE, 1 * TILE_SIZE, 9 * TILE_SIZE + TILE_SIZE, 10 * TILE_SIZE + TILE_SIZE);
            popupBackground.draw(canvas);
            canvas.drawText(name, 2 * TILE_SIZE, 6 * TILE_SIZE, namePaint);
            if (descriptionParts.size() >= 1) {
                canvas.drawText(descriptionParts.get(0), 2 * TILE_SIZE, (float) 6.5 * TILE_SIZE, description1Paint);
            }
            if (descriptionParts.size() >= 2) {
                canvas.drawText(descriptionParts.get(1), 2 * TILE_SIZE, (float) 7.0 * TILE_SIZE, description2Paint);
            }

            canvas.drawRect(2*TILE_SIZE, (float)9.0*TILE_SIZE, 5*TILE_SIZE, (float)10*TILE_SIZE,character.runButtonPaint);
            canvas.drawRect(6*TILE_SIZE, (float)9.0*TILE_SIZE, 9*TILE_SIZE, (float)10*TILE_SIZE,character.fightButtonPaint);
            canvas.drawText("UCIEKAJ", (float) 2.1 * TILE_SIZE, (float) 9.5 * TILE_SIZE, runTextPaint);
            canvas.drawText("WALCZ", (float) 7 * TILE_SIZE, (float) 9.5 * TILE_SIZE, fightTextPaint);


            switch (id) {
                case 1:
                    enemy1.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy1.draw(canvas);
                    break;
                case 2:
                    enemy2.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy2.draw(canvas);
                    break;
                case 3:
                    enemy3.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy3.draw(canvas);
                    break;
                case 4:
                    enemy4.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy4.draw(canvas);
                    break;
                case 5:
                    enemy5.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy5.draw(canvas);
                    break;
                case 6:
                    enemy6.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy6.draw(canvas);
                    break;
                case 7:
                    enemy7.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy7.draw(canvas);
                    break;
                case 8:
                    enemy8.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy8.draw(canvas);
                    break;
                case 9:
                    enemy9.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy9.draw(canvas);
                    break;
                case 10:
                    enemy10.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy10.draw(canvas);
                    break;
                case 11:
                    enemy11.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy11.draw(canvas);
                    break;
                case 12:
                    enemy12.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy12.draw(canvas);
                    break;
                case 13:
                    enemy13.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy13.draw(canvas);
                    break;
                case 14:
                    enemy14.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy14.draw(canvas);
                    break;
                case 15:
                    enemy15.setBounds(2 * TILE_SIZE, 2 * TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE, 4 * TILE_SIZE + TILE_SIZE);
                    enemy15.draw(canvas);
                    break;
            }

        }

    }

    public void show_move() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                if (overlay[i][j] == 3) {

                    try {
                        if (overlay[i + 1][j + 1] != 0 && overlay[i + 1][j + 1] < 5)
                            if (!(overlay[i][j + 1] == 0 && overlay[i + 1][j] == 0))
                                overlay[i + 1][j + 1] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i + 1][j - 1] != 0 && overlay[i + 1][j - 1] < 5)
                            if (!(overlay[i][j - 1] == 0 && overlay[i + 1][j] == 0))
                                overlay[i + 1][j - 1] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i - 1][j + 1] != 0 && overlay[i - 1][j + 1] < 5)
                            if (!(overlay[i - 1][j] == 0 && overlay[i][j + 1] == 0))
                                overlay[i - 1][j + 1] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i - 1][j - 1] != 0 && overlay[i - 1][j - 1] < 5)
                            if (!(overlay[i][j - 1] == 0 && overlay[i - 1][j] == 0))
                                overlay[i - 1][j - 1] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i][j + 1] != 0 && overlay[i][j + 1] < 5)
                            overlay[i][j + 1] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i][j - 1] != 0 && overlay[i][j - 1] < 5)
                            overlay[i][j - 1] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i + 1][j] != 0 && overlay[i + 1][j] < 5)
                            overlay[i + 1][j] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }

                    try {
                        if (overlay[i - 1][j] != 0 && overlay[i - 1][j] < 5)
                            overlay[i - 1][j] = 4;
                    } catch (ArrayIndexOutOfBoundsException ignored) {
                    }
                }
            }
        }
    }

    public void showMap(Canvas canvas, Character character) {

        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setColor(Color.BLACK);
        strokePaint.setStrokeWidth(3);

        show_move();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int pos_i = TILE_SIZE * i;
                int pos_j = TILE_SIZE * j;

                if (overlay[i][j] == 1) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.GREEN);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] == 0) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.RED);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] == 4) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.YELLOW);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] == 3) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.WHITE);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] > 10 && overlay[i][j] < 30) {
                    box.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                    box.draw(canvas);
                    canvas.drawRect(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE, strokePaint);
                } else if (overlay[i][j] > 30) {
                    fillPaint.setStyle(Paint.Style.FILL);
                    fillPaint.setColor(Color.GREEN);

                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, fillPaint);
                    int enemyId = overlay[i][j] - 30;
                    switch (enemyId) {
                        case 1:
                            enemy1.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy1.draw(canvas);
                            break;
                        case 2:
                            enemy2.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy2.draw(canvas);
                            break;
                        case 3:
                            enemy3.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy3.draw(canvas);
                            break;
                        case 4:
                            enemy4.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy4.draw(canvas);
                            break;
                        case 5:
                            enemy5.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy5.draw(canvas);
                            break;
                        case 6:
                            enemy6.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy6.draw(canvas);
                            break;
                        case 7:
                            enemy7.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy7.draw(canvas);
                            break;
                        case 8:
                            enemy8.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy8.draw(canvas);
                            break;
                        case 9:
                            enemy9.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy9.draw(canvas);
                            break;
                        case 10:
                            enemy10.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy10.draw(canvas);
                            break;
                        case 11:
                            enemy11.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy11.draw(canvas);
                            break;
                        case 12:
                            enemy12.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy12.draw(canvas);
                            break;
                        case 13:
                            enemy13.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy13.draw(canvas);
                            break;
                        case 14:
                            enemy14.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy14.draw(canvas);
                            break;
                        case 15:
                            enemy15.setBounds(i * TILE_SIZE, j * TILE_SIZE, i * TILE_SIZE + TILE_SIZE, j * TILE_SIZE + TILE_SIZE);
                            enemy15.draw(canvas);
                            break;
                    }
                    canvas.drawRect(pos_i, pos_j, pos_i + TILE_SIZE, pos_j + TILE_SIZE, strokePaint);
                }
            }
        }
        showTreasurePopUp(canvas, character);
        showEnemyPopUp(canvas, character);
    }


    public void update() {

    }

    public void getTreasureAchievement(int gameId) {
        Call<List<TreasureAchievementEntity>> call = RestClient.getInstance().getTreasuresAchievement(gameId);

        call.enqueue(new Callback<List<TreasureAchievementEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<TreasureAchievementEntity>> call, Response<List<TreasureAchievementEntity>> response) {
                treasureAchievements = TreasureAchievementMapper.transform(response.body());
                makeTreasures();
            }

            @Override
            public void onFailure(Call<List<TreasureAchievementEntity>> call, Throwable throwable) {
                treasureAchievements = new ArrayList<>();
                Log.e(TAG, throwable.toString());
            }
        });
    }

    public void getEnemiesAchievement(int gameId) {
        Call<List<EnemiesAchievementEntity>> call = RestClient.getInstance().getEnemiesAchievement(gameId);

        call.enqueue(new Callback<List<EnemiesAchievementEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<EnemiesAchievementEntity>> call, Response<List<EnemiesAchievementEntity>> response) {
                enemiesAchievements = EnemiesAchievementEntityMapper.transform(response.body());
                makeEnemies();
            }

            @Override
            public void onFailure(Call<List<EnemiesAchievementEntity>> call, Throwable throwable) {
                enemiesAchievements = new ArrayList<>();
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
