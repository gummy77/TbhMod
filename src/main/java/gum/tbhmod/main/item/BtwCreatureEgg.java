package gum.tbhmod.main.item;

import gum.tbhmod.main.entity.TbhEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.SpawnEggItem;

public class BtwCreatureEgg extends SpawnEggItem {
    public BtwCreatureEgg(EntityType<TbhEntity> entityType) {
        super(entityType, 0xffffff , 0xB18AB7, new FabricItemSettings());
    }
}
