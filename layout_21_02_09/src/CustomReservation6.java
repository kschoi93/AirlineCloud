import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbAll.CustomReservaion6DAO;
import dbAll.CustomReservation3FellowVO;
import dbAll.CustomReservation3VO;
import dbAll.CustomReservation4VO;

public class CustomReservation6 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel ticketLbl = new JLabel("예약 확인서");
	JLabel bookingNumLbl1 = new JLabel("예약번호");
	JLabel bookingNumLbl2 = new JLabel("AAA123");
	JLabel bookingDateLbl1 = new JLabel("예약날짜");
	JLabel bookingDateLbl2 = new JLabel("2021-01-31");
	
	JLabel flightLbl = new JLabel("선택한 노선");
	String[] flightStr = {"출발지", "도착지", "출발일", "출발시간", "도착시간", "비행편", "좌석", "운임"};
	DefaultTableModel model1;
	JTable flightTable;
	JScrollPane sp1;
	
	JLabel passengerLbl = new JLabel("탑승자 정보");
	String[] passengerStr = {"성명(한)", "영문명", "여권번호", "여권만료일", "발행국가", "생년월일", "연락처", "이메일"};
	DefaultTableModel model2;
	JTable passengerTable;
	JScrollPane sp2;
	
	JButton saveBtn = new JButton("저장");
	JButton okBtn = new JButton("확인");
	
	public CustomReservation6() {
		setLayout(null);
		this.setBackground(Color.white);
		
		add(ticketLbl).setBounds(420, 60, 200, 50);
			ticketLbl.setFont(titleFnt);
		add(bookingNumLbl1).setBounds(380, 120, 70, 35);
			bookingNumLbl1.setFont(fnt);
		add(bookingNumLbl2).setBounds(385, 150, 150, 35);
			bookingNumLbl2.setFont(fnt);
			
		add(bookingDateLbl1).setBounds(580, 120, 70, 35);
			bookingDateLbl1.setFont(fnt);
		add(bookingDateLbl2).setBounds(575, 150, 100, 35);
			bookingDateLbl2.setFont(fnt);
			
		add(flightLbl).setBounds(420, 200, 200, 50);
			flightLbl.setFont(titleFnt);
		model1 = new DefaultTableModel(flightStr,0);
		flightTable = new JTable(model1);
		sp1 = new JScrollPane(flightTable);
		add(sp1).setBounds(200, 250, 600, 150);
			sp1.getViewport().setBackground(Color.white);
		
		add(passengerLbl).setBounds(420, 400, 200, 50);
			passengerLbl.setFont(titleFnt);
		model2 = new DefaultTableModel(passengerStr, 0);
		passengerTable = new JTable(model2);
		sp2 = new JScrollPane(passengerTable);
		add(sp2).setBounds(200, 450, 600, 150);
			sp2.getViewport().setBackground(Color.white);
			
		add(saveBtn).setBounds(350, 620, 100, 30);
			saveBtn.setFont(fnt);
			saveBtn.setBackground(new Color(0,130,255));
			saveBtn.setForeground(Color.white);
		add(okBtn).setBounds(550, 620, 100, 30);
			okBtn.setFont(fnt);
			okBtn.setBackground(new Color(0,130,255));
			okBtn.setForeground(Color.white);
		
			
		setSize(1000,800);
		setVisible(true);
		
		saveBtn.addActionListener(this);
		okBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = ((JButton) obj).getText();
			if(btn.equals("저장")) {
				JOptionPane.showMessageDialog(this, "예약확인서가 저장되었습니다.");
				 try {
					 BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			         Graphics2D graphics2D = image.createGraphics();
			         CustomFrame.bookingChange4.paint(graphics2D);
			         ImageIO.write(image,"jpeg", new File("D://E-ticket.jpeg"));
			        }
			        catch(Exception exception)
			        {
			            //code
			        }
				//파일 쓰기
			}else if(btn.equals("확인")) {
				JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");
				
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			}
		}
	}
	public void getData() {
			CustomReservation4VO vo4 = new CustomReservation4VO();
			CustomReservation3VO vo3 = new CustomReservation3VO();
			CustomReservation3FellowVO voFellow3 = new CustomReservation3FellowVO();
			String meal ="";
			String seatno ="";
			
			// 출발지, 도착지, 출발일, 출발시간, 도착시간, 항공편명, 좌석, 운임
			String startCountry = (String) CustomReservation.startCombo.getSelectedItem();//출발지
			String startEndCountry = (String) CustomReservation.arriveCombo.getSelectedItem();//도착지
			String startDate = CustomReservation.startDateField.getText(); // 출발일
			String startTime = CustomReservation2.startTime; // 출발시간
			String startEndTime = CustomReservation2.startEndTime; // 도착시간
			String startResno = CustomReservation2.startSelect; // 출발 항공편명
			String startSeatNo = "";
			
			if(CustomReservation.humanCount==1) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo();	
				}
			} else if(CustomReservation.humanCount==2) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2();
				}
			} else if(CustomReservation.humanCount==3) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3();	
				}
			} else if(CustomReservation.humanCount==4) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4();			
				}
			} else if(CustomReservation.humanCount==5) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4() +", "+ vo4.getSeatNo5();
				}
			}
			
			int startFare = CustomReservation2.startFare;
			
			// 출발지, 도착지, 출발일, 출발시간, 도착시간, 항공편명, 좌석, 운임
			String arriveCountry = (String) CustomReservation.arriveCombo.getSelectedItem();//출발지
			String arriveEndCountry = (String) CustomReservation.startCombo.getSelectedItem();//도착지
			String arriveDate = CustomReservation.arriveDateField.getText(); // 도착지에서 출발일
			String arriveTime = CustomReservation2.arriveTime; // 도착지에서 출발시간
			String arriveEndTime = CustomReservation2.arriveendTime;
			String arriveResno = CustomReservation2.arriveSelect; // 출발 항공편명
			String arriveSeatNo = "";
			if(CustomReservation.humanCount==1) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i); 
					arriveSeatNo = vo4.getSeatNo();	
				}
			} else if(CustomReservation.humanCount==2) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2();
				}
			} else if(CustomReservation.humanCount==3) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3();
				}
			} else if(CustomReservation.humanCount==4) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4();
				}
			} else if(CustomReservation.humanCount==5) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4() +", "+ vo4.getSeatNo5();
				}
			}
			int arriveFare = CustomReservation2.arriveFare;
			Object startflightlst[] = {startCountry,startEndCountry,startDate,startTime,startEndTime,startResno,startSeatNo,startFare};
			Object arriveflightlst[] = {arriveCountry,arriveEndCountry,arriveDate,arriveTime,arriveEndTime,arriveResno,arriveSeatNo,arriveFare};
			model1.addRow(startflightlst);
			model1.addRow(arriveflightlst);

			////////////////////////////////////// 예약정보/////////////////////////////////
			
			
			
			//성명(한), 성명(영), 여권번호, 여권만료일, 발행국가, 생년월일, 연락처, 이메일
			for(int i=0; i<CustomReservation3.lst.size();i++){
				vo3 = CustomReservation3.lst.get(i);
				Object Data1[] = {vo3.getUser_name(),vo3.getUser_ename(),vo3.getUser_passno(),vo3.getUser_exdate(),vo3.getUser_nation(),vo3.getUser_birth()
						,vo3.getUser_tel(),vo3.getUser_email()};
				model2.setRowCount(0);
				model2.addRow(Data1);
			}
			for(int i=0; i<CustomReservation3.fellowLst.size();i++) {
				voFellow3 = CustomReservation3.fellowLst.get(i);
				Object Data2[] = {voFellow3.getCom_name(),voFellow3.getCom_ename(),voFellow3.getCom_passno(),voFellow3.getCom_exdate(),voFellow3.getCom_nation()
						,voFellow3.getCom_birth(),voFellow3.getCom_tel(),voFellow3.getCom_email()};
				model2.addRow(Data2);
			}
			
			
		}
	
	
	/// 데이터 저장
	public int setData() {
		CustomReservaion6DAO dao = new CustomReservaion6DAO();
		
		String startDate = CustomReservation.startDateField.getText(); // 출발일
		String arriveDate = CustomReservation.arriveDateField.getText(); // 도착지에서 출발일
		String startResno = CustomReservation2.startSelect; // 출발 항공편명
		String arriveResno = CustomReservation2.arriveSelect; // 복귀 항공편명
		
		int result = dao.userUpdate(CustomReservation3.lst,AirlineMain.idField.getText());
		dao.reservationStartUpdate(startResno, startDate, CustomReservation3.lst);
		dao.reservationArriveUpdate(arriveResno, arriveDate, CustomReservation3.lst);
		String sResno = dao.reservationStartResnoCheck(AirlineMain.idField.getText());
		String aResno = dao.reservationArriveResnoCheck(AirlineMain.idField.getText());
		if(!(CustomReservation.humanCount == 1)) { // 명 수가 1이 아닐때는 동승자가 있기 때문에 실행한다.
			dao.companyStartUpdate(CustomReservation3.fellowLst);
			dao.companyArriveUpdate(CustomReservation3.fellowLst);
		}
		dao.seatStartUpdate(CustomReservation.humanCount, CustomReservation4.onelst);
		dao.seatArriveUpdate(CustomReservation.humanCount, CustomReservation4.roundlst);
		
		bookingNumLbl2.setText(sResno + ", " + aResno);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		bookingDateLbl2.setText(year +"-"+month+"-"+date);
		
		return result;
		
	}

}
