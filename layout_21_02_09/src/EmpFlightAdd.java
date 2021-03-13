import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbAll.AirlineSignUpVO;
import dbAll.EmpFlightAddDAO;
import dbAll.EmpFlightAddVO;

public class EmpFlightAdd  extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel titleLbl = new JLabel("항 공 편  등 록");
	JButton calBtn = new JButton(new ImageIcon("img/calendar.png"));
	
	JLabel regNoLbl = new JLabel("등록번호");
	JComboBox<String> regNoBox;
	
	
	JLabel flightnameLbl = new JLabel("항공편명");
	JTextField flightnameTf = new JTextField(30);
	JButton flightNameDuplicatBtn = new JButton("중복확인");
	
	JLabel depLbl = new JLabel("출발지");
	JComboBox<String> depBox;
	
	JLabel desLbl = new JLabel("도착지");
	JComboBox<String> desBox;
	JButton depDesCheckBtn = new JButton("노선확인");
	
	JLabel depTimeLbl = new JLabel("출발시간");
	JTextField depTimeTf = new JTextField(30);
	JLabel arrTimeLbl = new JLabel("도착시간");
	JTextField arrTimeTf = new JTextField(30);
	
	JLabel fareLbl = new JLabel("항공운임");
	JTextField fareTf = new JTextField(30);
	
	
