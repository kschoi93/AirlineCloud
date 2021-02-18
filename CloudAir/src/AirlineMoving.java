

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JPanel;

public class AirlineMoving extends JPanel implements Runnable{
	// 캔버스와 이미지 정의
	private MyCanvas mc = new MyCanvas();
	private Image airplane = Toolkit.getDefaultToolkit().getImage("img/airplane.png");
	private Image cloud1 = Toolkit.getDefaultToolkit().getImage("img/cloud1.png");
	private Image cloud2 = Toolkit.getDefaultToolkit().getImage("img/cloud2.png");
	private int airplaneX, airplaneY;// 항공기 x,y 좌표
	private Random ran = new Random();
	private int[][] r = new int[30][2]; // 구름 위치 랜덤
	private int cloudMotion;// 구름 움직임,
	private int change=0, change1 =0; // 구름 움직이는 기준
	private Dimension frameSize; // 프레임 사이즈와 풀스크린 사이즈 구하기
	public AirlineMoving() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(mc);
	}
	// 스레드 런 메소드
	public void run() {
		while(true) {
			// 스레드 0.1초
			try {Thread.sleep(100);}catch(Exception e) {e.printStackTrace();}
			
			// 비행기 위치가 -100 일 때 첫 위치로 이동
			if(airplaneX==-100) airplaneX = ( (int)frameSize.getWidth()/10 )*10;
			// 비행기는 서쪽으로 쭉 이동
			airplaneX-=5;
			/////////////// 구름모션////////////			
			change1++;
			if(change1==10) {
				// 구름 모션 +1 , -1
				if(change==0) {
					cloudMotion = 1;
					change = 1;
				} else if(change == 1 ) {
					cloudMotion = -1;
					change = 0;
				}
				change1=0;
			}
			/////////////////////////////////
			mc.repaint();
		}
	}
	///////////////// 캔버스///////////////
	class MyCanvas extends Canvas{
		public void paint(Graphics g) {
			g.drawImage(airplane, airplaneX, airplaneY, 100, 100, this);
			for(int i=0; i<r.length;i++) {
				if(i<(r.length/2)) g.drawImage(cloud1, r[i][0]+cloudMotion, r[i][1], 40, 20, this);
				else if(i>=(r.length/2)) g.drawImage(cloud2, r[i][0]+cloudMotion, r[i][1], 40, 20, this);
			}
		}
	}
	
	////////////////// 프레임 사이즈 구하기///////////////
	public void getFrameSize() {
		frameSize = getSize();
		airplaneSize();
		cloudSize();
	}
	
	//// 항공기 사이즈////////
	public void airplaneSize() {
		airplaneX = ( (int)frameSize.getWidth()/10 )*10;
		airplaneY = (( (int)frameSize.getHeight()/2 - 50) /10 ) *10;

	}
	
	///////// 구름 위치//////////
	public void cloudSize(){
		for(int i =0; i<r.length;i++) {
			r[i][0] = ran.nextInt(((int)frameSize.getWidth()/10))*10;
			r[i][1] = ran.nextInt(((int)frameSize.getHeight()/10))*10;
		}
	}
}
