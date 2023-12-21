package dev.dubhe.chinesefestivals.festivals;

import com.nlf.calendar.JieQi;
import com.nlf.calendar.Lunar;
import dev.dubhe.chinesefestivals.ChineseFestivals;

public class SolarTermFestival implements IFestival {
    public final String id;
    public final SolarTerm solarTerm;
    protected boolean flag = false;

    public SolarTermFestival(String id, SolarTerm solarTerm) {
        this.id = id;
        this.solarTerm = solarTerm;
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
        boolean flag;
        if (this == ChineseFestivals.debugFestival) flag = true;
        else {
            Lunar lunar = new Lunar();
            JieQi jieQi = lunar.getCurrentJieQi();
            flag = jieQi != null && jieQi.getName().equals(this.solarTerm.name());
        }
        if (flag != this.flag) {
            Festivals.hasChanged = true;
            this.flag = flag;
        }
    }

    public enum SolarTerm {
        LI_CHUN("立春"),
        YU_SHUI("雨水"),
        JING_ZHE("惊蛰"),
        CHUN_FEN("春分"),
        QING_MING("清明"),
        GU_YU("谷雨"),
        LI_XIA("立夏"),
        XIAO_MAN("小满"),
        MANG_ZHONG("芒种"),
        XIA_ZHI("夏至"),
        XIAO_SHU("小暑"),
        DA_SHU("大暑"),
        LI_QIU("立秋"),
        CHU_SHU("处暑"),
        BAI_LU("白露"),
        QIU_FEN("秋分"),
        HAN_LU("寒露"),
        SHUANG_JIANG("霜降"),
        LI_DONG("立冬"),
        XIAO_XUE("小雪"),
        DA_XUE("大雪"),
        DONG_ZHI("冬至"),
        XIAO_HAN("小寒"),
        DA_HAN("大寒");

        public final String name;

        SolarTerm(String name) {
            this.name = name;
        }
    }
}
