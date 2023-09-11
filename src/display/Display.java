package display;

import javax.swing.*;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.Canvas;
import java.awt.Dimension;

public class Display {
	private JFrame mainFrame;
	private Canvas mainCanvas;
	
	private String frameTitle;
	private int frameWidth, frameHeight;
	
	public Display(String argTitle, int argWidth, int argHeight) {
		this.frameTitle = argTitle;
		this.frameWidth = argWidth;
		this.frameHeight = argHeight;
		
		try {			
			createNewFrame();
		}catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("Failed to create new frame.");
		}
	}
	private boolean createNewFrame() {
		// 존재하는 메인프레임이 있다면 삭제
		if (mainFrame != null) {
			mainFrame.dispose();
			mainFrame = null;
		}
		// 새 프레임 생성
		mainFrame = new JFrame(frameTitle);
		if (frameWidth == 0 || frameHeight == 0) {
			System.out.println("Check frame size not be ZERO.");
			return false;
		}
		mainFrame.setSize(frameWidth, frameHeight);
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		// 종료 선택 옵션 추가
		mainFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirmResult = JOptionPane.showConfirmDialog(mainFrame, "Are you sure?", "Warning", JOptionPane.OK_CANCEL_OPTION);
				if (confirmResult == JOptionPane.OK_OPTION) {
					System.out.println("Closed.");
					System.exit(0);				
				}
				else {
					System.out.println("Canceled.");
				}
			}
			
		});
		mainFrame.setResizable(false);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		System.out.println("Successfully created new frame.");
		
		mainCanvas = new Canvas();
		mainCanvas.setPreferredSize(new Dimension(frameWidth, frameHeight));
		mainCanvas.setMaximumSize(new Dimension(frameWidth, frameHeight));
		mainCanvas.setMinimumSize(new Dimension(frameWidth, frameHeight));
		
		mainFrame.add(mainCanvas);
		mainFrame.pack();
		System.out.println("Successfully created new canvas.");
		return true;
	}
}
