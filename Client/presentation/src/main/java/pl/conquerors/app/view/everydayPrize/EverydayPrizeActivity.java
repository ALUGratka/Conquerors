package pl.conquerors.app.view.everydayPrize;

import android.content.Context;
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
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.OnClick;
import pl.conquerors.app.R;
import pl.conquerors.app.base.BaseActivity;
import pl.conquerors.app.domain.interactor.everydayPrize.EverydayPrizeUseCase;
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

    @Override
    public void getPrize() {
        showPrizeView(100);
    }

    @OnClick(R.id.giftImage)
    public void onPrizeButtonClicked() {
        mEverydayPrizePresenter.performEverydayPrize(userId);
    }

    @Override
    public void showPrizeView(Integer points) {
        Toast.makeText(this, getString(R.string.info_got_gift, points), Toast.LENGTH_SHORT).show();
        Navigator.startHome(this);
    }

    public void showAlreadyGifted(){
        Toast.makeText(this, "You already got a prize", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEverydayPrizeSucceeded() {
        Toast.makeText(this, getString(R.string.info_got_gift, 2), Toast.LENGTH_SHORT).show();
        Navigator.startHome(this);
    }
}
