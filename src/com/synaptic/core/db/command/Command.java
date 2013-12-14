package com.synaptic.core.db.command;

import java.sql.Connection;

import cpw.mods.fml.common.FMLLog;

public abstract class Command {
	
	protected String command;
	
	public void execute(Connection con){
		FMLLog.info("---------------%s---------------", command);
	}
	public abstract void setArgs(Object...args);
	protected abstract String getCommandTemplate();
	public abstract void execute();
}
