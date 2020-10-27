package io.github.pheonixvx.hugetater.entity;

import io.github.pheonixvx.hugetater.HugeTater;
import io.github.pheonixvx.hugetater.entity.goals.HugePotatoAttackGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.boss.BossBar;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

@SuppressWarnings("EntityConstructor")
public class HugePotatoEntity extends PathAwareEntity implements RangedAttackMob {
	private final ServerBossBar bossBar;

	public HugePotatoEntity (EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		this.bossBar = (ServerBossBar)(new ServerBossBar(this.getDisplayName(), BossBar.Color.PURPLE, BossBar.Style.PROGRESS)).setDarkenSky(true);
		this.bossBar.setPercent(this.getHealth() / this.getMaxHealth());
		this.bossBar.setVisible(true);
		this.setHealth(100.0f);
	}

	public void onStartedTrackingBy(ServerPlayerEntity player) {
		super.onStartedTrackingBy(player);
		this.bossBar.addPlayer(player);
	}

	public void onStoppedTrackingBy(ServerPlayerEntity player) {
		super.onStoppedTrackingBy(player);
		this.bossBar.removePlayer(player);
	}

	public void initGoals() {
		this.goalSelector.add(2, new ProjectileAttackGoal(this, 1.0D, 40, 20.0F));
		this.goalSelector.add(6, new WanderAroundFarGoal(this, 1.0D));
		this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.add(8, new LookAroundGoal(this));
		this.goalSelector.add(7, new LookAroundGoal(this));
		this.goalSelector.add(0, new SwimGoal(this));
		this.targetSelector.add(2, new FollowTargetGoal(this, PlayerEntity.class, true));
		this.goalSelector.add(2, new HugePotatoAttackGoal(this, 1.0D, false));
	}

	public boolean shouldRenderOverlay() {
		return true;
	}

	public SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.ENTITY_HOGLIN_RETREAT;
	}
	public SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ZOMBIE_VILLAGER_CURE;
	}

	@Override
	public void attack (LivingEntity target, float pullProgress) {
		// Custom attack
		this.tryAttack(target);
		this.onAttacking(target);
	}

	@Override
	public void onDeath(DamageSource source) {
		if (!this.removed && !this.dead) {
			Entity entity = source.getAttacker();
			LivingEntity livingEntity = this.getPrimeAdversary();
			if (this.scoreAmount >= 0 && livingEntity != null) {
				livingEntity.updateKilledAdvancementCriterion(this, this.scoreAmount, source);
			}

			if (this.isSleeping()) {
				this.wakeUp();
			}

			this.dead = true;
			this.getDamageTracker().update();
			if (this.world instanceof ServerWorld) {
				if (entity != null) {
					entity.onKilledOther((ServerWorld)this.world, this);
				}

				this.drop(source);
				this.onKilledBy(livingEntity);
			}

			this.world.sendEntityStatus(this, (byte)3);
			this.setPose(EntityPose.DYING);
			this.dropStack(new ItemStack(HugeTater.POTATO_BLOCK));
		}
	}
}
