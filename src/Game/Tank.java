package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

import util.Constant;
import util.GameUtil;

public class Tank {
	int x = 50;
    int y = 50;
    double speed = 6;
    String imgPath;
    boolean bL,bU,bR,bD;
    boolean good;
    boolean P1;
    int shengming;
    
	private int xOfLastStep;//上一步x的位置
    private int yOfLastStep;//上一步y的位置
    private int life = 100;
	private boolean live = true;
    static Image imgm1U =GameUtil.getImage("images/myTankU.png");
    static Image imgm1D =GameUtil.getImage("images/myTankD.png");
    static Image imgm1R =GameUtil.getImage("images/myTankR.png");
    static Image imgm1L =GameUtil.getImage("images/myTankL.png");
    static Image imgm1RU =GameUtil.getImage("images/myTankRU.png");
    static Image imgm1RD =GameUtil.getImage("images/myTankRD.png");
    static Image imgm1LU =GameUtil.getImage("images/myTankLU.png");
    static Image imgm1LD =GameUtil.getImage("images/myTankLD.png");
    static Image imgm2U =GameUtil.getImage("images/myTank2U.png");
    static Image imgm2D =GameUtil.getImage("images/myTank2D.png");
    static Image imgm2R =GameUtil.getImage("images/myTank2R.png");
    static Image imgm2L =GameUtil.getImage("images/myTank2L.png");
    static Image imgm2RU =GameUtil.getImage("images/myTank2RU.png");
    static Image imgm2RD =GameUtil.getImage("images/myTank2RD.png");
    static Image imgm2LU =GameUtil.getImage("images/myTank2LU.png");
    static Image imgm2LD =GameUtil.getImage("images/myTank2LD.png");
    static Image imgeU =GameUtil.getImage("images/enemyTankU.png");
    static Image imgeD =GameUtil.getImage("images/enemyTankD.png");
    static Image imgeR =GameUtil.getImage("images/enemyTankR.png");
    static Image imgeL =GameUtil.getImage("images/enemyTankL.png");
    static Image imgeRU =GameUtil.getImage("images/enemyTankRU.png");
    static Image imgeRD =GameUtil.getImage("images/enemyTankRD.png");
    static Image imgeLU =GameUtil.getImage("images/enemyTankLU.png");
    static Image imgeLD =GameUtil.getImage("images/enemyTankLD.png");
    
    
	BloodBar bloodbar = new BloodBar();
    
    
    //定义坦克的方向
    enum Direction{L,R,U,D,LU,RU,RD,LD,STOP}
    //定义一个随机生成器
    private static Random r = new Random();
    //定义敌人坦克移动的步数
    int step = r.nextInt(10) + 5;
    Direction dir = Direction.STOP;
    Direction barrelOfGunDir = Direction.R;
    //持有对方的引用
    CrazyTankClient ctc;
	public static final int WIDTH = 48;
    public static final int HEIGHT = 48;
    

