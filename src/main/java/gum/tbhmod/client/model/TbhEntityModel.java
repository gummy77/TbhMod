package gum.tbhmod.client.model;

import com.sun.jna.platform.win32.WinBase;
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
	private final ModelPart head, body;

	private final ModelPart rightFrontLeg, leftFrontLeg, rightBackLeg, leftBackLeg;

	public TbhEntityModel(ModelPart root) {
		this.root = root;
		this.head = root.getChild(EntityModelPartNames.HEAD);
		this.body = root.getChild(EntityModelPartNames.BODY);
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
						.cuboid(-3f, -2.5f, -8f, 6f, 5f, 8f),
				ModelTransform.pivot(0f, 18.5f, 6f));

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
	}
	public void animateModel(TbhEntity tbhEntity, float limbAngle, float limbDistance, float tickDelta) {
		if (tbhEntity.isInSittingPose()) {
			this.head.setPivot(0f, 15.5f, 0f);

			this.body.setPivot(0f, 24f, 2f);
			this.body.pitch = -1.2F;

			this.rightBackLeg.setPivot(-4f, 23f, 1f);
			this.leftBackLeg.setPivot(2f, 23f, 1f);
			this.rightFrontLeg.setPivot(0.5f, 21f, -1f);
			this.leftFrontLeg.setPivot(-2.5f, 21f, -1f);

			this.leftFrontLeg.pitch = 5.811947F;
			this.rightFrontLeg.pitch = 5.811947F;
			this.leftBackLeg.pitch = 4.712389F;
			this.rightBackLeg.pitch = 4.712389F;
			this.leftBackLeg.yaw = -0.5F;
			this.rightBackLeg.yaw = 0.5F;
		}else{
			this.head.setPivot(0f, 15f, -1f);

			this.body.setPivot(0f, 18.5f, 6f);
			this.body.pitch = 0;

			this.rightBackLeg.setPivot(-3f, 21f, 5f);
			this.leftBackLeg.setPivot(1f, 21f, 5f);
			this.rightFrontLeg.setPivot(1f, 21f, -1f);
			this.leftFrontLeg.setPivot(-3f, 21f, -1f);

			this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
			this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
			this.leftBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * 1.4F * limbDistance;
			this.rightBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * 1.4F * limbDistance;
			this.leftBackLeg.yaw = 0;
			this.rightBackLeg.yaw = 0;
		}

	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
		this.root.render(matrices, vertices, light, overlay);
	}
}