package io.github.pheonixvx.hugetater.entity;

import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class HugePotatoEntityRenderer extends MobEntityRenderer<HugePotatoEntity, HugePotatoEntityModel> {

	public HugePotatoEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
		super(entityRenderDispatcher, new HugePotatoEntityModel(), 0.5f);
	}

	@Override
	public Identifier getTexture(HugePotatoEntity entity) {
		return new Identifier("hugetater", "textures/entity/potato/hugetater.png");
	}
}
