package gum.tbhmod.main.init;

import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.item.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.*;

public class ItemRegistry {

    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    public static final Cola COLA;
    public static final Item CAN;
    public static final EnergyDrink ENERGY_DRINK;
    public static final TbhMeat TBH_MEAT;
    public static final TbhMeat BTW_MEAT;
    public static final SpawnEggItem TBH_CREATURE_SPAWN_EGG;
    public static final SpawnEggItem BTW_CREATURE_SPAWN_EGG;



    public static void registerItems() {

        Iterator<Map.Entry<Identifier, Item>> var0 = ITEMS.entrySet().iterator();
        Map.Entry entry;

        while(var0.hasNext()) {
            entry = var0.next();
            Registry.register(Registry.ITEM, (Identifier) entry.getKey(), (Item) entry.getValue());
        }
    }

    public static Item register (String path, Item item) {
        return ITEMS.put(new Identifier(TbhMod.MODID, path), item);
    }

    public static Item.Settings getSettings(){
        return new FabricItemSettings().group(TbhMod.MODGROUP);
    }

    static {
        COLA = (Cola) register("cola", new Cola(getSettings()));
        CAN = register("can", new Item(getSettings().maxCount(16)));
        ENERGY_DRINK = (EnergyDrink) register("energy_drink", new EnergyDrink(getSettings()));

        TBH_MEAT = (TbhMeat) register("tbh_meat", new TbhMeat(getSettings(), EffectRegistry.AUTISM));
        BTW_MEAT = (TbhMeat) register("btw_meat", new TbhMeat(getSettings(), EffectRegistry.ADHD));

        TBH_CREATURE_SPAWN_EGG = (SpawnEggItem) register("tbh_creature_spawn_egg", new SpawnEggItem(EntityRegistry.TBH_ENTITY, 0xffffff , 0x401d15, getSettings()));
        BTW_CREATURE_SPAWN_EGG = (SpawnEggItem) register("btw_creature_spawn_egg", new SpawnEggItem(EntityRegistry.BTW_ENTITY, 0xffffff , 0xB18AB7, getSettings()));
    }
}
