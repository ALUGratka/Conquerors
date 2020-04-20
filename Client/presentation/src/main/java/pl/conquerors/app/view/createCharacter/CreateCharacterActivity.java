package pl.conquerors.app.view.createCharacter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;


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

    @BindView(R.id.fragment)
    FrameLayout fragment;

    SexFragment sexFragment = new SexFragment();
    ClassFragment classFragment = new ClassFragment();
    AppearanceFragment appearanceFragment = new AppearanceFragment();
    NameFragment nameFragment = new NameFragment();
    SummaryFragment summaryFragment = new SummaryFragment();

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, CreateCharacterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_character);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment, sexFragment);
        transaction.commit();
    }

    @OnClick(R.id.nextFragmentButton)
    public void onNextButtonClicked() {
        System.out.println("DUPA");
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        transaction.replace(R.id.fragment, classFragment);
        transaction.commit();
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
