package pl.conquerors.app.view.createGame;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.navigation.Navigator;


public class createGameActivity extends BaseActivity implements createGameView {

    public String[] game_values;
    public static Intent getStartingIntents(Context context){
        return new Intent(context, createGameActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        CharacterEntity character;

    }
    int[] images = {R.drawable.female_256,R.drawable.male_256};
    String [] txts = {"Female character","Male character"};
    private int value = 0;
    @OnClick(R.id.previousButton)
    public void onLeftButtonClicked(){
        TextView txtview = (TextView)findViewById(R.id.characterName);
        ImageView imgview = (ImageView)findViewById(R.id.characterView);
        value++;
        value= value % 2;
        imgview.setImageResource(images[value]);
        txtview.setText(txts[value]);
    }
    @OnClick(R.id.nextButton)
    public void onRightButtonClicked(){
        TextView txtview = (TextView)findViewById(R.id.characterName);
        ImageView imgview = (ImageView)findViewById(R.id.characterView);
        value++;
        value= value % 2;
        imgview.setImageResource(images[value]);
        txtview.setText(txts[value]);
    }

    @OnClick(R.id.backButton)
    public void onPreviousButtonClicked(){
        game_values[0]="1";
        game_values[1]=txts[value];
        game_values[2]=String.valueOf(images[value]);
        game_values[3]= String.valueOf(Calendar.getInstance().getTime());

        Navigator.startHome(this);
    }

    @OnClick(R.id.forthButton)
    public void onNextButtonClicked(){

        Navigator.startChooseOpponent(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public String[] getGame_values(){
        return game_values;
    }

}
