package gum.tbhmod.main.entity;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.data.server.BiomeTagProvider;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

import java.util.Collections;
import java.util.List;

public class entitySettings {

    public String path;
    public EntityType.EntityFactory entityFactory;
    public SpawnGroup spawnGroup;
    public TagKey<Biome> selectorTag;
    public float x;
    public float y;
    public int spawnWeight;
    public int minGroupSize;
    public int maxGroupSize;
    public boolean spawnsNaturally = false;

    public entitySettings (String path, EntityType.EntityFactory entityFactory, SpawnGroup spawnGroup, TagKey<Biome> selectorTag, float x, float y, int spawnWeight, int minGroupSize, int maxGroupSize){
        this.path = path;
        this.entityFactory = entityFactory;
        this.spawnGroup = spawnGroup;
        this.selectorTag = selectorTag;
        this.x = x;
        this.y = y;
        this.spawnWeight = spawnWeight;
        this.minGroupSize = minGroupSize;
        this.maxGroupSize = maxGroupSize;
        this.spawnsNaturally = true;
    }

    public entitySettings (String path, EntityType.EntityFactory entityFactory, SpawnGroup spawnGroup, TagKey<Biome> selectorTag, float x, float y){
        this.path = path;
        this.entityFactory = entityFactory;
        this.spawnGroup = spawnGroup;
        this.selectorTag = selectorTag;
        this.x = x;
        this.y = y;
    }
}
