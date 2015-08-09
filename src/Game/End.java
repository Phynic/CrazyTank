package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;

import Game.Tank.Direction;
import util.Constant;
import util.GameUtil;

public class End {
	int x = 440;
	int y = 440;
	int z = 440;
	int level = 1;
	boolean gameover = false;
	boolean win = false;
	boolean finish = false;
	
	
	
	static Image imgGameover = GameUtil.getImage("images/gameover.png");
	static Image imgCongratulations = GameUtil.getImage("images/congratulations.png");
	static Image imgYouwin = GameUtil.getImage("images/win.png");
	CrazyTankClient ctc;
	public End(){}
	public End(CrazyTankClient ctc){
		this.ctc = ctc;
	}
	public boolean isGameover() {
		return gameover;
	}
	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	public boolean isFinish() {
		return finish;
	}
	public void setFinish(boolean finish) {
		this.finish = finish;
	}
	public void drawGameover(Graphics g){
		if(y != 0){
			y -= 10;
		}else{
			y = 0;
		}
		g.drawImage(imgGameover, 15, y, null);
	}
	public void drawCongratulations(Graphics g){
		if(x != 0){
			x -= 10;
		}else{
			x = 0;
		}
		g.drawImage(imgCongratulations, 15, x, null);
	}
	public void drawYouwin(Graphics g){
		if(z != 0){
			z -= 10;
		}else{
			z = 0;
		}
		g.drawImage(imgYouwin, 15, z, null);
	}
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		if(ctc.begin.isStart()&&y == 0||z == 0){
			switch (key) {
			case KeyEvent.VK_ENTER:
				ctc.begin.setStart(false);
				ctc.myTank1.setLive(false);
				ctc.myTank2.setLive(false);
				ctc.step = 4;
				for(int i=0;i<ctc.homes.size();i++){
					Home home = ctc.homes.get(i);
					home.live = true;
				}
				y = 440;
				z = 440;
				gameover = false;
				finish = false;
				level = 1;
				break;

			default:
				break;
			}
		}
		if(ctc.begin.isStart()&&x == 0){
			switch (key) {
			case KeyEvent.VK_ENTER:
				ctc.myTank1.setLife(100);
				ctc.myTank1.setLive(true);
				ctc.myTank1.x = Constant.MYTANK1_X;
				ctc.myTank1.y = Constant.MYTANK1_Y;
				ctc.myTank1.barrelOfGunDir = Direction.U;
				ctc.myTank2.setLife(100);
				ctc.myTank2.setLive(true);
				ctc.myTank2.x = Constant.MYTANK2_X;
				ctc.myTank2.y = Constant.MYTANK2_Y;
				ctc.myTank2.barrelOfGunDir = Direction.U;
				ctc.homes.clear();
				ctc.bullets.clear();
				ctc.lazers.clear();
				ctc.enemyTanks.clear();
				ctc.walls.clear();
				ctc.grasses.clear();
				ctc.ices.clear();
				ctc.steels.clear();
				ctc.rivers.clear();
				ctc.explodes.clear();
				ctc.myTank1.setShengming(2);
				ctc.myTank2.setShengming(2);
				for(int i=0;i<ctc.homes.size();i++){
					Home home = ctc.homes.get(i);
					home.live = true;
				}
				x = 440;
				win = false;
				finish = false;
				ctc.step = 4;
				level ++;
				ctc.drawMap();
				if(!ctc.begin.isSelect()){
			        ctc.myTank2.setLive(false);
				}else{
					ctc.myTank2.setLive(true);
				}
				break;
			default:
				break;
			}
		}
	}
}
