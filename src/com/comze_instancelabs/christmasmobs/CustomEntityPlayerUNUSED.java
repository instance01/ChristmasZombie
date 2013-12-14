package com.comze_instancelabs.christmasmobs;

import net.minecraft.server.v1_7_R1.EnumGamemode;
import net.minecraft.server.v1_7_R1.GenericAttributes;
import net.minecraft.server.v1_7_R1.MinecraftServer;
import net.minecraft.server.v1_7_R1.PlayerInteractManager;
import net.minecraft.server.v1_7_R1.WorldServer;
import net.minecraft.util.com.mojang.authlib.GameProfile;

public class CustomEntityPlayerUNUSED extends net.minecraft.server.v1_7_R1.EntityPlayer {
	
    protected int m_jumpTicks = 0;

    public CustomEntityPlayerUNUSED(MinecraftServer minecraftserver, WorldServer world, GameProfile profile, PlayerInteractManager iteminworldmanager)
    {
            super(minecraftserver, world, profile, iteminworldmanager);

            this.bc().b(GenericAttributes.b).setValue(16);
            iteminworldmanager.setGameMode(EnumGamemode.SURVIVAL);
            this.noDamageTicks = 1;
            this.X = 1;
            this.fauxSleeping = true;
    }


    private void applyMovement()
    {
    	if(this.bd){
    		boolean inLiquid = L() || N();
    		if (inLiquid)
    			this.motY += 0.04;
    		else if (this.onGround && this.m_jumpTicks == 0){
    			this.be();
    			this.m_jumpTicks = 10;
    		}
    	}else
    		this.m_jumpTicks = 0;

        this.be *= 0.98F;
        this.bf *= 0.98F;
        this.bg *= 0.9F;

        this.e(this.be, this.bf);
    }

    @Override
    public void e(float inXMotion, float inZMotion)
    {
            float[] motion = new float[] { inXMotion, inZMotion, (float)this.motY };
            this.motY = (double)motion[2];
            super.e(motion[0], motion[1]);
    }

}
