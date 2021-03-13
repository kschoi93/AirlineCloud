import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Calendar;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import dbAll.EmpFlightChangeDAO;
import dbAll.EmpFlightChangeVO;

public class EmpFlightChange extends JPanel implements ActionListener, MouseListener, ItemListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	
	ImageIcon icon = new ImageIcon("img/calendar.png");
	Image im = icon.getImage();
	Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
	ImageIcon icon2 = new ImageIcon(im2);
	JButton calBtn = new JButton(icon2);
	
	String dateSearchWord ="";//날짜검색어 저장
	String acSearchWord = "";//항공편검색어 저장
	String depSearchWord = "";//출발지검색어 저장
	String desSearchWord = "";//도착지검색어 저장
	
	
	JLabel acLbl = new JLabel("AC");
	JTextField acTf = new JTextField(10);
	JTextField dateTf = new JTextField(10);//검색날짜 보여주는 필드
	JLabel rowCountLbl = new JLabel("");
	
	JLabel depLbl = new JLabel("출발지");
	JTextField depTf = new JTextField(10);
	
	JLabel desLbl = new JLabel("도착지");
	JTextField desTf = new JTextField(10);
	JButton searchBtn = new JButton("검색");
	
	////table
	String info[] = {"항공편명","출발날짜", "출발지", "출발시간", "도착지", "도착시간", "상태"};
	JTable table;
	JScrollPane sp;
	DefaultTableModel model;
	
	//
