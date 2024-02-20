package dev.dubhe.chinesefestivals.features;

import com.google.gson.*;
import dev.dubhe.chinesefestivals.festivals.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FeatureParser implements JsonSerializer<IFeature>, JsonDeserializer<IFeature> {
    @Override
    public IFeature deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        if (!jsonElement.isJsonObject()) throw new JsonParseException("JSON format does not meet the requirements.");
        JsonObject featureJsonObject = jsonElement.getAsJsonObject();
        JsonElement tempJsonElement = featureJsonObject.get("id");
        if (!(tempJsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString()))
            throw new JsonParseException("JSON format does not meet the requirements.");
        String id = tempJsonElement.getAsString();
        tempJsonElement = featureJsonObject.get("enable_times");
        if (!tempJsonElement.isJsonArray()) throw new JsonParseException("JSON format does not meet the requirements.");
        JsonArray enableTimesArray = tempJsonElement.getAsJsonArray();
        List<IFestival> enableTimesList = new ArrayList<>() {{
            for (JsonElement element : enableTimesArray) {
                this.add(Features.GSON.fromJson(element, IFestival.class));
            }
        }};
        IFestival[] enableTimes = enableTimesList.toArray(new IFestival[]{});
        if (!Features.FEATURE_GENERATORS.containsKey(id))
            throw new JsonParseException("This feature does not exist.");
        return Features.FEATURE_GENERATORS.get(id).apply(id, enableTimes);
    }

    @Override
    public JsonElement serialize(IFeature feature, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject root = new JsonObject();
        root.addProperty("id", feature.getId());
        JsonArray enableTimes = new JsonArray();
        if (feature instanceof Feature feature1) {
            for (IFestival time : feature1.enableTimes) {
                enableTimes.add(Features.GSON.toJsonTree(time, IFeature.class));
            }
        }
        root.add("enable_times", enableTimes);
        return root;
    }

    public static class FestivalParser implements JsonSerializer<IFestival>, JsonDeserializer<IFestival> {
        @Override
        public IFestival deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            if (jsonElement.isJsonPrimitive() && jsonElement.getAsJsonPrimitive().isString())
                return this.deserialize(jsonElement.getAsString());
            else if (jsonElement.isJsonObject())
                return this.deserialize(jsonElement.getAsJsonObject());
            else throw new JsonParseException("JSON format does not meet the requirements.");
        }

        public IFestival deserialize(String str) throws JsonParseException {
            str = str.replaceAll(" ", "");
            // "spring"
            for (IFestival festival : Festivals.FESTIVALS) {
                if (festival.getId().equals(str)) return festival;
            }
            // "冬至"
            for (SolarTermFestival.SolarTerm jieQi : SolarTermFestival.SolarTerm.values()) {
                if (jieQi.name.equals(str)) {
                    IFestival festival = new SolarTermFestival("jieqi" + str.hashCode(), jieQi);
                    Festivals.FESTIVALS.add(festival);
                    return festival;
                }
            }
            // "12.23"
            String regular = "^\\d+.\\d+$";
            if (str.matches(regular)) {
                String[] split = str.split("\\.");
                int month = Integer.parseInt(split[0]);
                int day = Integer.parseInt(split[1]);
                IFestival festival = new Festival("solar" + str.hashCode(), month, day);
                Festivals.FESTIVALS.add(festival);
                return festival;
            }
            // "腊月廿三"
            regular = "^\\D月[初二廿三]\\D$";
            if (str.matches(regular)) {
                int month = 0, day = 0;
                for (LunarMonth lunarMonth : LunarMonth.values()) {
                    if (lunarMonth.name.equals(str)) {
                        month = lunarMonth.month;
                        break;
                    }
                }
                for (LunarDay lunarDay : LunarDay.values()) {
                    if (lunarDay.name.equals(str)) {
                        day = lunarDay.day;
                        break;
                    }
                }
                if (month == 0 || day == 0) throw new JsonParseException("JSON format does not meet the requirements.");
                IFestival festival = new LunarFestival("lunar" + str.hashCode(), month, day);
                Festivals.FESTIVALS.add(festival);
                return festival;
            }
            // "[12.23, 1.8]"
            regular = "^\\[\\d+.\\d+,\\d+.\\d+]$";
            if (str.matches(regular)) {
                String[] split = str.substring(1, str.length() - 1).split(",");
                int fromMonth = Integer.parseInt(split[0].split("\\.")[0]);
                int fromDay = Integer.parseInt(split[0].split("\\.")[1]);
                int toMonth = Integer.parseInt(split[1].split("\\.")[0]);
                int toDay = Integer.parseInt(split[1].split("\\.")[1]);
                IFestival festival = new Festival("solar" + str.hashCode(), fromMonth, fromDay, toMonth, toDay);
                Festivals.FESTIVALS.add(festival);
                return festival;
            }
            // "[腊月廿三, 正月初八]"
            regular = "^\\[\\D月[初二廿三]\\D,\\D月[初二廿三]\\D]$";
            if (str.matches(regular)) {
                String[] split = str.substring(1, str.length() - 1).split(",");
                int fromMonth = 0, fromDay = 0, toMonth = 0, toDay = 0;
                for (LunarMonth lunarMonth : LunarMonth.values()) {
                    if (lunarMonth.name.equals(split[0])) {
                        fromMonth = lunarMonth.month;
                    }
                    if (lunarMonth.name.equals(split[1])) {
                        toMonth = lunarMonth.month;
                    }
                }
                for (LunarDay lunarDay : LunarDay.values()) {
                    if (lunarDay.name.equals(split[0])) {
                        fromDay = lunarDay.day;
                    }
                    if (lunarDay.name.equals(split[1])) {
                        toDay = lunarDay.day;
                    }
                }
                if (fromMonth == 0 || fromDay == 0 || toMonth == 0 || toDay == 0)
                    throw new JsonParseException("JSON format does not meet the requirements.");
                IFestival festival = new LunarFestival("lunar" + str.hashCode(), fromMonth, fromDay, toMonth, toDay);
                Festivals.FESTIVALS.add(festival);
                return festival;
            }
            throw new JsonParseException("JSON format does not meet the requirements.");
        }

        public IFestival deserialize(JsonObject obj) throws JsonParseException {
            if (obj.has("month") && obj.has("day")) {
                int month = 0, day = 0;
                // { "month": 12, "day": 23 }
                if (obj.get("month").isJsonPrimitive() && obj.get("month").getAsJsonPrimitive().isNumber()) {
                    month = obj.get("month").getAsInt();
                    if (obj.get("day").isJsonPrimitive() && obj.get("day").getAsJsonPrimitive().isNumber()) {
                        day = obj.get("day").getAsInt();
                        IFestival festival = new Festival("solar" + obj.hashCode(), month, day);
                        Festivals.FESTIVALS.add(festival);
                        return festival;
                    }
                }
                // { "month": "腊月", "day": "廿三" }
                if (obj.get("month").isJsonPrimitive() && obj.get("month").getAsJsonPrimitive().isString()) {
                    String monthStr = obj.get("month").getAsString();
                    for (LunarMonth lunarMonth : LunarMonth.values()) {
                        if (lunarMonth.name.equals(monthStr)) {
                            month = lunarMonth.month;
                            break;
                        }
                    }
                    if (obj.get("day").isJsonPrimitive() && obj.get("day").getAsJsonPrimitive().isString()) {
                        String dayStr = obj.get("day").getAsString();
                        for (LunarDay lunarDay : LunarDay.values()) {
                            if (lunarDay.name.equals(dayStr)) {
                                day = lunarDay.day;
                                break;
                            }
                        }
                        if (month != 0 && day != 0) {
                            IFestival festival = new LunarFestival("lunar" + obj.hashCode(), month, day);
                            Festivals.FESTIVALS.add(festival);
                            return festival;
                        }
                    }
                }
            }
            if (obj.has("from") && obj.has("to")) {
                JsonElement from = obj.get("from");
                JsonElement to = obj.get("to");
                // { "from": "12.23", "to": "1.8" }
                // { "from": "腊月廿三", "to": "正月初八" }
                if (from.isJsonPrimitive() && from.getAsJsonPrimitive().isString() && to.isJsonPrimitive() && to.getAsJsonPrimitive().isString()) {
                    return this.deserialize("[" + from.getAsString() + "," + to.getAsString() + "]");
                }
                if (from.isJsonObject() && to.isJsonObject()) {
                    JsonObject fromObj = obj.get("from").getAsJsonObject();
                    JsonObject toObj = obj.get("to").getAsJsonObject();
                    // { "from": { "month": 12, "day": 23 }, "to": { "month": 1, "day": 8 } }
                    if (fromObj.has("month") && fromObj.has("day") && toObj.has("month") && toObj.has("day")) {
                        return this.deserialize("[" + fromObj.get("month").getAsInt() + "." + fromObj.get("day").getAsInt() + "," + toObj.get("month").getAsInt() + "." + toObj.get("day").getAsInt() + "]");
                    }
                    // { "from": { "month": "腊月", "day": "廿三" }, "to": { "month": "正月", "day": "初八" } }
                    if (fromObj.has("month") && fromObj.has("day") && toObj.has("month") && toObj.has("day")) {
                        return this.deserialize("[" + fromObj.get("month").getAsString() + fromObj.get("day").getAsString() + "," + toObj.get("month").getAsString() + toObj.get("day").getAsString() + "]");
                    }
                }
            }
            throw new JsonParseException("JSON format does not meet the requirements.");
        }

        @Override
        public JsonElement serialize(IFestival iFestival, Type type, JsonSerializationContext jsonSerializationContext) {
            if (iFestival.getId().startsWith("solar") && iFestival instanceof Festival festival) {
                if (festival.getStartMonth() == festival.getEndMonth() && festival.getStartDay() == festival.getEndDay()) {
                    return new JsonPrimitive(festival.getStartMonth() + "." + festival.getStartDay());
                } else {
                    return new JsonPrimitive("[" + festival.getStartMonth() + "." + festival.getStartDay() + ", " + festival.getEndMonth() + "." + festival.getEndDay() + "]");
                }
            } else if (iFestival.getId().startsWith("lunar") && iFestival instanceof LunarFestival lunarFestival) {
                if (lunarFestival.getStartMonth() == lunarFestival.getEndMonth() && lunarFestival.getStartDay() == lunarFestival.getEndDay()) {
                    return new JsonPrimitive(LunarMonth.values()[lunarFestival.getStartMonth() - 1].name + LunarDay.values()[lunarFestival.getStartDay() - 1].name);
                } else {
                    return new JsonPrimitive("[" + LunarMonth.values()[lunarFestival.getStartMonth() - 1].name + LunarDay.values()[lunarFestival.getStartDay() - 1].name + ", " + LunarMonth.values()[lunarFestival.getEndMonth() - 1].name + LunarDay.values()[lunarFestival.getEndDay() - 1].name + "]");
                }
            } else if (iFestival.getId().startsWith("jieqi") && iFestival instanceof SolarTermFestival solarTermFestival) {
                return new JsonPrimitive(solarTermFestival.solarTerm.name);
            } else if (Festivals.FESTIVALS.contains(iFestival)) {
                return new JsonPrimitive(iFestival.getId());
            } else {
                throw new JsonParseException("This festival does not exist.");
            }
        }
    }

    public enum LunarMonth {
        MONTH_1("正月", 1),
        MONTH_2("二月", 2),
        MONTH_3("三月", 3),
        MONTH_4("四月", 4),
        MONTH_5("五月", 5),
        MONTH_6("六月", 6),
        MONTH_7("七月", 7),
        MONTH_8("八月", 8),
        MONTH_9("九月", 9),
        MONTH_10("十月", 10),
        MONTH_11("冬月", 11),
        MONTH_12("腊月", 12);
        public final String name;
        public final int month;

        LunarMonth(String name, int month) {
            this.name = name;
            this.month = month;
        }
    }

    public enum LunarDay {
        DAY_1("初一", 1),
        DAY_2("初二", 2),
        DAY_3("初三", 3),
        DAY_4("初四", 4),
        DAY_5("初五", 5),
        DAY_6("初六", 6),
        DAY_7("初七", 7),
        DAY_8("初八", 8),
        DAY_9("初九", 9),
        DAY_10("初十", 10),
        DAY_11("十一", 11),
        DAY_12("十二", 12),
        DAY_13("十三", 13),
        DAY_14("十四", 14),
        DAY_15("十五", 15),
        DAY_16("十六", 16),
        DAY_17("十七", 17),
        DAY_18("十八", 18),
        DAY_19("十九", 19),
        DAY_20("二十", 20),
        DAY_21("廿一", 21),
        DAY_22("廿二", 22),
        DAY_23("廿三", 23),
        DAY_24("廿四", 24),
        DAY_25("廿五", 25),
        DAY_26("廿六", 26),
        DAY_27("廿七", 27),
        DAY_28("廿八", 28),
        DAY_29("廿九", 29),
        DAY_30("三十", 30);
        public final String name;
        public final int day;

        LunarDay(String name, int day) {
            this.name = name;
            this.day = day;
        }
    }
}
