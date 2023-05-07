package gum.tbhmod.client.renderer;

import gum.tbhmod.client.TbhModClient;
import gum.tbhmod.client.model.TbhEntityModel;
import gum.tbhmod.main.entity.TbhEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class TbhEntityRenderer extends MobEntityRenderer<TbhEntity, TbhEntityModel> {
    public TbhEntityRenderer(EntityRendererFactory.Context context){
        super(context, new TbhEntityModel(context.getPart(TbhModClient.TBH_MODEL_LAYER)), 0.5f);
        this.shadowRadius = 0.45f;
    }

    @Override
    public Identifier getTexture(TbhEntity entity) {
        return new Identifier("tbhmod", "textures/entity/tbh/tbh_texture.png");
    }
}
