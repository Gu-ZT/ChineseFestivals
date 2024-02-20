package dev.dubhe.chinesefestivals.festivals;

public interface IFestival {
    String getId();

    boolean isNow();

    void refresh();
}
