import display.Display;
import gameData.BlockEnum;
import java.util.Random;

public class GameLoop {
	private Display currentGameDisplay;
	private Random rand = new Random();

	private BlockEnum[] blockEnums;

	public GameLoop() {
		currentGameDisplay = new Display("JAVA TEST GAME", 400, 800);
		blockEnums = BlockEnum.values();
	}

	private BlockEnum getRandomBlock() {
		return blockEnums[rand.nextInt(blockEnums.length)];
	}

	public void createNewBlock() {

		switch (getRandomBlock()) {
		default:
		case I: {
			break;
		}
		case O: {
			break;
		}
		case T: {
			break;
		}
		case L: {
			break;
		}
		case J: {
			break;
		}
		case S: {
			break;
		}
		case Z: {
			break;
		}
		}
	}
}
