package dev.dubhe.chinesefestivals.festivals;

import com.nlf.calendar.Lunar;

import java.util.Date;

public class LunarFestival extends Festival {

    public LunarFestival(String id, int month, int day) {
        super(id, month, day);
    }

    public LunarFestival(String id, int startMonth, int startDay, int endMonth, int endDay) {
        super(id, startMonth, startDay, endMonth, endDay);
    }

    @Override
    public void refresh() {
        Lunar lunar = new Lunar(new Date());
        refresh(() -> Math.abs(lunar.getMonth()), () -> Math.abs(lunar.getDay()));
    }
}
