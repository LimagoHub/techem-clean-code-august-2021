package de.techem.games.takegame.player;

public class ComputerPlayer extends AbstractTakeGamePlayer {

	private static final int TURNS [] = {3,1,1,2};
	public ComputerPlayer() {
		// Ok
	}

	public ComputerPlayer(String name) {
		super(name);
		
	}

	@Override
	public Integer doTurn(Integer board) {
		int turn = TURNS[board % 4];
		System.out.printf("Computer nimmt %s Steine.\n", turn);
		return turn;
	}

}
