package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemRegistry {

    public static final Item CAN;
    public static final EnergyDrink ENERGY_DRINK;
    public static final Cola COLA, COLA_BOTTLE;
    public static final CreatureMeat TBH_MEAT;
    public static final CreatureMeat BTW_MEAT;
    public static SpawnEggItem TBH_CREATURE_SPAWN_EGG;
    public static SpawnEggItem BTW_CREATURE_SPAWN_EGG;



    public static void registerItems() {
        TBH_CREATURE_SPAWN_EGG = (SpawnEggItem) register("tbh_creature_spawn_egg", new SpawnEggItem(EntityRegistry.TBH_ENTITY, 0xffffff , 0x401d15, getSettings()));
        BTW_CREATURE_SPAWN_EGG = (SpawnEggItem) register("btw_creature_spawn_egg", new SpawnEggItem(EntityRegistry.BTW_ENTITY, 0xffffff , 0xB18AB7, getSettings()));
    }

    public static Item register (String path, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(TbhMod.MODID, path), item);
    }

    public static Item.Settings getSettings(){
        return new FabricItemSettings().group(TbhMod.MODGROUP);
    }

    static {
        CAN = register("can", new Item(getSettings().maxCount(16)));
        ENERGY_DRINK = (EnergyDrink) register("energy_drink", new EnergyDrink(getSettings()));
        COLA = (Cola) register("cola", new Cola(getSettings()));
        COLA_BOTTLE = (Cola) register("cola_bottle", new Cola(getSettings()));

        TBH_MEAT = (CreatureMeat) register("tbh_meat", new CreatureMeat(getSettings(), EffectRegistry.AUTISM));
        BTW_MEAT = (CreatureMeat) register("btw_meat", new CreatureMeat(getSettings(), EffectRegistry.ADHD));
    }
}
