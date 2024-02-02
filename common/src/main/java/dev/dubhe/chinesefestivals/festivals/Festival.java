package dev.dubhe.chinesefestivals.festivals;

import dev.dubhe.chinesefestivals.ChineseFestivals;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.function.Supplier;

public class Festival implements IFestival {
    protected final String id;
    protected final int startMonth;
    protected final int startDay;
    protected final int endMonth;
    protected final int endDay;
    protected boolean flag = false;

    public Festival(String id, int month, int day) {
        this(id, month, day, month, day);
    }

    public Festival(String id, int startMonth, int startDay, int endMonth, int endDay) {
        this.id = id;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endMonth = endMonth;
        this.endDay = endDay;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isNow() {
        return this.flag;
    }

    @Override
    public void refresh() {
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime now = ldt.atZone(ZoneId.of("GMT+8"));
        refresh(now::getMonthValue, now::getDayOfMonth);
    }

    protected void refresh(Supplier<Integer> month, Supplier<Integer> day) {
        int currentMonth = month.get();
        int currentDay = day.get();
        boolean flag;
        if (this == ChineseFestivals.debugFestival) flag = true;
        else flag = inFestival(currentMonth, currentDay);
        if (flag != this.flag) {
            Festivals.hasChanged = true;
            this.flag = flag;
        }
    }

    public boolean inFestival(int month, int day) {
        if (startMonth == endMonth) {
            return month == startMonth && day >= startDay && day <= endDay;
        } else {
            return (month == startMonth && day >= startDay) ||
                    (month == endMonth && day <= endDay);
        }
    }
}