//	JLabel foodLbl = new JLabel("기내식");
//		JComboBox<String> korFoodBox;
//		String[] korFoodStr = {"비빔밥", "떡갈비", "불고기"};
//		
//		JComboBox<String> chineseFoodBox;
//		String[] chineseFoodStr = {"베이징면","사천볶음밥", "광저우생선"};
//		
//		JComboBox<String> westFoodBox;
//		String[] westFoodStr = {"파스타", "찹스테이크", "토마토스튜"};
//	
//	JLabel empLbl = new JLabel("사원");
//	JComboBox<String> emp1;
//	String[] pilot1 = {"최다니엘", "놀란", "피터", "알렉스", "루니"};
//	JComboBox<String> emp2;
//	String[] pilot2 = {"스미스", "김씨", "이씨", "박씨", "유씨"};
//	JComboBox<String> emp3;
//	String[] crew = {"나씨", "놀란", "피터", "알렉스", "루니"};	

	
	JButton saveBtn = new JButton("저장");
	JButton cancelBtn = new JButton("취소");
	
	//이벤트용 변수
	ArrayList<String> regNoArray = new ArrayList<String>();//등록번호 콤보박스에 들어갈 배열
	ArrayList<String> depdesArray = new ArrayList<String>();//등록번호 콤보박스에 들어갈 배열
	
	String flightName;//항공편명 중복확인
	EmpFlightAddDAO dao = new EmpFlightAddDAO();
	
	String dep = "";//콤보박스에서 선택된 출발지
	String des = "";//콤보박스에서 선택된 도착지
	
	public EmpFlightAdd() {
		setLayout(null);
		this.setBackground(Color.white);
		int x = 300;
		int x1 = 420;
		setRegNo();//등록번호 콤보박스에 모든 등록번호 넣어주는 메소드
		setDepDes();//모든 출발지, 도착지 콤보박스에 세팅
		
		
//		add(calBtn).setBounds(300, 90, 50, 50);
//			calBtn.setBorderPainted(false);
//			calBtn.setContentAreaFilled(false);
//			calBtn.setFocusPainted(false);
		add(titleLbl).setBounds(400,70, 250, 30);
			titleLbl.setFont(titleFnt);
		add(regNoLbl).setBounds(x, 180, 100,30); 
		regNoLbl.setFont(fnt);
		regNoBox = new JComboBox<String>(regNoArray.toArray(new String[regNoArray.size()]));
		add(regNoBox).setBounds(x1, 180, 250, 30);
			regNoBox.setFont(fnt); regNoBox.setBackground(Color.white);
		add(flightnameLbl).setBounds(x, 230, 100,30); add(flightnameTf).setBounds(x1, 230, 150,30); add(flightNameDuplicatBtn).setBounds(600, 230, 70,30);
			flightnameLbl.setFont(fnt);
			flightnameTf.setFont(fnt);
			flightNameDuplicatBtn.setFont(fnt);
			flightNameDuplicatBtn.setForeground(Color.white);
			flightNameDuplicatBtn.setBackground(new Color(255,128,128));
			flightNameDuplicatBtn.setBorder(new LineBorder(Color.white, 1, true));
			
		add(depLbl).setBounds(x, 280, 100,30);
			depLbl.setFont(fnt);
		depBox = new JComboBox<String>(depdesArray.toArray(new String[depdesArray.size()]));	
		add(depBox).setBounds(x1, 280, 150,30);
			depBox.setFont(fnt); depBox.setBackground(Color.white);
		add(desLbl).setBounds(x, 330, 100,30); 
			desLbl.setFont(fnt); 
		desBox = new JComboBox<String>(depdesArray.toArray(new String[depdesArray.size()]));	
		add(desBox).setBounds(x1, 330, 150,30);
			desBox.setFont(fnt); desBox.setBackground(Color.white);
		add(depDesCheckBtn).setBounds(600, 330, 70,30);
			depDesCheckBtn.setFont(fnt);
			depDesCheckBtn.setForeground(Color.white);
			depDesCheckBtn.setBackground(new Color(255,128,128));
			depDesCheckBtn.setBorder(new LineBorder(Color.white, 1, true));
		add(depTimeLbl).setBounds(x, 380, 100,30); add(depTimeTf).setBounds(x1, 380, 250,30);
			depTimeLbl.setFont(fnt);
			depTimeTf.setFont(fnt);
		add(arrTimeLbl).setBounds(x, 430, 100,30); add(arrTimeTf).setBounds(x1, 430, 250,30);
			arrTimeLbl.setFont(fnt);
			arrTimeTf.setFont(fnt);
		
		add(fareLbl).setBounds(x,480, 100,30); fareLbl.setFont(fnt);
		add(fareTf).setBounds(x1,480, 250,30); fareTf.setFont(fnt); fareTf.setBackground(Color.white);
//		add(foodLbl).setBounds(x, 480, 100,30); 
//		foodLbl.setFont(fnt);
//		korFoodBox = new JComboBox<String>(korFoodStr); korFoodBox.setBackground(Color.white);korFoodBox.setFont(fnt);
//		add(korFoodBox).setBounds(x1, 480, 100,30);
//		
//		chineseFoodBox = new JComboBox<String>(chineseFoodStr); chineseFoodBox.setBackground(Color.white);chineseFoodBox.setFont(fnt);
//		add(chineseFoodBox).setBounds(530,480, 100,30);
//		
//		westFoodBox = new JComboBox<String>(westFoodStr); westFoodBox.setBackground(Color.white); westFoodBox.setFont(fnt);
//		add(westFoodBox).setBounds(640,480, 100,30);
//		
//		add(empLbl).setBounds(x, 530, 100,30); 
//		emp1 = new JComboBox<String>(pilot1); emp1.setBackground(Color.white);
//		add(emp1).setBounds(x1, 530, 100,30);
//			empLbl.setFont(fnt);
//			emp1.setFont(fnt);
//		emp2 = new JComboBox<String>(pilot2); emp2.setBackground(Color.white);
//		add(emp2).setBounds(530, 530, 100,30);
//			emp2.setFont(fnt);
//		emp3 = new JComboBox<String>(crew); emp3.setBackground(Color.white);
//		add(emp3).setBounds(640, 530, 100,30);
//				emp3.setFont(fnt);

		
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
		
				
		//이벤트 등록
		flightNameDuplicatBtn.addActionListener(this);
		depDesCheckBtn.addActionListener(this);
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
	}
	public void createNewFlight() {
		EmpFlightAddVO vo = new EmpFlightAddVO((String)regNoBox.getSelectedItem(), flightnameTf.getText(),
				(String)depBox.getSelectedItem(), (String)desBox.getSelectedItem(), depTimeTf.getText(), 
				arrTimeTf.getText(), fareTf.getText());
		if(vo.getRegNo().equals("")||vo.getFlightno().equals("")||
				vo.getDep().equals("")||vo.getDes().equals("")||
				vo.getDepTime().equals("")||vo.getDesTime().equals("")||
				vo.getFare().equals("")) {
			JOptionPane.showMessageDialog(this, "모든 항목을 입력해야합니다.");
		}else {
			EmpFlightAddDAO dao = new EmpFlightAddDAO();
			int result = dao.flightInsert(vo);
			System.out.println(result);
			if(result>0) {//항공편 등록 성공
				JOptionPane.showMessageDialog(this, "항공편이 등록되었습니다.");
				setFormClear();
			}else {//실패
				JOptionPane.showMessageDialog(this, "항공편 등록이 실패하였습니다");
			}
		}
		
		
	}
	public void checkDuplicateDesDep() {//출발지 목적지 중복인지 확인
		dep = (String)depBox.getSelectedItem();
		System.out.println("출발지->"+dep);
		des = (String)desBox.getSelectedItem();
		System.out.println("도착지"+des);
		List<EmpFlightAddVO> result = dao.getDepDesCheck(dep, des);
		if (dep.equals(des)){
			JOptionPane.showMessageDialog(this, "출발지와 도착지는 같을 수 없습니다");
		}else if(result.size()==0) {
			JOptionPane.showMessageDialog(this, "등록가능한 노선 입니다");
			depBox.setEnabled(false);
			desBox.setEnabled(false);
		} else {
			JOptionPane.showMessageDialog(this, "이미 등록되어 있는 노선 입니다");
		}
	}
	public void setDepDes() {//콤보박스에 출발지 세팅
		String check = "dep";
		EmpFlightAddDAO dao = new EmpFlightAddDAO();
		List<EmpFlightAddVO> lst = dao.getDepDes(check);
		
		for(int i=0; i<lst.size();i++) {
			EmpFlightAddVO vo = lst.get(i);
			Object data = vo.getDep();
			depdesArray.add((String) data);
		}
	}
	public void setRegNo() {//콤보박스에 등록번호 세팅
		EmpFlightAddDAO dao = new EmpFlightAddDAO();
		List<EmpFlightAddVO> lst = dao.getRegNo();
		
		for(int i=0; i<lst.size();i++) {
			EmpFlightAddVO vo = lst.get(i);
			Object data = vo.getRegNo();
			regNoArray.add((String) data);
		}
		
	}
	public int checkFlightNameMethod(String flightNum) {
		int check = 0;
	
		for(int i=0; i<flightName.length(); i++) {
			char c = flightName.charAt(i);
			if(c<48||c>57) {
				check = 1;
				break;
			}
		}
		return check;
	}
	public void setFormClear() {
		regNoBox.setSelectedIndex(0);
		flightnameTf.setText("");
		flightnameTf.setEditable(true);
		depBox.setEnabled(true);
		depBox.setSelectedIndex(0);
		desBox.setEnabled(true);
		desBox.setSelectedIndex(0);
		depTimeTf.setText("");
		arrTimeTf.setText("");
		fareTf.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btnName = e.getActionCommand();
			if(btnName.equals("중복확인")) {
				flightName = flightnameTf.getText();
				System.out.println(flightName.length());
				if(!(flightName.length()==3)) {
					JOptionPane.showMessageDialog(this, "숫자 3자리를 입력하세요.");
				}else if(checkFlightNameMethod(flightName)==1){
					JOptionPane.showMessageDialog(this, "문자를 포함하고 있습니다. \n 숫자 3자리를 입력해주세요.");
				}else {
					List<EmpFlightAddVO> result = dao.getFlightNameCheck(flightName);
					if(result.size()==0) {
						JOptionPane.showMessageDialog(this, "사용 가능한 항공편명 입니다");
						flightnameTf.setEditable(false);
					} else {
						JOptionPane.showMessageDialog(this, "등록되어 있는 항공편명 입니다");
					}
					System.out.println("아ㅣ러나ㅣㄹㄴ머런ㅁ");
				}
			}else if(btnName.equals("취소")) {
				setFormClear();
				this.setVisible(false);
				EmpMainFrame.plan.setVisible(true);
				EmpMainFrame.centerPane.add(EmpMainFrame.plan);
			}else if(btnName.equals("저장")) {
				createNewFlight();
			}else if(btnName.equals("노선확인")) {
				checkDuplicateDesDep();
			}
		}
	}
	
	
}

