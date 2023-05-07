package gum.tbhmod.client.model;

import gum.tbhmod.main.entity.TbhEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class TbhEntityModel extends EntityModel<TbhEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart root;
	private final ModelPart head;

	private final ModelPart rightFrontLeg, leftFrontLeg, rightBackLeg, leftBackLeg;

	public TbhEntityModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild(EntityModelPartNames.HEAD);
		this.rightFrontLeg = root.getChild(EntityModelPartNames.RIGHT_FRONT_LEG);
		this.leftFrontLeg = root.getChild(EntityModelPartNames.LEFT_FRONT_LEG);
		this.rightBackLeg = root.getChild(EntityModelPartNames.RIGHT_HIND_LEG);
		this.leftBackLeg = root.getChild(EntityModelPartNames.LEFT_HIND_LEG);
	}

	public static TexturedModelData getTexturedModelData () {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(-4f, -4f, -7f, 8f, 8f, 8f),
				ModelTransform.pivot(0f, 15f, -1f));

		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
						.uv(0, 16)
						.cuboid(0f, 0f, 0f, 6f, 5f, 8f),
				ModelTransform.pivot(-3f, 16f, -2f));

		modelPartData.addChild(EntityModelPartNames.RIGHT_FRONT_LEG, ModelPartBuilder.create()
						.uv(24, 0)
						.cuboid(0f, 0f, -1f, 2f, 3f, 2f),
				ModelTransform.pivot(1f, 21f, -1f));

		modelPartData.addChild(EntityModelPartNames.LEFT_FRONT_LEG, ModelPartBuilder.create()
						.uv(20, 16)
						.cuboid(0f, 0f, -1f, 2f, 3f, 2f),
				ModelTransform.pivot(-3f, 21f, -1f));
		modelPartData.addChild(EntityModelPartNames.RIGHT_HIND_LEG, ModelPartBuilder.create()
						.uv(0, 16)
						.cuboid(0f, 0f, -1f, 2f, 3f, 2f),
				ModelTransform.pivot(1f, 21f, 5f));
		modelPartData.addChild(EntityModelPartNames.LEFT_HIND_LEG, ModelPartBuilder.create()
						.uv(0, 0)
						.cuboid(0f, 0f, -1f, 2f, 3f, 2f),
				ModelTransform.pivot(-3f, 21f, 5f));

		return TexturedModelData.of(modelData, 32, 32);
	}

	public void setAngles(TbhEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;

		this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;

		this.leftBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
		this.rightBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
	}
	public void animateModel(TbhEntity entity, float limbAngle, float limbDistance, float tickDelta) {}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.root.render(matrices, vertices, light, overlay);
	}
}