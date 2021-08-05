package de.techem.games.players;

public interface GamePlayer<BOARD,TURN> {
	
	String getName();
	TURN doTurn(final BOARD board);
	
	

}
