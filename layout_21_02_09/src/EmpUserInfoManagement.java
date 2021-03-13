import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbAll.EmpUserInfoDAO;
import dbAll.EmpUserInfoVO;

public class EmpUserInfoManagement extends JPanel implements ActionListener {
	JPanel mainPane = new JPanel();
		//회원정보 수정 패널
		JPanel topPane = new JPanel(new BorderLayout());
			//상단-Center
			JPanel lblPane = new JPanel(new GridLayout(5, 4));
				JLabel userNoLbl = new JLabel("회원번호");
				JLabel user_nameLbl = new JLabel("이름(한글)");
				JLabel user_birthLbl = new JLabel("생년월일");
				JLabel user_enameLbl = new JLabel("이름(영문)");
				JLabel user_genderLbl = new JLabel("성별");
				JLabel user_telLbl = new JLabel("연락처");
				JLabel user_passNoLbl = new JLabel("여권번호");	
				JLabel user_emailLbl = new JLabel("이메일");	
				JLabel mileageLbl = new JLabel("마일리지");
				JLabel gradeLbl = new JLabel("등급");
				JTextField userNoTf = new JTextField(30);
				JTextField user_nameTf = new JTextField(30);
				JTextField user_birthTf = new JTextField(30);
				JTextField user_enameTf = new JTextField(30);
				JTextField user_genderTf = new JTextField(30);
				JTextField user_telTf = new JTextField(30);
				JTextField user_passNoTf = new JTextField(30);
				JTextField user_emailTf = new JTextField(30);
				JTextField mileageTf = new JTextField(30);
				JTextField gradeTf = new JTextField(30);		
		JLabel[] lbl = {userNoLbl, user_nameLbl, user_birthLbl, user_enameLbl, user_genderLbl,
				user_telLbl, user_passNoLbl, user_emailLbl, mileageLbl, gradeLbl};
		JTextField[] tf = {userNoTf, user_nameTf, user_birthTf, user_enameTf, user_genderTf,
				user_telTf, user_passNoTf, user_emailTf, mileageTf, gradeTf};
			//상단-South
			JPanel searchPane = new JPanel(new BorderLayout());
				JPanel tfPane = new JPanel();
					//JLabel searchLbl = new JLabel("이름 또는 회원번호");
					JTextField searchTf = new JTextField("이름 또는 회원번호", 10);
				JPanel btnPane = new JPanel();
					String[] btnLbl = {"조회", "수정", "삭제", "전체", "초기화", "블랙리스트 등록"};
		//회원정보 조회 패널	
		JPanel tablePane = new JPanel(new BorderLayout());
			JLabel userInfoLbl = new JLabel("회원정보조회");
			JTable userTable;
				String[] title = {"회원번호", "이름", "성별", "생년월일", "여권번호", "연락처", "이메일"};
			DefaultTableModel model;
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
				lblPane.add(user_nameLbl);		lblPane.add(user_nameTf);
				lblPane.add(user_birthLbl);		lblPane.add(user_birthTf);
				lblPane.add(user_enameLbl);		lblPane.add(user_enameTf);
				lblPane.add(user_genderLbl);	lblPane.add(user_genderTf);
				lblPane.add(user_telLbl);		lblPane.add(user_telTf);
				lblPane.add(user_passNoLbl);	lblPane.add(user_passNoTf);
				lblPane.add(user_emailLbl);		lblPane.add(user_emailTf);
				lblPane.add(mileageLbl);		lblPane.add(mileageTf);
				lblPane.add(gradeLbl);			lblPane.add(gradeTf);
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
//					tfPane.add(searchLbl);
//						searchLbl.setFont(fnt);
//						searchLbl.setForeground(new Color(70,70,70));
					tfPane.add(searchTf);
						searchTf.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
						searchTf.setForeground(new Color(70,70,70));
					tfPane.setBackground(Color.white);
				searchPane.add("East", btnPane);
					for(int i=0; i<btnLbl.length; i++) {
						JButton btn = new JButton(btnLbl[i]);
						btn.setBackground(new Color(116,108,101));
						btn.setForeground(Color.white);
						btn.setFont(fnt);
						btn.setBorderPainted(false);
						btnPane.add(btn);
						//이벤트 등록
						btn.addActionListener(this);						
					}
					btnPane.setBackground(Color.white);
					
		//회원정보 조회
		mainPane.add("South", tablePane);
			model = new DefaultTableModel(title, 0);
			userTable = new JTable(model);
			sp = new JScrollPane(userTable);
			tablePane.add(sp);
		
