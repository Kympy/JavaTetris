package display;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.text.MessageFormat;

public class GamePanel extends JPanel {
	
	private int frameWidth, frameHeight;
	private int gameAreaWidth, gameAreaHeight;
	private int areaOffsetX = 2, areaOffsetY = 10;
	
	private final Color backgroundColor = new Color(5, 0, 153);
	private final Color areaColor = new Color(0, 0, 30);
	
	public GamePanel(int frameWidth, int frameHeight) {
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		
		gameAreaWidth = (int)(frameWidth * 0.5);
		gameAreaHeight = frameHeight;
		
		areaOffsetY = (int)(frameHeight * 0.03);
		
		System.out.println(MessageFormat.format("Game Area Width : {0}", gameAreaWidth));
		System.out.println(MessageFormat.format("Game Area Height : {0}", gameAreaHeight));
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		drawDefaultGameArea(g);
		
		g.setColor(Color.yellow);

		g.fill3DRect(50, 50, 30, 30, true);
		g.fill3DRect(80, 50, 30, 30, true);
		g.fill3DRect(110, 50, 30, 30, true);
		
		System.out.println("Print");
	}
	private void drawDefaultGameArea(Graphics g) {
		
		g.setColor(backgroundColor);
		g.fillRect(0, 0, frameWidth, frameHeight);
		g.setColor(areaColor);
		g.fill3DRect(areaOffsetX, areaOffsetY, gameAreaWidth, gameAreaHeight - 100, true);
	
		for(int i = 0; i < gameAreaWidth; i++) {
			
		}
	}
	public void UpdateBlockArray() {
		
	}
}
