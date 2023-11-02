package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.entity.BtwEntity;
import gum.tbhmod.main.entity.TbhEntity;
import gum.tbhmod.main.entity.entitySettings;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class EntityRegistry {

    //private static final Map<Identifier,  EntityType> ENTITIES = new LinkedHashMap<>();
    public static EntityType<TbhEntity> TBH_ENTITY;
    public static EntityType<BtwEntity> BTW_ENTITY;


    public static void registerEntityAttributes() {
        FabricDefaultAttributeRegistry.register(TBH_ENTITY, TbhEntity.createTbhAttributes());
        FabricDefaultAttributeRegistry.register(BTW_ENTITY, BtwEntity.createBtwAttributes());
    }

    public static void registerEntities() {
        TBH_ENTITY = registerEntity("tbh_entity", TbhEntity::new, TbhEntity.getSettings());
        BTW_ENTITY = registerEntity("btw_entity", BtwEntity::new, BtwEntity.getSettings());
    }

    protected static <T extends Entity> EntityType<T> registerEntity(String path, EntityType.EntityFactory<T> type, entitySettings settings) {
        EntityType<T> entityType = Registry.register(
                Registry.ENTITY_TYPE,
                new Identifier(TbhMod.MODID, path),
                FabricEntityTypeBuilder.create(settings.spawnGroup, type)
                    .dimensions(EntityDimensions.fixed(settings.x, settings.y))
                    .build());



        if (settings.spawnsNaturally) {
            BiomeModifications.addSpawn(
                    (biomeSelectionContext -> biomeSelectionContext.hasTag(settings.selectorTag)),
                    settings.spawnGroup,
                    entityType,
                    settings.spawnWeight,
                    settings.minGroupSize,
                    settings.maxGroupSize);
        }
        return entityType;
    }

//    static {
//        TBH_ENTITY = registerEntity("tbh_entity", TbhEntity::new, TbhEntity.getSettings());
//        BTW_ENTITY = registerEntity("btw_entity", BtwEntity::new, BtwEntity.getSettings());
//    }
}
