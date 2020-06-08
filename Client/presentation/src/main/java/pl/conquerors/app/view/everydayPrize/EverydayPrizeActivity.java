package pl.conquerors.app.view.everydayPrize;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;
import pl.conquerors.app.domain.model.Character;
import pl.conquerors.app.domain.model.PrizeDate;
import pl.conquerors.app.navigation.Navigator;
import pl.conquerors.app.scheduler.AndroidComposedScheduler;
import pl.conquerors.app.util.SharedPreferenceUtil;

public class EverydayPrizeActivity extends BaseActivity implements EverydayPrizeView {
    EverydayPrizePresenter mEverydayPrizePresenter;
    CompactCalendarView compactCalendar;
    int userId = 0;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMMM yyyy", Locale.getDefault());

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
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);

        compactCalendar = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        compactCalendar.shouldDrawIndicatorsBelowSelectedDays(true);
        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendar.getEvents(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                actionBar.setTitle(simpleDateFormat.format(firstDayOfNewMonth));
            }
        });
        userId = (int) SharedPreferenceUtil.getUser(this.getContext()).getUserId();
        mEverydayPrizePresenter.getEverydayPrize(userId);
    }

    public void updateCalendar(List<PrizeDate> prizeDates) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date gmt = null;
        for (PrizeDate prizeDate:prizeDates)
        {
            String date = prizeDate.getPrizeDate();
            try {
                gmt = formatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long millisecondsSinceEpoch0 = gmt.getTime();
            Event ev1 = new Event(Color.RED, millisecondsSinceEpoch0);
            compactCalendar.addEvent(ev1);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @OnClick(R.id.giftImage)
    public void onPrizeButtonClicked() {
        int characterId = 5;
        mEverydayPrizePresenter.getCharacters(userId);
    }


    public void showAlreadyGifted(){
        Toast.makeText(this, "You already got a prize", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEverydayPrizeSucceeded(int attributeId) {
        switch(attributeId){
            case 0:
                Toast.makeText(this, "You have been gifted 2 agility points", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "You have been gifted 2 charisma points", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "You have been gifted 2 strength points", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "You have been gifted 2 intelligence points", Toast.LENGTH_SHORT).show();
                break;
        }
        Navigator.startHome(this);
    }

    public void openDialog(List<Character> characters){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        List<String> charactersString = new ArrayList<String>();
        for(Character character:characters)
        {
            charactersString.add(character.getmNickname());
        }
        builder.setItems(charactersString.toArray(new String[0]), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Character character = characters.get(i);
                int characterId = character.getmId();
                mEverydayPrizePresenter.performEverydayPrize(userId, characterId);
            }
        });
        builder.create();

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
