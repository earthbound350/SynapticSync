package com.synaptic.sync.core;
import com.synaptic.sync.db.DatabaseWrapper;
import com.synaptic.sync.forge.ForgeWrapper;
import com.synaptic.sync.forge.PlayerOnlineStatusTracker;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "@MODID@")
public class SynapticSync {

	@Instance
	public static SynapticSync instance;
	
	@EventHandler
	public void init(FMLInitializationEvent event){
		GameRegistry.registerPlayerTracker(new PlayerOnlineStatusTracker());
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		submitServerInfo();
	}

	private void submitServerInfo() {
		//DatabaseWrapper.connect();
		//DatabaseWrapper.submitMods(ForgeWrapper.getModNames());
		//DatabaseWrapper.submitVersions(ForgeWrapper.getModVersions());
		//DatabaseWrapper.disconnect();
	}
}