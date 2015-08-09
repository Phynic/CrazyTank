package Game;


import java.awt.Graphics;
import java.awt.Image;
import util.GameUtil;

public class UI {
	int a = 0;
	CrazyTankClient ctc;
	public UI(){}
	public UI(CrazyTankClient ctc){
		this.ctc = ctc;
	}
	static Image[] imgs = {GameUtil.getImage("images/shengming0.png"),
		GameUtil.getImage("images/shengming1.png"),
		GameUtil.getImage("images/shengming2.png"),
		GameUtil.getImage("images/shengming3.png"),
		GameUtil.getImage("images/shengming4.png"),
		GameUtil.getImage("images/shengming5.png"),
		GameUtil.getImage("images/shengming6.png"),
		GameUtil.getImage("images/shengming7.png"),
		GameUtil.getImage("images/shengming8.png"),
		GameUtil.getImage("images/shengming9.png")
		};
	static Image imgGround =GameUtil.getImage("images/ground.png");
	static Image imgBiankuang =GameUtil.getImage("images/biankuang1.png");
	static Image imgFlag1 = GameUtil.getImage("images/Flag1.png");
	static Image imgFlag2 = GameUtil.getImage("images/Flag2.png");
	static Image imgEnemyNum = GameUtil.getImage("images/enemyTankD.png");
	static Image imgEnemyLast = GameUtil.getImage("images/enemylast.png");
	public void drawGround(Graphics g){
		g.drawImage(imgGround,0,0,null);
		
	}
	public void drawBiankuang(Graphics g){
		g.drawImage(imgBiankuang,3+1150,26,null);
	}
	public void drawFlag1(Graphics g){
		if(ctc.begin.isSelect()){
			g.drawImage(imgFlag1,1185,545,100,100,null);
			g.drawImage(imgs[ctc.myTank1.shengming],1268,578,70,70,null);
		}else{
			g.drawImage(imgFlag1,1185,645,100,100,null);
			g.drawImage(imgs[ctc.myTank1.shengming],1268,677,70,70,null);
		}
	}
	public void drawFlag2(Graphics g){
		g.drawImage(imgFlag2,1185,645,100,100,null);
		g.drawImage(imgs[ctc.myTank2.shengming],1268,677,70,70,null);
	}
	public void drawEnemyNum(Graphics g){
		g.drawImage(imgEnemyLast,1185,39,150,41,null);
		a = (ctc.step - 1)*5 + ctc.enemyTanks.size();
		if(a<10){
			for(int j = 0;j<a;j++){
				g.drawImage(imgEnemyNum,1210,90 + 45*j,35,35,null);	
			}
		}else{
			for(int j = 0;j<10;j++){
				g.drawImage(imgEnemyNum,1210,90 + 45*j,35,35,null);	
			}
		}
		for(int j=0;j<a-10;j++){
			g.drawImage(imgEnemyNum,1268,90 + 45*j,35,35,null);	
		}
		
	}
}
