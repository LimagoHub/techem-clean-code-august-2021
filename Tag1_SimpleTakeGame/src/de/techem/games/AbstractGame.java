package de.techem.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.techem.games.players.GamePlayer;
import de.techem.io.Writer;

public abstract class AbstractGame<BOARD,TURN> implements Game {

	
	private static final String GAME_OVER_MESSAGE = "%s  hat verloren";
	private static final String INVALID_TURN = "Ungültiger Zug";
	
	private final Writer writer;
	
	protected BOARD board;
	protected TURN turn;
	
	
	
	
	protected AbstractGame(final Writer writer) {
		this.writer = writer;
	}

	private List<GamePlayer<BOARD, TURN>> players = new ArrayList<>();
	
	
	private GamePlayer<BOARD, TURN> currentPlayer;
	
	protected GamePlayer<BOARD, TURN> getCurrentPlayer() {
		return currentPlayer;
	}

	protected void setCurrentPlayer(GamePlayer<BOARD, TURN>currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
	protected List<GamePlayer<BOARD, TURN>> getPlayers() {
		return Collections.unmodifiableList(players);
	}
	
	public void addPlayer(GamePlayer<BOARD,TURN> player) {
		players.add(player);
	}

	
	public void removePlayer(GamePlayer<BOARD,TURN>  player) {
		players.remove(player);
	}


	@Override
	public void play() {
		while(! isGameOver()) executeTurns();
	}
	
	private void executeTurns() { 
		for (GamePlayer<BOARD,TURN> player : getPlayers()) 	executeTurnForPlayer(player);
		
	}
	
	private void executeTurnForPlayer(GamePlayer<BOARD,TURN> player) {
		setCurrentPlayer(player);
		executeSingleTurn();
	}

	private void executeSingleTurn() {
		if(initTurn()) return;
		invokeCurrentPlayersTurn();
		terminateTurn();
	}
	
	private boolean initTurn() {
		print(String.format("Spieler %s ist am Zug", getCurrentPlayer().getName()));
		return isGameOver();
	}

	private void invokeCurrentPlayersTurn() { 
		
		do 	turn = getCurrentPlayer().doTurn(board);  while(invalidTurn());

	}
	

	private void terminateTurn() { 
		updateBoard();
		checkLosing();
	}

	private boolean invalidTurn() {
		if(isTurnValid()) 	return false;
		print(INVALID_TURN);
		return true;
	}

	

	
	private void checkLosing() {
		if(isGameOver()) 	print(String.format( GAME_OVER_MESSAGE, getCurrentPlayer().getName() ));
	
	}
	
	protected void print(String message) {
		writer.write(message);
	}

	protected abstract boolean isGameOver();
	
	protected abstract boolean isTurnValid();
	
	protected abstract void updateBoard();
	

}
