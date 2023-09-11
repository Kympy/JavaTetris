import display.Display;
import gameData.BlockEnum;
import java.util.Random;

public class GameLoop {
	private Display currentGameDisplay;
	private Random rand = new Random();
	
	private BlockEnum[] blockEnums;
	
	public GameLoop() {
		currentGameDisplay = new Display("JAVA TEST GAME", 250, 250);
		blockEnums = BlockEnum.values();
	}
	
	public void createNewBlock() {
		 BlockEnum randomBlock = blockEnums[rand.nextInt(blockEnums.length)];
		 
		 switch(randomBlock) {
		 default:
		 case I:{
			 break;
		 }
		 case O:{
			 break;
		 }
		 }
	}
}
