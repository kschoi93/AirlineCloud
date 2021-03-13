import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

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
import javax.swing.table.DefaultTableModel;

import dbAll.CustomBookingChange2VO;
import dbAll.CustomBookingChange3DAO;
import dbAll.CustomBookingChange3VO;

public class CustomBookingChange3 extends JPanel implements MouseListener, ItemListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel titleLbl1 = new JLabel("선택 항공 내역");
	String flightStr[] = {"출발지", "도착지", "출발일",  "출발시간", "도착시간", "항공편명", "좌석", "운임"};
	JTable table1;
	JScrollPane sp1;
	DefaultTableModel model1;
	JCheckBox check1 = new JCheckBox("", false);
	JLabel lbl1 = new JLabel("상기 내용을 확인하고 변경을 진행합니다.");
	
	JLabel titleLbl2 = new JLabel("탑승자 정보");
	String passengerStr[] = {"성명(한)", "성명(영)", "여권번호", "여권만료일", "발행국가", "생년월일", "연락처", "이메일"};
	JTable table2;
	JScrollPane sp2;
	DefaultTableModel model2;
	JCheckBox check2 = new JCheckBox("", false);
	JLabel lbl2 = new JLabel("해당 정보를 확인하세요. 위 내용은 예약 완료 후 변경이 불가합니다.");
	
	JLabel titleLbl3 = new JLabel("결제 내역");
	String paymentStr[] = {"변경 전", "변경 후"};
	JTable table3;
	JScrollPane sp3;
	DefaultTableModel model3;
	JCheckBox check3 = new JCheckBox("", false);
	JLabel lbl3 = new JLabel("변경된 금액을 확인하고 결제를 진행합니다.");
	
	JButton payBtn = new JButton("결제하기");
	JButton cancelBtn = new JButton("예약취소");
	
	//이벤트용 변수
	int allSelected = 0;// 체크박스가 다 체크되어야지 결제창 버튼이 활성화되는 용도
	int paymentWindowState = 0; //결제창 열려있으면 또 안열리는 용도
	//int paymentState = 0;
	public CustomBookingChange3() {
		setLayout(null);
		this.setBackground(Color.white);
		
		//table1 선택항공내역
		add(titleLbl1).setBounds(390, 60, 230, 50);
			titleLbl1.setFont(titleFnt);
		model1 = new DefaultTableModel(flightStr, 0);
		table1 = new JTable(model1);
		sp1 = new JScrollPane(table1);
			sp1.getViewport().setBackground(Color.white);
		add(sp1).setBounds(100,110, 800,100);
		add(check1).setBounds(100, 217, 17, 17);
			check1.setBackground(Color.white);
		add(lbl1).setBounds(120, 215, 400, 25);
			lbl1.setFont(fnt);
		
		//table2 탑승자 정보
		add(titleLbl2).setBounds(410, 230, 200, 50);
			titleLbl2.setFont(titleFnt);
		model2  = new DefaultTableModel(passengerStr, 0);
		table2 = new JTable(model2);
		sp2 = new JScrollPane(table2);
		add(sp2).setBounds(100,280, 800, 100);
			sp2.getViewport().setBackground(Color.white);
		add(check2).setBounds(100, 387, 17,17);
			check2.setBackground(Color.white);
			check2.setEnabled(false);
		add(lbl2).setBounds(120, 385, 470, 25);
			lbl2.setFont(fnt);
		
		//table3 결제정보
		add(titleLbl3).setBounds(425, 410, 200, 50);
			titleLbl3.setFont(titleFnt);
		model3 = new DefaultTableModel(paymentStr,0);
		table3 = new JTable(model3);
		sp3 = new JScrollPane(table3);
		add(sp3).setBounds(300,460, 400, 100);
			sp3.getViewport().setBackground(Color.white);
		add(check3).setBounds(300, 567, 17, 17); //283
			check3.setBackground(Color.white);
			check3.setEnabled(false);
		add(lbl3).setBounds(320, 565, 400, 25); //310
			lbl3.setFont(fnt);
			
		//buttons
		add(payBtn).setBounds(370, 610, 100, 30);
			payBtn.setFont(fnt);
			payBtn.setBackground(new Color(0,130,255));
			payBtn.setForeground(Color.white);
		add(cancelBtn).setBounds(530, 610, 100, 30);
			cancelBtn.setFont(fnt);
			cancelBtn.setBackground(new Color(0,130,255));
			cancelBtn.setForeground(Color.white);
		
		setSize(1000, 800);
		setVisible(true);
		
		//이벤트
		check1.addItemListener(this);
		check2.addItemListener(this);
		check3.addItemListener(this);
		
		payBtn.addMouseListener(this);
			payBtn.setEnabled(false);
		cancelBtn.addMouseListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==ItemEvent.SELECTED) {
			if(ie.getItem()==check1) {
				check2.setEnabled(true);
			}else if(ie.getItem()==check2) {
				check3.setEnabled(true);
			}else {
				allSelected = 1;
				payBtn.setEnabled(true);
				//System.out.println(allSelected);
			}
		}else if(ie.getStateChange()==ItemEvent.DESELECTED) {
			if(ie.getItem()==check1) {
				allSelected = 0;
			}else if(ie.getItem()==check2){
				allSelected = 0;
			}else if(ie.getItem()==check3) {
				allSelected = 0;
			}
		}
		
	}
	
	public void setMileageUpdate(int mileageTotal, int mileageOld, int mileageNew, String passNo) {//결제창에서 결제버튼 누르면 마일리지 업데이트
		CustomBookingChange3VO vo = new CustomBookingChange3VO();
		vo.setMileageTotal(mileageTotal);
		vo.setMileageOld(mileageOld);
		vo.setMileageNew(mileageNew);
		vo.setUserPassNo(passNo);
		
		CustomBookingChange3DAO dao = new CustomBookingChange3DAO();
		int result = dao.mileageUpdate(vo);
		
	}
	public void setBookingUpdate(String newFlightFromModel1, String resNo) {//결제창에서 결제버튼 눌리면 예약정보에서 항공편명 업데이트
		CustomBookingChange3VO vo = new CustomBookingChange3VO();
		vo.setNewFlightNum(newFlightFromModel1);
		vo.setResNo(resNo);
		System.out.println("도와주세요_>"+newFlightFromModel1);
		
		CustomBookingChange3DAO dao = new CustomBookingChange3DAO();
		int result = dao.bookingUpdate(vo);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "예약정보가 변경되었습니다.");
		}else {
			JOptionPane.showMessageDialog(this, "예약정보 수정이 실패하였습니다. \n 다시 시도해주세요.");
			this.setVisible(false);
			paymentWindowState = 0;
		}
		
	}
	///**************************************************************************************TABLE1
	public void table1Print() {
		String newFlight = CustomBookingChange2.newFlightNum;//t1,t3
		
		String resNo = CustomBookingChange1.getResNo;//t1,t2
		String oldFlight = CustomBookingChange1.getFlight;//t3
		
		setTable1(newFlight, resNo);
		//System.out.println(newFlight+"        "+resNo);
		//System.out.println(oldFlight);
	}
	public void setTable1(String newFlight, String resNo) {
		CustomBookingChange3DAO dao = new CustomBookingChange3DAO();
		List<CustomBookingChange3VO> lst = dao.getTable1(newFlight, resNo);
		
		setTable1List(lst);
	}
	public void setTable1List(List<CustomBookingChange3VO> lst) {
		model1.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			CustomBookingChange3VO vo = lst.get(i);
			Object[] data = {vo.getDep(), vo.getDes(), vo.getBrdDate(), vo.getDepTime(),
					vo.getDesTime(), vo.getFlightNo(), vo.getSeatNo(), vo.getFare()};

			model1.addRow(data);
			}
	}
	
