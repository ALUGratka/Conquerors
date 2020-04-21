package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import pl.conquerors.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppearanceFragment extends Fragment {

    @BindView(R.id.hair_back_arrow_button)
    Button hairBackArrowButton;

    @BindView(R.id.hair_next_arrow_button)
    Button hairNextArrowButton;

    @BindView(R.id.eye_color_back_arrow_button)
    Button eyeColorBackArrowButton;

    @BindView(R.id.eye_color_next_arrow_button)
    Button eyeColorNextArrowButton;

    @BindView(R.id.hat_back_arrow_button)
    Button hatBackArrowButton;

    @BindView(R.id.hat_next_arrow_button)
    Button hatNextArrowButton;

    @BindView(R.id.blouse_back_arrow_button)
    Button blouseBackArrowButton;

    @BindView(R.id.blouse_next_arrow_button)
    Button blouseNextArrowButton;

    @BindView(R.id.pants_back_arrow_button)
    Button pantsBackArrowButton;

    @BindView(R.id.pants_next_arrow_button)
    Button pantsNextArrowButton;

    @BindView(R.id.boots_back_arrow_button)
    Button shoesBackArrowButton;

    @BindView(R.id.boots_next_arrow_button)
    Button shoesNextArrowButton;

    public AppearanceFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_appearance, container, false);
    }
}