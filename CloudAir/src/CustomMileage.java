
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomMileage extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	JPanel main = new JPanel();
	JPanel centerMain = new JPanel();
	JPanel wrapPane = new JPanel();
		JPanel northPane = new JPanel();
			JPanel northN = new JPanel();
				JLabel memberName = new JLabel("@@@님");
				JLabel memberNum = new JLabel("회원번호 006587");
			JLabel southLbl = new JLabel("Silver 회원");
		JPanel centerPane = new JPanel();
			Object head[] = {"출발지","도착지","출발일","도착일","비행편","운임","마일리지","총 마일리지"};
				String test[][] = {
						{"서울","호놀룰루","20210202","20210228","BCP0836","190000","190","3800"}
				};
				DefaultTableModel model = new DefaultTableModel(test,head);
				JTable tbl = new JTable(model);
				JScrollPane sp = new JScrollPane(tbl);
			JPanel btnPane	= new JPanel();
				JButton returnBtn = new JButton("돌아가기");
		
		CustomPlan plan = new CustomPlan();
	public CustomMileage() {
		setLayout(new BorderLayout());
		add("Center",main);
		main.add(centerMain);
		main.setBackground(Color.white);
			centerMain.setLayout(new BorderLayout(150,50));
			centerMain.setBackground(Color.white);
			centerMain.add("North",new JLabel());
			centerMain.add("East",new JLabel());
			centerMain.add("West",new JLabel());
			centerMain.add("South",new JLabel());
			centerMain.add("Center",wrapPane);
				wrapPane.setLayout(new BorderLayout(0,10));
				wrapPane.setBackground(Color.white);
				wrapPane.setBackground(Color.white);
				wrapPane.add("North",northPane);
					northPane.setBackground(Color.white);
					northPane.setLayout(new BorderLayout(0,10));
					northPane.add("North",northN);
						northN.setLayout(new BorderLayout());
						northN.add("Center",memberName);
						memberName.setHorizontalAlignment(JLabel.CENTER);
						memberName.setFont(fnt);
						memberName.setOpaque(true);
						memberName.setBackground(Color.white);
						northN.add("South",memberNum);
						memberNum.setHorizontalAlignment(JLabel.CENTER);
						memberNum.setFont(fnt);
						memberNum.setOpaque(true);
						memberNum.setBackground(Color.white);
					northPane.add("South",southLbl);
						southLbl.setHorizontalAlignment(JLabel.CENTER);
						southLbl.setFont(fnt);
						southLbl.setOpaque(true);
						southLbl.setBackground(Color.white);
				
				wrapPane.add("Center",centerPane);
				wrapPane.setBackground(Color.white);
					centerPane.setLayout(new BorderLayout(0,30));
					centerPane.setBackground(Color.white);
					centerPane.add("Center",sp);
						tbl.getParent().setBackground(Color.white);
					centerPane.add("South",btnPane);
						btnPane.setBackground(Color.white);
						btnPane.add(returnBtn);
						returnBtn.setFont(fnt);
						returnBtn.setBackground(new Color(0,128,255));
						returnBtn.setForeground(Color.white);
		
		setBackground(Color.white);	
		setVisible(true);
		
		returnBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("돌아가기")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			}
		} 
	}

}
