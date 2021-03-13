import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmpPieChart_Age extends JFrame {

	CenterPane centerPane = new CenterPane();
	NorthPane northPane = new NorthPane();
	
	int[] angle;	//파이차트 그릴 각도의 변수
	int[] per;		//항목별 %
	
	//노,초,갈,빨,보,회
	Color[] col = {new Color(238,161,69), new Color(96,178,160), new Color(97,100,114), 
			new Color(200,76,68), new Color(147,116,158), new Color(136,107,104)};
	
	public EmpPieChart_Age() {

		add(centerPane);
		add("North", northPane);
		
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	
	class CenterPane extends JPanel {
		JLabel perLbl;
		JPanel perPane = new JPanel(new GridLayout(1,0));
		CenterPane() {
			setLayout(new FlowLayout());
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int sumAngle = 0;
		
			//차트 그리기
			for(int i=0; i<angle.length; i++) {
				if(angle[i]>0) {
					g.setColor(col[i]);
					g.fillArc(this.getWidth() / 2-150, 100, 300, 300, sumAngle, angle[i]);
					sumAngle += angle[i];
				}
			}
		}
	}
	
	
	class NorthPane extends JPanel {
		JLabel countLbl;
		JLabel perLbl;
		JPanel pane = new JPanel(new GridLayout(2,0));
			JPanel pane1 = new JPanel(new GridLayout(1,0));
			JPanel pane2 = new JPanel(new GridLayout(1,0));
		
		Color[] col = {new Color(238,161,69), new Color(96,178,160), new Color(97,100,114), 
				new Color(200,76,68), new Color(147,116,158), new Color(136,107,104)};
		
		NorthPane() {
			setLayout(new BorderLayout(1,0));
			
			int sum = 0;
			int i = 0;

			per = new int[6]; angle = new int[6];
			for (int j = 0; j < per.length; j++) {per[j]=0;}
			for (int j = 0; j < angle.length; j++) {angle[j]=0;}
			
			for(Map.Entry<String, String> data2 : EmpAirlineSales.data.entrySet()) {
				sum += Integer.parseInt(data2.getValue());
			}
			
			for(Map.Entry<String, String> data2 : EmpAirlineSales.data.entrySet()) {
					//연령대+인원수 라벨(상단에 위치)
					countLbl = new JLabel(data2.getKey()+"대 ▶ " + data2.getValue()+"명");
					countLbl.setForeground(col[i]);
					countLbl.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					countLbl.setHorizontalAlignment(JLabel.CENTER);
					pane1.add(countLbl);
					
					int count = Integer.parseInt(data2.getValue());
					
					//항목별 비율 구하기
					Double rate = (count*100) / (double)sum;
					per[i] = (int)Math.round(rate);	//비율(%) 반올림
					
					angle[i] = toAngle((double)per[i] / 100);	//비율에 해당하는 각도 구하기(파이차트에서 차지하는 부분)
					
					//비율 라벨(상단에 위치)
					perLbl = new JLabel(String.valueOf(per[i])+"%");
					perLbl.setFont(new Font("맑은 고딕", Font.BOLD, 14));
					perLbl.setForeground(col[i]);
					perLbl.setHorizontalAlignment(JLabel.CENTER);
					pane2.add(perLbl);
					
					pane.add(pane1); pane.add(pane2);
					add(pane);
					
					i++;
			}
			centerPane.repaint();	//차트 그리기
		}
	}
	
	//비율에 해당하는 각도 구하기
	public int toAngle(double per) {
		System.out.println((int)Math.round(360 * per));
		return (int)Math.round(360 * per);
	}


}
