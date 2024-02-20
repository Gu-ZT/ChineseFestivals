package dev.dubhe.chinesefestivals.festivals;

import java.util.List;
import java.util.Vector;

public class Festivals {
    public static boolean hasChanged = false;
    // 春节
    public static final IFestival CHINESE_SPRING_FESTIVAL = new LunarFestival("spring", 12, 23, 1, 8);
    // 元宵节
    public static final IFestival LANTERN_FESTIVAL = new LunarFestival("lantern", 1, 15);
    // 端午节
    public static final IFestival DRAGON_BOAT_FESTIVAL = new LunarFestival("dragon_boat", 5, 5);
    // 七夕节
    public static final IFestival QIXI_FESTIVAL = new LunarFestival("qixi", 7, 7);
    // 中秋节
    public static final IFestival MOON_FESTIVAL = new LunarFestival("moon", 8, 15);
    // 重阳节
    public static final IFestival DOUBLE_NINTH_FESTIVAL = new LunarFestival("double_ninth", 9, 9);
    // 重阳节
    public static final IFestival DONG_ZHI_FESTIVAL = new SolarTermFestival("dong_zhi", SolarTermFestival.SolarTerm.DONG_ZHI);
    // 腊八节
    public static final IFestival LABA_FESTIVAL = new LunarFestival("laba", 12, 8);

    public static final List<IFestival> FESTIVALS = new Vector<>() {{
        this.add(CHINESE_SPRING_FESTIVAL);
        this.add(LANTERN_FESTIVAL);
        this.add(DRAGON_BOAT_FESTIVAL);
        this.add(QIXI_FESTIVAL);
        this.add(MOON_FESTIVAL);
        this.add(DOUBLE_NINTH_FESTIVAL);
        this.add(DONG_ZHI_FESTIVAL);
        this.add(LABA_FESTIVAL);
    }};

    public static void refresh() {
        FESTIVALS.stream().parallel().forEach(IFestival::refresh);
    }
}
