package gum.tbhmod.client.model;

import gum.tbhmod.main.entity.BtwEntity;
import gum.tbhmod.main.entity.TbhEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelPartNames;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class BtwEntityModel extends EntityModel<BtwEntity> {
	private final ModelPart root;
	private final ModelPart head, body;

	private final ModelPart rightLeg, leftLeg;

	public BtwEntityModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild(EntityModelPartNames.HEAD);
		this.body = root.getChild(EntityModelPartNames.BODY);
		this.rightLeg = root.getChild(EntityModelPartNames.RIGHT_LEG);
		this.leftLeg = root.getChild(EntityModelPartNames.LEFT_LEG);
	}

	public static TexturedModelData getTexturedModelData () {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();

		modelPartData.addChild(EntityModelPartNames.HEAD, ModelPartBuilder.create()
						.uv(0, 0).cuboid(-4f, -8f, -3f, 8f, 8f, 6f)
						.uv(0, 20).cuboid(1f, -10f, 0f, 3f, 2f, 1f)
						.uv(8, 20).cuboid(-4f, -10f, 0f, 3f, 2f, 1f),
				ModelTransform.pivot(0f, 18f, 0f));

		modelPartData.addChild(EntityModelPartNames.BODY, ModelPartBuilder.create()
						.uv(0, 14).cuboid(-2f, -4f, -1f, 4f, 4f, 2f),
				ModelTransform.pivot(0f, 19.5f, 0f));

		modelPartData.addChild(EntityModelPartNames.RIGHT_LEG, ModelPartBuilder.create()
						.uv(12, 14).cuboid(0f, 0f, -1f, 2f, 3f, 2f),
				ModelTransform.pivot(0f, 21f, 0f));
		modelPartData.addChild(EntityModelPartNames.LEFT_LEG, ModelPartBuilder.create()
						.uv(18, 17).cuboid(-2f, 0f, -1f, 2f, 3f, 2f),
				ModelTransform.pivot(0f, 21f, 0f));

		return TexturedModelData.of(modelData, 32, 32);
	}

	public void setAngles(BtwEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;
	}
	public void animateModel(BtwEntity tbhEntity, float limbAngle, float limbDistance, float tickDelta) {
		if (tbhEntity.isInSittingPose()) {
			this.head.setPivot(0f, 20f, 0f);

			this.body.setPivot(0f, 24f, 0f);

			this.rightLeg.setPivot(0.5f, 23f, -1.5f);
			this.leftLeg.setPivot(-0.5f, 23f, -1.5f);

			this.leftLeg.pitch = 4.712389F;
			this.rightLeg.pitch = 4.712389F;
			this.leftLeg.yaw = 0.5F;
			this.rightLeg.yaw = -0.5F;
			this.leftLeg.roll = 0F;
			this.rightLeg.roll = 0F;
		}else{
			this.head.setPivot(0f, 17f, 0f);

			this.body.setPivot(0f, 21f, 0f);

			this.rightLeg.setPivot(0f, 21f, 0f);
			this.leftLeg.setPivot(0f, 21f, 0f);


			this.leftLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
			this.rightLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
			this.leftLeg.yaw = 0F;
			this.rightLeg.yaw = 0F;
			this.leftLeg.roll = 0.1F;
			this.rightLeg.roll = -0.1F;
		}

	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.root.render(matrices, vertices, light, overlay);
	}
}