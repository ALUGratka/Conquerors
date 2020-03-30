package pl.conquerors.app.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.navigation.Navigator;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.homePrizeButton)
    Button mHomePrizeButton;

    @BindView(R.id.homeCreatorButton)
    Button mHomeCreatorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public static Intent getStartingIntents(final Context context){
        final Intent startingIntent = new Intent(context, HomeActivity.class);
        return startingIntent;
    }

    @OnClick(R.id.homePrizeButton)
    public void onPrizeButtonClicked() {
        Navigator.startPrize(this);;
    }

    @OnClick(R.id.homeCreatorButton)
    public void onCreatorButtonClicked() {
        Navigator.startCreator(this);;
    }

    @Override
    public void showLoading() {
        
    }

    @Override
    public void hideLoading() {

    }
}
