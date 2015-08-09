package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;




import util.Constant;
import util.GameUtil;

public class Home {
	int x;
	int y;
	static Image img =GameUtil.getImage("images/HOME.png");
    boolean live = true;
	
	int width = Constant.OBSTRUCTION_WIDTH;
	int height = Constant.OBSTRUCTION_HEIGHT;
	CrazyTankClient ctc;
    public Home(){}
    public Home(int x,int y,CrazyTankClient ctc){
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
	public void drawHome(Graphics g) {
		if(live){
			g.drawImage(img,x,y,width,height,null);
		}else{
			ctc.end.drawGameover(g);
		}
	}
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}
}

