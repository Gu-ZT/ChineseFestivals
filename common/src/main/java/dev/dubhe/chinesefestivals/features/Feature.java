package dev.dubhe.chinesefestivals.features;

import dev.dubhe.chinesefestivals.festivals.IFestival;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Feature implements IFeature {
    private final String id;
    protected final List<IFestival> enableTimes;

    public Feature(String id, IFestival... enableTimes) {
        this.id = id;
        this.enableTimes = Collections.synchronizedList(new ArrayList<>(List.of(enableTimes)));
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public boolean isNow() {
        for (IFestival enableTime : this.enableTimes) {
            if (enableTime.isNow()) {
                return true;
            }
        }
        return false;
    }
}
