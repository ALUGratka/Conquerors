package pl.conquerors.app.view.createCharacter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.conquerors.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SexFragment extends Fragment {

    public SexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sex, container, false);
    }
}
