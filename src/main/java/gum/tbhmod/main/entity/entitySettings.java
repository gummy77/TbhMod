package gum.tbhmod.main.entity;

import net.minecraft.entity.SpawnGroup;
import net.minecraft.tag.TagKey;
import net.minecraft.world.biome.Biome;

public class entitySettings {

    public String path;
    public SpawnGroup spawnGroup;
    public TagKey<Biome> selectorTag;
    public float x;
    public float y;
    public int spawnWeight;
    public int minGroupSize;
    public int maxGroupSize;
    public boolean spawnsNaturally = false;

    public entitySettings(String path, SpawnGroup spawnGroup, TagKey<Biome> selectorTag, float x, float y, int spawnWeight, int minGroupSize, int maxGroupSize) {
        this.path = path;
        this.spawnGroup = spawnGroup;
        this.selectorTag = selectorTag;
        this.x = x;
        this.y = y;
        this.spawnWeight = spawnWeight;
        this.minGroupSize = minGroupSize;
        this.maxGroupSize = maxGroupSize;
        this.spawnsNaturally = true;
    }

    public entitySettings(String path, SpawnGroup spawnGroup, TagKey<Biome> selectorTag, float x, float y) {
        this.path = path;
        this.spawnGroup = spawnGroup;
        this.selectorTag = selectorTag;
        this.x = x;
        this.y = y;
    }
}
