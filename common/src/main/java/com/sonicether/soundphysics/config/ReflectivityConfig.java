package com.sonicether.soundphysics.config;

import com.sonicether.soundphysics.SoundPhysics;
import com.sonicether.soundphysics.SoundPhysicsMod;
import de.maxhenkel.configbuilder.PropertyConfig;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.block.SoundType;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ReflectivityConfig extends PropertyConfig {

    private static final Map<SoundType, String> TRANSLATION_MAP;

    private Map<SoundType, Double> reflectivity;

    public ReflectivityConfig(Path path) {
        super(path);
        save();
    }

    @Override
    public void load() throws IOException {
        super.load();

        reflectivity = createDefaultMap();

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            double value;
            try {
                value = Double.parseDouble((String) entry.getValue());
            } catch (NumberFormatException e) {
                SoundPhysics.LOGGER.warn("Failed to parse reflectivity of {}", key);
                continue;
            }
            SoundType soundType = TRANSLATION_MAP.entrySet().stream().filter(e -> e.getValue().equals(key)).map(Map.Entry::getKey).findFirst().orElse(null);
            if (soundType == null) {
                SoundPhysics.LOGGER.warn("Sound type {} not found", key);
                continue;
            }

            reflectivity.put(soundType, value);
        }
    }

    @Override
    public void saveSync() {
        properties.clear();

        for (Map.Entry<SoundType, Double> entry : reflectivity.entrySet()) {
            properties.put(getName(entry.getKey()), String.valueOf(entry.getValue()));
        }

        super.saveSync();
    }

    public Map<SoundType, Double> getReflectivities() {
        return reflectivity;
    }

    public double getReflectivity(SoundType soundType) {
        return reflectivity.getOrDefault(soundType, SoundPhysicsMod.CONFIG.defaultBlockReflectivity.get());
    }

    public ReflectivityConfig setReflectivity(SoundType soundType, double value) {
        reflectivity.put(soundType, value);
        return this;
    }

    public Map<SoundType, Double> createDefaultMap() {
        Map<SoundType, Double> map = new HashMap<>();
        for (SoundType type : TRANSLATION_MAP.keySet()) {
            map.put(type, SoundPhysicsMod.CONFIG.defaultBlockReflectivity.get());
        }

        map.put(SoundType.STONE, 1.5D);
        map.put(SoundType.NETHERITE_BLOCK, 1.5D);
        map.put(SoundType.TUFF, 1.5D);
        map.put(SoundType.AMETHYST, 1.5D);
        map.put(SoundType.BASALT, 1.5D);
        map.put(SoundType.CALCITE, 1.5D);
        map.put(SoundType.BONE_BLOCK, 1.5D);
        map.put(SoundType.COPPER, 1.25D);
        map.put(SoundType.DEEPSLATE, 1.5D);
        map.put(SoundType.DEEPSLATE_BRICKS, 1.5D);
        map.put(SoundType.DEEPSLATE_TILES, 1.5D);
        map.put(SoundType.POLISHED_DEEPSLATE, 1.5D);
        map.put(SoundType.NETHER_BRICKS, 1.5D);
        map.put(SoundType.NETHERRACK, 1.1D);
        map.put(SoundType.NETHER_GOLD_ORE, 1.1D);
        map.put(SoundType.NETHER_ORE, 1.1D);
        map.put(SoundType.STEM, 0.4D);
        map.put(SoundType.WOOL, 0.1D);
        map.put(SoundType.HONEY_BLOCK, 0.1D);
        map.put(SoundType.MOSS, 0.1D);
        map.put(SoundType.SOUL_SAND, 0.2D);
        map.put(SoundType.SOUL_SOIL, 0.2D);
        map.put(SoundType.CORAL_BLOCK, 0.2D);
        map.put(SoundType.METAL, 1.25D);
        map.put(SoundType.WOOD, 0.4D);
        map.put(SoundType.GRAVEL, 0.3D);
        map.put(SoundType.GRASS, 0.3D);
        map.put(SoundType.GLASS, 0.75D);
        map.put(SoundType.SAND, 0.2D);
        map.put(SoundType.SNOW, 0.15D);

        return map;
    }

    static {
        Map<SoundType, String> names = new HashMap<>();

        // We need to put it in manually, because of remapping
        names.put(SoundType.WOOD, "WOOD");
        names.put(SoundType.GRAVEL, "GRAVEL");
        names.put(SoundType.GRASS, "GRASS");
        names.put(SoundType.LILY_PAD, "LILY_PAD");
        names.put(SoundType.STONE, "STONE");
        names.put(SoundType.METAL, "METAL");
        names.put(SoundType.GLASS, "GLASS");
        names.put(SoundType.WOOL, "WOOL");
        names.put(SoundType.SAND, "SAND");
        names.put(SoundType.SNOW, "SNOW");
        names.put(SoundType.POWDER_SNOW, "POWDER_SNOW");
        names.put(SoundType.LADDER, "LADDER");
        names.put(SoundType.ANVIL, "ANVIL");
        names.put(SoundType.SLIME_BLOCK, "SLIME_BLOCK");
        names.put(SoundType.HONEY_BLOCK, "HONEY_BLOCK");
        names.put(SoundType.WET_GRASS, "WET_GRASS");
        names.put(SoundType.CORAL_BLOCK, "CORAL_BLOCK");
        names.put(SoundType.BAMBOO, "BAMBOO");
        names.put(SoundType.BAMBOO_SAPLING, "BAMBOO_SAPLING");
        names.put(SoundType.SCAFFOLDING, "SCAFFOLDING");
        names.put(SoundType.SWEET_BERRY_BUSH, "SWEET_BERRY_BUSH");
        names.put(SoundType.CROP, "CROP");
        names.put(SoundType.HARD_CROP, "HARD_CROP");
        names.put(SoundType.VINE, "VINE");
        names.put(SoundType.NETHER_WART, "NETHER_WART");
        names.put(SoundType.LANTERN, "LANTERN");
        names.put(SoundType.STEM, "STEM");
        names.put(SoundType.NYLIUM, "NYLIUM");
        names.put(SoundType.FUNGUS, "FUNGUS");
        names.put(SoundType.ROOTS, "ROOTS");
        names.put(SoundType.SHROOMLIGHT, "SHROOMLIGHT");
        names.put(SoundType.WEEPING_VINES, "WEEPING_VINES");
        names.put(SoundType.TWISTING_VINES, "TWISTING_VINES");
        names.put(SoundType.SOUL_SAND, "SOUL_SAND");
        names.put(SoundType.SOUL_SOIL, "SOUL_SOIL");
        names.put(SoundType.BASALT, "BASALT");
        names.put(SoundType.WART_BLOCK, "WART_BLOCK");
        names.put(SoundType.NETHERRACK, "NETHERRACK");
        names.put(SoundType.NETHER_BRICKS, "NETHER_BRICKS");
        names.put(SoundType.NETHER_SPROUTS, "NETHER_SPROUTS");
        names.put(SoundType.NETHER_ORE, "NETHER_ORE");
        names.put(SoundType.BONE_BLOCK, "BONE_BLOCK");
        names.put(SoundType.NETHERITE_BLOCK, "NETHERITE_BLOCK");
        names.put(SoundType.ANCIENT_DEBRIS, "ANCIENT_DEBRIS");
        names.put(SoundType.LODESTONE, "LODESTONE");
        names.put(SoundType.CHAIN, "CHAIN");
        names.put(SoundType.NETHER_GOLD_ORE, "NETHER_GOLD_ORE");
        names.put(SoundType.GILDED_BLACKSTONE, "GILDED_BLACKSTONE");
        names.put(SoundType.CANDLE, "CANDLE");
        names.put(SoundType.AMETHYST, "AMETHYST");
        names.put(SoundType.AMETHYST_CLUSTER, "AMETHYST_CLUSTER");
        names.put(SoundType.SMALL_AMETHYST_BUD, "SMALL_AMETHYST_BUD");
        names.put(SoundType.MEDIUM_AMETHYST_BUD, "MEDIUM_AMETHYST_BUD");
        names.put(SoundType.LARGE_AMETHYST_BUD, "LARGE_AMETHYST_BUD");
        names.put(SoundType.TUFF, "TUFF");
        names.put(SoundType.CALCITE, "CALCITE");
        names.put(SoundType.DRIPSTONE_BLOCK, "DRIPSTONE_BLOCK");
        names.put(SoundType.POINTED_DRIPSTONE, "POINTED_DRIPSTONE");
        names.put(SoundType.COPPER, "COPPER");
        names.put(SoundType.CAVE_VINES, "CAVE_VINES");
        names.put(SoundType.SPORE_BLOSSOM, "SPORE_BLOSSOM");
        names.put(SoundType.AZALEA, "AZALEA");
        names.put(SoundType.FLOWERING_AZALEA, "FLOWERING_AZALEA");
        names.put(SoundType.MOSS_CARPET, "MOSS_CARPET");
        names.put(SoundType.MOSS, "MOSS");
        names.put(SoundType.BIG_DRIPLEAF, "BIG_DRIPLEAF");
        names.put(SoundType.SMALL_DRIPLEAF, "SMALL_DRIPLEAF");
        names.put(SoundType.ROOTED_DIRT, "ROOTED_DIRT");
        names.put(SoundType.HANGING_ROOTS, "HANGING_ROOTS");
        names.put(SoundType.AZALEA_LEAVES, "AZALEA_LEAVES");
        names.put(SoundType.SCULK_SENSOR, "SCULK_SENSOR");
        names.put(SoundType.GLOW_LICHEN, "GLOW_LICHEN");
        names.put(SoundType.DEEPSLATE, "DEEPSLATE");
        names.put(SoundType.DEEPSLATE_BRICKS, "DEEPSLATE_BRICKS");
        names.put(SoundType.DEEPSLATE_TILES, "DEEPSLATE_TILES");
        names.put(SoundType.POLISHED_DEEPSLATE, "POLISHED_DEEPSLATE");

        TRANSLATION_MAP = Collections.unmodifiableMap(names);
    }

    public static String getName(SoundType soundType) {
        return TRANSLATION_MAP.get(soundType);
    }

    public static Component getNameComponent(SoundType soundType) {
        String name = getName(soundType);
        String[] split = name.split("_");
        StringBuilder builder = new StringBuilder();
        for (String s : split) {
            builder.append(s.charAt(0));
            builder.append(s.substring(1).toLowerCase(Locale.ROOT));
            builder.append(" ");
        }

        return new TextComponent(builder.toString().trim());
    }

}