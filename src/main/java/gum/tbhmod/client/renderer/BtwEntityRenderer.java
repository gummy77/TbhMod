package gum.tbhmod.client.renderer;

import gum.tbhmod.client.TbhModClient;
import gum.tbhmod.client.model.BtwEntityModel;
import gum.tbhmod.client.model.TbhEntityModel;
import gum.tbhmod.main.entity.BtwEntity;
import gum.tbhmod.main.entity.TbhEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class BtwEntityRenderer extends MobEntityRenderer<BtwEntity, BtwEntityModel> {
    public BtwEntityRenderer(EntityRendererFactory.Context context){
        super(context, new BtwEntityModel(context.getPart(TbhModClient.BTW_MODEL_LAYER)), 0.5f);
        this.shadowRadius = 0.45f;
    }

    @Override
    public Identifier getTexture(BtwEntity entity) {
        return new Identifier("tbhmod", "textures/entity/btw/btw_texture.png");
    }
}
