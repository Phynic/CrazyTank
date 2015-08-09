package util;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import util.Constant;



public class MyFrame extends Frame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3918194839094440864L;
	public void lauchFrame(){
    	this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
    	this.setLocation(0,0);
    	this.setVisible(true);
    	this.setTitle("MyGameFrame");
    	this.setBackground(Color.DARK_GRAY);
    	this.setResizable(false);
    	this.addWindowListener(new WindowAdapter(){
    		@Override
    		public void windowClosing(WindowEvent e){
    			System.exit(0);
    		}
    	});
    	new MyThread().start();
    }
	class MyThread extends Thread{
    	@Override
    	public void run(){
    		while(true){
    			repaint();
    			try {
					Thread.sleep(35);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
    		}
    	}
    }
	//双缓冲解决闪烁
	Image backImg = null;
    @Override
    public void update(Graphics g){
    	if(backImg == null){
    		backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
    	}
    	Graphics backg = backImg.getGraphics();
    	Color c = backg.getColor();
    	backg.setColor(Color.DARK_GRAY);
    	backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
    	backg.setColor(c);
    	paint(backg);
    	g.drawImage(backImg, 0, 0, null);
    }
    
    
}
