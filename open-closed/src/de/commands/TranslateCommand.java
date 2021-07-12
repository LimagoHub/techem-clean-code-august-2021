package de.commands;

import de.dictonary.Dictionary;

public class TranslateCommand extends AbstractCommand{
	
	private String deutsch;
	
	@Override
	public void parse(String[] tokens) {
		deutsch  = tokens[1];
	}

	@Override
	public void execute() {
		Dictionary.getInstance().translate(deutsch);
		
	}

}
