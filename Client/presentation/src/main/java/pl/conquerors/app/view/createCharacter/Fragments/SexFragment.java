package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseFragment;
import pl.conquerors.app.domain.model.Character;

/**
 * A simple {@link Fragment} subclass.
 */
public class SexFragment extends BaseFragment {

    RadioGroup sexRadioGroup;
    RadioButton sexRadioButton;

    public SexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sex, container, false);
    }

    @OnClick(R.id.radioWomen)
    public void onRadioWomanClicked(){
        // display woman on canvas
    }

    @OnClick(R.id.radioMan)
    public void onRadioManClicked(){
        //display man on canvas
    }

    public Character.Sex getSelectedSex() {
        sexRadioGroup = getView().findViewById(R.id.selectSexRadioGroup);

        int selectedId = sexRadioGroup.getCheckedRadioButtonId();
        sexRadioButton = getView().findViewById(selectedId);

        if (sexRadioButton.getText().equals(R.string.radio_woman)) {
            return Character.Sex.Woman;
        } else {
            return Character.Sex.Man;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
