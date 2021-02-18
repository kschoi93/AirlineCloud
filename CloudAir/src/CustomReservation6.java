import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomReservation6 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel ticketLbl = new JLabel("예약 확인서");
	JLabel bookingNumLbl1 = new JLabel("예약번호");
	JLabel bookingNumLbl2 = new JLabel("AAA123");
	JLabel bookingDateLbl1 = new JLabel("예약날짜");
	JLabel bookingDateLbl2 = new JLabel("2021-01-31");
	
	JLabel flightLbl = new JLabel("선택한 노선");
	String[] flightStr = {"출발지", "도착지", "출발일", "도착일", "출발시간", "도착시간", "비행편", "좌석", "운임"};
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
		add(bookingNumLbl2).setBounds(385, 150, 100, 35);
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
				JOptionPane.showMessageDialog(this, "저장하기..");
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			} else if(btn.equals("확인")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} 
		}
	}

	
}
