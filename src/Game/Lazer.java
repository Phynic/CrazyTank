package Game;


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import Game.Tank.Direction;
import util.GameUtil;


public class Lazer{
	int x;
	int y;
	int i = 0;
	Direction dir;
	boolean live = true;
	Image img;
	CrazyTankClient ctc;
	public static final int WIDTH = 1345;
    public static final int HEIGHT = 248;
    public static final int tWIDTH = 1226;
    public static final int tHEIGHT = 86;
    boolean good;
	
	public Lazer(){}
	public Lazer(int x,int y,Direction dir,CrazyTankClient ctc,boolean good){
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.ctc = ctc;
		this.good = good;
	}
	
	
	String imgPathR[] = {"images/R/0.png",
			             "images/R/1.png",
	        		     "images/R/2.png",
			             "images/R/3.png",
			             "images/R/4.png",
			             "images/R/5.png",
			             "images/R/6.png",
			             "images/R/7.png",
			             "images/R/8.png",
	};
	String imgPathL[] = {"images/L/0.png",
                         "images/L/1.png",
		                 "images/L/2.png",
                         "images/L/3.png",
                         "images/L/4.png",
                         "images/L/5.png",
                         "images/L/6.png",
                         "images/L/7.png",
                         "images/L/8.png",
    };
	String imgPathU[] = {"images/U/0.png",
            "images/U/1.png",
            "images/U/2.png",
            "images/U/3.png",
            "images/U/4.png",
            "images/U/5.png",
            "images/U/6.png",
            "images/U/7.png",
            "images/U/8.png",
    };
	String imgPathD[] = {"images/D/0.png",
            "images/D/1.png",
            "images/D/2.png",
            "images/D/3.png",
            "images/D/4.png",
            "images/D/5.png",
            "images/D/6.png",
            "images/D/7.png",
            "images/D/8.png",
    };
	public void drawLazer(Graphics g){
		if(!live){
    		ctc.lazers.remove(this);
    		return;
    	}
		switch (dir) {
		case L:
			img = GameUtil.getImage(imgPathL[i]);
			g.drawImage(img,x,y, null);
			break;
		case U:
			img = GameUtil.getImage(imgPathU[i]);
			g.drawImage(img,x,y, null);
			break;
		case R:
			img = GameUtil.getImage(imgPathR[i]);
			g.drawImage(img,x,y, null);
			break;
		case D:
			img = GameUtil.getImage(imgPathD[i]);
			g.drawImage(img,x,y, null);
			break;
		default:
			break;
	    }
		i++;
		if(i>imgPathR.length-1){
            live = false;
    		return;
    	}
	}
	public Rectangle getRect(Direction dir){
		switch (dir) {
		case R:
			return new Rectangle(this.x + 92,this.y + HEIGHT/2 - tHEIGHT/2,tWIDTH,tHEIGHT);
		case L:
			return new Rectangle(x,this.y + HEIGHT/2 - tHEIGHT/2,tWIDTH,tHEIGHT);
		case U:
			return new Rectangle(this.x + HEIGHT/2 - tHEIGHT/2,y,tHEIGHT,tWIDTH);
		case D:
			return new Rectangle(this.x + HEIGHT/2 - tHEIGHT/2,this.y + 92,tHEIGHT,tWIDTH);
		default:
			return new Rectangle(x,y,0,0);
		}
    }
	public boolean hitTank(Tank tank){
    	if(this.live&&tank.isLive()&&this.getRect(dir).intersects(tank.getRect())&&this.good != tank.isGood()){
    		tank.setLive(false);
    		return true;
    	}else{
    		return false;
    	}
    }
    public boolean hitTanks(List<Tank> tanks){
    	for(int i=0;i<tanks.size();i++){
    		if(hitTank(tanks.get(i))){
    			Explode e = new Explode(tanks.get(i).x,tanks.get(i).y,ctc);
        		ctc.explodes.add(e);
    			return true;
    		}
    	}
    	return false;
    }
    public void hitWalls(List<Wall> walls){
    	for(int i=0;i<walls.size();i++){
			Wall wall = walls.get(i);
			if(this.live&&this.getRect(dir).intersects(wall.getRect())){
	    		wall.setLive(false);
	    	}
		}
    }
    public void hitHomes(List<Home> homes){
    	for(int i=0;i<homes.size();i++){
			Home home = homes.get(i);
			if(this.live&&this.getRect(dir).intersects(home.getRect())){
	    		this.live = false;
	    		home.setLive(false);
	    	}
		}
    }
}

