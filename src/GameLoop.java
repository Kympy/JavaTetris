import display.Display;
import gameData.BlockEnum;
import gameData.Pixel;
import gameData.Settings;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.MessageFormat;
import java.util.Random;

public class GameLoop {
	private boolean isEnd = false;

	public boolean getIsEnd() {
		return isEnd;
	}

	private Display currentGameDisplay;
	private Random rand = new Random();

	private BlockEnum[] blockEnums;

	// 게임 영역 좌표
	private Pixel[] gamePixels;
	// 현재 블럭 : 좌표로 가지고 있는다
	private int[] currentBlock;

	private long lastLoopTime;

	public GameLoop() {
		// 그리드 1차원 배열로 생성
		gamePixels = new Pixel[Settings.gameWidth * Settings.gameHeight];
		// 그리드 정보를 모두 초기화
		for (int i = 0; i < gamePixels.length; i++) {
			gamePixels[i] = new Pixel();
			gamePixels[i].setIsEmpty();
		}
		// 디스플레이 생성
		currentGameDisplay = new Display("JAVA TETRIS", Settings.windowWidth, Settings.windowHeight);
		// 블럭 종류 가져오기
		blockEnums = BlockEnum.values();
		lastLoopTime = 0;

		if (currentGameDisplay.getPanel() != null) {
			currentGameDisplay.getPanel().addKeyListener(new KeyAdapter() {
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
						rightCurrentBlock();
					}
					if (e.getKeyCode() == KeyEvent.VK_LEFT) {
						leftCurrentBlock();
					}
					if (e.getKeyCode() == KeyEvent.VK_DOWN) {
						downCurrentBlock();
					}
					if (e.getKeyCode() == KeyEvent.VK_SPACE) {
						rotateCurrentBlock();
					}
				}
			});
			currentGameDisplay.getPanel().requestFocus();
		}

	}

	public void loop() {
		if (System.currentTimeMillis() - lastLoopTime >= Settings.gameLoopInterval) {
			lastLoopTime = System.currentTimeMillis();
			// 블럭 하강
			downCurrentBlock();
		}
		// 렌더
		currentGameDisplay.draw(gamePixels);
		// System.out.println("===========================");
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

		for (int i = 0; i < currentBlock.length; i++) {
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

	private void downCurrentBlock() {
		if (currentBlock == null || currentBlock.length == 0) {
			createNewBlock();
			return;
		}
		// 아래로 내려갔을 때 충돌가능성 있는지 검사
		for(int i = currentBlock.length - 1; i >= 0; i--) {
			int nextPosition = currentBlock[i] + Settings.gameWidth;
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
		}

		Color currentColor = gamePixels[currentBlock[0]].getColor();
		
		for(int i = 0; i < currentBlock.length; i++) {
			int nextPosition = currentBlock[i] + Settings.gameWidth;
			
			gamePixels[currentBlock[i]].setIsEmpty();
			gamePixels[nextPosition].setIsFilled(currentColor);
			currentBlock[i] = nextPosition;
		}

		System.out.println("Down");
	}

	private void rightCurrentBlock() {
		if (currentBlock == null || currentBlock.length == 0) {
			createNewBlock();
			return;
		}

		// 오른쪽에 충돌 가능성이 하나라도 있는지 검사
		for (int i = currentBlock.length - 1; i >= 0; i--) {

			// 게임 영역을 벗어나면 취소
			if (currentBlock[i] + 1 >= gamePixels.length) {
				return;
			}
			// 다른 블럭과 충돌하면 취소
			if (gamePixels[currentBlock[i] + 1].isEmpty() == false) {
				return;
			}

			// 현재 행 : +1 인덱스가 행 변경을 발생하는지 검사
			int currentRow = currentBlock[i] / Settings.gameWidth;
			// 다음 행
			int nextRow = (currentBlock[i] + 1) / Settings.gameWidth;
			// 행이 변경되면 취소
			if (currentRow != nextRow) {
				return;
			}
		}

		Color currentColor = gamePixels[currentBlock[0]].getColor();
		// 아래 방향에서 부터 칠한다.
		for (int i = currentBlock.length - 1; i >= 0; i--) {
			int nextPosition = currentBlock[i] + 1;

			gamePixels[currentBlock[i]].setIsEmpty();
			gamePixels[nextPosition].setIsFilled(currentColor);
			currentBlock[i] = nextPosition;
		}
		System.out.println("Right");
	}

	private void leftCurrentBlock() {
		if (currentBlock == null || currentBlock.length == 0) {
			createNewBlock();
			return;
		}

		// 왼쪽에 충돌 가능성이 하나라도 있는지 검사
		for (int i = currentBlock.length - 1; i >= 0; i--) {

			// 게임 영역을 벗어나면 취소
			if (currentBlock[i] - 1 >= gamePixels.length) {
				return;
			}
			// 다른 블럭과 충돌하면 취소
			if (gamePixels[currentBlock[i] - 1].isEmpty() == false) {
				return;
			}

			// 현재 행 : -1 인덱스가 행 변경을 발생하는지 검사
			int currentRow = currentBlock[i] / Settings.gameWidth;
			// 다음 행
			int nextRow = (currentBlock[i] - 1) / Settings.gameWidth;
			// 행이 변경되면 취소
			if (currentRow != nextRow) {
				return;
			}
		}

		Color currentColor = gamePixels[currentBlock[0]].getColor();
		// 아래 방향에서 부터 칠한다.
		for (int i = currentBlock.length - 1; i >= 0; i--) {
			int nextPosition = currentBlock[i] - 1;

			gamePixels[currentBlock[i]].setIsEmpty();
			gamePixels[nextPosition].setIsFilled(currentColor);
			currentBlock[i] = nextPosition;
		}
		System.out.println("Left");
	}
	private void rotateCurrentBlock() {
		if (currentBlock == null || currentBlock.length == 0) {
			createNewBlock();
			return;
		}
		// 돌려서 문제가 있는지 검사
		for(int i = 0; i < currentBlock.length; i++) {
			int rotatedPosition = currentBlock[i];
		}
		for(int i = 0; i < currentBlock.length; i++) {
			
		}
		System.out.println("Rotate");
	}
}
