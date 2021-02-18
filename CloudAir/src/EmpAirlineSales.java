import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
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


//  수정

public class EmpAirlineSales extends JPanel implements ActionListener{
	
	// 폰트
	Font fnt = new Font("굴림체",Font.BOLD,24);//매출관리
	Font fnt2 = new Font("굴림체",Font.BOLD,14); // 여남 버튼 	




	//중간 매츌관리 부분 		
	JPanel centerPane = new JPanel(new BorderLayout());
				JLabel salesLbl= new JLabel("매출 관리");
	JPanel tablePane = new JPanel(new BorderLayout());			
			JPanel buttonPane = new JPanel(new GridLayout(1,0));
				String listLbl [] = {"출발일", "도착일", "항공편", "출발지","도착지","비용","매출","영업이익","증감률"};
		
			JTable table;
			JScrollPane sp;
			DefaultTableModel sales;
		
		JPanel searchPane = new JPanel(new GridLayout(0,1));


			JPanel datePane = new JPanel();
				JTextField tfTest [] = {new JTextField(30), new JTextField(30)};
			
				JLabel startDateLbl = new JLabel(" 시 작 일 ");
					JTextField tf = new JTextField(30);
				ImageIcon icon = new ImageIcon("img/calendar.png");
					Image cal = icon.getImage();
					Image cal2 = cal.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
				     ImageIcon icon2 = new ImageIcon(cal2);
				     JLabel startCalendar = new JLabel(icon2);
					
				JLabel endDateLbl = new JLabel(" 종 료 일 ");
					JTextField tf2 = new JTextField(30);
					Image cal4 = icon.getImage();
					Image cal3 = cal2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
				     ImageIcon icon3 = new ImageIcon(cal2);
				     JLabel endCalendar = new JLabel(icon2);	

				JLabel startLbl = new JLabel(" 출 발 지 ");
					JComboBox<String> cb1;
					String[] startName = {"ICN","GMP", "CJU","SIN","YYZ"};

				JLabel endLbl = new JLabel(" 목 적 지 ");
					JComboBox<String> cb2;
					String[] endName = {"VVO","ICN","GMP", "CJU","SIN","YYZ"};
		
				JLabel ageLbl = new JLabel("고객연령대");
					JComboBox<Integer> cb3;
					Integer [] age = {10,20,30,40,50,60};
					JComboBox<Integer> cb4;
					Integer [] age2 = {10,20,30,40,50,60};
		
				JLabel genderLbl = new JLabel(" 성    별 ");
					JCheckBox  female = new JCheckBox (" 여 ");
					JCheckBox  male = new JCheckBox (" 남 ");
			


		// 매출내역 조회버튼	
		JPanel SouthPane = new JPanel(new BorderLayout()); 
			JPanel btnPane = new JPanel();		
				JButton enterBtn = new JButton("조회");
				JLabel empty = new JLabel("    ");
					JButton resetBtn = new JButton("초기화");
				JLabel empty2 = new JLabel("    ");
					JButton allBtn = new JButton("전체보기");
			

