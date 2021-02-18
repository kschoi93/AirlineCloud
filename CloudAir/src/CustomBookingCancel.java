import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomBookingCancel extends JPanel implements MouseListener, ItemListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel titleLbl1 = new JLabel("선택 항공 내역");
	String flightStr[] = {"출발지", "도착지", "출발일", "도착일", "촐발시간", "도착시간", "항공편명", "좌석", "운임"};
	JTable table1;
	JScrollPane sp1;
	DefaultTableModel model1;
	JCheckBox check1 = new JCheckBox();
	JLabel lbl1 = new JLabel("상기 내용을 확인하고 취소를 진행합니다.");
	
	JLabel titleLbl2 = new JLabel("탑승자 정보");
	String passengerStr[] = {"성명(한)", "성명(영)", "여권번호", "여권만료일", "발행국가", "생년월일", "연락처", "이메일"};
	JTable table2;
	JScrollPane sp2;
	DefaultTableModel model2;
	JCheckBox check2 = new JCheckBox();
	JLabel lbl2 = new JLabel("탑승자 정보를 확인해주세요.");
	
	JLabel titleLbl3 = new JLabel("환불 내역");
	String paymentStr[] = {"구분",  "환불예정금액"};
	JTable table3;
	JScrollPane sp3;
	DefaultTableModel model3;
	JCheckBox check3 = new JCheckBox();
	JLabel lbl3 = new JLabel("상기 내용을 확인하고 취소를 진행합니다.");
	
	JButton payBtn = new JButton("예약취소");
	JButton cancelBtn = new JButton("돌아가기");
	
	//이벤트용 변수
	int allSelected = 0;
	public CustomBookingCancel() {
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
		
		
		//이벤트등록
		check1.addItemListener(this);
		check2.addItemListener(this);
		check3.addItemListener(this);
		
		payBtn.addMouseListener(this);
		cancelBtn.addMouseListener(this);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btnStr = ((JButton) obj).getText();
			if(btnStr.equals("예약취소")) {
				System.out.println("11111");
				if(allSelected == 0) {
					JOptionPane.showMessageDialog(this, "모든 내용을 확인해주세요.");
				}else if(allSelected == 1) {
					JOptionPane.showMessageDialog(this, "예약이 취소되었습니다.");
					//그리고 예약이 디비에서도 삭제되는 메소드
					this.setVisible(false);
					CustomFrame.plan.setVisible(true);
					CustomFrame.centerPane.add(CustomFrame.plan);
				}
			}else if(btnStr.equals("돌아가기")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()== ItemEvent.SELECTED) {
			if(ie.getItem()==check1) {
				check2.setEnabled(true);
			}else if(ie.getItem()==check2) {
				check3.setEnabled(true);
			}else {
				allSelected = 1;
				System.out.println(allSelected);
			}
		}else if(ie.getStateChange()==ItemEvent.DESELECTED) {
			if(ie.getItem()==check1) {
				allSelected = 0;
			}else {
				allSelected = 0;
			}
		}
		
	}

}
