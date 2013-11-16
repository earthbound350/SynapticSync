package com.synaptic.sync.forge;

import com.synaptic.sync.db.DatabaseWrapper;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.IPlayerTracker;

public class PlayerOnlineStatusTracker implements IPlayerTracker {

	@Override
	public void onPlayerLogin(EntityPlayer player) {
		DatabaseWrapper.playerLoggedIn(player.username);
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) {
		DatabaseWrapper.playerLoggedOut(player.username);
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
