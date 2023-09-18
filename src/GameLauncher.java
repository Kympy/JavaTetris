
public class GameLauncher {

	private static GameLoop currentGame;

	public static void main(String[] args) {

		
		currentGame = new GameLoop();
		System.out.println("Game Loop is started.");
		
		while(true) {
			
			try {
				currentGame.loop();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		
		System.out.println("Game Loop is finished.");
	}
}
