package dev.dubhe.chinesefestivals.data;

import dev.dubhe.chinesefestivals.ChineseFestivals;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.Property;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BlockModelData {
    public final ResourceLocation resourceLocation;
    public final List<PropertyInfo> properties = new ArrayList<>();

    public BlockModelData(String id) {
        this.resourceLocation = ChineseFestivals.of(id);
    }

    public BlockModelData property(String key, String defaultValue, Function<String, Property<?>> generator) {
        this.properties.add(new PropertyInfo(key, defaultValue, generator));
        return this;
    }

    public ModelResourceLocation model() {
        List<String> nodes = properties.stream().parallel().map(it -> it.key + "=" + it.defaultValue).toList();
        return new ModelResourceLocation(this.resourceLocation, String.join(",", nodes));
    }

    public static class PropertyInfo {
        public final String key;
        public final String defaultValue;
        public final Function<String, Property<?>> generator;

        private PropertyInfo(String key, String defaultValue, Function<String, Property<?>> generator) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.generator = generator;
        }

        public Property<?> genProperty() {
            return this.generator.apply(this.key);
        }
    }
}
