package gum.tbhmod.main;

import gum.tbhmod.main.item.Cola;
import net.fabricmc.api.ModInitializer;
import gum.tbhmod.main.init.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class TbhMod implements ModInitializer {
    public static final String MODID = "tbhmod";

    public static final ItemGroup MODGROUP = FabricItemGroupBuilder.create(
            new Identifier(TbhMod.MODID, "tbhmodgroup"))
            .icon(() -> new ItemStack(ItemRegistry.COLA))
            .build();

    @Override
    public void onInitialize() {
        ItemRegistry.registerItems();
        EntityRegistry.registerEntityAttributes();


        EffectRegistry.registerEffects();
        SoundRegistry.registerSounds();
        AdvancementRegistry.registerAdvancements();
    }
}
