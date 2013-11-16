package com.synaptic.core.db.command;

public abstract class Command {
	
	protected String command;
	
	public abstract void execute();
	
	public void setArgs(Object...args){
		command = String.format(getCommandTemplate(), args);
	}
	
	protected abstract String getCommandTemplate();
}
