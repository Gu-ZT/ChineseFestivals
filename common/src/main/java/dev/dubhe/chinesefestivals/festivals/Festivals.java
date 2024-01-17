package dev.dubhe.chinesefestivals.festivals;

import java.util.List;
import java.util.Vector;

public class Festivals {
    public static final List<IFestival> FESTIVALS = new Vector<>();
    public static boolean hasChanged = false;
    // 春节
    public static final IFestival CHINESE_SPRING_FESTIVAL = new LunarFestival("spring", 12, 23, 1, 8);
    // 元旦节
    public static final IFestival NEW_YEAR = new Festival("new_year", 1, 1);
    // 元宵节
    public static final IFestival LANTERN_FESTIVAL = new LunarFestival("lantern", 1, 15);
    // 端午节
    public static final IFestival DRAGON_BOAT_FESTIVAL = new LunarFestival("dragon_boat", 5, 5);
    // 七夕节
    public static final IFestival QIXI_FESTIVAL = new LunarFestival("qixi", 7, 7);
    // 中秋节
    public static final IFestival MOON_FESTIVAL = new MoonFestival();
    // 重阳节
    public static final IFestival DOUBLE_NINTH_FESTIVAL = new DoubleNinthFestival();
    // 重阳节
    public static final IFestival DONG_ZHI_FESTIVAL = new DongZhiFestival();
    // 腊八节
    public static final IFestival LABA_FESTIVAL = new LabaFestival();

    public static void init() {
        FESTIVALS.add(CHINESE_SPRING_FESTIVAL);
        FESTIVALS.add(NEW_YEAR);
        FESTIVALS.add(LANTERN_FESTIVAL);
        FESTIVALS.add(DRAGON_BOAT_FESTIVAL);
        FESTIVALS.add(QIXI_FESTIVAL);
        FESTIVALS.add(MOON_FESTIVAL);
        FESTIVALS.add(DOUBLE_NINTH_FESTIVAL);
        FESTIVALS.add(DONG_ZHI_FESTIVAL);
        FESTIVALS.add(LABA_FESTIVAL);
    }

    public static void refresh() {
        for (IFestival festival : FESTIVALS) {
            festival.refresh();
        }
    }
}
