package de.techem.main;

import de.techem.GameClient;
import de.techem.games.takegame.TakeGameImpl;

public class Main {

	public static void main(String[] args) {
		TakeGameImpl game = new TakeGameImpl();
		GameClient client = new GameClient(game);
		client.run();

	}

}