///**************************************************************************************TABLE2
	public void table2Print() {
		String resNo = CustomBookingChange1.getResNo;//t1,t2
		
		setTable2(resNo);
	}
	public void setTable2(String resNo) {
		CustomBookingChange3DAO dao = new CustomBookingChange3DAO();
		List<CustomBookingChange3VO> lst = dao.getTable2(resNo);
		
		setTable2List(lst);
	}
	public void setTable2List(List<CustomBookingChange3VO> lst) {
		model2.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			CustomBookingChange3VO vo = lst.get(i);
			Object[] data = {vo.getUserName(), vo.getUserEname(), vo.getUserPassNo(), vo.getUserExdate(),
					vo.getUserNation(), vo.getUserBirth(), vo.getUserTel(), vo.getUserEmail()};

			model2.addRow(data);
			}
	}
	///**************************************************************************************TABLE3
	public void table3Print() {
		String oldFlight = CustomBookingChange1.getFlight;
		String newFlight = CustomBookingChange2.newFlightNum;
		
		setTable3(oldFlight, newFlight);
	}
	public void setTable3(String oldFlight, String newFlight) {
		CustomBookingChange3DAO dao = new CustomBookingChange3DAO();
		List<CustomBookingChange3VO> lst = dao.getTable3(oldFlight, newFlight);
		
		setTable3List(lst);
	}
	public void setTable3List(List<CustomBookingChange3VO> lst) {
		model3.setRowCount(0);
		
		//for(int i=0;i<lst.size();i++) {
			CustomBookingChange3VO vo1 = lst.get(0);//변경 전
			CustomBookingChange3VO vo2 = lst.get(1);//변경 후
			
			Object data = vo1.getOldFare();//변경전
			Object[] data1 = {data, vo2.getOldFare()};//변경전 운임과 변경후 운임을 같은 배열에 넣어주고 
			
			model3.addRow(data1);// 운임 배열은 테이블에 넣는다
			
			System.out.println("oldFare->"+vo1.getOldFare());//변경전 확인용
			System.out.println("NewFare->"+vo2.getOldFare());//변경후 확인용
		//}
	}
	@Override
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if(obj instanceof JButton) {
			String btnStr = ((JButton) obj).getText();
			if(btnStr.equals("결제하기")) {
				if(allSelected==0) {
					JOptionPane.showMessageDialog(this, "모든 내용을 확인해주세요");
				}else if(allSelected != 0) {
					if(paymentWindowState==0) {
						String fare1 = (String) model3.getValueAt(0, 0);
						String fare2 = (String) model3.getValueAt(0, 1);
						int oldFare = Integer.parseInt(fare1);
						int newFare = Integer.parseInt(fare2);
						int rowCount = model1.getRowCount();
						
						if(oldFare>newFare) {
							JOptionPane.showMessageDialog(this, "예약 변경이 완료되었습니다. \n 이용해주셔서 감사합니다.");
													
							
							CustomFrame.bookingChange4.table1Print();
							CustomFrame.bookingChange4.table2Print();
							
							
							this.setVisible(false);
							CustomFrame.bookingChange4.setVisible(true);
							CustomFrame.centerPane.add(CustomFrame.bookingChange4);
						}else {
							new CustomChangePayment(oldFare,newFare, rowCount);
							paymentWindowState = 1;
						}
					}
				}
			}else if(btnStr.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
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
	/////////////////////////////////////////////////////////////////////////
								//결제창//
	////////////////////////////////////////////////////////////////////////
	class CustomChangePayment extends JFrame implements ActionListener, WindowListener{
		Font fnt = new Font("굴림체", Font.BOLD, 14);
		
		JLabel amountLbl1 = new JLabel("초기 운임");
		JLabel amountLbl2 = new JLabel("555000");
		JLabel wonLbl = new JLabel("원");
		
		JLabel amountLbl3 = new JLabel("변경된 운임");
		JLabel amountLbl4 = new JLabel("570000");
		JLabel wonLbl2 = new JLabel("원");
		
		JLabel paymentLbl = new JLabel("결제수단");
		String paymentStr[] = {"카드결제", "계좌이체"};
		JComboBox<String> paymentBox;
		
		JLabel cardNumLbl = new JLabel("카드번호");
		JTextField cardNumTf= new JTextField(20);
		JLabel bankLbl = new JLabel("우리은행");
		JLabel numLbl = new JLabel("0001-010-05-0002");
		
		
		
		JLabel mileageLbl1 = new JLabel("적립 마일리지");
		JLabel mileageLbl2 = new JLabel("570");
//		JLabel mileageLbl3 = new JLabel("등급명");
//		JLabel mileageLbl4 = new JLabel("보유 마일리지");
//		JLabel mileageLbl5 = new JLabel("3800");
//		JButton mileageBtn = new JButton("사용하기");
		
		JLabel finalPayLbl1 = new JLabel("최종 결제금액");
		JLabel finalPayLbl2 = new JLabel("15000");
		
		JLabel wonLbl3 = new JLabel("원");
		
		JButton payBtn = new JButton("결제");
		JButton cancelBtn = new JButton("취소");
		
		public CustomChangePayment(int oldFare, int newFare, int rowCount) {
			setLayout(null);
			setBackground(Color.white);
			
			
			add(amountLbl1).setBounds(60,70, 80,25); amountLbl1.setFont(fnt);
			add(amountLbl2).setBounds(200,70, 100,25);amountLbl2.setFont(fnt);
			amountLbl2.setText(Integer.toString(oldFare*rowCount));
			add(wonLbl).setBounds(270,70, 20,25); wonLbl.setFont(fnt);
			
			add(amountLbl3).setBounds(60,100, 80, 25); amountLbl3.setFont(fnt); 
			add(amountLbl4).setBounds(200, 100, 100, 25);amountLbl4.setFont(fnt); 
			amountLbl4.setText(Integer.toString(newFare*rowCount));
			add(wonLbl2).setBounds(270,100, 20,25);	wonLbl2.setFont(fnt);
				
			add(paymentLbl).setBounds(60, 130, 80, 25); paymentLbl.setFont(fnt);
				paymentBox = new JComboBox<String>(paymentStr);
				paymentBox.setBackground(Color.white);
			add(paymentBox).setBounds(200, 130, 100, 25); paymentBox.setFont(fnt);
			
				
			add(cardNumLbl).setBounds(60, 160, 80, 25); cardNumLbl.setFont(fnt);
			add(cardNumTf).setBounds(200,160, 160, 25);	cardNumTf.setFont(fnt); cardNumTf.setBackground(Color.white);
			add(bankLbl).setBounds(60, 160, 80, 25); bankLbl.setFont(fnt); bankLbl.setVisible(false);
			add(numLbl).setBounds(200,160, 160, 25);numLbl.setFont(fnt); numLbl.setVisible(false);
				
			add(mileageLbl1).setBounds(60,190, 100,25); mileageLbl1.setFont(fnt);
			add(mileageLbl2).setBounds(200,190, 60,25); mileageLbl2.setFont(fnt);
			int mileage = (int)(newFare * 0.0001);
			mileageLbl2.setText(Integer.toString(mileage));
			//add(mileageLbl3).setBounds(270,190, 60, 25); mileageLbl3.setFont(fnt);
			
					
			add(finalPayLbl1).setBounds(60,220, 100,25); finalPayLbl1.setFont(fnt);
			add(finalPayLbl2).setBounds(200,220, 100,25); finalPayLbl2.setFont(fnt);
			
			finalPayLbl2.setText(Integer.toString((newFare-oldFare)*rowCount));
			add(wonLbl3).setBounds(270,220, 20,25); wonLbl3.setFont(fnt);
			
			add(payBtn).setBounds(75,320, 100, 30); payBtn.setFont(fnt);
				payBtn.setBackground(new Color(0,130,255));
				payBtn.setForeground(Color.white);
			add(cancelBtn).setBounds(220,320, 100,30); cancelBtn.setFont(fnt);
				cancelBtn.setBackground(new Color(0,130,255));
				cancelBtn.setForeground(Color.white);
				
			setSize(400, 450);
			setVisible(true);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			paymentBox.addActionListener(this);
			payBtn.addActionListener(this);
			cancelBtn.addActionListener(this);
			addWindowListener(this);
		}
		public void getMileage(String passNo) {
			CustomBookingChange3DAO dao = new CustomBookingChange3DAO();
			
			int oldFare = Integer.parseInt((String)CustomFrame.bookingChange3.model3.getValueAt(0, 0));
			int newFare = Integer.parseInt((String)CustomFrame.bookingChange3.model3.getValueAt(0, 1));

			int mileageTotal = dao.getMileage(passNo);//현재 누적마일리지
			int mileageOld = (int)(oldFare*0.0001);//변경전 운임 마일리지
			int mileageNew = (int)(newFare*0.0001);//변경후 운임 마일리지
			
			CustomFrame.bookingChange3.setMileageUpdate(mileageTotal, mileageOld, mileageNew, passNo);
			
			System.out.println(mileageTotal+"그리고"+mileageOld+"그리고또"+mileageNew);
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if(obj instanceof JComboBox) {
				String comboItem = (String)paymentBox.getSelectedItem();
				//System.out.println(comboItem);
				
				if(comboItem.equals("계좌이체")) {
					cardNumLbl.setVisible(false);
					bankLbl.setVisible(true);
					cardNumTf.setVisible(false);
					numLbl.setVisible(true);
				}else if(comboItem.equals("카드결제")) {
					bankLbl.setVisible(false);
					cardNumLbl.setVisible(true);
					numLbl.setVisible(false);
					cardNumTf.setVisible(true);
				}
			}else if(obj instanceof JButton) {
				String btnStr = ((JButton) obj).getText();
				if(btnStr.equals("취소")) {
					JOptionPane.showMessageDialog(this, "예약변경이 취소되었습니다.");
					this.setVisible(false);
					paymentWindowState = 0;
				}else if(btnStr.equals("결제")) {
					JOptionPane.showMessageDialog(this, "결제가 완료되었습니다");
					String newFlightFromModel1 = (String)CustomFrame.bookingChange3.model1.getValueAt(0, 5);
					String resno = CustomFrame.bookingChange1.getResNo;
					CustomFrame.bookingChange3.setBookingUpdate(newFlightFromModel1, resno);
					
					String passNo = (String)CustomFrame.bookingChange3.model2.getValueAt(0, 2);
					System.out.println(passNo);
					getMileage(passNo);
					
					CustomFrame.bookingChange4.table1Print();
					CustomFrame.bookingChange4.table2Print();
					
					this.setVisible(false);
					CustomFrame.bookingChange3.setVisible(false);
					CustomFrame.bookingChange4.setVisible(true);
					CustomFrame.centerPane.add(CustomFrame.bookingChange4);
				}
			}
			
		}
		
		@Override
		public void windowOpened(WindowEvent e) {
			paymentWindowState = 1;
		}

		@Override
		public void windowClosing(WindowEvent e) {
			paymentWindowState = 0;
		}

		@Override
		public void windowClosed(WindowEvent e) {}

		@Override
		public void windowIconified(WindowEvent e) {}

		@Override
		public void windowDeiconified(WindowEvent e) {}

		@Override
		public void windowActivated(WindowEvent e) {}

		@Override
		public void windowDeactivated(WindowEvent e) {}

		

	}
}