//	JLabel foodLbl = new JLabel("기내식");
//		JLabel korFoodLbl = new JLabel("한식");
//			JComboBox<String> korFoodBox;
//			String[] korFoodStr = {"비빔밥", "떡갈비", "불고기"};
//		JLabel chineseFoodLbl = new JLabel("중식");
//			JComboBox<String> chineseFoodBox;
//			String[] chineseFoodStr = {"베이징면","사천볶음밥", "광저우생선"};
//		JLabel westFoodLbl = new JLabel("양식");
//			JComboBox<String> westFoodBox;
//			String[] westFoodStr = {"파스타", "찹스테이크", "토마토스튜"};
	
	JLabel delayLbl = new JLabel("지연");
	JCheckBox delayCheckBox = new JCheckBox("", false);
	JLabel depTimeLbl = new JLabel("출발시간");
	JTextField depTimeTf = new JTextField(10);
	JLabel arrTimeLbl = new JLabel("~   도착시간");
	JTextField arrTimeTf = new JTextField(10);
	JLabel cancelLbl = new JLabel("결항");
	JComboBox<String> cancelState;
	String[] state = {"사유선택", "기상악화", "기장실종", "기타사유"};
	JCheckBox cancelBox = new JCheckBox("", false);
	
	JButton btn2 = new JButton("설정");
	JButton delayBtn = new JButton("지연 설정");
	JButton cancelBtn = new JButton("결항 설정");
	
	//이벤트용 변수
	int clickCheck = 0; //달력
	int calendarWindowTest = 0;//달력 열려있는지
	
	int rowSelected = 0; //j테이블에서 항목이 선택되었는지
	String flightNo = "";//테이블에서 선택된 항공편명 담기
	String brdDate = "";//테이블에서 선탠된 항공편 탑승일 담기
	
	int rowCount = 0; 
	
	public EmpFlightChange() {
		setLayout(null);
		this.setBackground(Color.white);
		
		add(calBtn).setBounds(200,100, 35,35);
			calBtn.setBorderPainted(false);
			calBtn.setContentAreaFilled(false);
			calBtn.setFocusPainted(false);
		add(dateTf).setBounds(200,140, 200,25);dateTf.setText("검색시작날짜 : "+dateSearchWord); dateTf.setFont(fnt); dateTf.setBorder(new LineBorder(Color.white)); dateTf.setEnabled(false);
		add(rowCountLbl).setBounds(680,140, 200,25);rowCountLbl.setText("검색결과 : "); rowCountLbl.setFont(fnt);
		add(acLbl).setBounds(260, 100, 80, 35);
			//acLbl.setFont(fnt);
		add(acTf).setBounds(285, 105, 100, 25);
			acTf.setFont(fnt);
		add(depLbl).setBounds(400, 100, 50, 35);
			depLbl.setFont(fnt);
		add(depTf).setBounds(445, 105, 100, 25);
			depTf.setFont(fnt);
		add(desLbl).setBounds(560, 100, 50, 35);
			desLbl.setFont(fnt);
		add(desTf).setBounds(605, 105, 100, 25);
			desTf.setFont(fnt);
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
		
//		//기내식
//		add(foodLbl).setBounds(200, 480, 60,35);
//			foodLbl.setFont(fnt);
//		//한식
//		add(korFoodLbl).setBounds(280, 480,60,35); korFoodLbl.setFont(fnt);
//		korFoodBox = new JComboBox<String> (korFoodStr);
//		add(korFoodBox).setBounds(320, 483, 100, 25);korFoodBox.setFont(fnt);korFoodBox.setBackground(Color.white);
//			//cb1.setAlignmentX(CENTER_ALIGNMENT);
//		//중식
//		add(chineseFoodLbl).setBounds(440,480, 60,35); chineseFoodLbl.setFont(fnt);
//		chineseFoodBox = new JComboBox<String>(chineseFoodStr);
//		add(chineseFoodBox).setBounds(480,483, 100, 25); chineseFoodBox.setFont(fnt);chineseFoodBox.setBackground(Color.white);
//		//양식
//		add(westFoodLbl).setBounds(600,480, 60,35); westFoodLbl.setFont(fnt);
//		westFoodBox = new JComboBox<String>(westFoodStr);
//		add(westFoodBox).setBounds(640,483, 100,25); westFoodBox.setFont(fnt);westFoodBox.setBackground(Color.white);
		
		//지연 상태
		add(delayLbl).setBounds(200, 515, 50, 35);
			delayLbl.setFont(fnt);
		add(delayCheckBox).setBounds(280, 524, 17, 17);
			delayCheckBox.setBackground(Color.white);
			delayCheckBox.setEnabled(false);
		add(depTimeLbl).setBounds(320,517, 60, 35);
			depTimeLbl.setFont(fnt);
		add(depTimeTf).setBounds(400, 521, 80, 25);
		depTimeTf.setFont(fnt);
		depTimeTf.setEnabled(false);
		add(arrTimeLbl).setBounds(500, 516, 100, 35);
			arrTimeLbl.setFont(fnt);
		add(arrTimeTf).setBounds(610, 521, 80, 25);
			arrTimeTf.setFont(fnt);
			arrTimeTf.setEnabled(false);
			
		//결항 상태
		add(cancelLbl).setBounds(200, 550, 50, 35);
			cancelLbl.setFont(fnt);
		add(cancelBox).setBounds(280, 559, 17, 17);
		cancelBox.setBackground(Color.white);
		cancelBox.setEnabled(false);
		cancelState = new JComboBox<String>(state);
		add(cancelState).setBounds(320,555, 150, 25);
			cancelState.setFont(fnt);
			cancelState.setBackground(Color.white);
			cancelState.setEnabled(false);
			
//		add(btn2).setBounds(750, 483, 50,25);
//			btn2.setFont(fnt);
//			btn2.setForeground(Color.white);
//			btn2.setBackground(new Color(255,128,128));
//			btn2.setBorder(new LineBorder(Color.white, 1, true));
		add(delayBtn).setBounds(720,521, 80, 25); 
			delayBtn.setFont(fnt);
			delayBtn.setForeground(Color.white);
			delayBtn.setBackground(new Color(255,128,128));
			delayBtn.setBorder(new LineBorder(Color.white, 1, true));
			delayBtn.setEnabled(false);
		add(cancelBtn).setBounds(720,555, 80, 25);
			cancelBtn.setFont(fnt);
			cancelBtn.setForeground(Color.white);
			cancelBtn.setBackground(new Color(255,128,128));
			cancelBtn.setBorder(new LineBorder(Color.white, 1, true));
			cancelBtn.setEnabled(false);
		
		setSize(1000, 800);
		setVisible(true);
		
		//이벤트 등록
		calBtn.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				me.getSource(); 
				if(calendarWindowTest==0) {
					 new CustomCalendar();
					 calendarWindowTest=1;
				 }
			}
		});
		table.addMouseListener(this);
		searchBtn.addActionListener(this);
		btn2.addActionListener(this);
		delayBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		
		cancelBox.addItemListener(this);
		delayCheckBox.addItemListener(this);
		//초기화면에 오늘날짜 이후 항공편 출력
		getAllFlight();
	}
	public void resetForm() {
		dateSearchWord = "";
		dateTf.setText("검색시작날짜 : "+dateSearchWord);
		acTf.setText("");
		depTf.setText("");
		desTf.setText("");
	}
	public void getAllFlight() {
		EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
		List<EmpFlightChangeVO> lst = dao.flightAllSelect();
		
		setNewTableList(lst);
	}
	public void setNewTableList(List<EmpFlightChangeVO> lst) {
		model.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			EmpFlightChangeVO vo = lst.get(i);
			Object[] data = {vo.getFlightno_r(), vo.getBrdDate_r(),
					vo.getDep(), vo.getDepTime(), vo.getDes(), vo.getDesTime(), vo.getFlight_state()};
			model.addRow(data);
		}
		rowCount = table.getRowCount();
		System.out.println(rowCount);
		rowCountLbl.setText("검색결과 : "+rowCount+"개");
		
	}
	public void flightSearch() {
		acSearchWord = acTf.getText();
		System.out.println(acSearchWord);
		depSearchWord = depTf.getText().toUpperCase();
		System.out.println(depSearchWord);
		desSearchWord = desTf.getText().toUpperCase();
		System.out.println(desSearchWord);
		//모든 tf에 검색어가 없을때 현재날짜 기준 모든 항공편 출력
		if(dateSearchWord.equals("")&& acSearchWord.equals("")&&depSearchWord.equals("")&&
				desSearchWord.equals("")) {
			JOptionPane.showMessageDialog(this, "검색조건 미입력으로 \n"
					+ "현재날짜기준 항공편이 출력됩니다");
			getAllFlight();
		}else if(!dateSearchWord.equals("")&&!acSearchWord.equals("")) {//2-2출발날짜&항공편
			System.out.println("출발날짜&항공편");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getDateFlightRecord(dateSearchWord, acSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}else if(!dateSearchWord.equals("")&&!depSearchWord.equals("")) {//2-3출발날짜&출발지
			System.out.println("출발날짜&출발지");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getDateDepRecord(dateSearchWord, depSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}else if(!dateSearchWord.equals("")&&!desSearchWord.equals("")) {//2-4출발날짜&도착지
			System.out.println("출발날짜&도착지");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getDateDesRecord(dateSearchWord, desSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}else if(!acSearchWord.equals("")&&!depSearchWord.equals("")) {//2-5항공편&출발지
			System.out.println("항공편&출발지");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getFlightDepRecord(acSearchWord, depSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}else if(!acSearchWord.equals("")&&!desSearchWord.equals("")) {//2-6항공편&도착지
			System.out.println("항공편&도착지");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getFlightDesRecord(acSearchWord, desSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}else if(!depSearchWord.equals("")&&!desSearchWord.equals("")) {//2-7출발지&도착지
			System.out.println("출발지&도착지");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getDepDesRecord(depSearchWord, desSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}else {//2-1검색어가 1개만 있을때
			System.out.println("검색어가 1개만 있을때");
			EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
			List<EmpFlightChangeVO> searchList = dao.getSearchOneRecord(dateSearchWord, acSearchWord, depSearchWord, desSearchWord);
			if (searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "검색 결과가 없습니다.");
			}else {
				setNewTableList(searchList);
			}
			resetForm();
		}
	}
	public void setDelayUpdate() {
		EmpFlightChangeVO vo = new EmpFlightChangeVO();
		vo.setFlightno_r(flightNo);
		vo.setBrdDate_r(brdDate);
		
		vo.setDepTime(depTimeTf.getText());//빈칸이 들어갈까 아니면 입력한 값이들어갈까?
		vo.setDesTime(arrTimeTf.getText());

		EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
		int result = dao.delayUpdate(vo);
		if(result >0) {//수정성공
			JOptionPane.showMessageDialog(this, "지연 정보가 수정되었습니다");
			getAllFlight();
		}else {
			JOptionPane.showMessageDialog(this, "지연 정보 수정에 실패했습니다. \n 다시 시도하세요");
		}
	}
	public void setCancelUpdate() {
		EmpFlightChangeVO vo = new EmpFlightChangeVO();
		vo.setFlightno_r(flightNo);
		vo.setBrdDate_r(brdDate);
		
		EmpFlightChangeDAO dao = new EmpFlightChangeDAO();
		int result = dao.cancelUpdate(vo);
		if(result >0) {//수정성공
			JOptionPane.showMessageDialog(this, "해당 항공편이 결항 처리되었습니다.");
			getAllFlight();
		}else {
			JOptionPane.showMessageDialog(this, "결항 처리에 실패했습니다. \n 다시 시도하세요");
		}
		
	}
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==ItemEvent.SELECTED) {
			if(ie.getItem()==delayCheckBox) {
				depTimeTf.setEnabled(true);
				arrTimeTf.setEnabled(true);
				delayBtn.setEnabled(true);
				cancelBox.setEnabled(false);
				System.out.println("안녕");
			}else if(ie.getItem()==cancelBox) {
				cancelState.setEnabled(true);
				delayCheckBox.setEnabled(false);
				cancelBtn.setEnabled(true);
			}
		}else if(ie.getStateChange()==ItemEvent.DESELECTED) {
			if(ie.getItem()==delayCheckBox) {
				depTimeTf.setEnabled(false);
				depTimeTf.setText("");
				arrTimeTf.setEnabled(false);
				arrTimeTf.setText("");
				cancelBox.setEnabled(true);
			}else if(ie.getItem()==cancelBox) {
				cancelState.setEnabled(false);
				cancelState.setSelectedIndex(0);
				delayCheckBox.setEnabled(true);
				cancelBox.setEnabled(false);
				cancelBtn.setEnabled(false);
			}
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("검색")) {
			flightSearch();
		}else if(eventBtn.equals("설정")) {
			
		}else if(eventBtn.equals("지연 설정")){
			if(depTimeTf.getText().equals("")||arrTimeTf.getText().equals("")){
				JOptionPane.showMessageDialog(this, "변경할 출도착시간을 입력하세요");
				System.out.println("DDDDDD");
			}else if(!depTimeTf.getText().equalsIgnoreCase("")||!arrTimeTf.getText().equals("")) {
				String checkStr = depTimeTf.getText()+arrTimeTf.getText();
				System.out.println("tf에 써진말"+ checkStr);
				for(int i=0;i<checkStr.length();) {
					char c = checkStr.charAt(i);
					if(c<48 || c>57) {//숫자가 아니야
						JOptionPane.showMessageDialog(this, "숫자만 입력해주세요");
						break;	
					}else {//입련한게 숫자면
						break;
					}
				}
				//디비에서 업데이트 시키기
				System.out.println("여기???");
				setDelayUpdate();
				JOptionPane.showMessageDialog(this, "항공편 지연설정이 완료되었습니다.");
				depTimeTf.setText("");
				arrTimeTf.setText("");
			}
		}else if(eventBtn.equals("결항 설정")) {
			if(cancelState.getSelectedIndex()==0) {
				JOptionPane.showMessageDialog(this, "결항 사유를 선택해주세요");
			}else {
				setCancelUpdate();
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		if(me.getButton()==1) {
			if(table.getSelectedRowCount() != 1) {
				System.out.println("@22");
				JOptionPane.showMessageDialog(this, "1개의 항공편을 선택해주세요.");
				delayCheckBox.setEnabled(false);
				cancelBox.setEnabled(false);
			}else {
				rowSelected = table.getSelectedRow()+1; //이벤트용
				int row = table.getSelectedRow(); //선택된 행에 항공편 이름 알기용
				flightNo = (String)table.getValueAt(row, 0);
				String brd = (String)table.getValueAt(row, 1);
				String brdYear = brd.substring(0, 4);
				String brdMonth = brd.substring(5, 7);
				String brdDay = brd.substring(8, 10);
				brdDate = brdYear+brdMonth+brdDay;
				System.out.println(brdYear);
				System.out.println(brdMonth);
				System.out.println(brdDay);
				System.out.println(flightNo);
				System.out.println("탑승일->"+brdDate);
				delayCheckBox.setEnabled(true);
				cancelBox.setEnabled(true);
				
				System.out.println(rowSelected);
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////            달                   력      ////////////////////////////////
	class CustomCalendar extends JFrame implements ActionListener, WindowListener{
		// 상단 지역
		JPanel bar = new JPanel();
			JButton lastMonth = new JButton("◀");
		
			JComboBox<Integer> yearCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
			
			JLabel yLbl = new JLabel("년 ");
			
			JComboBox<Integer> monthCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
			JLabel mLbl = new JLabel("월");
			JButton nextMonth = new JButton("▶");
		// 중앙 지역
		JPanel center = new JPanel(new BorderLayout());
			// 중앙 상단 지역
			JPanel cntNorth = new JPanel(new GridLayout(0,7));
			// 중앙 중앙 지역
			JPanel cntCenter = new JPanel(new GridLayout(0,7));
		
		// 요일 입력
		String dw[] = {"일","월","화","수","목","금","토"};
		
		
		Calendar now = Calendar.getInstance();
		int year, month, date;
		
		public CustomCalendar() {
			super("검색날짜 선택");
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH)+1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for(int i=year-5; i<=year+50; i++){yearModel.addElement(i);}
			for(int i=1; i<=12; i++) { monthModel.addElement(i); }
			//////////////////////////프레임///////////////////////////////////////////
			// 상단 지역
			add("North", bar);
				bar.setLayout(new FlowLayout());
				bar.setSize(300,400);
				bar.add(lastMonth);
				//////////////////////////달력/////////////////////////////////////////////
				bar.add(yearCombo);
					yearCombo.setModel(yearModel);
					yearCombo.setSelectedItem(year);

				bar.add(yLbl);
				bar.add(monthCombo);
					monthCombo.setModel(monthModel);
					monthCombo.setSelectedItem(month);

				bar.add(mLbl);
				bar.add(nextMonth);
				bar.setBackground(new Color(0,210,180));

			// 중앙 지역
			add("Center", center);
				// 중앙 상단 지역
				center.add("North",cntNorth);
				for(int i=0; i<dw.length; i++) {
					JLabel dayOfWeek = new JLabel(dw[i],JLabel.CENTER);
					if(i==0) dayOfWeek.setForeground(Color.red);
					else if(i==6) dayOfWeek.setForeground(Color.blue);
					cntNorth.add(dayOfWeek);
				}

				// 중앙 중앙 지역
				center.add("Center",cntCenter);
				dayPrint(year,month);
				
				
			// 이벤트
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
			lastMonth.addActionListener(this);
			nextMonth.addActionListener(this);
			addWindowListener(this);
			
			// frame 기본 셋팅
			setSize(400,300);
			setVisible(true);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}	


		// 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton){
				JButton eventBtn = (JButton)obj;
				int yy = (Integer)yearCombo.getSelectedItem();
				int mm = (Integer)monthCombo.getSelectedItem();
				if(eventBtn.equals(lastMonth)){	//전달
					if(mm == 1) {
						yy--; mm = 12;
					}else {
						mm--;
					}
				}else if(eventBtn.equals(nextMonth)){	//다음달
					if(mm == 12){
						yy++; mm = 1;
					}else{
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			}else if(obj instanceof JComboBox){	//콤보박스 이벤트 발생시
				createDayStart();
			}
		}


		private void createDayStart() {
			cntCenter.setVisible(false);	//패널 숨기기
			cntCenter.removeAll();	//날짜 출력한 라벨 지우기
			dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
			cntCenter.setVisible(true);	//패널 재출력	
		}


		// 날짜 출력
		public void dayPrint(int y,int m) {
			Calendar cal = Calendar.getInstance();
			cal.set(y, m-1, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
			for(int i =1; i<week; i++) { // 1월 1일 전까지 공백을 표시해라
				cntCenter.add(new JLabel(""));
			}
			
			for(int i =0;i<=lastDate-1;i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
				JLabel day = new JLabel();
				day.setHorizontalAlignment(JLabel.CENTER);
				if((week+i)%7==0) {
					cntCenter.add(day).setForeground(Color.blue);
					day.setText(1+i+"");
				} else if((week+i)%7==1) {
					cntCenter.add(day).setForeground(Color.red);
					day.setText(1+i+"");
				} else {
					cntCenter.add(day);
					day.setText(1+i+"");
				}
				day.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel)me.getSource();
						String str= mouseClick.getText();
						String y = ""+yearCombo.getSelectedItem();
						String m = ""+monthCombo.getSelectedItem();
						
						// 받은 "요일"이 1자리면 0을 붙여라
						if(str.equals("")) ;
						else if(str.length()==1) str = "0"+str; 
						
						// 받은 "월"이 1자리면 0을 붙여라
						if(m.length()==1) m = "0"+m;
						
						if(clickCheck==0) {
							dateTf.setText("검색시작날짜 : "+y+m+str);
							dateSearchWord = y+m+str;
							dateTf.setEnabled(false);
						}
					}
				});
			}
			

			
		}

		public void windowOpened(WindowEvent e) {
			calendarWindowTest = 1;
		}
		public void windowClosing(WindowEvent e) {
			calendarWindowTest = 0;
		}	
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
	}

	

	

}
