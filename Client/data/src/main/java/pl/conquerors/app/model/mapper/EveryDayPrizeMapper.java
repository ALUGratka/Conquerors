package pl.conquerors.app.model.mapper;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

import pl.conquerors.app.model.PrizeDateEntity;
import pl.conquerors.app.domain.model.PrizeDate;

public class EveryDayPrizeMapper {
    public static PrizeDate transform (PrizeDateEntity prizeDateEntity) {
        PrizeDate prizeDate = null;
        if(prizeDateEntity!=null){
            prizeDate = new PrizeDate();
            prizeDate.setmLastDate(prizeDateEntity.getPrizeDate());
        }
        return prizeDate;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static List<PrizeDate> transform (List<PrizeDateEntity> prizeDateEntities) {
        final List<PrizeDate> prizeDates = new ArrayList<>();
        prizeDateEntities.forEach(prizeDateEntity -> prizeDates.add(transform(prizeDateEntity)));
        return prizeDates;
    }
}
