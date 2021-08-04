package de.techem.games.takegame.player;

public abstract class AbstractTakeGamePlayer implements TakeGamePlayer {
	
	
	private String name = this.getClass().getSimpleName();
	
	public AbstractTakeGamePlayer() {
	
	}

	public AbstractTakeGamePlayer(String name) {
		this.name = name;
	}
	
	
	@Override
	public String getName() {
		return name;
	}
	
}
