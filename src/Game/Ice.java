package Game;

import java.awt.Graphics;
import java.awt.Image;
import util.GameUtil;

public class Ice extends Obstruction{
	static Image img =GameUtil.getImage("images/ice.png");
    public Ice(){}
    public Ice(int x,int y,CrazyTankClient ctc){
    	super(x,y,ctc);
    }
	public void drawIce(Graphics g) {
		g.drawImage(img,x,y,width,height,null);	
	}
}
