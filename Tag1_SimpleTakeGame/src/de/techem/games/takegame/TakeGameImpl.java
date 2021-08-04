package de.techem.games.takegame;

import java.util.Scanner;

import de.techem.games.Game;

public class TakeGameImpl implements Game {
	
	private static final String INVALID_TURN = "Ungültiger Zug";
	private static final String USER_PROMPT = "Es gibt %s Steine. Bitte nehmen Sie 1,2 oder 3.\n";
	private Scanner scanner = new Scanner(System.in);
	private int stones;
	private boolean gameover;
	
	public TakeGameImpl() {
		stones = 23;
		gameover = false;
	}

	@Override
	public void play() {
		while(! gameover) {
			executeTurns();
		}
	}

	private void executeTurns() {
		humanTurn();
		computerTurn();
		
		
	}

	private void humanTurn() {
		int turn;
		while(true) {
			System.out.printf(USER_PROMPT , stones);
			turn = scanner.nextInt();
			if(turn >= 1 && turn <= 3) break;
			System.out.println(INVALID_TURN);
					
		}
		stones -= turn;
	}

	private void computerTurn() {
		final int turns [] = {3,1,1,2};
		int turn;
		
		if(stones <= 0) {
			System.out.println("Du Loser");
			gameover = true;
			return ;
		}
		
		if(stones == 1) {
			System.out.println("Du hast nur Glück gehabt");
			gameover = true;
			return ;
		}
		
		turn = turns[stones % 4];
		System.out.printf("Compuer nimmt %s Steine.\n", turn);
		stones -= turn;
		
	}

}
