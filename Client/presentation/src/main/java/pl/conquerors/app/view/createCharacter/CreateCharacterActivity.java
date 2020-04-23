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

    Bundle sexBundle = new Bundle();
    Bundle classBundle = new Bundle();
    Bundle appearanceBundle = new Bundle();
    Bundle nameBundle = new Bundle();

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
                    sexBundle.putInt("sex", 0); // man
                } else {
                    sexBundle.putInt("sex", 1); // woman
                }

                classFragment.setArguments(sexBundle);
                appearanceFragment.setArguments(sexBundle);

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
                    classBundle.putInt("class", 0); // bard
                } else if (characterClass.equals(Character.CharacterClass.Thief)) {
                    classBundle.putInt("class", 1); // thief
                } else if (characterClass.equals(Character.CharacterClass.Warrior)) {
                    classBundle.putInt("class", 2); // warrior
                } else {
                    classBundle.putInt("class", 3); // wizard
                }

                classFragment.setArguments(classBundle);

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
                    appearanceBundle.putInt("hair", 0); // blond
                } else if (hair.equals(Character.Hair.Brown)) {
                    appearanceBundle.putInt("hair", 1); // brown
                } else {
                    appearanceBundle.putInt("hair", 2); // black
                }
                // hat
                if (hat.equals(Character.Hat.Hat1)) {
                    appearanceBundle.putInt("hat", 0); // Hat1
                } else if (hat.equals(Character.Hat.Hat2)) {
                    appearanceBundle.putInt("hat", 1); // Hat2
                } else {
                    appearanceBundle.putInt("hat", 2); // Hat3
                }
                // eyeColor
                if (eyeColor.equals(Character.EyeColor.Blue)) {
                    appearanceBundle.putInt("eyeColor", 0); // Blue
                } else if (eyeColor.equals(Character.EyeColor.Brown)) {
                    appearanceBundle.putInt("eyeColor", 1); // Brown
                } else {
                    appearanceBundle.putInt("eyeColor", 2); // Green
                }
                // blouse
                if (blouse.equals(Character.Blouse.BlouseBlue)) {
                    appearanceBundle.putInt("blouse", 0); // Blue
                } else if (blouse.equals(Character.Blouse.BlouseRed)) {
                    appearanceBundle.putInt("blouse", 1); // Red
                } else {
                    appearanceBundle.putInt("blouse", 2); // Yellow
                }
                // pants
                if (pants.equals(Character.Pants.Pants1)) {
                    appearanceBundle.putInt("pants", 0); // Pants1
                } else if (pants.equals(Character.Pants.Pants2)) {
                    appearanceBundle.putInt("pants", 1); // Pants2
                } else {
                    appearanceBundle.putInt("pants", 2); // Pants3
                }
                // shoes
                if (shoes.equals(Character.Shoes.Shoes1)) {
                    appearanceBundle.putInt("shoes", 0); // Shoes1
                } else if (shoes.equals(Character.Shoes.Shoes2)) {
                    appearanceBundle.putInt("shoes", 1); // Shoes2
                } else {
                    appearanceBundle.putInt("shoes", 2); // Shoes3
                }

                appearanceFragment.setArguments(appearanceBundle);

                transaction.replace(R.id.fragment, nameFragment);
                transaction.addToBackStack("NAME_FRAGMENT_TAG");
                currentFragment = 3;
                break;
            case 3:
                String nickname = nameFragment.getNickname();
                createCharacterPresenter.setNickname(nickname);

                nameBundle.putString("nickname", nickname);

                transaction.replace(R.id.fragment, summaryFragment);
                transaction.addToBackStack("SUMMARY_FRAGMENT_TAG");
                currentFragment = 4;
                break;
            case 4:
                summaryFragment.setArguments(nameBundle);
                summaryFragment.setArguments(classBundle);

                // get userId from somewhere ????

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
