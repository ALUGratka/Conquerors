package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import pl.conquerors.app.R;
import pl.conquerors.app.domain.model.Character;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment {

    RadioGroup characterClassRadioGroup;
    RadioButton characterClassRadioButton;

    public ClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    public Character.CharacterClass getSelectedCharacterClass() {
        characterClassRadioGroup = getView().findViewById(R.id.selectClassTypeGroup);

        int selectedId = characterClassRadioGroup.getCheckedRadioButtonId();
        characterClassRadioButton = getView().findViewById(selectedId);

        if (characterClassRadioButton.getText().equals(R.string.radio_bard)) {
            return Character.CharacterClass.Bard;
        } else if (characterClassRadioButton.getText().equals(R.string.radio_thief)) {
            return Character.CharacterClass.Thief;
        } else if (characterClassRadioButton.getText().equals(R.string.radio_warrior)) {
            return Character.CharacterClass.Warrior;
        } else if (characterClassRadioButton.getText().equals(R.string.radio_wizard)) {
            return Character.CharacterClass.Wizard;
        } else {
            System.out.println("Error : not existing class selected");
        }
        return null;
    }
}
