package pl.conquerors.app.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.util.DialogUtil;

public abstract class BaseFragment extends Fragment implements BaseView {

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @Override
    public void startLogin() {
        Navigator.startLogin(getActivity());
    }

    @Override
    public void showError(final String title, final String message) {
        DialogUtil.showSimpleDialog(getActivity(), title, message);
    }

    public void showError(String title, String message,
                          int positiveButton, DialogInterface.OnClickListener positive,
                          int negativeButton, DialogInterface.OnClickListener negative) {
        DialogUtil.showSimpleDialog(getActivity(), title, message, positiveButton, positive, negativeButton, negative);
    }
}
