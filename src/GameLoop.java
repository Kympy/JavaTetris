import display.Display;
import gameData.BlockEnum;
import java.util.Random;

public class GameLoop {
	private Display currentGameDisplay;
	private Random rand = new Random();

	private BlockEnum[] blockEnums;
	
	// 게임 영역 좌표
	private int[] gameGrids;
	private final int gameGridRow = 10;
	private final int gameGridCol = 20;

	public GameLoop() {
		// 그리드 1차원 배열로 생성
		gameGrids = new int[gameGridRow * gameGridCol];
		// 그리드 정보를 모두 초기화
		for(int i = 0; i < gameGrids.length; i++) {
			gameGrids[i] = 0;
		}
		// 디스플레이 생성
		currentGameDisplay = new Display("JAVA TEST GAME", 850, 800);
		// 블럭 종류 가져오기
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
	public void Loop() {
		currentGameDisplay.draw(gameGrids);
	}
}
