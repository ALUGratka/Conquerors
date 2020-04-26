package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseFragment;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.util.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends BaseFragment {

    RadioGroup characterClassRadioGroup;
    RadioButton characterClassRadioButton;

    Character.Sex sex;

    // zaimplementowac onClick radiobutton i zmieniać na canvas klasy i uwzgledniac plec

    public ClassFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferenceUtil.setCharacterClass(view.getContext(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int arg_sex = SharedPreferenceUtil.getCharacterSex(getContext());
        if (arg_sex == 0) {
            sex = Character.Sex.Man;
        } else {
            sex = Character.Sex.Woman;
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    public Character.CharacterClass getSelectedCharacterClass() {
        characterClassRadioGroup = getView().findViewById(R.id.selectClassTypeGroup);

        int selectedId = characterClassRadioGroup.getCheckedRadioButtonId();
        characterClassRadioButton = getView().findViewById(selectedId);

        if (characterClassRadioButton.getText().equals(getString(R.string.radio_bard))) {
            SharedPreferenceUtil.setCharacterClass(this.getContext(), 0);
            return Character.CharacterClass.Bard;
        } else if (characterClassRadioButton.getText().equals(getString(R.string.radio_thief))) {
            SharedPreferenceUtil.setCharacterClass(this.getContext(), 1);
            return Character.CharacterClass.Thief;
        } else if (characterClassRadioButton.getText().equals(getString(R.string.radio_warrior))) {
            SharedPreferenceUtil.setCharacterClass(this.getContext(), 2);
            return Character.CharacterClass.Warrior;
        } else if (characterClassRadioButton.getText().equals(getString(R.string.radio_wizard))) {
            SharedPreferenceUtil.setCharacterClass(this.getContext(), 3);
            return Character.CharacterClass.Wizard;
        } else {
            System.out.println("Error : not existing class selected");
        }
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
