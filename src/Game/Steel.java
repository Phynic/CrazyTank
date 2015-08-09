package Game;

import java.awt.Graphics;
import java.awt.Image;
import util.GameUtil;

public class Steel extends Obstruction{
	static Image img =GameUtil.getImage("images/steel.png");
    public Steel(){}
    public Steel(int x,int y,CrazyTankClient ctc){
    	super(x,y,ctc);
    }
	public void drawSteel(Graphics g) {
		g.drawImage(img,x,y,width,height,null);
	}
}
