import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class EmpFlightAdd  extends JPanel{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel titleLbl = new JLabel("항 공 편  등 록");
	JButton calBtn = new JButton(new ImageIcon("img/3.png"));
	
	JLabel flightNameLbl = new JLabel("항공편명");
	JTextField flightnameTf = new JTextField(30);
	JLabel depLbl = new JLabel("출발지");
	JTextField depTf = new JTextField(30);
	JLabel desLbl = new JLabel("도착지");
	JTextField desTf = new JTextField(30);
	JLabel depTimeLbl = new JLabel("출발시간");
	JTextField depTimeTf= new JTextField(30);
	JLabel arrTimeLbl = new JLabel("도착시간");
	JTextField arrTimeTf = new JTextField(30);
	JLabel seatLbl = new JLabel("좌석수");
	JTextField seatTf = new JTextField(30);
	
	JLabel foodLbl = new JLabel("기내식");
		JComboBox<String> korFoodBox;
		String[] korFoodStr = {"비빔밥", "떡갈비", "불고기"};
		
		JComboBox<String> chineseFoodBox;
		String[] chineseFoodStr = {"베이징면","사천볶음밥", "광저우생선"};
		
		JComboBox<String> westFoodBox;
		String[] westFoodStr = {"파스타", "찹스테이크", "토마토스튜"};
	
	
	
	
	JLabel empLbl = new JLabel("사원");
	JComboBox<String> emp1;
	String[] pilot1 = {"최다니엘", "놀란", "피터", "알렉스", "루니"};
	JComboBox<String> emp2;
	String[] pilot2 = {"스미스", "김씨", "이씨", "박씨", "유씨"};
	JComboBox<String> emp3;
	String[] crew = {"나씨", "놀란", "피터", "알렉스", "루니"};	
	
	JButton saveBtn = new JButton("저장");
	JButton cancelBtn = new JButton("취소");
	
	public EmpFlightAdd() {
		setLayout(null);
		this.setBackground(Color.white);
		int x = 300;
		int x1 = 420;
		
		add(calBtn).setBounds(300, 90, 50, 50);
			calBtn.setBorderPainted(false);
			calBtn.setContentAreaFilled(false);
			calBtn.setFocusPainted(false);
		add(titleLbl).setBounds(420,100, 250, 30);
			titleLbl.setFont(titleFnt);
		add(flightNameLbl).setBounds(x, 200, 100,30); add(flightnameTf).setBounds(x1, 200, 250, 30);
			flightNameLbl.setFont(fnt);
			flightnameTf.setFont(fnt);
		add(depLbl).setBounds(x, 250, 100,30); add(depTf).setBounds(x1, 250, 250,30);
			depLbl.setFont(fnt);
			depTf.setFont(fnt);
		add(desLbl).setBounds(x, 300, 100,30); add(desTf).setBounds(x1, 300, 250,30);
			desLbl.setFont(fnt);
			desTf.setFont(fnt);
		add(depTimeLbl).setBounds(x, 350, 100,30); add(depTimeTf).setBounds(x1, 350, 250,30);
			depTimeLbl.setFont(fnt);
			depTimeTf.setFont(fnt);
		add(arrTimeLbl).setBounds(x, 400, 100,30); add(arrTimeTf).setBounds(x1, 400, 250,30);
			arrTimeLbl.setFont(fnt);
			arrTimeTf.setFont(fnt);
		add(seatLbl).setBounds(x, 450, 100,30); add(seatTf).setBounds(x1, 450, 250,30);
			seatLbl.setFont(fnt);
			seatTf.setFont(fnt);
		add(foodLbl).setBounds(x, 500, 100,30); 
		
		foodLbl.setFont(fnt);
		korFoodBox = new JComboBox<String>(korFoodStr); korFoodBox.setBackground(Color.white);korFoodBox.setFont(fnt);
		add(korFoodBox).setBounds(x1, 500, 100,30);
		
		chineseFoodBox = new JComboBox<String>(chineseFoodStr); chineseFoodBox.setBackground(Color.white);chineseFoodBox.setFont(fnt);
		add(chineseFoodBox).setBounds(530,500, 100,30);
		
		westFoodBox = new JComboBox<String>(westFoodStr); westFoodBox.setBackground(Color.white); westFoodBox.setFont(fnt);
		add(westFoodBox).setBounds(640,500, 100,30);
		
		add(empLbl).setBounds(x, 550, 100,30); 
		emp1 = new JComboBox<String>(pilot1); emp1.setBackground(Color.white);
		add(emp1).setBounds(x1, 550, 100,30);
			empLbl.setFont(fnt);
			emp1.setFont(fnt);
		emp2 = new JComboBox<String>(pilot2); emp2.setBackground(Color.white);
		add(emp2).setBounds(530, 550, 100,30);
			emp2.setFont(fnt);
		emp3 = new JComboBox<String>(crew); emp3.setBackground(Color.white);
		add(emp3).setBounds(640, 550, 100,30);
				emp3.setFont(fnt);

		
		add(saveBtn).setBounds(370,620,100,30); add(cancelBtn).setBounds(520,620,100,30);	
		saveBtn.setFont(fnt);
		saveBtn.setForeground(Color.white);
		saveBtn.setBackground(new Color(255,128,128));
		saveBtn.setBorder(new LineBorder(Color.white, 1, true));
		
		cancelBtn.setFont(fnt);
		cancelBtn.setForeground(Color.white);
		cancelBtn.setBackground(new Color(255,128,128));
		cancelBtn.setBorder(new LineBorder(Color.white, 1, true));
		
		setSize(1000, 800);
		setVisible(true);
	}


}
