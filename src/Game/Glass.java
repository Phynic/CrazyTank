package Game;

import java.awt.Graphics;
import java.awt.Image;
import util.GameUtil;

public class Grass extends Obstruction{	
	static Image img =GameUtil.getImage("images/grass.png");	
    public Grass(){}
    public Grass(int x,int y,CrazyTankClient ctc){
    	super(x,y,ctc);
    }
	public void drawGrass(Graphics g) {
		g.drawImage(img,x,y,width,height,null);	
	}
}
