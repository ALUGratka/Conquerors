package pl.conquerors.app.view.splash;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import pl.conquerors.app.R;
import pl.conquerors.app.navigation.Navigator;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

public class SplashActivity extends AppCompatActivity {

    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    public void onBackPressed() {
        mSubscription.unsubscribe();
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        mSubscription.unsubscribe();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mSubscription = Observable.timer(3, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() { }

            @Override
            public void onError(Throwable e) { }

            @Override
            public void onNext(Long aLong) {
                //TODO if login HomeActivity if not LoginActivity/RegistrationActivity

                Navigator.startLogin(SplashActivity.this);
                finish();
            }

        });
        super.onResume();
    }
}
