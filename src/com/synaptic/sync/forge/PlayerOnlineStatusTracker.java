package com.synaptic.sync.forge;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerOnlineStatusTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		FMLLog.info("*****PLAYER LOGIN DETECTED*****", 0);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPlayerRespawn(EntityPlayer player) {
		// TODO Auto-generated method stub

	}

}
