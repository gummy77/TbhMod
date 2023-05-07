package gum.tbhmod.main.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.item.SpawnEggItem;



public class TbhCreatureEgg extends SpawnEggItem {
    public TbhCreatureEgg(EntityType entityType) {
        super(entityType, 0xffffff , 0x401d15, new FabricItemSettings());
    }
}
