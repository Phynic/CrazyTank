package Game;

public class Maps {
	
	CrazyTankClient ctc;
	public Maps(){}
	public Maps(CrazyTankClient ctc){
		this.ctc = ctc;
	}
	static class Map1{
		public static int wallX[] = {500,600,550,500,500,600,400,350,700,650,950,350,350,300};
		public static int wallY[] = {237,237,337,637,687,687,62,112,62,112,187,337,437,387};
		public static int wallWIDTH[] = {1,1,1,3,1,1,1,3,1,3,2,1,1,3};
	    public static int wallHEIGHT[] = {3,5,1,1,1,1,1,1,1,1,2,1,1,1};
		public static int grassX[] = {150,850,250,200,150,100,850,0,0,1050,1050,0,250,50,200,100,850,1100,900,1050,950};
		public static int grassY[] = {62,62,237,287,337,387,387,187,387,187,387,587,587,637,637,687,587,587,637,637,687};
		public static int grassWIDTH[] = {3,3,2,2,2,2,3,2,2,2,2,1,1,1,1,2,1,1,1,1,2};
		public static int grassHEIGHT[] = {2,2,1,1,1,1,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1};
		public static int steelX[] = {800,900,750,100,125,975};
		public static int steelY[] = {287,287,337,237,612,612};
		public static int steelWIDTH[] = {1,1,5,1,1,1};
		public static int steelHEIGHT[] = {1,1,1,1,1,1};
		public static int riverX[] = {450,450,650,500};
		public static int riverY[] = {187,237,237,487};
		public static int riverWIDTH[] = {5,1,1,3};
		public static int riverHEIGHT[] = {1,6,6,1};
		public static int iceX[] = {0,850,0,0,1050,1050};
		public static int iceY[] = {487,487,87,287,87,287};
		public static int iceWIDTH[] = {6,6,2,2,2,2};
		public static int iceHEIGHT[] = {2,2,2,2,2,2};
		public static int homeX[] = {550};
		public static int homeY[] = {687};
		public static int homeWIDTH[] = {1};
		public static int homeHEIGHT[] = {1};
	}
	static class Map2{
		public static int wallX[] = {950,275,800,900,525,525,625};
		public static int wallY[] = {187,437,487,437,637,687,687};
		public static int wallWIDTH[] = {2,7,2,2,3,1,1};
	    public static int wallHEIGHT[] = {2,2,1,2,1,1,1};
		public static int grassX[] = {0,1050,1100,1050,0,0,200,850,1050,1050};
		public static int grassY[] = {87,87,137,287,437,637,537,537,437,637};
		public static int grassWIDTH[] = {4,2,1,2,2,2,3,3,2,2};
	    public static int grassHEIGHT[] = {2,1,3,1,2,2,2,2,2,2};
		public static int steelX[] = {0,350};
		public static int steelY[] = {212,187};
		public static int steelWIDTH[] = {2,2};
	    public static int steelHEIGHT[] = {1,2};
		public static int riverX[] = {350,300,450,250,300,450,500,350,650,700,700,750,800,750,800};
		public static int riverY[] = {87,137,137,187,187,187,187,337,87,237,187,287,337,137,87};
		public static int riverWIDTH[] = {2,1,1,1,1,1,1,2,1,1,1,1,1,1,1};
	    public static int riverHEIGHT[] = {1,1,1,2,3,3,2,1,6,1,1,1,1,1,1};
	    public static int iceX[] = {100,100,250,850,1050,1050};
		public static int iceY[] = {337,537,637,637,537,337};
		public static int iceWIDTH[] = {2,2,2,2,2,2};
	    public static int iceHEIGHT[] = {2,2,2,2,2,2};
		public static int homeX[] = {575};
		public static int homeY[] = {687};
		public static int homeWIDTH[] = {1};
		public static int homeHEIGHT[] = {1};
	}
	
	
}
