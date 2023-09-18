package display;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.text.MessageFormat;

import gameData.Pixel;
import gameData.Settings;

public class GamePanel extends JPanel {
	
	private int frameWidth, frameHeight;
	private int areaOffsetX = 2, areaOffsetY = 10;
	
	private final Color backgroundColor = new Color(5, 0, 153);
	
	public GamePanel(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		
		areaOffsetY = (int)(frameHeight * 0.03);
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// 게임 영역 그리기
		drawDefaultGameArea(g);
		// 블럭 정보 그리기
		drawBlocks(g);
	
		System.out.println("Print");
	}
	private void drawDefaultGameArea(Graphics g) {
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, frameWidth, frameHeight);
	}
	private void drawBlocks(Graphics g) {
		if (this.blocksArray == null || this.blocksArray.length == 0) return;
		
		int[] tempConverted = null;
		
		for(int i = 0; i < blocksArray.length; i++) {
			g.setColor(blocksArray[i].getColor());
			tempConverted = convertArrayIndex(i);
			g.fill3DRect(Settings.pixelSize * tempConverted[1] + areaOffsetX, Settings.pixelSize * tempConverted[0] + areaOffsetY, Settings.pixelSize, Settings.pixelSize, true);
			//System.out.println(MessageFormat.format("{0}, {1} < {2}", tempConverted[0], tempConverted[1], i));
		}
	}
	// 1차원 좌표를 2차원 좌표로 변환
	private int[] convertArrayIndex(int argIndex) {
		int[] converted = new int[2];
		
		if (argIndex == 0){
			converted[0] = 0;
			converted[1] = 0;
			return converted;
		}
		
		converted[0] = argIndex / Settings.gameWidth;
		
		if (converted[0] == 0){
			converted[1] = argIndex;
		} else {
			converted[1] = argIndex % Settings.gameWidth;			
		}
		return converted;
	}
	// 임시 캐싱 블럭 배열
	private Pixel[] blocksArray;
	// 블럭 배열 넘겨받아 저장
	public void updateBlockArray(Pixel[] newBlocksArray) {
		this.blocksArray = newBlocksArray;
	}
}
