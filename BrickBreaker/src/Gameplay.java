import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

//import java.util.Timer;
import javax.swing.Timer;


import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener,ActionListener  {
	
	private boolean play=false;//starting game will not run
	private int score=0;
	
	private int totalbricks=21;//map size is 7*3
	private Timer time;//Timer class to set time of ball move
	private int delay=8;//speed of ball
	private int playerX=310;//starting position of slider
	private int ballPosX=120;//starting pos of ball
	private int ballPosY=350;//starting pos of ball
	private int ballXdir=-1;//direction of ball
	private int ballYdir=-2;//''''''
	private Timer timer;
	private MapGenerator map;
	
	 
	public Gameplay(){
		map=new MapGenerator(3,7);
		
		addKeyListener(this);
		setFocusable(true);//method from component class to get focusable set the component to be focused
		                    //slider will not move
		
		setFocusTraversalKeysEnabled(false);//Sets the focus traversal keys for a given traversal operation
    timer= new Timer(delay,this);//speed in delay variable and context is this	
	timer.start();
	}
	public void paint(Graphics g)//with the help of graphics we will draw different types of shape
	{
		//background
	g.setColor(Color.red);
	g.fillRect(1,1,692,592);//rectanglee for background
	
	//map drawing

	map.draw((Graphics2D)g);//graphics object copies to map object
	//border...three rectangle...border in three sides
	g.setColor(Color.yellow);
	g.fillRect(0,0,3,592);
	g.fillRect(0,0,592,3);
	g.fillRect(691,0,3,592);
	//paddle...slider
	g.setColor(Color.green);
	g.fillRect(playerX,550,100,8);
	//ball
	g.setColor(Color.BLUE);
	
	g.fillRect(ballPosX,ballPosY,20,20);
	g.dispose();
	//score
    g.setColor(Color.WHITE);
    g.setFont(new Font("serif",Font.BOLD,25));//25 size of text
    g.drawString(""+score,590,30);
    
    
    
	}
	

	//private void() private void setFocusTraversalKeyEnable() {
		// TODO Auto-generated method stub



	//public static void main(String[] e) {
		// TODO Auto-generated method stub

	//}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		timer.start();
		repaint();
		
		//code to move ball
		if(play)//check whether variable play is true ..it will be true when we will press left or right key
		{
			if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8)))
			{
				ballYdir=-ballYdir;
			}
			
			A:for(int i=0;i<map.map.length;i++)//accessing 2d array
			{
				
				for(int j=0;j<map.map[0].length;j++)
				{
					
					
					if(map.map[i][j]>0)
					{
						
						int brickX=j*map.brickwidth+80;
						int brickY=i*map.brickheight+50;
						int brickwidth=map.brickwidth;
						int brickheight=map.brickheight;
						Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickheight);
						
						Rectangle ballrect=new Rectangle(ballPosX,ballPosY,20,20);
						Rectangle brickrect=rect;
						if(ballrect.intersects(brickrect))
						{
							
							map.setBrickValue(0,i,j);
							totalbricks--;
							score +=5;
							if((ballPosX+19<=brickrect.x) || (ballPosX+1>=brickrect.x+brickrect.width) )
							{
								ballXdir=-ballXdir;
								
								
							}
							else{
								
								ballYdir=-ballYdir;
								break A;
							}
							
						}
						
						
						
					}
					
				}
			}
			
			
			ballPosX+=ballXdir;
			ballPosY+=ballYdir;
			//for left 
			if(ballPosX<0)
			{
				
				ballXdir=-ballXdir;
			}
			//for top
			if(ballPosY<0)
			{
				
				ballYdir=-ballYdir;
			}
			//for right
			if(ballPosX>670)
			{
				
				ballXdir=-ballXdir;
			}
		}
		
		
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=600)
			{
				playerX=600;
			}
			else
			{
			moveRight();
			}
			
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(playerX<=10)
			{
				playerX=10;
			}
			else
			{
			moveLeft();
			}
			
		}

		
		
		
	}
	
	

	private void moveLeft() {
		// TODO Auto-generated method stub
		play=true;
		playerX-=20;
		
	}
	private void moveRight() {
		// TODO Auto-generated method stub
		play=true;
		playerX+=20;
		
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
		// Git 
		
	}

}
