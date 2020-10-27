package io.github.pheonixvx.hugetater.client;

import io.github.pheonixvx.hugetater.HugeTater;
import io.github.pheonixvx.hugetater.entity.HugePotatoEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public class HugeTaterClient implements ClientModInitializer {
	@Override
	public void onInitializeClient () {
		EntityRendererRegistry.INSTANCE.register(HugeTater.POTATO, (dispatcher, context) -> {
			return new HugePotatoEntityRenderer(dispatcher);
		});
	}
}
