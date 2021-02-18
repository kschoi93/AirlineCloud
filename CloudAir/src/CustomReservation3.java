
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class CustomReservation3 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
	JPanel wrapPane = new JPanel();
		JPanel lblPane= new JPanel();
			JLabel titleLbl = new JLabel("예약자의 정보");
			JPanel hanPane = new JPanel();
				JLabel hanLbl = new JLabel("예약자 성명(한)");
				JTextField hanField = new JTextField(30);
			JPanel engPane = new JPanel();
				JLabel engLbl = new JLabel("예약자 성명(영어)");
				JTextField engField = new JTextField(30);
			JPanel passportPane = new JPanel();
				JLabel	passportLbl = new JLabel("여권번호");
				JTextField passportField = new JTextField(30);
			JPanel passEndPane = new JPanel();
				JLabel passEndLbl = new JLabel("여권만료일");
				JTextField passEndField = new JTextField(30);
			JPanel birthPane = new JPanel();
				JLabel birthLbl = new JLabel("생년월일");
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
				JLabel telLbl = new JLabel("연락처");
				JTextField telField = new JTextField(30);
			JPanel emailPane = new JPanel();
				JLabel emailLbl = new JLabel("이메일");
				JTextField emailField = new JTextField(30);
				
			JPanel checkPane = new JPanel();
				JCheckBox checkBox = new JCheckBox();
				JLabel checkStr = new JLabel("해당 정보를 맞게 입력하셨나요? 위 내용은 예약 완료 후 변경이 불가합니다");
				int c = 0;
			JPanel buttonPane = new JPanel();
				JButton saveBtn = new JButton("임시저장");
				JButton nextBtn = new JButton("다음단계");
				JButton cancelBtn = new JButton("예약취소");
		JPanel[] pane = {hanPane,engPane,passportPane,passEndPane,birthPane,telPane,emailPane};
		JLabel[] lbl = {hanLbl,engLbl,passportLbl,passEndLbl,birthLbl,telLbl,emailLbl};
		JTextField[] tf = {hanField,engField,passportField,passEndField,birthField,telField,emailField};
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
			checkPane.add("Center",checkStr);
				checkStr.setHorizontalAlignment(JLabel.RIGHT);
				checkStr.setBackground(Color.white);
				checkStr.setFont(fnt);
		
		wrapPane.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
			buttonPane.setBackground(Color.white);
				buttonPane.add(saveBtn);
					saveBtn.setFont(fnt);
					saveBtn.setForeground(Color.white);
					saveBtn.setBackground(new Color(0,130,255));
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
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if(btn.equals("임시저장")) {
				
			} else if(btn.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(btn.equals("다음단계")) {
				this.setVisible(false);
				CustomFrame.reservation4.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.reservation4);
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

}
