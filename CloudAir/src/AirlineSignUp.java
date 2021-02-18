
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import dbAll.AirlineSignUpDAO;
import dbAll.AirlineSignUpVO;

public class AirlineSignUp extends JFrame implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,32);
	JLabel titleLbl = new JLabel("회 원 가 입");
	JLabel idLbl = new JLabel("아이디");
	JTextField idField = new JTextField(30);
	JButton duplicateBtn = new JButton("중복 확인");
	JLabel pwdLbl = new JLabel("비밀번호");
	JPasswordField pwdField = new JPasswordField(30);
	JLabel pwdcheckLbl = new JLabel("비밀번호 확인");
	JPasswordField pwdcheckField = new JPasswordField(30);
	JLabel nameHanLbl = new JLabel("이름(한글)");
	JTextField nameHanField = new JTextField(30);
	JLabel nameEngLbl = new JLabel("이름(영문)");
	JTextField nameEngField = new JTextField(30);
	JLabel telLbl = new JLabel("연락처");
	JTextField telField = new JTextField(30);
	JLabel emailLbl = new JLabel("이메일");
	JTextField emailField = new JTextField(30);
	JLabel genderLbl = new JLabel("성별");
	String gender[] = {"F","M"};
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(gender);
	JComboBox<String> genderCombo = new JComboBox<String>(model);
	JButton signUpBtn = new JButton("회원가입");
	JButton cancelBtn = new JButton("취소");
	AirlineSignUpDAO dao = new AirlineSignUpDAO();
	public AirlineSignUp() {
		setLayout(null);
		int x=300;
		int x1=410;
		add(titleLbl).setBounds(450,100,200,30);
		titleLbl.setFont(titleFnt);
		
		add(idLbl).setBounds(x,200,100,30); add(idField).setBounds(x1,200,250,30); add(duplicateBtn).setBounds(670,200,110,30);
		idLbl.setFont(fnt);
		idField.setFont(fnt);
		duplicateBtn.setFont(fnt);
		duplicateBtn.setBackground(new Color(0,130,255));
		duplicateBtn.setForeground(Color.white);
		
		add(pwdLbl).setBounds(x,250,100,30); add(pwdField).setBounds(x1,250,250,30); 
		pwdLbl.setFont(fnt);
		pwdField.setFont(fnt);
		
		add(pwdcheckLbl).setBounds(x,300,100,30); add(pwdcheckField).setBounds(x1, 300, 250, 30); 
		pwdcheckLbl.setFont(fnt);
		pwdcheckField.setFont(fnt);
		
		add(nameHanLbl).setBounds(x,350,100,30); add(nameHanField).setBounds(x1,350,250,30);
		nameHanLbl.setFont(fnt);
		nameHanField.setFont(fnt);
		
		add(nameEngLbl).setBounds(x,400,100,30); add(nameEngField).setBounds(x1,400,250,30);
		nameEngLbl.setFont(fnt);
		nameEngField.setFont(fnt);
		
		add(telLbl).setBounds(x,450,100,30); add(telField).setBounds(x1,450,250,30);
		telLbl.setFont(fnt);
		telField.setFont(fnt);
		
		add(emailLbl).setBounds(x,500,100,30); add(emailField).setBounds(x1,500,250,30);
		emailLbl.setFont(fnt);
		emailField.setFont(fnt);
		
		add(genderLbl).setBounds(x,550,100,30); add(genderCombo).setBounds(x1,550,250,30);
		genderLbl.setFont(fnt);
		genderCombo.setFont(fnt);
		genderCombo.setBackground(Color.white);
		
		
		add(signUpBtn).setBounds(400,630,100,30); add(cancelBtn).setBounds(530,630,100,30);
		signUpBtn.setFont(fnt);
		signUpBtn.setBackground(new Color(0,130,255));
		signUpBtn.setForeground(Color.white);
		signUpBtn.setEnabled(false);
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(0,130,255));
		cancelBtn.setForeground(Color.white);
		
		getContentPane().setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		duplicateBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if(btn.equals("회원가입")) {
				String id = idField.getText();
				String password = pwdField.getText();
				String pwdCheck = pwdcheckField.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
				} else if(password.equals("")) {
					JOptionPane.showMessageDialog(this, "비밀번호를 입력하셔야 합니다");
				} else if(password.length()<6 || password.length()>20) {    
					JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능 합니다.");
				} else if(pwdCheck.equals("")) {
					JOptionPane.showMessageDialog(this, "비교할 비밀번호를 입력해 주시기 바랍니다");
				} else if(!password.equals(pwdCheck)) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				} else if(checkPWDMethod(password)==1) {
					JOptionPane.showMessageDialog(this, "비밀번호 특수문자는 !@#만 포함 가능 합니다");
				} else if(nameHanField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이름(한글)을 입력하셔야 합니다");
				} else if(nameEngField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이름(영문)을 입력하셔야 합니다");
				} else if(telField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "연락처를 입력하셔야 합니다");
				} else if(emailField.getText().equals("")){
					JOptionPane.showMessageDialog(this, "이메일을 입력하셔야 합니다");
				} else if(emailCheck(emailField.getText())==1){
					JOptionPane.showMessageDialog(this, "잘못된 이메일을 입력하셨습니다");					
				} else {
					AirlineSignUpVO vo = new AirlineSignUpVO(idField.getText(),pwdField.getText(),
							nameHanField.getText(),nameEngField.getText().toUpperCase(),
							telField.getText(),emailField.getText(), (String)genderCombo.getSelectedItem());
									
					int result = dao.SignUpInsert(vo);
					if(result>0) { // 회원등록 성공함
						JOptionPane.showMessageDialog(this, "회원가입에 성공하였습니다\n원활한 이용을 위하여\n로그인 해주시기 바랍니다");
					} else { //회원등록 실패함
						JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다\n 관리자에게 문의해 주시기 바랍니다");
					}
					dispose();
					new AirlineMain();
				}
			} else if(btn.equals("취소")) {
				dispose();
				new AirlineMain();
			} else if(btn.equals("중복 확인")) {
				String idSearch = idField.getText();
				System.out.println(idSearch.length());
				if(idSearch.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
					//id 특수문자 포함 확인
				} else if(idSearch.length() < 6 || idSearch.length() > 15) {
					JOptionPane.showMessageDialog(this, "아이디는 6자리 이상, 15자리 이하만 가능 합니다.");
				} else if(checkIDMethod(idSearch)==1){
					JOptionPane.showMessageDialog(this, "아이디는 특수문자 포함이 불가능합니다");
				} else {
					List<AirlineSignUpVO> result = dao.getidCheck(idSearch);
					if(result.size()==0) {
						JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다");
						signUpBtn.setEnabled(true);
						idField.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(this, "등록되어 있는 아이디 입니다");
					}
						
				} 
			}
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
