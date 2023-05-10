package gum.tbhmod.main.item;

import gum.tbhmod.main.entity.TbhEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;

public class TbhCreatureEgg extends SpawnEggItem {
    public TbhCreatureEgg(EntityType<TbhEntity> entityType) {
        super(entityType, 0xffffff , 0x401d15, new FabricItemSettings());
    }
}
