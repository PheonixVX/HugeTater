package io.github.pheonixvx.hugetater.entity.goals;

import io.github.pheonixvx.hugetater.entity.HugePotatoEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class HugePotatoAttackGoal extends MeleeAttackGoal {
	private final HugePotatoEntity potatoEntity;
	private int ticks;

	public HugePotatoAttackGoal(HugePotatoEntity potato, double speed, boolean pauseWhenMobIdle) {
		super(potato, speed, pauseWhenMobIdle);
		this.potatoEntity = potato;
	}

	public void start() {
		super.start();
		this.ticks = 0;
	}

	public void stop() {
		super.stop();
		this.potatoEntity.setAttacking(false);
	}

	public void tick() {
		super.tick();
		++this.ticks;
		if (this.ticks >= 5 && this.method_28348() < this.method_28349() / 2) {
			this.potatoEntity.setAttacking(true);
		} else {
			this.potatoEntity.setAttacking(false);
		}

	}
}
