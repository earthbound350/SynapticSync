package com.synaptic.core.db.command;

public class SubmitModsCommand extends Command {
	@Override
	public void setArgs(Object... args) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String getCommandTemplate() {
		return "INSERT ";
	}
}
