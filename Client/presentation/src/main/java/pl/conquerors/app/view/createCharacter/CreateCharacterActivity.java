package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.domain.model.User;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.createCharacter.Fragments.AppearanceFragment;
import pl.conquerors.app.view.createCharacter.Fragments.ClassFragment;
import pl.conquerors.app.view.createCharacter.Fragments.NameFragment;
import pl.conquerors.app.view.createCharacter.Fragments.SexFragment;
import pl.conquerors.app.view.createCharacter.Fragments.SummaryFragment;

public class CreateCharacterActivity extends BaseActivity implements CreateCharacterView {

    @BindView(R.id.previousFragmentButton)
    Button previousScreenButton;

    @BindView(R.id.nextFragmentButton)
    Button nextScreenButton;

    @BindView(R.id.fragment)
    FrameLayout fragment;

    SexFragment sexFragment = new SexFragment();
    ClassFragment classFragment = new ClassFragment();
    AppearanceFragment appearanceFragment = new AppearanceFragment();
    NameFragment nameFragment = new NameFragment();
    SummaryFragment summaryFragment = new SummaryFragment();

    int currentFragment = 0;

    CreateCharacterUseCase createCharacterUseCase;
    CreateCharacterPresenter createCharacterPresenter;

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CreateCharacterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        createCharacterUseCase = new CreateCharacterUseCase(new AndroidComposedScheduler());
        createCharacterPresenter = new CreateCharacterPresenter();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment, sexFragment, "SEX_FRAGMENT_TAG");
        transaction.addToBackStack("SEX_FRAGMENT_TAG");
        transaction.commit();
    }

    @OnClick(R.id.nextFragmentButton)
    public void onNextButtonClicked() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();


        switch (currentFragment) {
            case 0:
                int sex = SharedPreferenceUtil.getCharacterSex(this.getContext());
                createCharacterPresenter.setSex(sex);
                transaction.replace(R.id.fragment, classFragment);
                transaction.addToBackStack("CLASS_FRAGMENT_TAG");

                currentFragment = 1;
                break;
            case 1:
                int characterClass = SharedPreferenceUtil.getCharacterClass(this.getContext());
                createCharacterPresenter.setCharacterClass(characterClass);

                transaction.replace(R.id.fragment, appearanceFragment);
                transaction.addToBackStack("APPEARANCE_FRAGMENT_TAG");

                SharedPreferenceUtil.setCharacterClass(this.getContext(), characterClass);

                currentFragment = 2;
                break;
            case 2:
                int hair = SharedPreferenceUtil.getCharacterHair(this.getContext());
                int hat = SharedPreferenceUtil.getCharacterHat(this.getContext());
                int eyeColor =SharedPreferenceUtil.getCharacterEyeColor(this.getContext());
                int blouse = SharedPreferenceUtil.getCharacterBlouse(this.getContext());
                int pants = SharedPreferenceUtil.getCharacterPants(this.getContext());
                int shoes = SharedPreferenceUtil.getCharacterShoes(this.getContext());

                createCharacterPresenter.setHair(hair);
                createCharacterPresenter.setHat(hat);
                createCharacterPresenter.setEyeColor(eyeColor);
                createCharacterPresenter.setBlouse(blouse);
                createCharacterPresenter.setPants(pants);
                createCharacterPresenter.setShoes(shoes);

                transaction.replace(R.id.fragment, nameFragment);
                transaction.addToBackStack("NAME_FRAGMENT_TAG");
                currentFragment = 3;
                break;
            case 3:
                String nickname = nameFragment.getNickname();
                createCharacterPresenter.setNickname(nickname);

                transaction.replace(R.id.fragment, summaryFragment);
                transaction.addToBackStack("SUMMARY_FRAGMENT_TAG");
                currentFragment = 4;
                break;
            case 4:
                User currentUser = SharedPreferenceUtil.getUser(this.getContext());
                int userId = new BigDecimal(currentUser.getUserId()).intValueExact();

                createCharacterPresenter.setUserId(userId);

                createCharacterPresenter.performCharacterCreation();

                currentFragment = 0;
                Toast.makeText(this, getString(R.string.info_character_created), Toast.LENGTH_SHORT).show();
                Navigator.startHome(this);
        }
        transaction.commit();
    }

    @OnClick(R.id.previousFragmentButton)
    public void onPreviousButtonClicked() {

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        switch (currentFragment) {
            case 0: // sex
                currentFragment = 4;
                Navigator.startHome(this);
                break;
            case 1: // class
                transaction.replace(R.id.fragment, sexFragment);
                transaction.addToBackStack("APPEARANCE_FRAGMENT_TAG");
                currentFragment = 0;
                break;
            case 2: // appearance
                transaction.replace(R.id.fragment, classFragment);
                transaction.addToBackStack("NAME_FRAGMENT_TAG");
                currentFragment = 1;
                break;
            case 3: // name
                transaction.replace(R.id.fragment, appearanceFragment);
                currentFragment = 2;
                break;
            case 4: // summary
                transaction.replace(R.id.fragment, nameFragment);
                currentFragment = 3;
                break;
        }
        transaction.commit();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
