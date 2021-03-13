import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbAll.CustomBookingChange3VO;
import dbAll.CustomBookingChange4DAO;
import dbAll.CustomBookingChange4VO;
//test
public class CustomBookingChange4 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel ticketLbl = new JLabel("예약 확인서");
	JLabel bookingNumLbl1 = new JLabel("예약번호");
	JLabel bookingNumLbl2 = new JLabel("AAA123");
	JLabel bookingDateLbl1 = new JLabel("예약완료 날짜");
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
	
	public CustomBookingChange4() {
		setLayout(null);
		this.setBackground(Color.white);
		
		add(ticketLbl).setBounds(420, 60, 200, 50);
			ticketLbl.setFont(titleFnt);
		add(bookingNumLbl1).setBounds(380, 120, 70, 35);
			bookingNumLbl1.setFont(fnt);
		add(bookingNumLbl2).setBounds(385, 150, 150, 35);
			bookingNumLbl2.setFont(fnt);
		add(bookingDateLbl1).setBounds(560, 120, 150, 35);
			bookingDateLbl1.setFont(fnt);
		add(bookingDateLbl2).setBounds(575, 150, 150, 35);
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
	public void setNewBookingDate(String newDate, String resNo) {
		CustomBookingChange4VO vo = new CustomBookingChange4VO();
		vo.setNewDate(newDate);
		vo.setResNo(resNo);
		
		CustomBookingChange4DAO dao = new CustomBookingChange4DAO();
		int result = dao.newBookingDate(vo);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "예약수정날짜 변경 성공");
		}else {
			JOptionPane.showMessageDialog(this, "예약수정날짜 변경 실패");
		}
	} 
	public void setBookingDate() {
		Calendar cal= Calendar.getInstance();
	    SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	    CustomFrame.bookingChange4.bookingDateLbl2.setText(df.format(cal.getTime()));
	    String newDate = df.format(cal.getTime());
	    String resNo = CustomBookingChange1.getResNo;//t1,t2
	    setNewBookingDate(newDate, resNo);
	    
	    System.out.println("날짜구하기---------------->"+df.format(cal.getTime()));
	}
	public void table2Print() {
		String resNo = CustomBookingChange1.getResNo;//t1,t2
		CustomFrame.bookingChange4.bookingNumLbl2.setText(resNo);
		setTable2(resNo);
	}
	public void setTable2(String resNo) {
		CustomBookingChange4DAO dao = new CustomBookingChange4DAO();
		List<CustomBookingChange4VO> lst = dao.getTable2(resNo);
		setBookingDate();
		setTable2List(lst);
	}
	public void setTable2List(List<CustomBookingChange4VO> lst) {
		model2.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			CustomBookingChange4VO vo = lst.get(i);
			Object[] data = {vo.getUserName(), vo.getUserEname(), vo.getUserPassNo(), vo.getUserExdate(),
					vo.getUserNation(), vo.getUserBirth(), vo.getUserTel(), vo.getUserEmail()};

			model2.addRow(data);
			}
	}
	public void table1Print() {
		String newFlight = CustomBookingChange2.newFlightNum;//t1,t3
		
		String resNo = CustomBookingChange1.getResNo;//t1,t2
		String oldFlight = CustomBookingChange1.getFlight;//t3
		
		setTable1(newFlight, resNo);
		System.out.println(newFlight+"    cbc4    "+resNo);
		System.out.println(oldFlight);
	}
	public void setTable1(String newFlight, String resNo) {
		CustomBookingChange4DAO dao = new CustomBookingChange4DAO();
		List<CustomBookingChange4VO> lst = dao.getTable1(newFlight, resNo);

		setTable1List(lst);
	}
	public void setTable1List(List<CustomBookingChange4VO> lst) {
		model1.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			CustomBookingChange4VO vo = lst.get(i);
			Object[] data = {vo.getDep(), vo.getDes(), vo.getBrdDate(), vo.getDepTime(),
					vo.getDesTime(), vo.getFlightNo(), vo.getSeatNo(), vo.getFare()};

			model1.addRow(data);
			}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btnStr = ((JButton) obj).getText();
			if(btnStr.equals("저장")) {
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
			}else if(btnStr.equals("확인")) {
				JOptionPane.showMessageDialog(this, "예약변경이 완료되었습니다.");
				
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			}
				
		}
		
	}

	
}
