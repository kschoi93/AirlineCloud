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

public class EmpBlacklistManagement extends JPanel {
	JPanel mainPane = new JPanel();
		//블랙리스트 등록 패널
		JPanel topPane = new JPanel(new BorderLayout());
			JPanel lblPane = new JPanel(new GridLayout(3,4));
				JLabel b_name_korLbl = new JLabel("이름(한글)");
				JLabel b_name_engLbl = new JLabel("이름(영문)");
				JLabel b_passportNoLbl = new JLabel("여권번호");	
				JLabel b_expireDateLbl = new JLabel("여권만료일");
				JLabel b_genderLbl = new JLabel("성별");
				JLabel b_reasonLbl = new JLabel("사유");
				JTextField b_name_korTf = new JTextField(30);
				JTextField b_name_engTf = new JTextField(30);
				JTextField b_passportTf = new JTextField(30);
				JTextField b_expireDateTf = new JTextField(30);
				JTextField b_genderTf = new JTextField(30);
				JTextField b_reasonTf = new JTextField(30);
	JLabel[] lbl = {b_name_korLbl, b_name_engLbl, b_passportNoLbl, b_expireDateLbl, b_genderLbl, b_reasonLbl};
	JTextField[] tf = {b_name_korTf, b_name_engTf, b_passportTf, b_expireDateTf, b_genderTf, b_reasonTf};
			JPanel btnPane = new JPanel();
				JButton addBtn = new JButton("블랙리스트 등록");
				JButton removeBtn = new JButton("블랙리스트 해제");
	JButton[] btn = {addBtn, removeBtn};
		//블랙리스트 목록 패널	
		JPanel listPane = new JPanel(new BorderLayout());
			JLabel listLbl = new JLabel("블랙리스트 목록");
			JTable listTable;
				String[] title = {"회원번호", "이름(한글)", "이름(영문)", "여권번호", "성별", "사유"};
				Object[][] test = {
						{"", "이미영", "LEE MIYOUNG", "M604832", "F", "사기죄 재판중"}
				};
			DefaultTableModel model = new DefaultTableModel(test, title);
			JScrollPane sp;
		
	Font fnt = new Font("맑은 고딕", Font.BOLD, 12);
	LineBorder lineBorder = new LineBorder(Color.white);
				
				
	public EmpBlacklistManagement() {
		
		setLayout(new BorderLayout(200,50));
		add(mainPane);
		add("East", new JLabel());
		add("West", new JLabel());
		add("South", new JLabel());
		add("North", new JLabel());
		
		mainPane.setLayout(new BorderLayout(0,10));
		//블랙리스트 등록
		mainPane.add(topPane);
			topPane.add(lblPane);
				lblPane.add(b_name_korLbl);		lblPane.add(b_name_korTf);
				lblPane.add(b_name_engLbl);		lblPane.add(b_name_engTf);
				lblPane.add(b_passportNoLbl);	lblPane.add(b_passportTf);
				lblPane.add(b_expireDateLbl);	lblPane.add(b_expireDateTf);
				lblPane.add(b_genderLbl);		lblPane.add(b_genderTf);
				lblPane.add(b_reasonLbl);		lblPane.add(b_reasonTf);
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
			topPane.add("South", btnPane);
				btnPane.add(addBtn);	btnPane.add(removeBtn);
				btnPane.setBackground(Color.white);
				for(int i=0; i<btn.length; i++) {
					btn[i].setBackground(new Color(70,70,70));
					btn[i].setForeground(Color.white);
					btn[i].setFont(fnt);
					btn[i].setBorderPainted(false);
				}
		//블랙리스트 목록
		mainPane.add("South", listPane);
			listPane.add("North", listLbl);
			listLbl.setHorizontalAlignment(JLabel.CENTER);
			listLbl.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			listLbl.setForeground(new Color(70,70,70));
			listTable = new JTable(model);
			sp = new JScrollPane(listTable);
			listPane.add(sp);
			

		
		//패널 설정
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		
	}

}
