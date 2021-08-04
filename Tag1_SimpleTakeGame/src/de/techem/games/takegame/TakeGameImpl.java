package de.techem.games.takegame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import de.techem.games.Game;
import de.techem.games.takegame.player.TakeGamePlayer;

public class TakeGameImpl implements Game {
	
	private static final String INVALID_TURN = "Ungültiger Zug";
	
	private List<TakeGamePlayer> players = new ArrayList<>();
	private int stones;
	private int turn;
	private TakeGamePlayer currentPlayer;
	
	public TakeGameImpl() {
		stones = 23;
		
	}
	
	private boolean isGameOver() {
		return stones < 1 || players.isEmpty();
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
			currentPlayer = player;
			executeSingleTurn();
		}
	}

	private void executeSingleTurn() {
		if(isGameOver()) return;
		
		while(true) {
			turn = currentPlayer.doTurn(stones);
			if(turn >= 1 && turn <= 3) break;
			System.out.println(INVALID_TURN);
					
		}
		terminateTurn();
	}

	
	private void terminateTurn() { 
		updateBoard();
		checkLosing();
	}
	
	private void checkLosing() {
		if(isGameOver()) {
			System.out.println(currentPlayer.getName() +  "  hat verloren");
		}
	}
	
	private void updateBoard() {
		stones -= turn;
	}
	

	

}
