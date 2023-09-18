package gameData;

import java.awt.Color;

public class Pixel {
	private boolean isEmpty;
	private Color color;
	
	public Pixel() {
		setIsEmpty();
	}
	public void setIsEmpty() {
		this.isEmpty = true;
		this.color = Color.YELLOW;
	}
	public void setIsFilled(Color argColor) {
		this.isEmpty = false;
		this.color = argColor;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public Color getColor() {
		return color;
	}
}
