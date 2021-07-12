package de.commands;

import de.dictonary.Dictionary;

public class ListCommand extends AbstractCommand {

	@Override
	public void execute() {
		Dictionary.getInstance().list();

	}

}
