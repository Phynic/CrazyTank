package Game;

import java.awt.Graphics;
import java.awt.Image;
import util.GameUtil;

public class River extends Obstruction{
	static Image img =GameUtil.getImage("images/river.png");
    public River(){}
    public River(int x,int y,CrazyTankClient ctc){
    	super(x,y,ctc);
    }
	public void drawRiver(Graphics g) {
		g.drawImage(img,x,y,width,height,null);
	}
}