		//패널 설정
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		//이벤트 등록
		userTable.addMouseListener(new MouseAdapter() {
			String userNo;
			public void mouseReleased(MouseEvent me) {
				if(me.getButton()==1) {
					int row = userTable.getSelectedRow();
					int num = (Integer)model.getValueAt(row, 0);
					userNo = String.valueOf(num);
					selectedUser(userNo);
				}
			}
			
		});
		searchTf.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent me) {
				if(me.getButton()==1) {
					searchTf.setText("");
				}
			}
		});
		searchTf.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent ke) {
				int key = ke.getKeyCode();
				if(key==KeyEvent.VK_ENTER) {
					userSearch();
				}
			}
		});

		getUserAll();

	}
	
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("조회")) {
			userSearch();
		}else if(eventBtn.equals("수정")) {
			setUserUpdate();
		}else if(eventBtn.equals("삭제")) {
			setUserDelete();
		}else if(eventBtn.equals("전체")) {
			getUserAll();
		}else if(eventBtn.equals("초기화")) {
			setFormClear();
		}else if(eventBtn.equals("블랙리스트 등록")) {
			setBlacklistInsert();
		}	
	}
	
	public void getUserAll() {		//회원 전체목록
		EmpUserInfoDAO dao = new EmpUserInfoDAO();
		List<EmpUserInfoVO> lst = dao.userAllSelect();
		setNewTable(lst);
		setFormClear();
		searchTf.setText("이름 또는 회원번호");
	}
	
	public void userSearch() {
		String searchWord = searchTf.getText();
		if(searchWord.equals("")) {
			JOptionPane.showMessageDialog(this, "검색어를 입력해 주시기 바랍니다.");
		}else {
			EmpUserInfoDAO dao = new EmpUserInfoDAO();
			List<EmpUserInfoVO> searchList = dao.getSearchRecord(searchWord);
			if(searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "'"+searchWord+"'의 검색결과가 존재하지 않습니다.");
			}else {
				setNewTable(searchList);
			}
			searchTf.setText("");			
		}
	}
	
	public void setNewTable(List<EmpUserInfoVO> lst) {
		model.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			EmpUserInfoVO vo = lst.get(i);
			Object[] data = {vo.getUserNo(), vo.getUser_name(), vo.getUser_gender(),
				vo.getUser_birth(), vo.getUser_passNo(), vo.getUser_tel(), vo.getUser_email()};
			model.addRow(data);		
		}
	}
	
	public void selectedUser(String userNo) {
		EmpUserInfoDAO dao = new EmpUserInfoDAO();
		List<EmpUserInfoVO> lst = dao.userAllInfo(userNo);
		EmpUserInfoVO vo = lst.get(0);
		Object[] data = {vo.getUserNo(), vo.getUser_name(), vo.getUser_birth(),
				vo.getUser_ename(), vo.getUser_gender(), vo.getUser_tel(),
				vo.getUser_passNo(), vo.getUser_email(), vo.getMileage(), vo.getGrade()};
		System.out.println("birth->"+vo.getUser_birth());
		System.out.println("gender->"+vo.getUser_gender());
		for(int i=0; i<data.length; i++) {
			if(i==0) {
				tf[i].setText(String.valueOf((Integer)data[i]));			
			}else {
				tf[i].setText((String)data[i]);	
			}
			tf[0].setEditable(false);
		}
	}
	
	public void setFormClear() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setText("");
			tf[0].setEditable(true);
			searchTf.setText("이름 또는 회원번호");
		}
	}
	
	///////////re-chk!!!!!//////
	public void setUserUpdate() {
		EmpUserInfoVO vo = new EmpUserInfoVO();
		vo.setUserNo(Integer.parseInt(tf[0].getText()));
		vo.setUser_name(tf[1].getText());
		vo.setUser_birth(tf[2].getText());
		vo.setUser_ename(tf[3].getText());
		vo.setUser_gender(tf[4].getText());
		vo.setUser_tel(tf[5].getText());
		vo.setUser_passNo(tf[6].getText());
		vo.setUser_email(tf[7].getText());
		vo.setMileage(tf[8].getText());
		vo.setGrade(tf[9].getText());
		System.out.println("birth--->"+tf[2].getText());
		EmpUserInfoDAO dao = new EmpUserInfoDAO();
		int result = dao.userUpdate(vo);
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "수정할 항목을 선택해 주시기 바랍니다.");
		}else {
			if(result>0) {
				JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
				getUserAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "수정에 실패하였습니다.");
			}
		}
	}
	
	public void setUserDelete() {
		int userNum = Integer.parseInt(tf[0].getText());
		EmpUserInfoDAO dao = new EmpUserInfoDAO();
		int result = dao.userDelete(userNum);
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "삭제할 항목을 선택해 주시기 바랍니다.");
		}else {
			if(result>0) {
				JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
				getUserAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "삭제에 실패하였습니다.");
			}
		}		
	}
	
	public void setBlacklistInsert() {
		String passNo = tf[6].getText();
		String answer = JOptionPane.showInputDialog(this, "블랙리스트 사유", "블랙리스트 등록", JOptionPane.PLAIN_MESSAGE);
		EmpUserInfoDAO dao = new EmpUserInfoDAO();
		int result = dao.blacklistInsert(passNo, answer);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "블랙리스트 등록이 완료되었습니다.");
			getUserAll();
			setFormClear();
		}else {
			JOptionPane.showMessageDialog(this, "블랙리스트 등록에 실패하였습니다.");
		}	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
