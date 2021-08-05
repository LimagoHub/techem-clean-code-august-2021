package de.techem.games.players;

public abstract class AbstractGamePlayer<BOARD,TURN> implements GamePlayer<BOARD,TURN> {
	
	
	private String name = this.getClass().getSimpleName();
	
	public AbstractGamePlayer() {
	
	}

	public AbstractGamePlayer(String name) {
		this.name = name;
	}
	
	
	@Override
	public String getName() {
		return name;
	}
	
}
