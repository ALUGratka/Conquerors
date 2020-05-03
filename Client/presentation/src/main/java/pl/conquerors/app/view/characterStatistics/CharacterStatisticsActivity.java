package pl.conquerors.app.view.characterStatistics;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @BindView(R.id.displayCharacterLevel)
    TextView level;

    @BindView(R.id.displayUserSkillPoints)
    TextView skillPoints;

    @BindView(R.id.displayCharacterNicknameText)
    TextView nickname;

    @BindView(R.id.displayCharacterClassText)
    TextView characterClass;

    CharacterStatisticsPresenter mCharacterStatisticsPresenter;

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CharacterStatisticsActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
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
    public void updateStatistics(CharacterStatistics characterStatistics) {
        strength.setText(String.valueOf(characterStatistics.getmStrength()));
        charisma.setText(String.valueOf(characterStatistics.getmCharisma()));
        agility.setText(String.valueOf(characterStatistics.getmAgility()));
        intelligence.setText(String.valueOf(characterStatistics.getmIntelligence()));
        level.setText(String.valueOf(characterStatistics.getmLevel()));
        skillPoints.setText(String.valueOf(characterStatistics.getmSkillPoints()));
        nickname.setText(characterStatistics.getmNickname());
        characterClass.setText(characterStatistics.getmCharacterClass());
    }
}
