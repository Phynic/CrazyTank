package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.List;

import util.Constant;
import util.GameUtil;
import Game.Tank.Direction;

public class Bullet {
    int x;
    int y;
    int speed = 20;
    boolean live = true;
    CrazyTankClient ctc;
    public static final int WIDTH = 36;
    public static final int HEIGHT = 36;
    Direction dir = Direction.R;
    static Image imgU =GameUtil.getImage("images/bulletU.png");
    static Image imgD =GameUtil.getImage("images/bulletD.png");
    static Image imgR =GameUtil.getImage("images/bulletR.png");
    static Image imgL =GameUtil.getImage("images/bulletL.png");
    static Image imgRU =GameUtil.getImage("images/bulletRU.png");
    static Image imgRD =GameUtil.getImage("images/bulletRD.png");
    static Image imgLU =GameUtil.getImage("images/bulletLU.png");
    static Image imgLD =GameUtil.getImage("images/bulletLD.png");
    
    private boolean good;
    
    public boolean isGood() {
		return good;
	}

	public Bullet(){}
    
    public Bullet(int x,int y,Direction dir,CrazyTankClient ctc){
    	this.x = x;
    	this.y = y;
    	this.dir = dir;
    	this.ctc = ctc;
    }
    public Bullet(int x,int y,Direction dir,CrazyTankClient ctc,boolean good){
    	this(x,y,dir,ctc);
    	this.good = good;
    }
    public void move(){
		switch (dir) {
		case L:
			x -= speed;
			break;
		case LU:
			x -= speed;
			y -= speed;
			break;
		case U:
			y -= speed;
			break;
		case RU:
			y -= speed;
			x += speed;
			break;
		case R:
			x += speed;
			break;
		case RD:
			x += speed;
			y += speed;
			break;
		case D:
			y += speed;
			break;
		case LD:
			x -= speed;
			y += speed;
			break;
		default:
			break;
		}
		if(x<0||x>Constant.GAME_WIDTH||y<0||y>Constant.GAME_HEIGHT){
			live = false;
		}
    }
    
    public void drawBullet(Graphics g){
    	if(!live){
    		ctc.bullets.remove(this);
    		return;
    	}
    	switch (dir) {
		case L:			
			g.drawImage(imgL, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case LU:
			g.drawImage(imgLU, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case U:
			g.drawImage(imgU, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case RU:
			g.drawImage(imgRU, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case R:
			g.drawImage(imgR, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case RD:
			g.drawImage(imgRD, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case D:
			g.drawImage(imgD, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		case LD:
			g.drawImage(imgLD, (int)x, (int)y,WIDTH,HEIGHT,null);
			break;
		default:
			break;
		}
    	move();
    }
    public Rectangle getRect(){
    	return new Rectangle(x + WIDTH/4,y + HEIGHT/4,WIDTH/2,HEIGHT/2);
    }
    public boolean hitTank(Tank tank){
    	if(this.live&&tank.isLive()&&this.getRect().intersects(tank.getRect())&&this.good != tank.isGood()){
    		if(tank.isGood()){
    			tank.setLife(tank.getLife() -25);
    			if(tank.getLife()<=0){
    				tank.setLive(false);
    				tank.shengming--;
    				Explode e = new Explode(x,y,ctc);
        			ctc.explodes.add(e);
        			if(ctc.begin.isSelect()){
        				if(ctc.myTank1.shengming<=0&&ctc.myTank2.shengming<=0){
							ctc.end.setGameover(true);
            			}
        			}else if(ctc.myTank1.shengming<=0){
        				ctc.end.setGameover(true);
        			}
    			}
    		}else{
    			tank.setLive(false);
    		}
    		this.live = false;
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
			if(this.live&&this.getRect().intersects(wall.getRect())){
	    		this.live = false;
	    		wall.setLive(false);
	    	}
		}
    }
    public void hitHomes(List<Home> homes){
    	for(int i=0;i<homes.size();i++){
			Home home = homes.get(i);
			if(this.live&&this.getRect().intersects(home.getRect())){
	    		this.live = false;
	    		home.setLive(false);
	    		Explode e = new Explode(homes.get(i).x,homes.get(i).y,ctc);
        		ctc.explodes.add(e);
	    	}
		}
    }
    public void hitSteels(List<Steel> steels){
    	for(int i=0;i<steels.size();i++){
			Steel steel = steels.get(i);
			if(this.live&&this.getRect().intersects(steel.getRect())){
	    		this.live = false;
	    	}
		}
    }
}
