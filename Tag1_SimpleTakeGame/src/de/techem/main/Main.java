package de.techem.main;

import de.techem.GameClient;
import de.techem.games.takegame.TakeGameImpl;
import de.techem.games.takegame.player.ComputerPlayer;
import de.techem.games.takegame.player.HumanPlayer;
import de.techem.io.ConsoleWriter;

public class Main {

	public static void main(String[] args) {
		TakeGameImpl game = new TakeGameImpl(new ConsoleWriter());
		game.addPlayer(new HumanPlayer());
		game.addPlayer(new ComputerPlayer());
		GameClient client = new GameClient(game);
		client.run();

	}

}
