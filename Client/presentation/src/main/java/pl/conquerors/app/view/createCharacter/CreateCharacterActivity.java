package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;


import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;

public class CreateCharacterActivity extends BaseActivity implements CreateCharacterView {

    @BindView(R.id.previousFragmentButton)
    Button previousScreenButton;

    @BindView(R.id.nextFragmentButton)
    Button nextScreenButton;

    @BindView(R.id.fragmentsLayout)
    LinearLayout fragmentsLayout;

    Fragment sexFragment;
    Fragment classFragment;
    Fragment appearanceFragment;
    Fragment nameFragment;
    Fragment summaryFragment;

    FragmentManager manager;
    FragmentTransaction transaction;


    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CreateCharacterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        sexFragment = new SexFragment();
        classFragment = new ClassFragment();
        appearanceFragment = new AppearanceFragment();
        nameFragment = new NameFragment();
        summaryFragment = new SummaryFragment();

        transaction.add(R.id.fragmentsLayout, sexFragment);
        transaction.commit();

    }

    @OnClick(R.id.nextFragmentButton)
    public void onNextButtonClicked() {
        System.out.println("DUPA");
        transaction.add(R.id.fragmentsLayout, classFragment);
    }

    @OnClick(R.id.previousFragmentButton)
    public void onPreviousButtonClicked() {
        Navigator.startHome(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
