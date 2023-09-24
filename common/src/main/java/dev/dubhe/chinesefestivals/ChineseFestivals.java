package dev.dubhe.chinesefestivals;

import com.nlf.calendar.Lunar;

import java.time.LocalDate;
import java.util.Date;

public class ChineseFestivals {
    // 春节
    public static final Festival CHINESE_SPRING_FESTIVAL = new LunarFestival(12, 23, 1, 5);
    // 元旦节
    public static final Festival NEW_YEAR = new Festival(1, 1);
    // 元宵节
    public static final Festival LANTERN_FESTIVAL = new LunarFestival(1, 15);
    // 端午节
    public static final Festival DRAGEON_BOAT_FESTIVAL = new LunarFestival(5, 5);
    // 七夕节
    public static final Festival QIXI_FESTIVAL = new LunarFestival(7, 7);
    // 中秋节
    public static final Festival MOON_FESTIVAL = new LunarFestival(8, 12);
    // 重阳节
    public static final Festival DOUBLE_NINTH_FESTIVAL = new LunarFestival(9, 9);
    // 腊八节
    public static final Festival LABA_FESTIVAL = new LunarFestival(12, 8);
    public static final String MOD_ID = "chinesefestivals";

    public static void init() {

    }

    public static class Festival {
        protected final int startMonth;
        protected final int startDay;
        protected final int endMonth;
        protected final int endDay;

        public Festival(int month, int day) {
            this(month, day, month, day);
        }

        public Festival(int startMonth, int startDay, int endMonth, int endDay) {
            this.startMonth = startMonth;
            this.startDay = startDay;
            this.endMonth = endMonth;
            this.endDay = endDay;
        }

        public boolean isNow() {
            LocalDate now = LocalDate.now();
            int currentMonth = now.getMonthValue();
            int currentDay = now.getDayOfMonth();
            return inFestival(currentMonth, currentDay);
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

    public static class LunarFestival extends Festival {

        public LunarFestival(int month, int day) {
            super(month, day);
        }

        public LunarFestival(int startMonth, int startDay, int endMonth, int endDay) {
            super(startMonth, startDay, endMonth, endDay);
        }

        @Override
        public boolean isNow() {
            Lunar lunar = new Lunar(new Date());
            int currentMonth = Math.abs(lunar.getMonth());
            int currentDay = Math.abs(lunar.getDay());
            return inFestival(currentMonth, currentDay);
        }
    }
}
