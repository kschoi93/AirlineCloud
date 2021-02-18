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

public class CustomBookingChange3 extends JPanel implements MouseListener, ItemListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel titleLbl1 = new JLabel("선택 항공 내역");
	String flightStr[] = {"출발지", "도착지", "출발일", "도착일", "촐발시간", "도착시간", "항공편명", "좌석", "운임"};
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
	String paymentStr[] = {"구분",  "변경 전", "변경 후", "결제예정금액"};
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
				System.out.println(allSelected);
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
						new CustomChangePayment();
						paymentWindowState = 1;
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
	class CustomChangePayment extends JFrame implements ActionListener, MouseListener, WindowListener{
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
		JLabel mileageLbl3 = new JLabel("등급명");
		JLabel mileageLbl4 = new JLabel("보유 마일리지");
		JLabel mileageLbl5 = new JLabel("3800");
		JButton mileageBtn = new JButton("사용하기");
		
		JLabel finalPayLbl1 = new JLabel("최종 결제금액");
		JLabel finalPayLbl2 = new JLabel("15000");
		
		JLabel wonLbl3 = new JLabel("원");
		
		JButton payBtn = new JButton("결제");
		JButton cancelBtn = new JButton("취소");
		
		public CustomChangePayment() {
			setLayout(null);
			setBackground(Color.white);
			
			add(amountLbl1).setBounds(60,70, 80,25); amountLbl1.setFont(fnt);
			add(amountLbl2).setBounds(200,70, 100,25);amountLbl2.setFont(fnt);
			add(wonLbl).setBounds(270,70, 20,25); wonLbl.setFont(fnt);
			
			add(amountLbl3).setBounds(60,100, 80, 25); amountLbl3.setFont(fnt); 
			add(amountLbl4).setBounds(200, 100, 100, 25);amountLbl4.setFont(fnt); 
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
			//add(mileageLbl3).setBounds(270,190, 60, 25); mileageLbl3.setFont(fnt);
			
					
			add(finalPayLbl1).setBounds(60,220, 100,25); finalPayLbl1.setFont(fnt);
			add(finalPayLbl2).setBounds(200,220, 100,25); finalPayLbl2.setFont(fnt);
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
			payBtn.addMouseListener(this);
			cancelBtn.addMouseListener(this);
			addWindowListener(this);
		}
		@Override
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if(obj instanceof JComboBox) {
				String comboItem = (String)paymentBox.getSelectedItem();
				System.out.println(comboItem);
				
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
			}
			
		}
		@Override
		public void mouseClicked(MouseEvent me) {
			Object obj = me.getSource();
			if(obj instanceof JButton) {
				String btnName = ((JButton) obj).getText();
				if(btnName.equals("결제")) {
					JOptionPane.showMessageDialog(this, "결제가 완료되었습니다");
					this.setVisible(false);
//					paymentState = 1; // 1이면 결제를 했다는 뜻이니까 cbc4로 화면 전환 가자!!
//					System.out.println(paymentState);
					CustomFrame.bookingChange3.setVisible(false);
					CustomFrame.bookingChange4.setVisible(true);
					CustomFrame.centerPane.add(CustomFrame.bookingChange4);
				}
			}
			
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}

		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}

		
		
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
