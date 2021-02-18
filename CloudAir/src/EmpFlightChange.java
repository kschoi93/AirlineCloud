import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EmpFlightChange extends JPanel{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	
	ImageIcon icon = new ImageIcon("img/calendar.png");
	Image im = icon.getImage();
	Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	ImageIcon icon2 = new ImageIcon(im2);
	JButton calBtn = new JButton(icon2);
	
	JLabel bcpLbl = new JLabel("AC");
	JTextField tf1 = new JTextField(10);
	
	JLabel depLbl = new JLabel("출발지");
	JTextField tf2 = new JTextField(10);
	
	JLabel desLbl = new JLabel("도착지");
	JTextField tf3 = new JTextField(10);
	JButton searchBtn = new JButton("검색");
	
	////table
	String info[] = {"항공편명", "출발지", "출발시간", "도착지", "도착시간"};
	JTable table;
	JScrollPane sp;
	DefaultTableModel model;
	
	//
	JLabel foodLbl = new JLabel("기내식");
		JLabel korFoodLbl = new JLabel("한식");
			JComboBox<String> korFoodBox;
			String[] korFoodStr = {"비빔밥", "떡갈비", "불고기"};
		JLabel chineseFoodLbl = new JLabel("중식");
			JComboBox<String> chineseFoodBox;
			String[] chineseFoodStr = {"베이징면","사천볶음밥", "광저우생선"};
		JLabel westFoodLbl = new JLabel("양식");
			JComboBox<String> westFoodBox;
			String[] westFoodStr = {"파스타", "찹스테이크", "토마토스튜"};
	
	JLabel delayLbl = new JLabel("지연");
	JCheckBox delayCheckBox = new JCheckBox("", false);
	JLabel depTimeLbl = new JLabel("출발시간");
	JTextField depTf = new JTextField(10);
	JLabel arrTimeLbl = new JLabel("~   도착시간");
	JTextField arrTf = new JTextField(10);
	JLabel cancelLbl = new JLabel("결항");
	JComboBox<String> cancelState;
	String[] state = {"기상악화", "기장실종", "기타사유"};
	JCheckBox cancelBox = new JCheckBox("", false);
	
	JButton btn2 = new JButton("설정완료");
	public EmpFlightChange() {
		setLayout(null);
		this.setBackground(Color.white);
		
		add(calBtn).setBounds(200,100, 35,35);
			calBtn.setBorderPainted(false);
			calBtn.setContentAreaFilled(false);
			calBtn.setFocusPainted(false);
		add(bcpLbl).setBounds(260, 100, 80, 35);
			bcpLbl.setFont(fnt);
		add(tf1).setBounds(285, 105, 100, 25);
			tf1.setFont(fnt);
		add(depLbl).setBounds(400, 100, 50, 35);
			depLbl.setFont(fnt);
		add(tf2).setBounds(445, 105, 100, 25);
			tf2.setFont(fnt);
		add(desLbl).setBounds(560, 100, 50, 35);
			desLbl.setFont(fnt);
		add(tf3).setBounds(605, 105, 100, 25);
			tf3.setFont(fnt);
		add(searchBtn).setBounds(720, 101, 70, 30);
			searchBtn.setFont(fnt);
			searchBtn.setForeground(Color.white);
			searchBtn.setBackground(new Color(255,128,128));
			searchBtn.setBorder(new LineBorder(Color.white, 1, true));
		
		//table
		model = new DefaultTableModel(info,0);
		table = new JTable(model);
		sp  = new JScrollPane(table);
		add(sp).setBounds(200, 165, 600, 300);
			sp.getViewport().setBackground(Color.white);
		
		//기내식
		add(foodLbl).setBounds(200, 480, 60,35);
			foodLbl.setFont(fnt);
		//한식
		add(korFoodLbl).setBounds(280, 480,60,35); korFoodLbl.setFont(fnt);
		korFoodBox = new JComboBox<String> (korFoodStr);
		add(korFoodBox).setBounds(320, 483, 100, 25);korFoodBox.setFont(fnt);korFoodBox.setBackground(Color.white);
			//cb1.setAlignmentX(CENTER_ALIGNMENT);
		//중식
		add(chineseFoodLbl).setBounds(440,480, 60,35); chineseFoodLbl.setFont(fnt);
		chineseFoodBox = new JComboBox<String>(chineseFoodStr);
		add(chineseFoodBox).setBounds(480,483, 100, 25); chineseFoodBox.setFont(fnt);chineseFoodBox.setBackground(Color.white);
		//양식
		add(westFoodLbl).setBounds(600,480, 60,35); westFoodLbl.setFont(fnt);
		westFoodBox = new JComboBox<String>(westFoodStr);
		add(westFoodBox).setBounds(640,483, 100,25); westFoodBox.setFont(fnt);westFoodBox.setBackground(Color.white);
		
		//지연 상태
		add(delayLbl).setBounds(200, 515, 50, 35);
			delayLbl.setFont(fnt);
		add(delayCheckBox).setBounds(280, 524, 17, 17);
			delayCheckBox.setBackground(Color.white);
		add(depTimeLbl).setBounds(320,517, 60, 35);
			depTimeLbl.setFont(fnt);
		add(depTf).setBounds(400, 521, 80, 25);
			depTf.setFont(fnt);
		add(arrTimeLbl).setBounds(500, 516, 100, 35);
			arrTimeLbl.setFont(fnt);
		add(arrTf).setBounds(610, 521, 80, 25);
			arrTf.setFont(fnt);
			
		//결항 상태
		add(cancelLbl).setBounds(200, 550, 50, 35);
			cancelLbl.setFont(fnt);
		add(cancelBox).setBounds(280, 559, 17, 17);
		cancelBox.setBackground(Color.white);
		cancelState = new JComboBox<String>(state);
		add(cancelState).setBounds(320,555, 150, 25);
			cancelState.setFont(fnt);
			cancelState.setBackground(Color.white);
			
		add(btn2).setBounds(440, 600, 100,30);
			btn2.setFont(fnt);
			btn2.setForeground(Color.white);
			btn2.setBackground(new Color(255,128,128));
			btn2.setBorder(new LineBorder(Color.white, 1, true));
		
		setSize(1000, 800);
		setVisible(true);
	}

	

}
