package pl.conquerors.app.view.createGame;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import butterknife.OnClick;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.model.CharacterEntity;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.rest.RestClient;
import pl.conquerors.app.util.SharedPreferenceUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;
import static pl.conquerors.app.model.mapper.CharacterEntityMapper.transform;


public class createGameActivity extends BaseActivity implements createGameView {
    int userId = 0;
    int characters_count = 0;
    public String[] game_values = new String[4];
    public List<Character> characters;
    public static Intent getStartingIntents(Context context){
        return new Intent(context, createGameActivity.class);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        userId = (int) SharedPreferenceUtil.getUser(this.getContext()).getmId();
        getCharacters(userId);


    }
    int[] images = {R.drawable.female_256,R.drawable.male_256};
    String [] txts = {"Female character","Male character"};
    private int value = 0;
    @OnClick(R.id.previousButton)
    public void onLeftButtonClicked(){
        TextView txtview = (TextView) findViewById(R.id.characterName);
        try {
            characters_count = characters.size();
            //ImageView imgview = (ImageView)findViewById(R.id.characterView);
            value--;
            if (value < 0) {
                value = characters_count-1;
            }
            //imgview.setImageResource(images[value]);
            txtview.setText(characters.get(value).getmNickname());
        }
        catch(NullPointerException e){txtview.setText("Brak postaci");}
    }
    @OnClick(R.id.nextButton)
    public void onRightButtonClicked(){
        TextView txtview = (TextView) findViewById(R.id.characterName);
        try {
            characters_count = characters.size();
            //ImageView imgview = (ImageView)findViewById(R.id.characterView);
            value++;
            if (value > characters_count - 1) {
                value = 0;
            }
            //imgview.setImageResource(images[value]);
            txtview.setText(characters.get(value).getmNickname());
        }
        catch(NullPointerException e){txtview.setText("Brak postaci");}
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

    public void getCharacters(int userId) {
        Call<List<CharacterEntity>> call = RestClient.getInstance().getCharacter(userId);

        call.enqueue(new Callback<List<CharacterEntity>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<CharacterEntity>> call, Response<List<CharacterEntity>> response) {
                characters = transform(response.body());
            }

            @Override
            public void onFailure(Call<List<CharacterEntity>> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }



}
