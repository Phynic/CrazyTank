package Game;

import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import Game.Tank.Direction;
import util.Constant;
import util.MyFrame;

public class CrazyTankClient extends MyFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8242602587502225965L;
    Start begin = new Start(this);
    End end = new End(this);
	UI ui = new UI(this);
	Tank myTank1 = new Tank(Constant.MYTANK1_X,Constant.MYTANK1_Y,this,true,Direction.STOP,Direction.U,true);
	Tank myTank2 = new Tank(Constant.MYTANK2_X,Constant.MYTANK2_Y,this,true,Direction.STOP,Direction.U,false);
	List<Bullet>bullets = new ArrayList<Bullet>();
	List<Lazer>lazers = new ArrayList<Lazer>();
	List<Explode>explodes = new ArrayList<Explode>();
	List<Tank>enemyTanks = new ArrayList<Tank>();
	List<Wall>walls = new ArrayList<Wall>();
	List<Grass>grasses = new ArrayList<Grass>();
	List<Ice>ices = new ArrayList<Ice>();
	List<Steel>steels = new ArrayList<Steel>();
	List<River>rivers = new ArrayList<River>();
	List<Home>homes = new ArrayList<Home>();
	int step = 4;
	@Override
    public void paint(Graphics g){
		if(begin.isStart()){
			g.drawString("爆炸容器中爆炸的个数："+explodes.size(), 20, 660);
			g.drawString("坦克容器中坦克的个数："+enemyTanks.size(), 20, 640);
			g.drawString("激光容器中激光的个数："+lazers.size(), 20, 620);
			g.drawString("主角坦克1的生命值：" +myTank1.getLife(),20,50);
			g.drawString("主角坦克2的生命值：" +myTank2.getLife(),20,50);
			g.drawString("子弹容器中子弹的个数："+bullets.size(), 20, 680);
			myTank1.collide();
			myTank1.collidesWithWalls(walls);
			myTank1.collidesWithSteels(steels);
			myTank1.collidesWithRivers(rivers);
			myTank1.collidesWithTanks(enemyTanks);
			myTank1.collidesWithMyTank(myTank2);
			myTank2.collide();
			myTank2.collidesWithWalls(walls);
			myTank2.collidesWithSteels(steels);
			myTank2.collidesWithRivers(rivers);
			myTank2.collidesWithTanks(enemyTanks);
			myTank2.collidesWithMyTank(myTank1);
			ui.drawGround(g);
			for(int i=0;i<walls.size();i++){
				Wall wall = walls.get(i);
				wall.drawWall(g);
			}
			
			for(int i=0;i<steels.size();i++){
				Steel steel = steels.get(i);
				steel.drawSteel(g);
			}
			for(int i=0;i<rivers.size();i++){
				River river = rivers.get(i);
				river.drawRiver(g);
			}
			for(int i=0;i<ices.size();i++){
				Ice ice = ices.get(i);
				ice.drawIce(g);
			}
			for(int i=0;i<lazers.size();i++){
				Lazer lazer = lazers.get(i);
				lazer.drawLazer(g);
				lazer.hitTank(myTank1);
				lazer.hitTank(myTank2);
				lazer.hitTanks(enemyTanks);
				lazer.hitWalls(walls);
				lazer.hitHomes(homes);
			}
			for(;enemyTanks.size()<=0&&step>1;step--){
				for(int i=0;i<5;i++){
		    		enemyTanks.add(new Tank(53+i*250,50,this,false,Direction.STOP,Direction.D));
		    	}
			}
			
			
			for(int i=0;i<bullets.size();i++){
				Bullet b = bullets.get(i);
				b.drawBullet(g);
				b.hitTanks(enemyTanks);
				b.hitTank(myTank1);
				b.hitTank(myTank2);
				b.hitWalls(walls);
				b.hitSteels(steels);
				b.hitHomes(homes);
			}
			myTank1.drawTank(g);
			myTank2.drawTank(g);
			
			
			
			for(int i=0;i<explodes.size();i++){
				Explode e = explodes.get(i);
				e.drawExplode(g);
			}
			for(int i=0;i<enemyTanks.size();i++){
				Tank t = enemyTanks.get(i);
				t.drawTank(g);
				t.collidesWithWalls(walls);
				t.collidesWithSteels(steels);
				t.collidesWithRivers(rivers);
				t.collide();
				t.collidesWithTanks(enemyTanks);
				t.collidesWithMyTank(myTank1);
				t.collidesWithMyTank(myTank2);
			}
			for(int i=0;i<grasses.size();i++){
				Grass grass = grasses.get(i);
				grass.drawGrass(g);
			}
			for(int i=0;i<homes.size();i++){
				Home home = homes.get(i);
				home.drawHome(g);
			}
			if(step == 1&&enemyTanks.size() == 0){
				if(end.level < 2){
					end.setWin(true);
				}else{
					end.drawYouwin(g);
				}
			}
			ui.drawBiankuang(g);	
			ui.drawFlag1(g);
			if(begin.isSelect()){
				ui.drawFlag2(g);
			}
			ui.drawEnemyNum(g);
			if(end.isGameover()){
				end.drawGameover(g);
			}
			if(end.isWin()){
				end.drawCongratulations(g);
			}
		}else{
			begin.drawStart(g); 
		}
		
		
    }
	public void lauchFrame(){
    	super.lauchFrame();
    	this.addKeyListener(new KeyMonitor());
    	drawMap();
    	
    }
	public void drawMap(){
		for(int i = 0;i<5;i++){
    		enemyTanks.add(new Tank(53 + i *250,0,this,false,Direction.STOP,Direction.D));
    	}
		//地图编辑器
		if(end.level==1){
			for(int a=0;a<Maps.Map1.wallWIDTH.length;a++){
				for(int i = 0;i<Maps.Map1.wallWIDTH[a]*2;i++){
				    for(int j = 0;j<Maps.Map1.wallHEIGHT[a]*2;j++){
						walls.add(new Wall(Maps.Map1.wallX[a] + i * 25 + 3, Maps.Map1.wallY[a] + j * 25 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map1.steelWIDTH.length;a++){
				for(int i = 0;i<Maps.Map1.steelWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map1.steelHEIGHT[a];j++){
						steels.add(new Steel(Maps.Map1.steelX[a] + i * 50 + 3, Maps.Map1.steelY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map1.grassWIDTH.length;a++){
				for(int i = 0;i<Maps.Map1.grassWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map1.grassHEIGHT[a];j++){
						grasses.add(new Grass(Maps.Map1.grassX[a] + i * 50 + 3, Maps.Map1.grassY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map1.iceWIDTH.length;a++){
				for(int i = 0;i<Maps.Map1.iceWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map1.iceHEIGHT[a];j++){
						ices.add(new Ice(Maps.Map1.iceX[a] + i * 50 + 3, Maps.Map1.iceY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map1.riverWIDTH.length;a++){
				for(int i = 0;i<Maps.Map1.riverWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map1.riverHEIGHT[a];j++){
						rivers.add(new River(Maps.Map1.riverX[a] + i * 50 + 3, Maps.Map1.riverY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map1.homeWIDTH.length;a++){
				for(int i = 0;i<Maps.Map1.homeWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map1.homeHEIGHT[a];j++){
						homes.add(new Home(Maps.Map1.homeX[a] + i * 50 + 3, Maps.Map1.homeY[a] + j * 50 + 28, this));
					}
				}
			}
		}
		if(end.level == 2){
			for(int a=0;a<Maps.Map2.wallWIDTH.length;a++){
				for(int i = 0;i<Maps.Map2.wallWIDTH[a]*2;i++){
				    for(int j = 0;j<Maps.Map2.wallHEIGHT[a]*2;j++){
						walls.add(new Wall(Maps.Map2.wallX[a] + i * 25 + 3, Maps.Map2.wallY[a] + j * 25 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map2.steelWIDTH.length;a++){
				for(int i = 0;i<Maps.Map2.steelWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map2.steelHEIGHT[a];j++){
						steels.add(new Steel(Maps.Map2.steelX[a] + i * 50 + 3, Maps.Map2.steelY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map2.grassWIDTH.length;a++){
				for(int i = 0;i<Maps.Map2.grassWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map2.grassHEIGHT[a];j++){
						grasses.add(new Grass(Maps.Map2.grassX[a] + i * 50 + 3, Maps.Map2.grassY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map2.iceWIDTH.length;a++){
				for(int i = 0;i<Maps.Map2.iceWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map2.iceHEIGHT[a];j++){
						ices.add(new Ice(Maps.Map2.iceX[a] + i * 50 + 3, Maps.Map2.iceY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map2.riverWIDTH.length;a++){
				for(int i = 0;i<Maps.Map2.riverWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map2.riverHEIGHT[a];j++){
						rivers.add(new River(Maps.Map2.riverX[a] + i * 50 + 3, Maps.Map2.riverY[a] + j * 50 + 28, this));
					}
				}
			}
			for(int a=0;a<Maps.Map2.homeWIDTH.length;a++){
				for(int i = 0;i<Maps.Map2.homeWIDTH[a];i++){
				    for(int j = 0;j<Maps.Map2.homeHEIGHT[a];j++){
						homes.add(new Home(Maps.Map2.homeX[a] + i * 50 + 3, Maps.Map2.homeY[a] + j * 50 + 28, this));
					}
				}
			}
		}
		
	}
	
    class KeyMonitor extends KeyAdapter{
    	@Override
    	public void keyPressed(KeyEvent e) {
    		myTank1.keyPressed(e);
    		myTank2.keyPressed(e);
            begin.keyPressed(e);
            end.keyPressed(e);
    	}
    	public void keyReleased(KeyEvent e){
    		myTank1.keyReleased(e);
    		myTank2.keyReleased(e);
    		
    	}
    }
    public static void main(String[] args){
    	CrazyTankClient mgf = new CrazyTankClient();
    	mgf.lauchFrame();	
    }
}
