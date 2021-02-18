import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class EmpUserInfoManagement extends JPanel {
	JPanel mainPane = new JPanel();
		//회원정보 수정 패널
		JPanel topPane = new JPanel(new BorderLayout());
			//상단-Center
			JPanel lblPane = new JPanel(new GridLayout(5, 4));
				JLabel userNoLbl = new JLabel("회원번호");
				JLabel name_korLbl = new JLabel("이름(한글)");
				JLabel user_birthLbl = new JLabel("생년월일");
				JLabel name_engLbl = new JLabel("이름(영문)");
				JLabel user_genderLbl = new JLabel("성별");
				JLabel user_telLbl = new JLabel("연락처");
				JLabel passportNoLbl = new JLabel("여권번호");	
				JLabel expireDateLbl = new JLabel("여권만료일");	
				JLabel user_emailLbl = new JLabel("이메일");
				JLabel mileageLbl = new JLabel("마일리지");
				JTextField userNoTf = new JTextField(30);
				JTextField name_korTf = new JTextField(30);
				JTextField user_birthTf = new JTextField(30);
				JTextField name_engTf = new JTextField(30);
				JTextField user_genderTf = new JTextField(30);
				JTextField user_telTf = new JTextField(30);
				JTextField passportNoTf = new JTextField(30);
				JTextField expireDateTf = new JTextField(30);
				JTextField user_emailTf = new JTextField(30);
				JTextField mileageTf = new JTextField(30);		
		JLabel[] lbl = {userNoLbl, name_korLbl, user_birthLbl, name_engLbl, user_genderLbl,
				user_telLbl, passportNoLbl, expireDateLbl, user_emailLbl, mileageLbl};
		JTextField[] tf = {userNoTf, name_korTf, user_birthTf, name_engTf, user_genderTf,
				user_telTf, passportNoTf,expireDateTf, user_emailTf, mileageTf};
			//상단-South
			JPanel searchPane = new JPanel(new BorderLayout());
				JPanel tfPane = new JPanel();
					JLabel searchLbl = new JLabel("이름 또는 회원번호");
					JTextField searchTf = new JTextField(10);
				JPanel btnPane = new JPanel();
					JButton btn;
					String[] btnStr = {"조회", "수정", "삭제", "블랙리스트 등록"};
		//회원정보 조회 패널	
		JPanel tablePane = new JPanel(new BorderLayout());
			JLabel userInfoLbl = new JLabel("회원정보조회");
			JTable userInfoTable;
				String[] title = {"회원번호", "이름", "성별", "여권번호", "연락처", "이메일"};
				Object[][] test = {
						{"42", "최우식", "M", "M801346", "010-3333-4457", "chldntlr@hanmail.net"}
				};
			DefaultTableModel model = new DefaultTableModel(test, title);
			JScrollPane sp;
	
	Font fnt = new Font("맑은 고딕", Font.BOLD, 12);
	LineBorder lineBorder = new LineBorder(Color.white);

	public EmpUserInfoManagement() {
		
		setLayout(new BorderLayout(200,50));
		add(mainPane);
		add("East", new JLabel());
		add("West", new JLabel());
		add("South", new JLabel());
		add("North", new JLabel());
		
		mainPane.setLayout(new BorderLayout(0,10));
		//회원정보 수정
		mainPane.add(topPane);
			topPane.setBackground(Color.white);
			topPane.setBorder(lineBorder);
			topPane.add(lblPane);
				lblPane.add(userNoLbl);			lblPane.add(userNoTf);
				lblPane.add(name_korLbl);		lblPane.add(name_korTf);
				lblPane.add(user_birthLbl);		lblPane.add(user_birthTf);
				lblPane.add(name_engLbl);		lblPane.add(name_engTf);
				lblPane.add(user_genderLbl);		lblPane.add(user_genderTf);
				lblPane.add(user_telLbl);		lblPane.add(user_telTf);
				lblPane.add(passportNoLbl);	lblPane.add(passportNoTf);
				lblPane.add(expireDateLbl);		lblPane.add(expireDateTf);
				lblPane.add(user_emailLbl);		lblPane.add(user_emailTf);
				lblPane.add(mileageLbl);		lblPane.add(mileageTf);
				lblPane.setBackground(new Color(255,181,140));				
				for(int i=0; i<lbl.length; i++) {
					lbl[i].setBorder(lineBorder);
					lbl[i].setHorizontalAlignment(JLabel.CENTER);
					lbl[i].setFont(fnt);
					lbl[i].setForeground(new Color(70,70,70));
					tf[i].setBorder(lineBorder);
					tf[i].setFont(new Font("맑은 고딕", Font.PLAIN, 12));
					tf[i].setBackground(new Color(255,181,140));
					tf[i].setForeground(new Color(70,70,70));					
				}
			topPane.add("South", searchPane);
				searchPane.setBackground(Color.white);
				searchPane.add("West", tfPane);
					tfPane.add(searchLbl);
						searchLbl.setFont(fnt);
						searchLbl.setForeground(new Color(70,70,70));
					tfPane.add(searchTf);
						searchTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
						searchTf.setForeground(new Color(70,70,70));
					tfPane.setBackground(Color.white);
				searchPane.add("East", btnPane);
					for(int i=0; i<btnStr.length; i++) {
						btn = new JButton(btnStr[i]);
						btn.setBackground(new Color(116,108,101));
						btn.setForeground(Color.white);
						btn.setFont(fnt);
						btn.setBorderPainted(false);
						btnPane.add(btn);
					}
					btnPane.setBackground(Color.white);
					
		//회원정보 조회
		mainPane.add("South", tablePane);
			userInfoTable = new JTable(model);
			sp = new JScrollPane(userInfoTable);
			tablePane.add(sp);
			
					
					
					
					
		
					
		//패널 설정
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
					
		
		
		
		
		
		
		
		
		
	}

}
