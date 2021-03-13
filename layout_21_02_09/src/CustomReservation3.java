
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbAll.CustomReservation3FellowVO;
import dbAll.CustomReservation3VO;

public class CustomReservation3 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
	JPanel wrapPane = new JPanel();
		JPanel lblPane= new JPanel();
			JLabel titleLbl = new JLabel("예약자 정보입력");
			JPanel hanPane = new JPanel();
				JLabel hanLbl = new JLabel("예약자 성명(한) ex) 홍길동");
				JTextField hanField = new JTextField(30);
			JPanel engPane = new JPanel();
				JLabel engLbl = new JLabel("예약자 성명(영어) ex) Hong GilDong");
				JTextField engField = new JTextField(30);
			JPanel passportPane = new JPanel();
				JLabel	passportLbl = new JLabel("여권번호 ex) M986541");
				JTextField passportField = new JTextField(30);
			JPanel passEndPane = new JPanel();
				JLabel passEndLbl = new JLabel("여권만료일 ex) 20230204");
				JTextField passEndField = new JTextField(30);
			JPanel birthPane = new JPanel();
				JLabel birthLbl = new JLabel("생년월일 ex) 19950807");
				JTextField birthField = new JTextField(30);
			JPanel genderPane = new JPanel();
				JLabel genderLbl = new JLabel("성별");
				String gender[] = {"F","M"};
				DefaultComboBoxModel<String> genderModel = new DefaultComboBoxModel<String>(gender);
				JComboBox<String> genderCombo = new JComboBox<String>(genderModel);
			JPanel nationPane = new JPanel();
				JLabel nationLbl = new JLabel("여권 발행국가");
				String nation[] = {"KOREA","AUSTRALIA","ARGENTINA","BRAZIL","CANADA","CHINA","FRANCE","GERMANY","ITALY","INDIA","INDONESIA",
						"JAPAN","MEXICO","RUSSIA","RSA","SAUDIARABIA","TURKEY","USA","UK"};
				DefaultComboBoxModel<String> nationModel = new DefaultComboBoxModel<String>(nation);
				JComboBox<String> nationCombo = new JComboBox<String>(nationModel);
			JPanel telPane = new JPanel();
				JLabel telLbl = new JLabel("연락처 ex) 010-1234-5678");
				JTextField telField = new JTextField(30);
			JPanel emailPane = new JPanel();
				JLabel emailLbl = new JLabel("이메일 ex) hong123@naver.com");
				JTextField emailField = new JTextField(30);
				
			JPanel checkPane = new JPanel();
				JCheckBox checkBox = new JCheckBox();
				JLabel checkStr = new JLabel("해당 정보를 맞게 입력하셨나요? 위 내용은 예약 완료 후 변경이 불가합니다");
				JLabel checkStr2 = new JLabel("정보 변경을 희망할 경우 예약취소를 하시고 다시 시도해주시기 바랍니다");
				int c = 0;
			JPanel buttonPane = new JPanel();
				JButton nextBtn = new JButton("다음단계");
				JButton cancelBtn = new JButton("예약취소");
		JPanel[] pane = {hanPane,engPane,passportPane,passEndPane,birthPane,telPane,emailPane};
		JLabel[] lbl = {hanLbl,engLbl,passportLbl,passEndLbl,birthLbl,telLbl,emailLbl};
		JTextField[] tf = {hanField,engField,passportField,passEndField,birthField,telField,emailField};
		
		static int nextCheck = 0; // 동승자 예약까지 하기위해 next버튼 누른 횟수를 저장한다.
		int count ;
		
		static List<CustomReservation3FellowVO> fellowLst = new ArrayList<CustomReservation3FellowVO>();
		static List<CustomReservation3VO> lst = new ArrayList<CustomReservation3VO>();
	public CustomReservation3() {
		setLayout(new BorderLayout());
		add(main);
		main.setLayout(new BorderLayout(200,0));
		main.add("North",new JLabel());
		main.add("East",new JLabel());
		main.add("West",new JLabel());
		main.add("South",new JLabel());
		main.setBackground(Color.white);
		main.add(wrapPane);
		wrapPane.setLayout(new GridLayout(12,1));
		wrapPane.setBackground(Color.white);
		wrapPane.add(titleLbl);
			titleLbl.setHorizontalAlignment(JLabel.CENTER);
			titleLbl.setOpaque(true);
			titleLbl.setBackground(Color.white);
			titleLbl.setFont(titleFnt);
		
		for(int i=0; i<pane.length;i++) {
			wrapPane.add(pane[i]);
			pane[i].setLayout(new BorderLayout(0,15));
			pane[i].setBackground(Color.white);
			pane[i].add("North",new JLabel());
			pane[i].add("South",new JLabel());
			pane[i].add("West", lbl[i]);
			pane[i].add("East", tf[i]);
			lbl[i].setOpaque(true);
			lbl[i].setFont(fnt);
			lbl[i].setBackground(Color.white);
			lbl[i].setHorizontalAlignment(JLabel.CENTER);
			tf[i].setFont(fnt);
		}
		wrapPane.add(nationPane);
		nationPane.setBackground(Color.white);
		nationPane.setLayout(new BorderLayout(0,15));
		nationPane.add("South",new JLabel());
			nationPane.add("West",nationLbl);
				nationLbl.setFont(fnt);
			nationPane.add("East", nationCombo);
				nationCombo.setFont(fnt);
				nationCombo.setBackground(Color.white);
		
		wrapPane.add(genderPane);
		genderPane.setLayout(new BorderLayout(0,15));
		genderPane.setBackground(Color.white);
		genderPane.add("South",new JLabel());
			genderPane.add("West",genderLbl);
				genderLbl.setFont(fnt);
			genderPane.add("East",genderCombo);
				genderCombo.setFont(fnt);
				genderCombo.setBackground(Color.white);
		
		wrapPane.add(checkPane);
			checkPane.setBorder(new LineBorder(Color.lightGray,1));
			checkPane.setBackground(Color.white);
			checkPane.setLayout(new BorderLayout(5,0));
			checkPane.add("West",checkBox);
				checkBox.setBackground(Color.white);
			JPanel checkLblPane = new JPanel();
			checkPane.add("Center",checkLblPane);
				checkLblPane.setBackground(Color.white);
				checkLblPane.add(checkStr);
				checkStr.setHorizontalAlignment(JLabel.RIGHT);
				checkStr.setBackground(Color.white);
				checkStr.setFont(fnt);
				checkLblPane.add(checkStr2);
				checkStr2.setHorizontalAlignment(JLabel.RIGHT);
				checkStr2.setBackground(Color.white);
				checkStr2.setFont(fnt);
		
		wrapPane.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
			buttonPane.setBackground(Color.white);
				buttonPane.add(cancelBtn);
					cancelBtn.setFont(fnt);
					cancelBtn.setForeground(Color.white);
					cancelBtn.setBackground(new Color(0,130,255));
				buttonPane.add(nextBtn);
					nextBtn.setEnabled(false);
					nextBtn.setFont(fnt);
					nextBtn.setForeground(Color.white);
					nextBtn.setBackground(new Color(0,130,255));
		

		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);

		checkBox.addActionListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if(btn.equals("예약취소")) {
				// 전부 초기화
				for(int i=0; i<tf.length; i++) {
					tf[i].setText("");
				}
				setTrue();
				lst.removeAll(lst);
				fellowLst.removeAll(fellowLst);
				titleLbl.setText("예약자 정보입력");
				hanLbl.setText("예약자 이름(한글) ex) 홍길동");
				engLbl.setText("예약자 이름(영어) ex) Hong GilDong");
				checkBox.setSelected(false);
				nextBtn.setEnabled(false);
				c=0;
				nextCheck=0;
				
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(btn.equals("다음단계")) {
				count = CustomReservation.humanCount;
				if(nextCheck==0 && nextCheck < count) {
					// 다음 눌렀느데 count가 인원수가 nextcheck랑 동일하면 다음화면 이동
					if( (nextCheck +1) == count) {
						
						lst.removeAll(lst);
						bookingRegist(lst);	
						
						setFalse();
						this.setVisible(false);
						// 다음을 누르면 reservation4를 세팅한다
						CustomFrame.reservation4.setStartSeatPaint();
						
						CustomFrame.reservation4.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation4);
					} else { // 아니라면 ++;
						// 리스트에 담는다
					
						bookingRegist(lst);
						// 전부 초기화
						for(int i=0; i<tf.length; i++) {
							tf[i].setText("");
						}
						checkBox.setSelected(false);
						nextBtn.setEnabled(false);
						c=0;
						titleLbl.setText("동승자 정보입력");
						hanLbl.setText("동승자 이름(한글) ex) 홍길동");
						engLbl.setText("동승자 이름(영어) ex) Hong GilDong");
						nextCheck++;
					}
					
				} else if(nextCheck < count) {
					if((nextCheck+1) == count) {
						
						if(!(fellowLst.size()==(count-1))) {
							bookingFellowRegist(fellowLst);	
						}
						
						setFalse();
						this.setVisible(false);
						// 다음을 누르면 reservation4를 세팅한다
						CustomFrame.reservation4.setStartSeatPaint();
						
						CustomFrame.reservation4.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation4);
						
					} else {
						bookingFellowRegist(fellowLst);
						for(int i=0; i<tf.length; i++) {
							tf[i].setText("");
						}
						setTrue();
						checkBox.setSelected(false);
						nextBtn.setEnabled(false);
						c=0; 
						nextCheck++;
					}
				} 

				
			}
		}
		if(obj instanceof JCheckBox) {
			if(c==0) {
				nextBtn.setEnabled(true);
				c++;
			} else if(c==1) {
				nextBtn.setEnabled(false);
				c--;
			}
		}
	}
	// 예약자 정보 입력
	public void bookingRegist(List<CustomReservation3VO> lst) {
		String han = hanField.getText();
		String eng = engField.getText();
		String pas = passportField.getText();
		String pasE = passEndField.getText();
		String bir = birthField.getText();
		String tel = telField.getText();
		String email = emailField.getText();
		String nation = (String) nationCombo.getSelectedItem();
		String gender = (String) genderCombo.getSelectedItem();
		CustomReservation3VO vo = new CustomReservation3VO(han,eng,pas,pasE,bir,tel,email,nation,gender);
		lst.add(vo);
	}
	
	// 동승자 정보 입력
	public void bookingFellowRegist(List<CustomReservation3FellowVO> fellowlst) {
		String han = hanField.getText();
		String eng = engField.getText();
		String pas = passportField.getText();
		String pasE = passEndField.getText();
		String bir = birthField.getText();
		String tel = telField.getText();
		String email = emailField.getText();
		String nation = (String) nationCombo.getSelectedItem();
		String gender = (String) genderCombo.getSelectedItem();
		CustomReservation3FellowVO vo = new CustomReservation3FellowVO(han,eng,pas,pasE,bir,tel,email,nation,gender);
		fellowlst.add(vo);
	}
	// 만약, 인원수가 변동이 있어서 줄어든다? 그러면 모두다 초기화하고 다시 받아라
	public void replaceCount() {
		setTrue();
		// 전부 초기화
		for(int i=0; i<tf.length; i++) {
			tf[i].setText("");
		}
		lst.removeAll(lst);
		fellowLst.removeAll(fellowLst);
		titleLbl.setText("예약자 정보입력");
		hanLbl.setText("예약자 이름(한글) ex) 홍길동");
		engLbl.setText("예약자 이름(영어) ex) Hong GilDong");
		checkBox.setSelected(false);
		nextBtn.setEnabled(false);
		c=0;
		nextCheck = 0;
	}
	
	// 모든 필드, 콤보를 트루로 다시 바꾼다
	public void setTrue() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setEnabled(true);
		}
		nationCombo.setEnabled(true);
		genderCombo.setEnabled(true);
	}
	
	// 모든 필드, 콤보를 펄스로 다시 바꾼다
	public void setFalse() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setEnabled(false);
		}
		nationCombo.setEnabled(false);
		genderCombo.setEnabled(false);
	}
}