	public EmpAirlineSales() {
		
		setLayout(new BorderLayout());
		
		add("North",centerPane);
			centerPane.setLayout(null);
			centerPane.setPreferredSize(new Dimension(1000,100));
		
			centerPane.add(salesLbl).setBounds(430,-80,750,300);
			salesLbl.setFont(fnt);
			centerPane.setBackground(Color.white);
			
			
			
		add("Center",tablePane);
			tablePane.setLayout(null);
			
			int x=250;
			int y= 340;
	
			
			sales = new DefaultTableModel(listLbl,0);
			table = new JTable(sales);
			sp = new JScrollPane(table);
			table.getParent().setBackground(Color.white);
			
			tablePane.add(sp).setBounds(116,0,750,300);
			
			
			tablePane.setBackground(Color.white);


			tablePane.add(startDateLbl).setBounds(x, 320 ,100,30);	startDateLbl.setFont(fnt2); // 시작일
//			tablePane.add(tf).setBounds(y, 320 ,100,30);	startDateLbl.setFont(fnt2); // 시작일 텍필
			tablePane.add(tfTest[0]).setBounds(y, 320 ,100,30);	startDateLbl.setFont(fnt2); // 테스트\
			tablePane.add(startCalendar).setBounds(450,320,30,30);
			
			

			
			tablePane.add(endDateLbl).setBounds(x+250, 320 ,100,30);	endDateLbl.setFont(fnt2); // 종료일
//			tablePane.add(tf2).setBounds(y+250, 320 ,100,30);	endDateLbl.setFont(fnt2); // 종료일 텍필
			tablePane.add(tfTest[1]).setBounds(y+250, 320 ,100,30);	startDateLbl.setFont(fnt2); // 테스트
			tablePane.add(endCalendar).setBounds(450+250,320,30,30);
			
			
			tablePane.add(startLbl).setBounds(x, 360 ,100,30);	startLbl.setFont(fnt2); // 출발지
			cb1 = new JComboBox<String>(startName); cb1.setBackground(Color.white);
			tablePane.add(cb1).setBounds(y, 360 ,200,30);cb1.setFont(fnt2); // 출발지 콤보박스
			
			tablePane.add(endLbl).setBounds(x, 400 ,100,30);endLbl.setFont(fnt2); // 목적지
			cb2 = new JComboBox<String>(endName);cb2.setBackground(Color.white);
			tablePane.add(cb2).setBounds(y, 400 ,200,30); cb2.setFont(fnt2);// 목적지 콤보박스
			
			tablePane.add(ageLbl).setBounds(x, 440 ,100,30); ageLbl.setFont(fnt2); // 고객연령대
			cb3 = new JComboBox<Integer>(age); cb3.setBackground(Color.white);
			tablePane.add(cb3).setBounds(y, 440 ,150,30); cb3.setFont(fnt2);// 고객연령대 콤보
			cb4 = new JComboBox<Integer>(age2); cb4.setBackground(Color.white);
			tablePane.add(cb4).setBounds(y+150+20, 440 ,150,30); cb4.setFont(fnt2);//고객연령대 콤보
			cb4.setSelectedIndex(5);

			

			
			tablePane.add(genderLbl).setBounds(x, 480 ,100,30);	genderLbl.setFont(fnt2); // 성별
			tablePane.add(female).setBounds(y,480,100,30); female.setBackground(Color.white); female.setFont(fnt2); // 여  체크박스
			tablePane.add(male).setBounds(y+100,480,100,30); male.setBackground(Color.white); male.setFont(fnt2);// 남  체크박스
			
			ButtonGroup bg = new ButtonGroup(); // 체크박스 여러개 중 하나만 선택되도록 설정
			bg.add(female); bg.add(male); 
			
	
					
		add("South",SouthPane);
			SouthPane.add(btnPane);
				btnPane.add(enterBtn);
					enterBtn.setFont(fnt2);
					enterBtn.setBackground(new Color(255,128,128));
					enterBtn.setForeground(Color.white);
//					enterBtn.setBorder(new LineBorder(Color.white, 1, true));
				btnPane.add(empty);
				btnPane.add(resetBtn);
					resetBtn.setFont(fnt2);
					resetBtn.setBackground(new Color(255,128,128));
					resetBtn.setForeground(Color.white);
//					resetBtn.setBorder(new LineBorder(Color.white, 1, true));
				btnPane.add(empty2);
				btnPane.add(allBtn);
					allBtn.setFont(fnt2);
					allBtn.setBackground(new Color(255,128,128));
					allBtn.setForeground(Color.white);
//					allBtn.setBorder(new LineBorder(Color.white, 1, true));
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			
				
				btnPane.setBackground(Color.white);	
//				enterBtn.setPreferredSize(new Dimension(100,40));	
//				resetBtn.setPreferredSize(new Dimension(100,40));	
//				allBtn.setPreferredSize(new Dimension(120,40));	
				
		setBackground(Color.white);	
		setSize(1000,800);
		setVisible(true);
		
		enterBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		allBtn.addActionListener(this);
		

	}
	/////////////////////////////////////////////레이아웃//////////////////////////////////////////////
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = e.getActionCommand();
			if(btn.equals("조회")) {	// 검색
//				getEnter();
			} else if(btn.equals("초기화")) { // 다지우기
				setFormClear();
			}	else if(btn.equals("전체보기")) {	// 모두검색
//				getMemberAll();
		}
		
	}

	}
	
	public void setFormClear() {	
		for (int i = 0 ; i <tfTest.length ; i++) {
			tfTest[i].setText("");
		}
	}
	
	
//	public void getMemberAll() {
//		//데이터 베이스의 모든 회원을 선택해서 JTabel 에 표시한다
//		
//	}
//	
//	public void setNewTableList(List<MemberVO> lst) {
//		 //JTable의 모든 레코드 지우기
//	
//		
//	}
}
