package com.comze_instancelabs.zombie;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.server.v1_7_R1.EntityHuman;
import net.minecraft.server.v1_7_R1.EntityMonster;
import net.minecraft.server.v1_7_R1.EntityVillager;
import net.minecraft.server.v1_7_R1.PathfinderGoal;
import net.minecraft.server.v1_7_R1.PathfinderGoalFloat;
import net.minecraft.server.v1_7_R1.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_7_R1.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveThroughVillage;
import net.minecraft.server.v1_7_R1.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_7_R1.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_7_R1.PathfinderGoalRandomStroll;
import net.minecraft.server.v1_7_R1.World;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Zombie;

public class CustomEntityZombie extends net.minecraft.server.v1_7_R1.EntityZombie {

	public CustomEntityZombie(World world) {
		super(world);
		
		try {
			Field goala = this.goalSelector.getClass().getDeclaredField("a");
			goala.setAccessible(true);
			((List<PathfinderGoal>) goala.get(this.goalSelector)).clear();
			Field targeta = this.targetSelector.getClass()
					.getDeclaredField("a");
			targeta.setAccessible(true);
			((List<PathfinderGoal>) targeta.get(this.targetSelector)).clear();
			this.goalSelector.a(0, new PathfinderGoalLookAtPlayer(this,
					EntityHuman.class, 6.0F));

			this.targetSelector.a(2,
					new PathfinderGoalHurtByTarget(this, false));
			this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(
					this, EntityMonster.class, 0, false, true));
			

            goala.set(this.goalSelector, new ArrayList());
            goala.set(this.targetSelector, new ArrayList());
		} catch (Exception e) {
		}
	    
		this.getNavigation().b(true);
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, EntityHuman.class, 0.0D, false));
        this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, EntityVillager.class, 0.0D, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 0.0D));
        this.goalSelector.a(6, new PathfinderGoalMoveThroughVillage(this, 0.0D, false));
        this.goalSelector.a(7, new PathfinderGoalRandomStroll(this, 0.0D));
        this.goalSelector.a(8, new PathfinderGoalLookAtPlayer(this, EntityHuman.class, 8.0F));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, 0, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityVillager.class, 0, false));
        this.a(0.1F, 0.1F);
        this.bi = 0.0D;
	}

	public void anger() {
		this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget(
				this, EntityHuman.class, 0, false, true));
	}

	public Entity getEntity() {
		Zombie currentZombie = (Zombie) this.getBukkitEntity();
		return currentZombie;
	}
}
