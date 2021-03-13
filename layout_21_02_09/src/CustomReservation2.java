
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbAll.CustomReservation2DAO;
import dbAll.CustomReservation2VO;

public class CustomReservation2 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	JPanel changePane = new JPanel();
		JPanel centerPane = new JPanel();
			JPanel northPane = new JPanel();
				JPanel titlePane = new JPanel();
					JLabel titleLbl = new JLabel("항공편을 선택하세요");
				JPanel countryPane = new JPanel();
					JLabel startCountry = new JLabel("출발지");
					ImageIcon icon = new ImageIcon("img/arrow.png");
					Image im = icon.getImage();
					Image im2 = im.getScaledInstance(50, 30, Image.SCALE_DEFAULT);
					ImageIcon icon2 = new ImageIcon(im2);
					JLabel arriveCountry = new JLabel("도착지");
				JPanel datePane = new JPanel();
					JLabel startDate = new JLabel("2020/02/02");
					JLabel arriveDate = new JLabel("2021/02/08");
					
			JPanel wrpTblPane = new JPanel();
			
				JPanel startTablePane = new JPanel();
					String startModelTitle[] = {"비행편명","출발시간","도착시간","총 비행시간","예약 상태","운임"};
					DefaultTableModel startModel = new DefaultTableModel(startModelTitle,0);
					JTable startTbl = new JTable(startModel);
					JScrollPane startSp = new JScrollPane(startTbl);
					
				JPanel arriveTablePane = new JPanel();
					String arriveModelTitle[] = {"비행편명","출발시간","도착시간","총 비행시간","예약상태","운임"};
					DefaultTableModel arriveModel = new DefaultTableModel(arriveModelTitle,0);
					JTable arriveTbl = new JTable(arriveModel);
					JScrollPane arriveSp = new JScrollPane(arriveTbl);
					
		JPanel wrpPane = new JPanel();
			JPanel selectTextPane = new JPanel();	
				JLabel finalStartText = new JLabel(" ");
				JLabel finalArriveText = new JLabel(" ");
			JPanel btnPane = new JPanel();
				JButton cancelBtn = new JButton("예약취소");
				JButton nextBtn = new JButton("다음단계");
		
		CustomReservation3 reservation3 = new CustomReservation3();
		
		String startD ; // start 날짜 데이터 저장용
		String arriveD ; // arrive 날짜 데이터 저장용
		String start ; // start 위치 데이터 저장용
		String arrive ; // arrive 위치 데이터 저장용
		String radioButton ="왕복"; // CustomReservation의 라디오 버튼이 왕복버튼인지 편도인지 확인용
		String selectStart =" "; // start 테이블 선택시
		String selectArrive = " "; // arrive 테이블 선택시
		int clickCount = 0; // 항공편 출발지 도착지 선택여부 확인용
		int radioCheck = 0; // 왕복인지 여부에 따라 clickCount 갯수를 세서 "다음"버튼을 풀어줄건지 말건지 용.
		static String arriveSelect;
		static String startSelect;
		static String startTime;
		static String startEndTime;
		static String arriveTime;
		static String arriveendTime;
		static int startFare;
		static int arriveFare;
	public CustomReservation2() {
		setLayout(new BorderLayout());
		
		// 전환되는 패널
		add("North",changePane);
		changePane.setLayout(new BorderLayout(0,0));
		changePane.setBackground(Color.white);
		changePane.add("North",new JLabel());
		changePane.add("East",new JLabel());
		changePane.add("West",new JLabel());
		changePane.add("South",new JLabel());
		changePane.add("Center",centerPane);
			centerPane.setLayout(new BorderLayout());
			centerPane.setBackground(Color.white);
			centerPane.add("North",northPane);
				northPane.setLayout(new GridLayout(3,1));
				northPane.setBackground(Color.white);
					// 타이틀 라벨
					northPane.add(titleLbl);
						titleLbl.setHorizontalAlignment(JLabel.CENTER);
						titleLbl.setFont(new Font("굴림체",Font.BOLD,24));
						titleLbl.setBackground(Color.white);
					// 도시표시 패널
					northPane.add(countryPane);
						countryPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
						//출발지
						countryPane.add(startCountry);
						countryPane.setBackground(Color.white);
							startCountry.setFont(fnt);
							startCountry.setBackground(Color.white);
						//화살표
						countryPane.add(new JLabel(icon2));
						//도착지
						countryPane.add(arriveCountry);
							arriveCountry.setFont(fnt);
							arriveCountry.setBackground(Color.white);
					//날짜패널
					northPane.add(datePane);
					datePane.setBackground(Color.white);
						datePane.setLayout(new FlowLayout(FlowLayout.CENTER,80,0));
						datePane.setBackground(Color.white);
						// 출발 날짜
						datePane.add(startDate);
							startDate.setText(CustomReservation.startDateField.getText()); // reservation에서 받아온다
							startDate.setFont(fnt);
							startDate.setBackground(Color.white);
							// 복귀 날짜
						datePane.add(arriveDate);
							arriveDate.setText(CustomReservation.arriveDateField.getText()); // reservation에서 받아온다
							arriveDate.setFont(fnt);
							arriveDate.setBackground(Color.white);
							
							
			//테이블 패널
			centerPane.add("Center",wrpTblPane);
				wrpTblPane.setLayout(new GridLayout(1,2));
				wrpTblPane.add(startTablePane);
					startTablePane.setBackground(Color.white);
					startTablePane.add(startSp);
					startSp.setFont(fnt);
				wrpTblPane.add(arriveTablePane);
					arriveTablePane.setBackground(Color.white);
					arriveTablePane.add(arriveSp);
					arriveSp.setFont(fnt);
			
			
			centerPane.add("South",wrpPane);
				wrpPane.setLayout(new BorderLayout());
				wrpPane.add("Center",selectTextPane);
				wrpPane.setBackground(Color.white);
				JPanel sPane = new JPanel();
				JPanel aPane = new JPanel();
				selectTextPane.setBackground(Color.white);
				selectTextPane.setLayout(new GridLayout(1,2));
					selectTextPane.add(sPane);
						sPane.add(finalStartText);
						sPane.setBackground(Color.white);
						finalStartText.setFont(fnt);
						
					selectTextPane.add(aPane);
						aPane.add(finalArriveText);
						aPane.setBackground(Color.white);
						finalArriveText.setFont(fnt);
						
				wrpPane.add("South",btnPane);
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
				btnPane.setBackground(Color.white);
				btnPane.add(cancelBtn);
					cancelBtn.setFont(fnt);
					cancelBtn.setBackground(new Color(0,130,255));
					cancelBtn.setForeground(Color.white);
					
				btnPane.add(nextBtn);
					nextBtn.setFont(fnt);
					nextBtn.setBackground(new Color(0,130,255));
					nextBtn.setForeground(Color.white);
					nextBtn.setEnabled(false);
					
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(false);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		
		// 마우스 움직일때마다 예약하기 1번화면에서 최신화된 값을 불러온다
		addMouseListener(new MouseAdapter() { 
		public void mouseEntered(MouseEvent e) {
			// start 날짜
			startD = CustomReservation.startDateField.getText();
			// arrive 날짜
			arriveD = CustomReservation.arriveDateField.getText();
			// start 위치
			start = (String)CustomReservation.startCombo.getSelectedItem();
			// arrive 위치
			arrive = (String)CustomReservation.arriveCombo.getSelectedItem();
			// 왕복여부 확인
			if(!CustomReservation.rdb.equals("왕복")) {
				radioButton = "편도";
				//편도이면 arriveModel을 초기화 해줘야 한다.
				arriveModel.setRowCount(0);
				//편도일 경우 clickCount도 편도만큼만 하게 설정할 수 있는 ->   radioCheck = 1
				radioCheck = 1;
				//편도이면 final arrive text가 초기화된다
				finalArriveText.setText(" ");
			} else if(CustomReservation.rdb.equals("왕복")) {
					radioButton = "왕복";
					startModel.setRowCount(0);
					radioCheck = 0;
			}
			// 데이터 넣기
			startDate.setText(startD);
			arriveDate.setText(arriveD);
			startCountry.setText(start);
			arriveCountry.setText(arrive);
			// table 최신화
			tblPrint();
		}
		});
		
		// 스타트 테이블에서 적힌것을 받아와야 한다.
		startTbl.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==1) {
					int row = startTbl.getSelectedRow();
					int col = startTbl.getColumnCount();
					startSelect = (String)startModel.getValueAt(row, 0);
					startTime =(String)startModel.getValueAt(row, 1);
					startEndTime = (String)startModel.getValueAt(row, 2);
					startFare = (int)startModel.getValueAt(row, 5);
					selectStart = "출발지 선택편명 [ "+startSelect+" ]";
					finalStartText.setText(selectStart);
				}
				if(radioCheck==0) { // radio check가 0이면 왕복이라는것
					clickCount = 1 ;
				} else { // radio check가 0이 아니면 넥스트 버튼을 풀어줘라
					nextBtn.setEnabled(true);
				}
			}
		});
		
		// 복귀 테이블에서 적힌것을 받아와야 한다.
		arriveTbl.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==1) {
					int row = arriveTbl.getSelectedRow();
					int col = arriveTbl.getColumnCount();
					arriveSelect = (String)arriveModel.getValueAt(row, 0);
					arriveTime =(String)startModel.getValueAt(row, 1);
					arriveendTime = (String)startModel.getValueAt(row, 2);
					arriveFare = (int)startModel.getValueAt(row, 5);
					selectArrive = "도착지 선택편명 [ "+arriveSelect+" ]";
					finalArriveText.setText(selectArrive);
				}
				if(clickCount == 1) {
					nextBtn.setEnabled(true);
				}
			}
		});
		
	}
	
	// 액션 이벤트
	public void actionPerformed(ActionEvent ae){
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(str.equals("다음단계")) {
				this.setVisible(false);
				// 만약.... 리저베이션에서 넥스트 체크가 휴먼카운트보다 작다면.... 리플레이스 카운트를 실행해라
				if(CustomFrame.reservation3.nextCheck>=CustomFrame.reservation.humanCount) {
					CustomFrame.reservation3.replaceCount();
				}
				CustomFrame.reservation3.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.reservation3);
			}
		} 
	}
	
	// 테이블 최신화
	public void tblPrint() {
		// sql에 start 값, arrive값 최신화된 값을 넣어야함
		String sqlStart = start;
		String sqlArrive = arrive;
		switch(start) {
			case "인천":  sqlStart="ICN"; break;
			case "호놀룰루": sqlStart="HNL"; break;
			case "시드니": sqlStart="SYD"; break;
			case "런던": sqlStart="LHR"; break;
			case "파리": sqlStart="CDG"; break;
			case "바르셀로나": sqlStart="BCN"; break;
			case "방콕": sqlStart="BKK"; break;
			case "발리": sqlStart="DPS"; break;
			case "싱가포르": sqlStart="SIN"; break;
			case "제주": sqlStart="CJU"; break;
			case "서울/김포": sqlStart="GMP"; break;
			case "부산": sqlStart="PUS"; break;
		}
		switch(arrive) {
		case "인천":  sqlArrive="ICN"; break;
		case "호놀룰루": sqlArrive="HNL"; break;
		case "시드니": sqlArrive="SYD"; break;
		case "런던": sqlArrive="LHR"; break;
		case "파리": sqlArrive="CDG"; break;
		case "바르셀로나": sqlArrive="BCN"; break;
		case "방콕": sqlArrive="BKK"; break;
		case "발리": sqlArrive="DPS"; break;
		case "싱가포르": sqlArrive="SIN"; break;
		case "제주": sqlArrive="CJU"; break;
		case "서울/김포": sqlArrive="GMP"; break;
		case "부산": sqlArrive="PUS"; break;
		}
		// 시작 테이블 최신화
		CustomReservation2DAO dao = new CustomReservation2DAO();
		List<CustomReservation2VO> startlst = dao.getStartPlan(sqlStart, sqlArrive);
		startModel.setRowCount(0);
		for(int i=0; i<startlst.size(); i++) {
			CustomReservation2VO vo = startlst.get(i);
			Object[] data = {vo.getFlightno(),vo.getDeptime(),vo.getDestime(),vo.getFlighttime(),vo.getFlight_state(),vo.getFare()};
			startModel.addRow(data);
		}
		
		// 복귀 테이블 최신화
		if(radioButton.equals("왕복")) {
			CustomReservation2DAO dao2 = new CustomReservation2DAO();
			List<CustomReservation2VO> arrivelst = dao2.getarrivePlan(sqlStart, sqlArrive);
			arriveModel.setRowCount(0);
			for(int i=0; i<arrivelst.size();i++) {
				CustomReservation2VO vo = arrivelst.get(i);
				Object[] data = {vo.getFlightno(),vo.getDeptime(),vo.getDestime(),vo.getFlighttime(),vo.getFlight_state(),vo.getFare()};
				arriveModel.addRow(data);
			}
		}
		
		
	}
	
}
