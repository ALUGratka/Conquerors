package pl.conquerors.app.view.everydayPrize;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;

public class EverydayPrizeActivity extends BaseActivity implements EverydayPrizeView {
    EverydayPrizePresenter mEverydayPrizePresenter;

    public static Intent getStartingIntents(Context context) {
        return new Intent(context, EverydayPrizeActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_everyday_prize);

        EverydayPrizeUseCase everydayPrizeUseCase = new EverydayPrizeUseCase(new AndroidComposedScheduler());

        mEverydayPrizePresenter = new EverydayPrizePresenter(everydayPrizeUseCase);
        mEverydayPrizePresenter.setmView(this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void getPrize() {
        showPrizeView(100);
    }

    @OnClick(R.id.giftImage)
    public void onPrizeButtonClicked() {
        getPrize();
    }

    @Override
    public void showPrizeView(Integer points) {
        Toast.makeText(this, getString(R.string.info_got_gift, points), Toast.LENGTH_SHORT).show();
        Navigator.startHome(this);
    }

}
