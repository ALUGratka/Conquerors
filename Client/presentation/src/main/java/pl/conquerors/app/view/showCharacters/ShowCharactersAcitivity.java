package pl.conquerors.app.view.showCharacters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class ShowCharactersAcitivity  extends BaseActivity implements ShowCharactersView{
    ShowCharactersPresenter mCharacterPresenter;
    int userId = 0;
    public static Intent getStartingIntents(Context context) {
        return new Intent(context, ShowCharactersAcitivity.class);
    }

    @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_show_characters);
            CreateCharacterUseCase characterUseCase = new CreateCharacterUseCase(new AndroidComposedScheduler());

            mCharacterPresenter = new ShowCharactersPresenter(characterUseCase);
            userId = (int) SharedPreferenceUtil.getUser(this.getContext()).getmId();
            mCharacterPresenter.getCharacters(userId);
            mCharacterPresenter.setmView(this);
    }

    public void showCharacters(List<Character> characters){
        TableLayout stk = (TableLayout) findViewById(R.id.characters);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Nickname ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Level ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        stk.addView(tbrow0);
        for (Character character:characters) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(character.getmNickname());
            t1v.setTextColor(Color.BLACK);
            t1v.setGravity(Gravity.CENTER);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(Integer.toString(character.getmLevel()));
            t2v.setTextColor(Color.BLACK);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            // Create Button
            final Button btn = new Button(this);
            // Give button an ID
            btn.setId(character.getmId());
            btn.setText("Show More");
            btn.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   characterInfo(character);
               }
            });
            tbrow.addView(btn);
            stk.addView(tbrow);
        }
    }
    public void characterInfo(Character ch)
    {
        //TODO: about character
        setContentView(R.layout.activity_statistic);
        TextView nickname = findViewById(R.id.displayCharacterNicknameText);
        TextView level = (TextView) findViewById(R.id.displayCharacterLevel);
        TextView strength = (TextView) findViewById(R.id.displayCurrentCharacterStrength);
        TextView agility = (TextView) findViewById(R.id.displayCurrentCharacterAgility);
        TextView charisma = (TextView) findViewById(R.id.displayCurrentCharacterCharisma);
        TextView intelligence = (TextView) findViewById(R.id.displayCurrentCharacterIntelligence);
        TextView skillpoints = (TextView) findViewById(R.id.displayUserSkillPoints);
        TextView skillpoints_description = (TextView) findViewById(R.id.userSkillpoints);
        TextView characterClassText = (TextView) findViewById(R.id.displayCharacterClassText);

        skillpoints.setVisibility(View.INVISIBLE);
        skillpoints_description.setVisibility(View.INVISIBLE);
        nickname.setText(ch.getmNickname());
        level.setText(Integer.toString(ch.getmLevel()));
        strength.setText(Integer.toString(ch.getmStrength()));
        agility.setText(Integer.toString(ch.getmAgility()));
        charisma.setText(Integer.toString(ch.getmCharisma()));
        intelligence.setText(Integer.toString(ch.getmIntelligence()));
        Character.CharacterClass arg_class = ch.getmCharacterClass();
        characterClassText.setText(arg_class.toString());

    }
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
