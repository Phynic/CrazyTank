package util;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;



public class GameUtil {
	static String musicPath[] = {
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\hit.wav",
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\add.wav",
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\blast.wav",
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\fire.wav",
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\start.wav",
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\win.wav",
			"E:\\WORKPLACE\\CrazyTank\\src\\Music\\loser.wav",
	};
    public static Image getImage(String imgPath){
    	URL u = GameUtil.class.getClassLoader().getResource(imgPath);
    	BufferedImage img = null;
    	try{
    		img = ImageIO.read(u);
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    	return img;
    }
    public static void getSound(int i) {
		try{
		File f = new File(musicPath[i]);
		@SuppressWarnings("deprecation")
		URL cb = f.toURL();
		AudioClip aau = Applet.newAudioClip(cb);
		aau.play();
		}catch(Exception E ){
			E.printStackTrace();
		}
		
	}
}
