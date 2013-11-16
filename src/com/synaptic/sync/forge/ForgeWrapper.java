package com.synaptic.sync.forge;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.versioning.ArtifactVersion;


public class ForgeWrapper {
	
	private static List<ModContainer> getModList(){
		List<ModContainer> mods = Loader.instance().getModList();
		return mods;
	}
	
	public static Map<String, String> getModNames(){
		Map<String, String> modNames = new HashMap<String, String>();
		
		List<ModContainer>mods = getModList();
		for(ModContainer mod : mods){
			modNames.put(mod.getModId(), mod.getName());
		}
		return modNames;
	}
	
	public static Map<String, String> getModVersions(){
		Map<String, String> modVersions = new HashMap<String, String>();
				
		List<ModContainer>mods = getModList();
		for(ModContainer mod : mods){
			modVersions.put(mod.getModId(), mod.getVersion());
		}
		return modVersions;
	}
	
	public static Set<String> getWhiteListedPlayers(){
		ServerConfigurationManager scm = MinecraftServer.getServerConfigurationManager(MinecraftServer.getServer());
		return scm.getWhiteListedPlayers();
	}
}
