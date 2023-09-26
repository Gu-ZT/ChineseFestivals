package dev.dubhe.chinesefestivals;

import com.mojang.logging.LogUtils;
import com.nlf.calendar.Lunar;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.util.Date;

public class ChineseFestivals {
    public static final Logger LOGGER = LogUtils.getLogger();
    // 春节
    public static final Festival CHINESE_SPRING_FESTIVAL = new LunarFestival("spring", 12, 23, 1, 5);
    // 元旦节
    public static final Festival NEW_YEAR = new Festival("new_year", 1, 1);
    // 元宵节
    public static final Festival LANTERN_FESTIVAL = new LunarFestival("lantern", 1, 15);
    // 端午节
    public static final Festival DRAGON_BOAT_FESTIVAL = new LunarFestival("dragon_boat", 5, 5);
    // 七夕节
    public static final Festival QIXI_FESTIVAL = new LunarFestival("qixi", 7, 7);
    // 中秋节
    public static final Festival MOON_FESTIVAL = new LunarFestival("moon", 8, 12);
    // 重阳节
    public static final Festival DOUBLE_NINTH_FESTIVAL = new LunarFestival("double_ninth", 9, 9);
    // 腊八节
    public static final Festival LABA_FESTIVAL = new LunarFestival("laba", 12, 8);
    public static final String MOD_ID = "chinesefestivals";
    public static String debugFestival = null;

    public static void init() {

    }

    public static ResourceLocation of(String id) {
        return new ResourceLocation(ChineseFestivals.MOD_ID, id);
    }

    public static class Festival {
        protected final String id;
        protected final int startMonth;
        protected final int startDay;
        protected final int endMonth;
        protected final int endDay;

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

        public boolean isNow() {
            LocalDate now = LocalDate.now();
            int currentMonth = now.getMonthValue();
            int currentDay = now.getDayOfMonth();
            return this.id.equals(ChineseFestivals.debugFestival) || inFestival(currentMonth, currentDay);
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

        public LunarFestival(String id, int month, int day) {
            super(id, month, day);
        }

        public LunarFestival(String id, int startMonth, int startDay, int endMonth, int endDay) {
            super(id, startMonth, startDay, endMonth, endDay);
        }

        @Override
        public boolean isNow() {
            Lunar lunar = new Lunar(new Date());
            int currentMonth = Math.abs(lunar.getMonth());
            int currentDay = Math.abs(lunar.getDay());
            return this.id.equals(ChineseFestivals.debugFestival) || inFestival(currentMonth, currentDay);
        }
    }
}
