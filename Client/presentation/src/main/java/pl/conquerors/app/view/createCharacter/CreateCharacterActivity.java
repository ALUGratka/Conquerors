package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.createCharacter.CreateCharacterUseCase;
import pl.conquerors.app.domain.model.Character;
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
        createCharacterPresenter = new CreateCharacterPresenter(createCharacterUseCase);

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
                Character.Sex sex = sexFragment.getSelectedSex();
                createCharacterPresenter.setSex(sex);

                transaction.replace(R.id.fragment, classFragment);
                transaction.addToBackStack("CLASS_FRAGMENT_TAG");

                if (sex.equals(Character.Sex.Man)) {
                    SharedPreferenceUtil.setCharacterSex(this.getContext(), 0); // man
                } else {
                    SharedPreferenceUtil.setCharacterSex(this.getContext(), 1); // woman
                }

                currentFragment = 1;
                break;
            case 1:
                Character.CharacterClass characterClass = classFragment.getSelectedCharacterClass();
                System.out.println("from activity character class");
                System.out.println(characterClass);
                createCharacterPresenter.setCharacterClass(characterClass);

                transaction.replace(R.id.fragment, appearanceFragment);
                transaction.addToBackStack("APPEARANCE_FRAGMENT_TAG");


                if (characterClass.equals(Character.CharacterClass.Bard)) {
                    SharedPreferenceUtil.setCharacterClass(this.getContext(), 0);
                } else if (characterClass.equals(Character.CharacterClass.Thief)) {
                    SharedPreferenceUtil.setCharacterClass(this.getContext(), 1);
                } else if (characterClass.equals(Character.CharacterClass.Warrior)) {
                    SharedPreferenceUtil.setCharacterClass(this.getContext(), 2);
                } else {
                    SharedPreferenceUtil.setCharacterClass(this.getContext(), 3);
                }

                currentFragment = 2;
                break;
            case 2:
                Character.Hair hair = appearanceFragment.getHairCurrent();
                Character.Hat hat = appearanceFragment.getHatCurrent();
                Character.EyeColor eyeColor = appearanceFragment.getEyeColorCurrent();
                Character.Blouse blouse = appearanceFragment.getBlouseCurrent();
                Character.Pants pants = appearanceFragment.getPantsCurrent();
                Character.Shoes shoes = appearanceFragment.getShoesCurrent();

                createCharacterPresenter.setHair(hair);
                createCharacterPresenter.setHat(hat);
                createCharacterPresenter.setEyeColor(eyeColor);
                createCharacterPresenter.setBlouse(blouse);
                createCharacterPresenter.setPants(pants);
                createCharacterPresenter.setShoes(shoes);

                // hair
                if (hair.equals(Character.Hair.Blond)) {
                    SharedPreferenceUtil.setCharacterHair(this.getContext(), 0); // blond
                } else if (hair.equals(Character.Hair.Brown)) {
                    SharedPreferenceUtil.setCharacterHair(this.getContext(), 1); // brown
                } else {
                    SharedPreferenceUtil.setCharacterHair(this.getContext(), 2); // black
                }
                // hat
                if (hat.equals(Character.Hat.Hat1)) {
                    SharedPreferenceUtil.setCharacterHat(this.getContext(), 0); // Hat1
                } else if (hat.equals(Character.Hat.Hat2)) {
                    SharedPreferenceUtil.setCharacterHat(this.getContext(), 1); // Hat2
                } else {
                    SharedPreferenceUtil.setCharacterHat(this.getContext(), 2); // Hat3
                }
                // eyeColor
                if (eyeColor.equals(Character.EyeColor.Blue)) {
                    SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 0); // Blue
                } else if (eyeColor.equals(Character.EyeColor.Brown)) {
                    SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 1); // Brown
                } else {
                    SharedPreferenceUtil.setCharacterEyeColor(this.getContext(), 2); // Green
                }
                // blouse
                if (blouse.equals(Character.Blouse.BlouseBlue)) {
                    SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 0); // Blue
                } else if (blouse.equals(Character.Blouse.BlouseRed)) {
                    SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 1); // Red
                } else {
                    SharedPreferenceUtil.setCharacterBlouse(this.getContext(), 2); // Yellow
                }
                // pants
                if (pants.equals(Character.Pants.Pants1)) {
                    SharedPreferenceUtil.setCharacterPants(this.getContext(), 0); // Pants1
                } else if (pants.equals(Character.Pants.Pants2)) {
                    SharedPreferenceUtil.setCharacterPants(this.getContext(), 1); // Pants2
                } else {
                    SharedPreferenceUtil.setCharacterPants(this.getContext(), 2); // Pants3
                }
                // shoes
                if (shoes.equals(Character.Shoes.Shoes1)) {
                    SharedPreferenceUtil.setCharacterShoes(this.getContext(), 0); // Shoes1
                } else if (shoes.equals(Character.Shoes.Shoes2)) {
                    SharedPreferenceUtil.setCharacterShoes(this.getContext(), 1); // Shoes2
                } else {
                    SharedPreferenceUtil.setCharacterShoes(this.getContext(), 2); // Shoes3
                }

                transaction.replace(R.id.fragment, nameFragment);
                transaction.addToBackStack("NAME_FRAGMENT_TAG");
                currentFragment = 3;
                break;
            case 3:
                String nickname = nameFragment.getNickname();
                SharedPreferenceUtil.setCharacterName(this.getContext(), nickname);

                transaction.replace(R.id.fragment, summaryFragment);
                transaction.addToBackStack("SUMMARY_FRAGMENT_TAG");
                currentFragment = 4;
                break;
            case 4:
                // get userId from somewhere ????
                // and send it to presenter
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
