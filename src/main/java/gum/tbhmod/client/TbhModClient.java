package gum.tbhmod.client;

import gum.tbhmod.client.model.*;
import gum.tbhmod.client.renderer.*;
import gum.tbhmod.main.TbhMod;
import gum.tbhmod.main.init.EntityRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;

public class TbhModClient implements ClientModInitializer {

    public static final EntityModelLayer TBH_MODEL_LAYER = registerModel("tbh", EntityRegistry.TBH_ENTITY, TbhEntityRenderer::new);
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(TBH_MODEL_LAYER, TbhEntityModel::getTexturedModelData);
    }
    private static EntityModelLayer registerModel(String path, EntityType entityType, EntityRendererFactory rendererFactory) {
        EntityModelLayer modelLayer = new EntityModelLayer(new Identifier(TbhMod.MODID, path), "main");
        EntityRendererRegistry.register(entityType, rendererFactory);
        return modelLayer;
    }
}
