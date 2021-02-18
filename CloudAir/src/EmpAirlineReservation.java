import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EmpAirlineReservation extends JPanel implements ActionListener{

	
	Font fnt = new Font("굴림체",Font.BOLD,32);// 탑승자 정보
	Font fnt2 = new Font("굴림체",Font.BOLD,24); //  	
	Font fnt3 = new Font("굴림체",Font.BOLD,14); //기본폰트 

//	JPanel NorthPane = new JPanel(new BorderLayout()); 		//메뉴들 
//		JPanel mainPane = new JPanel(new GridLayout(1,0));
//			String mainLbl [] = { "매출관리", "예약관리", "항공편관리","회원관리","사원정보관리","로그아웃"};
	
	JPanel centerPane = new JPanel();
		JLabel reservationLbl = new JLabel("예약번호");
			JTextField reservationtf = new JTextField(10);
			JButton btn = new JButton("검색");
		JLabel startLbl = new JLabel("출 발 지");
			JTextField starttf = new JTextField(10);
			JButton btn1 = new JButton("검색");
		JLabel airLbl = new JLabel("항 공 편 ");
			JTextField airtf = new JTextField(10);
			JButton btn2 = new JButton("검색");
		JLabel dateLbl = new JLabel("출발날짜");
			JTextField datetf = new JTextField(10);
//			ImageIcon icon = new ImageIcon("img/2.png");
			ImageIcon icon = new ImageIcon("img/calendar.png");
				Image cal = icon.getImage();
				Image cal2 = cal.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			     ImageIcon icon2 = new ImageIcon(cal2);
			     JLabel startCalendar = new JLabel(icon2);
			     
			     
		 JTextField tf [] = {new JTextField(10), new JTextField(10),new JTextField(10),new JTextField(10)};	     
			     

	JPanel tablePane = new JPanel(new BorderLayout());	
//		JPanel buttonPane = new JPanel(new GridLayout(1,0));
		JLabel passengerlbl = new JLabel("탑승자 정보");
		JTable table;
			String listLbl [] = {"예약번호", "성명 (한)", "생년월일", "연락처","출발지","항공편"};
			Object test[][] = {
					{"DEKL8","홍길동","00/11/11","010-0000-0000","ICN","AC832"},
					{"2DWR7","이순신","06/08/31","010-7777-7777","ICN","AC614"},
					{"D2W5G","세종대왕","92/05/20","010-8888-8888","ICN","AC682"},
					{"21RES","장영실","80/10/31","010-3333-3333","ICN","AC516"},		
					
			};     	

			JScrollPane sp;
			DefaultTableModel sales;	
				
				
				
				
	JPanel SouthPane = new JPanel(new BorderLayout()); 
		JPanel btnPane = new JPanel();		
			JButton modibtn = new JButton("수정");
			JLabel empty = new JLabel("      ");
			JButton resetBtn = new JButton("초기화");
			JLabel empty2 = new JLabel("      ");
			JButton delBtn = new JButton("삭제");
		

	JPanel mainSouthPane = new JPanel();
		
	
	
	public EmpAirlineReservation() {

		setLayout(new BorderLayout());
		
		add("North",centerPane);
			centerPane.setLayout(null);
			centerPane.setPreferredSize(new Dimension(1000,250));
			int x=320;
			int x1=400;
			
			centerPane.add(reservationLbl).setBounds(x,50,70,30);
				reservationLbl.setFont(fnt3);
//			centerPane.add(reservationtf).setBounds(x1,50,150,30); 
			centerPane.add(tf[0]).setBounds(x1,50,150,30); 
			centerPane.add(btn).setBounds(570,50,70,30);
				btn.setFont(fnt3);
				btn.setForeground(Color.white);
				btn.setBackground(new Color(255,128,128));
				btn.setBorder(new LineBorder(Color.white, 1, true));

			centerPane.add(startLbl).setBounds(x,100,70,30);
				startLbl.setFont(fnt3);
//			centerPane.add(starttf).setBounds(x1,100,150,30); 
			centerPane.add(tf[1]).setBounds(x1,100,150,30); 
			centerPane.add(btn1).setBounds(570,100,70,30);
				btn1.setFont(fnt3);
				btn1.setForeground(Color.white);
				btn1.setBackground(new Color(255,128,128));
				btn1.setBorder(new LineBorder(Color.white, 1, true));

			centerPane.add(airLbl).setBounds(x,150,70,30);
				airLbl.setFont(fnt3);
//			centerPane.add(airtf).setBounds(x1,150,150,30); 
			centerPane.add(tf[2]).setBounds(x1,150,150,30); 
			centerPane.add(btn2).setBounds(570,150,70,30);	
				btn2.setFont(fnt3);
				btn2.setForeground(Color.white);
				btn2.setBackground(new Color(255,128,128));
				btn2.setBorder(new LineBorder(Color.white, 1, true));
		
			
			centerPane.add(dateLbl).setBounds(x,200,70,30);
			dateLbl.setFont(fnt3);
//			centerPane.add(datetf).setBounds(x1,200,150,30);
			centerPane.add(tf[3]).setBounds(x1,200,150,30); 
			centerPane.add(startCalendar).setBounds(545,200,70,30);
			
			
			centerPane.setBackground(Color.white);



					
		add("Center",tablePane);
		
		
			tablePane.setLayout(null);
			
				tablePane.add(passengerlbl).setBounds(430,-130,750,300);
				passengerlbl.setFont(fnt2);
			
//				tablePane.add("Center",passengerlbl);
//				passengerlbl.setHorizontalAlignment(JLabel.CENTER);
				
				sales = new DefaultTableModel(test,listLbl);
				table = new JTable(sales);
				sp = new JScrollPane(table);
			
//				table.setPreferredSize(new Dimension(750,300));
				table.getParent().setBackground(Color.white);
				tablePane.add(sp).setBounds(116,50,750,300); 
			
//				tablePane.add("Center",sp);
//				
				tablePane.setBackground(Color.white);
				
				
		add("South",SouthPane);
			SouthPane.add(btnPane);
				btnPane.add(modibtn);
					modibtn.setFont(fnt3);
					modibtn.setForeground(Color.white);
					modibtn.setBackground(new Color(255,128,128));
//					modibtn.setBorder(new LineBorder(Color.white, 1, true));
				
				btnPane.add(empty);
				btnPane.add(resetBtn);
					resetBtn.setFont(fnt3);
					resetBtn.setForeground(Color.white);
					resetBtn.setBackground(new Color(255,128,128));
//					delBtn.setBorder(new LineBorder(Color.white, 1, true));
					
					
				btnPane.add(empty2);
				btnPane.add(delBtn);
					delBtn.setFont(fnt3);
					delBtn.setForeground(Color.white);
					delBtn.setBackground(new Color(255,128,128));
//						delBtn.setBorder(new LineBorder(Color.white, 1, true));	
			
				
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER));		
			
				btnPane.setBackground(Color.white);
			
		
		
		setSize(1000,800);
		setVisible(true);
		
		modibtn.addActionListener(this);
		delBtn.addActionListener(this);
		resetBtn.addActionListener(this);
		
		
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) { //JTable에서 클릭하면 이벤트생김
				//이벤트 발생버튼이마우스 인쪽 버튼이면
				if(me.getButton()==1) {
					// 선택된 행번호 가져오기
					int row = table.getSelectedRow(); // 행번호가져오기
					int col =table.getColumnCount(); //칸수 구하기

						tf[0].setText((String)sales.getValueAt(row,1));
						tf[1].setText((String)sales.getValueAt(row,4));
						tf[2].setText((String)sales.getValueAt(row,5));
							
				}
			}
		});

	}

//	public static void main(String[] args) {
//		
//
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = e.getActionCommand();
			if(btn.equals("삭제")) {
			
			} else if(btn.equals("초기화")) { // 다지우기
				setFormClear();
			}else if(btn.equals("수정")) {
				this.setVisible(false);
				EmpMainFrame.reservation1.setVisible(true);
				EmpMainFrame.centerPane.add(EmpMainFrame.reservation1);
			}
		}
		
	}
	
	public void setFormClear() {	
		for (int i = 0 ; i <tf.length ; i++) {
			tf[i].setText("");
		}
	}


}
