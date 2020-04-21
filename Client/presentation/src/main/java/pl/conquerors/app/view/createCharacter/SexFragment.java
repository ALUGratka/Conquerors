package pl.conquerors.app.view.createCharacter;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import pl.conquerors.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SexFragment extends Fragment {

    RadioGroup sexRadioGroup;
    RadioButton sexRadioButton;

    public SexFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex, container, false);
    }

    public boolean getSelectedSex(){
        sexRadioGroup = getView().findViewById(R.id.selectSexRadioGroup);

        int selectedId = sexRadioGroup.getCheckedRadioButtonId();
        sexRadioButton = getView().findViewById(selectedId);

        if (sexRadioButton.getText() == "Kobieta") {
            return true; // woman
        }else{
            return false; // man
        }
    }
}
