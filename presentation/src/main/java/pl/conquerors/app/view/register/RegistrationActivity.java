package pl.conquerors.app.view.register;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.conquerors.app.R;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
    }

    public static Intent getStartingIntents(Context context){
        final Intent startingIntent = new Intent(context, RegistrationActivity.class);
        return startingIntent;
    }
}
