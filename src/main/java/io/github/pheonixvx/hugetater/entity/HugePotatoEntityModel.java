package io.github.pheonixvx.hugetater.entity;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class HugePotatoEntityModel extends EntityModel<HugePotatoEntity> {
	private final ModelPart bb_main;

	public HugePotatoEntityModel() {
		textureWidth = 256;
		textureHeight = 256;
		bb_main = new ModelPart(this);
		bb_main.setPivot(0.0F, 24.0F, 0.0F);
		bb_main.setTextureOffset(0, 0).addCuboid(-32.0F, -112.0F, -32.0F, 64.0F, 112.0F, 64.0F, 0.0F, false);
	}

	@Override
	public void setAngles (HugePotatoEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {

	}

	@Override
	public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		bb_main.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelPart bone, float x, float y, float z) {
		bone.pitch = x;
		bone.yaw = y;
		bone.roll = z;
	}

}