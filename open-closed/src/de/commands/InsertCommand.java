package de.commands;

import de.dictonary.Dictionary;
import de.dictonary.DictionaryMemento;

public class InsertCommand implements Command{

	private String deutsch;
	private String english;
	private DictionaryMemento memento;
	
	@Override
	public void parse(String[] tokens) {
		deutsch = tokens[1];
		english = tokens[2];
	}

	@Override
	public void execute() {
		memento = Dictionary.getInstance().getMemento();
		Dictionary.getInstance().insert(deutsch, english);
		
	}

	@Override
	public boolean isQuery() {
		
		return false;
	}

	@Override
	public void undo() {
		Dictionary.getInstance().setMemento(memento);
	}

}
