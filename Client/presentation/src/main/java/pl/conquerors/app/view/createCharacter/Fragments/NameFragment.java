package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import butterknife.BindView;
import pl.conquerors.app.R;

/**
 * A simple {@link Fragment} subclass.\
 */
public class NameFragment extends Fragment {

    @BindView(R.id.characterNickname)
    AutoCompleteTextView characterNickname;

    public NameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    public String getNickname() {
        System.out.println(characterNickname.getText());
        return characterNickname.getText().toString();
    }


}
