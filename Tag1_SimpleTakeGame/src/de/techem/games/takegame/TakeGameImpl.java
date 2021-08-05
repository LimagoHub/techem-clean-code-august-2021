package de.techem.games.takegame;

import java.util.ArrayList;
import java.util.List;

import de.techem.games.Game;
import de.techem.games.takegame.player.TakeGamePlayer;
import de.techem.io.Writer;

public class TakeGameImpl implements Game {
	
	private static final String GAME_OVER_MESSAGE = "%s  hat verloren";
	private static final String INVALID_TURN = "Ungültiger Zug";
	
	private final Writer writer;
	
	private List<TakeGamePlayer> players = new ArrayList<>();
	private int stones;
	private int turn;
	private TakeGamePlayer currentPlayer;
	
	
	
	public TakeGameImpl(final Writer writer) {
		this.writer = writer;
		stones = 23;
		
	}
	

	private TakeGamePlayer getCurrentPlayer() {
		return currentPlayer;
	}

	private void setCurrentPlayer(TakeGamePlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	
	
	public void addPlayer(TakeGamePlayer player) {
		players.add(player);
	}

	
	public void removePlayer(TakeGamePlayer player) {
		players.remove(player);
	}

	@Override
	public void play() {
		while(! isGameOver()) {
			executeTurns();
		}
	}

	private void executeTurns() { 
		for (TakeGamePlayer player : players) {
			executeTurnForPlayer(player);
		}
	}

	private void executeTurnForPlayer(TakeGamePlayer player) {
		setCurrentPlayer(player);
		executeSingleTurn();
	}

	private void executeSingleTurn() {
		if(isGameOver()) return;
		invokeCurrentPlayersTurn();
		terminateTurn();
	}


	private void invokeCurrentPlayersTurn() { // Bruch
		
		do {
			turn = getCurrentPlayer().doTurn(stones);
		}  while(invalidTurn());

	}

	
	private boolean invalidTurn() {
		if(isTurnValid()) 	return false;
		print(INVALID_TURN);
		return true;
	}

	

	private void terminateTurn() { 
		updateBoard();
		checkLosing();
	}
	
	private void checkLosing() {
		if(isGameOver()) {
			print(String.format( GAME_OVER_MESSAGE, getCurrentPlayer().getName() ));
		}
	}
	
	private void print(String message) {
		writer.write(message);
	}
	// -------------------------------
	private boolean isTurnValid() {
		return turn >= 1 && turn <= 3;
	}
	
	private void updateBoard() {
		stones -= turn;
	}
	
	private boolean isGameOver() {
		return stones < 1 || players.isEmpty();
	}
	

}
