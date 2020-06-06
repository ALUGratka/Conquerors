package pl.conquerors.app.view.characterStatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.characterStatistics.CharacterStatisticsUseCase;
import pl.conquerors.app.domain.model.CharacterStatistics;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;

public class CharacterStatisticsActivity extends BaseActivity implements CharacterStatisticsView{

    @BindView(R.id.displayCurrentCharacterStrength)
    TextView strength;

    @BindView(R.id.displayCurrentCharacterCharisma)
    TextView charisma;

    @BindView(R.id.displayCurrentCharacterAgility)
    TextView agility;

    @BindView(R.id.displayCurrentCharacterIntelligence)
    TextView intelligence;

    @BindView(R.id.displayCharacterNicknameText)
    TextView nickname;

    @BindView(R.id.displayCharacterClassText)
    TextView characterClass;

    @BindView(R.id.characterClassImage)
    ImageView characterClassImage;

    @BindView(R.id.characterClassSignature)
    ImageView characterClassSignatureImage;

    CharacterStatisticsPresenter mCharacterStatisticsPresenter;
    static private int characterId;

    public static Intent getStartingIntents(Context context, final int id) {
        characterId = id;
        return new Intent(context, CharacterStatisticsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_details);
        CharacterStatisticsUseCase statisticsUseCase = new CharacterStatisticsUseCase(new AndroidComposedScheduler());

        mCharacterStatisticsPresenter = new CharacterStatisticsPresenter(statisticsUseCase);
        mCharacterStatisticsPresenter.setmView(this);
        mCharacterStatisticsPresenter.getCharacterStatistic();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @Override
    public int getCharacterId() {
        return characterId;
    }

    @Override
    public void updateStatistics(CharacterStatistics characterStatistics) {
        strength.setText(String.valueOf(characterStatistics.getmStrength()));
        charisma.setText(String.valueOf(characterStatistics.getmCharisma()));
        agility.setText(String.valueOf(characterStatistics.getmAgility()));
        intelligence.setText(String.valueOf(characterStatistics.getmIntelligence()));
        nickname.setText(characterStatistics.getmNickname());
        Log.e("class", characterStatistics.getmCharacterClass());
        setCharacterClass(characterStatistics.getmCharacterClass());
    }


    private void setCharacterClass(final String characterClassIndex) {
        if(characterClassIndex.equals("0")) {
            characterClass.setText(getString(R.string.radio_bard));
            characterClassSignatureImage.setImageResource(R.drawable.ic_bard_class);
        }
        else if(characterClassIndex.equals("1")) {
            characterClass.setText(getString(R.string.radio_thief));
            characterClassSignatureImage.setImageResource(R.drawable.ic_thief_class);
        }
        else if(characterClassIndex.equals("2")) {
            characterClass.setText(getString(R.string.radio_warrior));
            characterClassSignatureImage.setImageResource(R.drawable.ic_warior_class);
        }
        else if(characterClassIndex.equals("3")) {
            characterClass.setText(getString(R.string.radio_wizard));
            characterClassSignatureImage.setImageResource(R.drawable.ic_wizard_class);
        }
    }
}
