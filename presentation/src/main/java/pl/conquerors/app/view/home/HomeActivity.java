package pl.conquerors.app.view.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.navigation.Navigator;

public class HomeActivity extends AppCompatActivity {

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
}
