
public class GameLauncher {

	private static GameLoop currentGame;

	public static void main(String[] args) {

		
		currentGame = new GameLoop();
		System.out.println("Game Loop is started.");
		
		while(currentGame.getIsEnd() == false) {
			currentGame.loop();
		}
		
		System.out.println("Game Loop is finished.");
	}
}
