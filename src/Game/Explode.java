package Game;

import java.awt.Graphics;
import java.awt.Image;

import util.GameUtil;

public class Explode {
    int x;
    int y;
    int a[] = {214,198,182,171,169,165,162,161,157,152,152,152,156,159};
    int b[] = {139,129,110,104,104,96,93,93,93,90,89,89,92,93};
    boolean live = true;
    int[] diameter = {2,13,26,35,48,60,51,30,8,1};
    static Image[] imgs = {GameUtil.getImage("images/explode/0.png"),
    		GameUtil.getImage("images/explode/1.png"),
    		GameUtil.getImage("images/explode/2.png"),
    		GameUtil.getImage("images/explode/3.png"),
    		GameUtil.getImage("images/explode/4.png"),
    		GameUtil.getImage("images/explode/5.png"),
    		GameUtil.getImage("images/explode/6.png"),
    		GameUtil.getImage("images/explode/7.png"),
    		GameUtil.getImage("images/explode/8.png"),
    		GameUtil.getImage("images/explode/9.png"),
    		GameUtil.getImage("images/explode/10.png"),
    		GameUtil.getImage("images/explode/11.png"),
    		GameUtil.getImage("images/explode/12.png"),
    		GameUtil.getImage("images/explode/13.png")
    		};
    int step = 0;
    CrazyTankClient ctc;
    public Explode(){}
    public Explode(int x,int y,CrazyTankClient ctc){
    	this.x = x;
    	this.y = y;
    	this.ctc = ctc;
    }
    public void drawExplode(Graphics g){
    	if(!live){
    		ctc.explodes.remove(this);
    		return;
    	}else{
    		g.drawImage(imgs[step],x + a[step] - 240,y + b[step] - 200,null);
        	step++;
    	}
    	//好像有错诶
    	if(step>diameter.length-1){
    		step = 0;
    		live = false;
    		return;
    	}
    }
}
