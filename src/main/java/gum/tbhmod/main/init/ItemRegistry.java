package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.entity.TbhEntity;
import gum.tbhmod.main.entity.entitySettings;
import gum.tbhmod.main.item.Cola;
import gum.tbhmod.main.item.TbhCreatureEgg;
import gum.tbhmod.main.item.TbhMeat;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static final Item COLA, TBH_CREATURE_SPAWN_EGG, TBH_MEAT;

    static {
        COLA = register(Registry.ITEM, "cola", new Cola());
        TBH_CREATURE_SPAWN_EGG = register(Registry.ITEM, "tbh_creature_spawn_egg", new TbhCreatureEgg(EntityRegistry.TBH_ENTITY));
        TBH_MEAT = register(Registry.ITEM, "tbh_meat", new TbhMeat());
    }

    public static void registerItems() {}

    public static final ItemGroup MODGROUP = FabricItemGroupBuilder.create(
                    new Identifier(TbhMod.MODID, "tbhmodgroup"))
            .icon(() -> new ItemStack(COLA))
            .appendItems(stacks -> {
                //Items
                stacks.add(new ItemStack(COLA));
                stacks.add(new ItemStack(TBH_MEAT));
                stacks.add(new ItemStack(TBH_CREATURE_SPAWN_EGG));

            })
            .build();

    public static Item register (Registry<Item> Registrytype, String path, Item item) {
        return Registry.register(Registrytype, new Identifier(TbhMod.MODID, path), item);
    }
}
