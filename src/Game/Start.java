package Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import Game.Tank.Direction;
import util.Constant;
import util.GameUtil;
public class Start {
	CrazyTankClient ctc;
	static Image img1 = GameUtil.getImage("images/player1.png");
	static Image img2 = GameUtil.getImage("images/player2.png");
    private boolean start = false;
    private boolean select = false;
    
    public boolean isSelect() {
		return select;
	}
	public void setSelect(boolean select) {
		this.select = select;
	}
	public Start(){}
	public Start(CrazyTankClient ctc){
		this.ctc = ctc;
	}
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(!start){
			switch (key) {
			case KeyEvent.VK_UP:
				select = false;
				GameUtil.getSound(0);
				break;
			case KeyEvent.VK_DOWN:
				select = true;
				GameUtil.getSound(0);
				break;
			case KeyEvent.VK_ENTER:
				start = true;
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
				ctc.drawMap();
				ctc.myTank1.setShengming(2);
				ctc.myTank2.setShengming(2);
				break;
			default:
				break;
			}
			if(!select){
		        ctc.myTank2.setLive(false);
			}else{
				ctc.myTank2.setLive(true);
			}
		}
	}
	public void drawStart(Graphics g){
		if(select){
			g.drawImage(img2, 0, 0, null);
		}else{
			g.drawImage(img1, 0, 0, null);
		}
	}
}
