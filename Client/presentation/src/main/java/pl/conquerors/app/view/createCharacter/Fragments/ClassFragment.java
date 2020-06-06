package pl.conquerors.app.view.createCharacter.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.OnClick;
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

    @BindView(R.id.classFragmentSexImageView)
    ImageView sexImageView;

    @BindView(R.id.classFragmentClassImageView)
    ImageView classImageView;

    public ClassFragment() {
        // Required empty public constructor
    }

    public void setCurrentSexImage(int sex){
        if (sex == 1) {
            sexImageView.setImageResource(R.drawable.female_256);
        } else if (sex == 0) {
            sexImageView.setImageResource(R.drawable.male_256);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int sex = SharedPreferenceUtil.getCharacterSex(getContext());

        setCurrentSexImage(sex);

        SharedPreferenceUtil.setCharacterClass(view.getContext(), 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    @OnClick(R.id.radioBard)
    public void onRadioBardClicked() {
        classImageView.setImageResource(R.drawable.ic_bard_class);
        SharedPreferenceUtil.setCharacterClass(this.getContext(), 0);
    }

    @OnClick(R.id.radioThief)
    public void onRadioThiefClicked() {
        classImageView.setImageResource(R.drawable.ic_thief_class);
        SharedPreferenceUtil.setCharacterClass(this.getContext(), 1);
    }

    @OnClick(R.id.radioWarrior)
    public void onRadioWarriorClicked() {
        classImageView.setImageResource(R.drawable.ic_warior_class);
        SharedPreferenceUtil.setCharacterClass(this.getContext(), 2);
    }

    @OnClick(R.id.radioWizard)
    public void onRadioWizardClicked() {
        classImageView.setImageResource(R.drawable.wand);
        SharedPreferenceUtil.setCharacterClass(this.getContext(), 3);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
