import display.Display;
import gameData.BlockEnum;
import gameData.Pixel;
import gameData.Settings;

import java.awt.Color;
import java.text.MessageFormat;
import java.util.Random;

public class GameLoop {
	private Display currentGameDisplay;
	private Random rand = new Random();

	private BlockEnum[] blockEnums;
	
	// 게임 영역 좌표
	private Pixel[] gamePixels;
	// 현재 블럭 : 좌표로 가지고 있는다
	private int[] currentBlock;

	public GameLoop() {
		// 그리드 1차원 배열로 생성
		gamePixels = new Pixel[Settings.gameWidth * Settings.gameHeight];
		// 그리드 정보를 모두 초기화
		for(int i = 0; i < gamePixels.length; i++) {
			gamePixels[i] = new Pixel();
			gamePixels[i].setIsEmpty();
		}
		// 디스플레이 생성
		currentGameDisplay = new Display("JAVA TETRIS", Settings.windowWidth, Settings.windowHeight);
		// 블럭 종류 가져오기
		blockEnums = BlockEnum.values();
	}

	private BlockEnum getRandomBlock() {
		return blockEnums[rand.nextInt(blockEnums.length)];
	}

	public void createNewBlock() {
		currentBlock = new int[4];
		int pivot = Settings.gameWidth / 2;
		currentBlock[0] = pivot;
		currentBlock[1] = pivot + Settings.gameWidth;
		currentBlock[2] = pivot + Settings.gameWidth * 2;
		currentBlock[3] = pivot + Settings.gameWidth * 3;
		
		for(int i = 0; i < currentBlock.length; i++) {
			gamePixels[currentBlock[i]].setIsFilled(Color.RED);				
		}
		System.out.println("Block created");
		return;
//		switch (getRandomBlock()) {
//		default:
//		case I: {
//			currentBlock = new int[4];
//			int pivot = Settings.gameWidth / 2;
//			currentBlock[0] = pivot;
//			currentBlock[1] = pivot + Settings.gameWidth;
//			currentBlock[2] = pivot + Settings.gameWidth * 2;
//			currentBlock[3] = pivot + Settings.gameWidth * 3;
//			
//			for(int i = 0; i < currentBlock.length; i++) {
//				gamePixels[currentBlock[i]].setIsFilled(Color.BLUE);				
//			}
//			break;
//		}
//		case O: {
//			break;
//		}
//		case T: {
//			break;
//		}
//		case L: {
//			break;
//		}
//		case J: {
//			break;
//		}
//		case S: {
//			break;
//		}
//		case Z: {
//			break;
//		}
//		}
	}
	public void loop() {
		// 블럭 하강
		downCurrentBlock();
		// 렌더
		currentGameDisplay.draw(gamePixels);
		System.out.println("===========================");
	}
	private void downCurrentBlock() {
		if (currentBlock == null || currentBlock.length == 0) {
			createNewBlock();
			return;
		}
		
		Color currentColor = gamePixels[currentBlock[0]].getColor();
		
		System.out.println(currentBlock[0]);
		System.out.println(currentColor);
		
		// 아래 방향에서 부터 칠한다.
		for(int i = currentBlock.length - 1; i >= 0; i--) {
			int nextPosition = currentBlock[i] + Settings.gameWidth;
			//System.out.println(MessageFormat.format("prev:{0}, next:{1}", currentBlock[i], nextPosition));
			// 게임 영역 아웃
			if (nextPosition >= gamePixels.length) {
				currentBlock = null;
				System.out.println("End Point Collision");
				return;
			}
			// 다른 블럭과 충돌
			if (gamePixels[nextPosition].isEmpty() == false) {
				currentBlock = null;
				System.out.println("Collision with other block.");
				return;
			}
			
			gamePixels[currentBlock[i]].setIsEmpty();
			gamePixels[nextPosition].setIsFilled(currentColor);
			currentBlock[i] = nextPosition;
		}
		System.out.println("Down");
	}
}
