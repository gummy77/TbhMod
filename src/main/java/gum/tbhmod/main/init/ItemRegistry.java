package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.item.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static final Item
            COLA, CAN, ENERGY_DRINK,
            TBH_CREATURE_SPAWN_EGG, TBH_MEAT,
            BTW_CREATURE_SPAWN_EGG, BTW_MEAT;

    static {
        COLA = register("cola", new Cola());
        CAN = register("can", new Can());
        ENERGY_DRINK = register("energy_drink", new EnergyDrink());

        TBH_CREATURE_SPAWN_EGG = register("tbh_creature_spawn_egg", new SpawnEggItem(EntityRegistry.TBH_ENTITY, 0xffffff , 0x401d15, new FabricItemSettings()));
        BTW_CREATURE_SPAWN_EGG = register("btw_creature_spawn_egg", new SpawnEggItem(EntityRegistry.BTW_ENTITY, 0xffffff , 0xB18AB7, new FabricItemSettings()));

        TBH_MEAT = register("tbh_meat", new TbhMeat());
        BTW_MEAT = register("btw_meat", new BtwMeat());
    }

    public static void registerItems() {}

    public static final ItemGroup MODGROUP = FabricItemGroupBuilder.create(
                    new Identifier(TbhMod.MODID, "tbhmodgroup"))
            .icon(() -> new ItemStack(COLA))
            .appendItems(stacks -> {
                //Items
                stacks.add(new ItemStack(COLA));
                stacks.add(new ItemStack(CAN));
                stacks.add(new ItemStack(ENERGY_DRINK));
                stacks.add(new ItemStack(TBH_MEAT));
                stacks.add(new ItemStack(BTW_MEAT));
                stacks.add(new ItemStack(TBH_CREATURE_SPAWN_EGG));
                stacks.add(new ItemStack(BTW_CREATURE_SPAWN_EGG));

            })
            .build();

    public static Item register (String path, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(TbhMod.MODID, path), item);
    }
}
