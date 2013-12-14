package com.synaptic.core.db.command;

public class SubmitUserListCommand extends Command {
	@Override
	public void setArgs(Object... args) {
		String argsList = "";
		for(Object arg : args){
			argsList += "(" + arg.toString() + "),";
		}
		argsList = argsList.substring(0, argsList.length()-1);
		command = String.format(getCommandTemplate(), argsList);
	}

	@Override
	protected String getCommandTemplate() {
		return "DELETE FROM online; DELETE FROM user; INSERT INTO user(username) VALUES %s";
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
}
