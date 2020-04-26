package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import pl.conquerors.app.R;
import pl.conquerors.app.util.SharedPreferenceUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummaryFragment extends Fragment {

    @BindView(R.id.displayCharacterNicknameText)
    TextView nickname;

    @BindView(R.id.displayCharacterClassText)
    TextView characterClass;

    @BindView(R.id.displayClassStrength)
    TextView strength;

    @BindView(R.id.displayCharacterCharisma)
    TextView charisma;

    @BindView(R.id.displayCharacterAgility)
    TextView agility;

    @BindView(R.id.displayCharacterIntelligence)
    TextView intelligence;

    public SummaryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String arg_nickname = SharedPreferenceUtil.getCharacterName(view.getContext());
        int arg_class = SharedPreferenceUtil.getCharacterClass(view.getContext());

        nickname.setText(arg_nickname);

        if (arg_class == 0) {
            characterClass.setText(getString(R.string.radio_bard));
            strength.setText("2");
            charisma.setText("10");
            agility.setText("5");
            intelligence.setText("3");
        } else if (arg_class == 1) {
            characterClass.setText(getString(R.string.radio_thief));
            strength.setText("2");
            charisma.setText("5");
            agility.setText("10");
            intelligence.setText("3");
        } else if (arg_class == 2) {
            characterClass.setText(getString(R.string.radio_warrior));
            strength.setText("10");
            charisma.setText("3");
            agility.setText("5");
            intelligence.setText("2");
        } else {
            characterClass.setText(getString(R.string.radio_wizard));
            strength.setText("5");
            charisma.setText("3");
            agility.setText("2");
            intelligence.setText("10");
        }
    }
}
