import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EmpPieChart extends JFrame {
//	JPanel centerPane = new JPanel();
	CenterPane centerPane = new CenterPane();
	NorthPane northPane = new NorthPane();
	
	JLabel perLbl_20 = new JLabel();
	JLabel perLbl_30 = new JLabel();
	JLabel perLbl_40 = new JLabel();
	JLabel perLbl_50 = new JLabel();
	
	int angle_20;
	int angle_30;
	int angle_40;
	int angle_50;
	JTextField tf_20;
	JTextField tf_30;
	JTextField tf_40;
	JTextField tf_50;
	int per_20;
	int per_30;
	int per_40;
	int per_50;


	public EmpPieChart() {
		add(centerPane);
		add("North", northPane);
		
		setSize(800, 500);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}
	
	class CenterPane extends JPanel {
		JPanel pane = new JPanel();
		CenterPane() {}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int sumAngle = 0;
			g.setColor(new Color(236,91,90));
			g.fillArc(this.getWidth() / 2-150, 100, 300, 300, sumAngle, angle_20);
			sumAngle += angle_20;
			g.setColor(new Color(255,200,27));
			g.fillArc(this.getWidth() / 2-150, 100, 300, 300, sumAngle, angle_30);
			sumAngle += angle_30;
			g.setColor(new Color(91,103,119));
			g.fillArc(this.getWidth() / 2-150, 100, 300, 300, sumAngle, angle_40);
			sumAngle += angle_40;
			g.setColor(new Color(28,163,146));
			g.fillArc(this.getWidth() / 2-150, 100, 300, 300, sumAngle, angle_50);
			
			add("North", pane);
				pane.add(perLbl_20);
				pane.add(perLbl_30);
				pane.add(perLbl_40);
				pane.add(perLbl_50);
				perLbl_20.setForeground(new Color(236,91,90));
				perLbl_30.setForeground(new Color(255,200,27));
				perLbl_40.setForeground(new Color(91,103,119));
				perLbl_50.setForeground(new Color(28,163,146));
			
		}
	}
	
	class NorthPane extends JPanel {
		NorthPane() {
			setLayout(new FlowLayout());
			
			JLabel lbl1 = new JLabel("20대");
			JLabel lbl2 = new JLabel("30대");
			JLabel lbl3 = new JLabel("40대");
			JLabel lbl4 = new JLabel("50대");
			tf_20 = new JTextField(10);
			tf_30 = new JTextField(10);
			tf_40 = new JTextField(10);
			tf_50 = new JTextField(10);
			
			add(lbl1);		add(tf_20);
			add(lbl2);		add(tf_30);
			add(lbl3);		add(tf_40);
			add(lbl4);		add(tf_50);
			
			tf_20.addActionListener(new setPieChart());
			tf_30.addActionListener(new setPieChart());
			tf_40.addActionListener(new setPieChart());
			tf_50.addActionListener(new setPieChart());

		}
	}
	
	class setPieChart implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			int count_20 = Integer.parseInt(tf_20.getText());
			int count_30 = Integer.parseInt(tf_30.getText());
			int count_40 = Integer.parseInt(tf_40.getText());
			int count_50 = Integer.parseInt(tf_50.getText());
			
			int sum = count_20 + count_30 + count_40 + count_50;
			
			Double rate_20 = (count_20*100) / (double)sum;
			Double rate_30 = (count_30*100) / (double)sum;
			Double rate_40 = (count_40*100) / (double)sum;
			Double rate_50 = (count_50*100) / (double)sum;
			
			per_20 = (int)Math.round(rate_20);	//반올림
			per_30 = (int)Math.round(rate_30);
			per_40 = (int)Math.round(rate_40);	//반올림
			per_50 = (int)Math.round(rate_50);
			
			angle_20 = toAngle((double)per_20 / 100);
			angle_30 = toAngle((double)per_30 / 100);
			angle_40 = toAngle((double)per_40 / 100);
			angle_50 = toAngle((double)per_50 / 100);
			
			perLbl_20.setText("20대 " + String.valueOf(per_20) + "%");
			perLbl_30.setText("30대 " + String.valueOf(per_30) + "%");
			perLbl_40.setText("40대 " + String.valueOf(per_40) + "%");
			perLbl_50.setText("50대 " + String.valueOf(per_50) + "%");
			
			centerPane.repaint();			
		}
	}
	
	public int toAngle(double per) {
		System.out.println((int)Math.round(360 * per));
		return (int)Math.round(360 * per);
	}

	public static void main(String[] args) {
		new EmpPieChart();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
