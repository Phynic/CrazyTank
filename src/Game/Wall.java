package Game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import util.Constant;
import util.GameUtil;

public class Wall{
	int x;
	int y;
	CrazyTankClient ctc;
	static Image img =GameUtil.getImage("images/wall.png");
    boolean live = true;
	
	int width = Constant.OBSTRUCTION_WIDTH/2;
	int height = Constant.OBSTRUCTION_HEIGHT/2;
    public Wall(){}
    public Wall(int x,int y,CrazyTankClient ctc){
    	this.x = x;
    	this.y = y;
    	this.ctc = ctc;
    }
    public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public void drawWall(Graphics g) {
		if(live){
			g.drawImage(img,x,y,width,height,null);
		}else{
			ctc.walls.remove(this);
		}
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
}
