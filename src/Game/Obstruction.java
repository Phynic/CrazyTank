package Game;

import java.awt.Rectangle;

import util.Constant;

public class Obstruction {
	int x;
	int y;
	CrazyTankClient ctc;
	int width = Constant.OBSTRUCTION_WIDTH;
	int height = Constant.OBSTRUCTION_HEIGHT;
	public Obstruction(){}
	public Obstruction(int x,int y,CrazyTankClient ctc){
		this.x = x;
		this.y = y;
		this.ctc = ctc;
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
}
