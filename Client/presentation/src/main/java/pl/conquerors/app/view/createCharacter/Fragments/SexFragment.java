package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseFragment;
import pl.conquerors.app.util.SharedPreferenceUtil;
import pl.conquerors.app.view.createCharacter.CharacterView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SexFragment extends BaseFragment {

    RadioGroup sexRadioGroup;
    RadioButton sexRadioButton;

    @BindView(R.id.characterCanvas)
    RelativeLayout characterCanvas;

    public SexFragment() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        characterCanvas.addView(new CharacterView(this.getContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sex, container, false);
    }

    @OnClick(R.id.radioWomen)
    public void onRadioWomanClicked() {
        // display woman on canvas
    }

    @OnClick(R.id.radioMan)
    public void onRadioManClicked() {
        //display man on canvas
    }

    public int getSelectedSex() {
        sexRadioGroup = getView().findViewById(R.id.selectSexRadioGroup);

        int selectedId = sexRadioGroup.getCheckedRadioButtonId();
        sexRadioButton = getView().findViewById(selectedId);

        if (sexRadioButton.getText().equals(R.string.radio_woman)) {
            SharedPreferenceUtil.setCharacterSex(this.getContext(), 1);
            return 1;
        } else {
            SharedPreferenceUtil.setCharacterSex(this.getContext(), 0);
            return 0;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
