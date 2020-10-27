package io.github.pheonixvx.hugetater;

import io.github.pheonixvx.hugetater.entity.HugePotatoEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class HugeTater implements ModInitializer {

	public static final String MOD_ID = "hugetater";

	public static final EntityType<HugePotatoEntity> POTATO = Registry.register(
		Registry.ENTITY_TYPE,
		new Identifier(MOD_ID, "potato"),
		FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, HugePotatoEntity::new).dimensions(EntityDimensions.fixed(4.0f, 4.0f)).build()
	);

	public static final ItemGroup itemGroup = FabricItemGroupBuilder.create(
		new Identifier(MOD_ID, "itemgroup"))
		.icon(() -> new ItemStack(Items.BOWL))
		.build();

	public static final Block POTATO_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(4.0f).nonOpaque());

	@Override
	public void onInitialize () {
		FabricDefaultAttributeRegistry.register(POTATO,
			HugePotatoEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, 100.00)
				.add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D)
		);

		Registry.register(Registry.BLOCK, new Identifier(MOD_ID, "potato_block"), POTATO_BLOCK);
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "potato_block"),
			new BlockItem(POTATO_BLOCK, new Item.Settings().group(itemGroup))
		);
		BlockRenderLayerMap.INSTANCE.putBlock(POTATO_BLOCK, RenderLayer.getCutout());
	}
}
