
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbAll.CustomReviseDAO;
import dbAll.CustomReviseVO;

public class CustomRevise extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
	JPanel lblPane= new JPanel();
		JLabel titleLbl = new JLabel("회원정보수정");
		JPanel hanPane = new JPanel();
			JLabel hanLbl = new JLabel("이름(한글)");
			JTextField hanField = new JTextField(30);
		JPanel engPane = new JPanel();
			JLabel engLbl = new JLabel("이름(영문) ex) Kyung Sik Choi");
			JTextField engField = new JTextField(30);
		JPanel pwdPane = new JPanel();
			JLabel pwdLbl = new JLabel("비밀번호 ex) 특수문자 !@#만 사용");
			JPasswordField pwdField = new JPasswordField(30);
		JPanel pwdCheckPane = new JPanel();
			JLabel pwdCheckLbl = new JLabel("비밀번호 확인");
			JPasswordField pwdCheckField = new JPasswordField(30);
		JPanel telPane = new JPanel();
			JLabel telLbl = new JLabel("연락처 ex) 010-0000-0000");
			JTextField telField = new JTextField(30);
		JPanel birthPane = new JPanel();
			JLabel birthLbl = new JLabel("생년월일 ex) 910101");
			JTextField birthField = new JTextField(30);
		JPanel emailPane = new JPanel();
			JLabel emailLbl = new JLabel("이메일 ex) admin93@gmail.com");
			JTextField emailField = new JTextField(30);
		JPanel nationPane = new JPanel();
			JLabel nationLbl = new JLabel("국적");
			String nation[] = {"KOREA","AUSTRALIA","ARGENTINA","BRAZIL","CANADA","CHINA","FRANCE","GERMANY","ITALY","INDIA","INDONESIA",
					"JAPAN","MEXICO","RUSSIA","RSA","SAUDIARABIA","TURKEY","USA","UK"};
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(nation);
			JComboBox<String> nationCombo = new JComboBox<String>(model);
		
		JPanel buttonPane = new JPanel();
			JButton saveBtn = new JButton("저장");
			JButton cancelBtn = new JButton("취소");
			JButton withdrawalBtn = new JButton("회원탈퇴");
		JPanel[] pane = {hanPane,engPane,pwdPane,pwdCheckPane,telPane,birthPane,emailPane};
		JLabel[] lbl = {hanLbl,engLbl,pwdLbl,pwdCheckLbl,telLbl,birthLbl,emailLbl};
		JTextField[] tf = {hanField,engField,pwdField,pwdCheckField,telField,birthField,emailField};
		
	public CustomRevise() {
		setLayout(new BorderLayout(250,50));
		add("North",new JLabel());
		add("East",new JLabel());
		add("West",new JLabel());
		add("South",new JLabel());
		add(main);
		main.setBackground(Color.white);
		main.setLayout(new GridLayout(0,1));
		main.add(titleLbl);
			titleLbl.setHorizontalAlignment(JLabel.CENTER);
			titleLbl.setOpaque(true);
			titleLbl.setBackground(Color.white);
			titleLbl.setFont(titleFnt);
		
		for(int i=0; i<pane.length;i++) {
			main.add(pane[i]);
			pane[i].setLayout(new BorderLayout(0,15));
			pane[i].setBackground(Color.white);
			pane[i].add("North",new JLabel());
			pane[i].add("East",new JLabel());
			pane[i].add("West",new JLabel());
			pane[i].add("South",new JLabel());
			pane[i].add("West", lbl[i]);
			pane[i].add("East", tf[i]);
			lbl[i].setOpaque(true);
			lbl[i].setFont(fnt);
			lbl[i].setBackground(Color.white);
			lbl[i].setHorizontalAlignment(JLabel.CENTER);
			tf[i].setFont(fnt);
		}
		main.add(nationPane);
		nationPane.setBackground(Color.white);
		nationPane.setLayout(new BorderLayout(0,15));
		nationPane.add("East",new JLabel());
		nationPane.add("West",new JLabel());
		nationPane.add("South",new JLabel());
		nationPane.add(nationLbl);
			nationPane.add("West",nationLbl);
				nationLbl.setFont(fnt);
			nationPane.add("East",nationCombo);
				nationCombo.setFont(fnt);
				nationCombo.setBackground(Color.white);
			
		
		
		main.add(buttonPane);
		buttonPane.setBackground(Color.white);
			buttonPane.add(saveBtn);
				saveBtn.setFont(fnt);
				saveBtn.setForeground(Color.white);
				saveBtn.setBackground(new Color(0,130,255));
			buttonPane.add(cancelBtn);
				cancelBtn.setFont(fnt);
				cancelBtn.setForeground(Color.white);
				cancelBtn.setBackground(new Color(0,130,255));
			buttonPane.add(withdrawalBtn);
				withdrawalBtn.setFont(fnt);
				withdrawalBtn.setForeground(Color.white);
				withdrawalBtn.setBackground(new Color(0,130,255));
		
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		saveBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		withdrawalBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			String id = AirlineMain.idField.getText();
			CustomReviseDAO dao = new CustomReviseDAO();
			List<CustomReviseVO> lst = dao.blankCheck(id);
			CustomReviseVO vo = new CustomReviseVO();
			// 적혀있는 부분이 공백일 경우 전에 저장했던 것을 불러와 그대로 저장한다.
			vo = lst.get(0);
			String birthCheck = vo.getUser_birth().substring(2,9);
			if(btn.equals("저장")) {
				if(!pwdField.getText().equals(pwdCheckField.getText())) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				} else if(!pwdField.getText().equals("")) { 
					if(pwdField.getText().length()<6 || pwdField.getText().length()>20) {    
						JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능 합니다.");
					} else if(checkPWDMethod(pwdField.getText())==1) {
						JOptionPane.showMessageDialog(this, "비밀번호에는 특수문자 !@#만 포함 가능 합니다");
					}
				} else if(birthCheck.equals("01/01/01") || birthField.getText().length()>=7) {
					JOptionPane.showMessageDialog(this, "올바른 생년월일 입력하셔야 합니다.");
				} else if(!emailField.getText().equals("")) { // 이메일 텍스트 필드가 공백이면 전에 사용하던 이메일을 저장하고, 무언가 입력되어 있다면 올바른 이메일 인지 확인하는 기능 추가
					if(emailCheck(emailField.getText())== 1) {
						JOptionPane.showMessageDialog(this, "잘못된 이메일을 입력하였습니다");
					} else {
						//회원정보 수정
						CustomInfoUpdate();
					}
				} else {
					CustomInfoUpdate();
				}
			} else if(btn.equals("취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(btn.equals("회원탈퇴")) {
				String msg = "마일리지를 포함한\n고객의 모든 정보가 삭제됩니다.\n탈퇴하시겠습니까?";
				int state = JOptionPane.showOptionDialog(this, msg, "회원탈퇴", JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE, null,null, obj);
				// yes 누르면 0 no 누르면 1 리턴
				if(state==0) {
					// db 연결하여 삭제
					int result = dao.ReviseDelete(AirlineMain.idField.getText());
					if(result>0) {
						JOptionPane.showMessageDialog(this, "회원탈퇴 되었습니다\n이용해주셔서 감사합니다");
						System.exit(0);
					} else {
						JOptionPane.showMessageDialog(this, "회원탈퇴에 실패하였습니다 \n 관리자에게 문의하시기 바랍니다");
					}
					
				}
			}
		}
	}
	
	// 회원정보 수정 업데이트 메소드
	public void CustomInfoUpdate() {
		String id = AirlineMain.idField.getText();
		CustomReviseDAO dao = new CustomReviseDAO();
		String nk = hanField.getText();
		String ne = engField.getText();
		String up = pwdField.getText();
		String ut = telField.getText();
		String ub = birthField.getText();
		// 분명 ... 소문자로 입력하는 사람들 있을 테니까 국가명 무조건 대문자로 변환
		String nt = (String) nationCombo.getSelectedItem();
		String em = emailField.getText();
		// 공백에 대응하도록 하도록 현재 저장되어 있는 값들을 불러온다 
		List<CustomReviseVO> lst = dao.blankCheck(id);
		CustomReviseVO vo = new CustomReviseVO();
		// 적혀있는 부분이 공백일 경우 전에 저장했던 것을 불러와 그대로 저장한다.
		vo = lst.get(0);
		if(nk.equals("")) nk = vo.getUser_name();
		if(ne.equals("")) ne = vo.getUser_ename();
		if(up.equals("")) up = vo.getUser_pwd();
		if(ut.equals("")) ut = vo.getUser_tel();
		if(ub.equals("")) {
			// 생년월일은 String -> Date로 sql에 저장되는데, 다시 가져올때 string으로 가져오기 때문에 (1991-01-01 00:00:00) 원본 데이터를 저장하기 위해서는 다시 19910101로 바꿔줘야 한다.
			String re = vo.getUser_birth().replace("-", "");
			String re2 = re.substring(2,9);
			ub = re2;
		}
		if(em.equals("")) em = vo.getUser_email();
		
		
		int result = dao.ReviseUpdate(id, nk, ne, up, ut, ub, nt, em);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "회원정보 수정이 완료되었습니다");
			this.setVisible(false);
			CustomFrame.plan.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(this, "회원정보 수정 실패하였습니다 \n 관리자에게 문의하시기 바랍니다");
		}
	}
	
	public int checkIDMethod(String id) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<id.length(); i++) {
			alpha = id.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
			}
		}
		return check;
	}
	
	public int checkPWDMethod(String pwd) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<pwd.length(); i++) {
			alpha = pwd.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=32 || code>=36 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
				
			}
		}
		return check;
	}
	
	public int emailCheck(String email) {
		int emailCheck =0;
		// email에 @ 가 있는가? email에 .이 올바르게 있나 ? email에 특수문자가 있나?
		if(email.indexOf("@")== -1 || period(email)==true ||specialCharacter(email)==0) {
			emailCheck = 1;
		}
		// 0이면 이메일 체크결과 이상 없다, 1이면 이상 있다
		return emailCheck;
	}
	
	public Boolean period(String email) {
		String spl[] = email.split("@");
		// split은 @ 기준으로 앞뒤로 나눈다
		// spl[0] = id,  spl[1] = email주소
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); // last와 index의 값차이를 검사
		
		boolean clear = false;
		
		// "@"의 앞단 "." 있는지 확인
		if(spl[0].indexOf(".")==-1);
		else clear=true;
		
		// "@"의 뒷단 도메인 부분의 "." 들의 간격이 4칸이상 떨어지면 이메일이 아니다
		if(num <4);
		else clear = true;
		
		// 이메일에 .이 잘못되었을 경우 true를 보낸다
		return clear;
	}
	
	public int specialCharacter(String email) {
		String text[] = { "#", "!","$","%","^","&","*","(",")","-", "_","+","=",",","[","]","{","}",":",";","'","?","<",">"};
		
		int result = 0;
		for(int i=0; i<text.length; i++) {
			if(email.indexOf(text[i]) == -1) {
				result = 1;
			} else {
				break;
			}
		}
		// 이메일 검사했을때 특수문자가 있으면 1을 보내고 없으면 0을 보낸다
		return result;
	}
}