    public int getLife() {
		return life;
	}
	public void setLife(int life) {
		this.life = life;
	}
    public boolean isGood() {
		return good;
	}

    
    public boolean isLive() {
		return live;
	}
	public void setLive(boolean live) {
		this.live = live;
	}
	public int getShengming() {
		return shengming;
	}
	public void setShengming(int shengming) {
		this.shengming = shengming;
	}
    public Tank(){}
    public Tank(int x,int y){
    	this.x = x;
    	this.y = y;
    	//当坦克初始化的时候，坦克的上一步的位置就是当前的位置
    	this.xOfLastStep = x;
    	this.yOfLastStep = y;
    }
    public Tank(int x,int y,CrazyTankClient ctc){
    	this(x,y);
    	this.ctc = ctc;
    }
    public Tank(int x,int y,CrazyTankClient ctc,boolean good){
    	this(x,y,ctc);
    	this.good = good;
    }
    //初始化时，给坦克添加一个方向
    public Tank(int x,int y,CrazyTankClient ctc,boolean good,Direction dir,Direction barrelOfGunDir){
    	this(x,y,ctc,good);
    	this.dir = dir;
    	this.barrelOfGunDir = barrelOfGunDir;
    }
    public Tank(int x,int y,CrazyTankClient ctc,boolean good,Direction dir,Direction barrelOfGunDir,boolean P1){
    	this(x,y,ctc,good);
    	this.dir = dir;
    	this.barrelOfGunDir = barrelOfGunDir;
    	this.P1 = P1;
    }

    
    public void move(){
    	//记录每次移动的位置
    	this.xOfLastStep = x;
    	this.yOfLastStep = y;
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
		if(this.dir != Direction.STOP){
			this.barrelOfGunDir = this.dir;
		}
		if(x<3) x = 3;
		if(y<28) y = 27;
		if(x>Constant.GAME_WIDTH-WIDTH - 210 -3) x = Constant.GAME_WIDTH-WIDTH-3 - 210;
		if(y>Constant.GAME_HEIGHT-HEIGHT-3) y = Constant.GAME_HEIGHT-HEIGHT-3;
		
		if(!good){
			//将Direction枚举类转化成数组
			Direction[] dirs = Direction.values();
			if(step == 0){
				step = r.nextInt(10) + 5;
				//换方向
				for(int i=0;i<dirs.length;i++){
					int randomNum = r.nextInt(dirs.length);
					dir = dirs[randomNum];
				}
			}
			step--;
			if(r.nextInt(50)>45){
				fire();
			}
		}
		
    }
    //确定炮筒方向的方法
    public void drawGOB(Graphics g){
    	if(good){
    		if(P1){	
    			switch (barrelOfGunDir) {
        		case L:			
        			g.drawImage(imgm1L, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case LU:
        			g.drawImage(imgm1LU, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case U:
        			g.drawImage(imgm1U, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case RU:
        			g.drawImage(imgm1RU, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case R:
        			g.drawImage(imgm1R, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case RD:
        			g.drawImage(imgm1RD, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case D:
        			g.drawImage(imgm1D, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case LD:
        			g.drawImage(imgm1LD, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		default:
        			break;
        		}
    		}else{
    			switch (barrelOfGunDir) {
        		case L:			
        			g.drawImage(imgm2L, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case LU:
        			g.drawImage(imgm2LU, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case U:
        			g.drawImage(imgm2U, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case RU:
        			g.drawImage(imgm2RU, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case R:
        			g.drawImage(imgm2R, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case RD:
        			g.drawImage(imgm2RD, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case D:
        			g.drawImage(imgm2D, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		case LD:
        			g.drawImage(imgm2LD, (int)x, (int)y,WIDTH,HEIGHT,null);
        			break;
        		default:
        			break;
        		}
    		}
    	}else{
    		switch (barrelOfGunDir) {
    		case L:
    			g.drawImage(imgeL, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case LU:
    			g.drawImage(imgeLU, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case U:
    			g.drawImage(imgeU, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case RU:
    			g.drawImage(imgeRU, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case R:
    			g.drawImage(imgeR, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case RD:
    			g.drawImage(imgeRD, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case D:
    			g.drawImage(imgeD, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		case LD:
    			g.drawImage(imgeLD, (int)x, (int)y,WIDTH,HEIGHT,null);
    			break;
    		default:
    			break;
    		}
    	}
    	
    }
    //定义一个确定方向的方法
    public void locateDirection(){
    	if(bL&&!bU&&!bR&&!bD) dir = Direction.L;
    	else if(bL&&bU&&!bR&&!bD) dir = Direction.LU;
    	else if(!bL&&bU&&!bR&&!bD) dir = Direction.U;
    	else if(!bL&&bU&&bR&&!bD) dir = Direction.RU;
    	else if(!bL&&!bU&&bR&&!bD) dir = Direction.R;
    	else if(!bL&&!bU&&bR&&bD) dir = Direction.RD;
    	else if(!bL&&!bU&&!bR&&bD) dir = Direction.D;
    	else if(bL&&!bU&&!bR&&bD) dir = Direction.LD;
    	else{
    		dir = Direction.STOP;
    	}
    }
    public void keyPressed(KeyEvent e){
    	int key = e.getKeyCode();
		if(P1){
			switch (key) {
			case KeyEvent.VK_A:
				bL = true;
				break;
			case KeyEvent.VK_W:
				bU = true;
				break;
			case KeyEvent.VK_S:
				bD = true;
				break;
			case KeyEvent.VK_D:
				bR = true;
				break;
			case KeyEvent.VK_J:
				fire();
				break;
			case KeyEvent.VK_K:
				superfire8();
				break;
			case KeyEvent.VK_F1:
				if(!live&&ctc.myTank1.shengming != 0){
					this.setLife(100);
					this.setLive(true);
					this.x = Constant.MYTANK1_X;
					this.y = Constant.MYTANK1_Y;
					this.barrelOfGunDir = Direction.U;
	      		}
				break;
			
			default:
				break;
			}
		}else{
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_NUMPAD4:
				if(this.live){
					fire();
				}
				
				break;
			case KeyEvent.VK_NUMPAD5:
				if(this.live){
					superfire8();
				}
				break;
			case KeyEvent.VK_F2:
				if(!live&&ctc.myTank2.shengming != 0){
					this.setLife(100);
					this.setLive(true);
					this.x = Constant.MYTANK2_X;
					this.y = Constant.MYTANK2_Y;
					this.barrelOfGunDir = Direction.U;
	      		}
				break;
			default:
				break;
			}
		}
		locateDirection();
    }
    public void keyReleased(KeyEvent e){
    	int key = e.getKeyCode();
		if(P1){
			switch (key) {
			case KeyEvent.VK_A:
				bL = false;
				break;
			case KeyEvent.VK_W:
				bU = false;
				break;
			case KeyEvent.VK_S:
				bD = false;
				break;
			case KeyEvent.VK_D:
				bR = false;
				break;
			case KeyEvent.VK_L:
				if(ctc.lazers.size() < 2){
					lazer(barrelOfGunDir);
				}
				
				break;
			default:
				break;
			}
		}else{
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_NUMPAD6:
				if(ctc.lazers.size() < 2){
					lazer(barrelOfGunDir);
				}
				break;
			default:
				break;
			}
		}
		locateDirection();
    }
    public void drawTank(Graphics g){
    	if(!live){
    		if(good){
    			return;
    		}else{
    			ctc.enemyTanks.remove(this);
        		return;
    		}
    	}
        if(good){
        	g.setColor(Color.LIGHT_GRAY);
    	}else{
    		g.setColor(new Color(111,125,230));
    	}
    	g.fillOval((int)x, (int)y, 0, 0);
    	g.setColor(Color.BLACK);
    	bloodbar.drawBloodBar(g);
    	drawGOB(g);
    	move();
    }
    //添加一个打出子弹的方法
    public Bullet fire(){
    	if(!live){
    		
    		return null;
    	}
    	
    	int x,y;
    	x = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
    	y = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
    	Bullet b = new Bullet(x,y,barrelOfGunDir,ctc,good);
    	ctc.bullets.add(b);
    	return b;
    }
    public Bullet fire(Direction dir){
    	if(!live){
    		return null;
    	}
    	int x,y;
    	x = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
    	y = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
    	Bullet b = new Bullet(x,y,dir,ctc,good);
    	ctc.bullets.add(b);
    	return b;
    }
    public void superfire8(){
    	Direction[] dirs = Direction.values();
    	for(int i=0;i<dirs.length - 1;i++){
    		fire(dirs[i]);
    	}
    }
    public Lazer lazer(Direction dir){
        if(!live){
    		return null;
    	}
    	int x = 0;
    	int y = 0;
    	switch (dir) {
		case R:
			x = this.x + Tank.WIDTH - 92;
	    	y = this.y + Tank.HEIGHT/2 - Lazer.HEIGHT/2;
			break;
		case L:
			x = this.x + 92 - Lazer.WIDTH;
	    	y = this.y + Tank.HEIGHT/2 - Lazer.HEIGHT/2;
			break;
		case U:
			x = this.x + Tank.WIDTH/2 -Lazer.HEIGHT/2;
	    	y = this.y + 92 - Lazer.WIDTH;
			break;
		case D:
			x = this.x + Tank.WIDTH/2 - Lazer.HEIGHT/2;
	    	y = this.y + Tank.HEIGHT - 92;
			break;
		default:
			break;
		}
    	
    	Lazer b = new Lazer(x,y,barrelOfGunDir,ctc,good);
    	ctc.lazers.add(b);
    	return b;
    }
    public Rectangle getRect(){
    	return new Rectangle(x,y,WIDTH,HEIGHT);
    }
    
    
    public boolean collidesWithWalls(List<Wall> walls){
    	for(int i=0;i<walls.size();i++){
    		Wall wall = walls.get(i);
    		if(this.live&&this.getRect().intersects(wall.getRect())){
	    		this.x = xOfLastStep;
	    		this.y = yOfLastStep;
	    		return true;
	    	}
    	}
    	return false;
    }
    public boolean collidesWithSteels(List<Steel> steels){
    	for(int i=0;i<steels.size();i++){
    		Steel steel = steels.get(i);
    		if(this.live&&this.getRect().intersects(steel.getRect())){
	    		this.x = xOfLastStep;
	    		this.y = yOfLastStep;
	    		return true;
	    	}
    	}
    	return false;
    }
    public boolean collidesWithRivers(List<River> rivers) {
    	for(int i=0;i<rivers.size();i++){
    		River river = rivers.get(i);
    		if(this.live&&this.getRect().intersects(river.getRect())){
	    		this.x = xOfLastStep;
	    		this.y = yOfLastStep;
	    		return true;
	    	}
    	}
    	return false;
	}
    public boolean collidesWithGrasses(List<Grass> grasses){
    	for(int i=0;i<grasses.size();i++){
    		Grass grass = grasses.get(i);
    		if(this.live&&this.getRect().intersects(grass.getRect())){
	    		return true;
	    	}
    	}
    	return false;	
    }
    public boolean collidesWithIces(List<Ice> ices){
    	for(int i=0;i<ices.size();i++){
    		Ice ice = ices.get(i);
    		if(this.live&&this.getRect().intersects(ice.getRect())){
	    		return true;
	    	}
    	}
    	return false;
    }
    public void collide(){
    	if(collidesWithGrasses(ctc.grasses)&&!collidesWithIces(ctc.ices)){
    		this.speed = 3;
    	}else if(!collidesWithGrasses(ctc.grasses)&&collidesWithIces(ctc.ices)){
    		this.speed = 12;
    	}else{
    		this.speed = 6;
    	}
    }
	
	public boolean collidesWithTanks(List<Tank> tanks){
		for(int i=0;i<tanks.size();i++){
			Tank tank = tanks.get(i);
			if(this != tank){
				if(this.live&&this.getRect().intersects(tank.getRect())){
		    		this.x = xOfLastStep;
		    		this.y = yOfLastStep;
		    		return true;
		    	}
			}
		}
		return false;
	}
	public boolean collidesWithMyTank(Tank tank){
		if(this.live&&tank.isLive()&&this.getRect().intersects(tank.getRect())){
    		this.x = xOfLastStep;
    		this.y = yOfLastStep;
    		return true;
    	}else{
    		return false;
    	}
	}
	private class BloodBar{
		public void drawBloodBar(Graphics g){
			
			g.setColor(new Color(0,120,0));
			g.drawRect(x, y - 8, Tank.WIDTH, 5);
			g.fillRect(x, y - 8, Tank.WIDTH * life/100, 5);
		}
	}
	public boolean eatBlood(Blood blood){
		if(this.live&&blood.isLive()&&this.getRect().intersects(blood.getRect())){
			if(this.isGood()){
				this.life = 100;
				blood.setLive(false);
			}
			return true;
		}else{
			return false;
		}
	}
	
}
