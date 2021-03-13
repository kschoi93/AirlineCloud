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
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import dbAll.EmpInfoDAO;
import dbAll.EmpInfoVO;

public class EmpInfoManagement extends JPanel implements ActionListener {
	JPanel mainPane = new JPanel();
		//사원정보 수정 패널
		JPanel topPane = new JPanel(new BorderLayout());
			//Center
			JPanel lblPane = new JPanel(new GridLayout(5, 4));
				JLabel empNoLbl = new JLabel("사원번호");
				JLabel emp_nameLbl = new JLabel("이름");
				JLabel emp_birthLbl = new JLabel("생년월일");
				JLabel emp_genderLbl = new JLabel("성별");
				JLabel emp_telLbl = new JLabel("연락처");
				JLabel emp_emailLbl = new JLabel("이메일");
				JLabel emp_deptLbl = new JLabel("부서");
				JLabel emp_jobLbl = new JLabel("직책");	
				JLabel emp_passNoLbl = new JLabel("여권번호");	
				JLabel emp_addrLbl = new JLabel("주소");	
				JTextField empNoTf = new JTextField(30);
				JTextField emp_nameTf = new JTextField(30);
				JTextField emp_birthTf = new JTextField(30);
				JTextField emp_genderTf = new JTextField(30);
				JTextField emp_telTf = new JTextField(30);
				JTextField emp_emailTf = new JTextField(30);
				JTextField emp_deptTf = new JTextField(30);
				JTextField emp_jobTf = new JTextField(30);
				JTextField emp_passNoTf = new JTextField(30);
				JTextField emp_addrTf = new JTextField(30);		
		JLabel[] lbl = {empNoLbl, emp_nameLbl, emp_birthLbl, emp_genderLbl,
				emp_telLbl, emp_emailLbl, emp_deptLbl, emp_jobLbl, emp_passNoLbl, emp_addrLbl};
		JTextField[] tf = {empNoTf, emp_nameTf, emp_birthTf, emp_genderTf,
				emp_telTf, emp_emailTf, emp_deptTf, emp_jobTf, emp_passNoTf, emp_addrTf};
			//South
			JPanel searchPane = new JPanel(new BorderLayout());
				JPanel tfPane = new JPanel();
					//JLabel searchLbl = new JLabel("이름 or 사원번호");
					JTextField searchTf = new JTextField("이름 또는 사원번호", 15);
				JPanel btnPane = new JPanel();
					String[] btnLbl = {"조회", "등록", "수정", "삭제", "전체", "초기화"};
	//사원정보 조회 패널		
	JPanel tablePane = new JPanel(new BorderLayout());
//		JLabel empLbl = new JLabel("사원정보조회");
		JTable empTable;
			String[] title = {"사원번호", "이름", "생년월일", "연락처", "이메일", "부서"};
		DefaultTableModel model;
		JScrollPane sp;
		
	Font fnt = new Font("맑은 고딕", Font.BOLD, 12);
	LineBorder lineBorder = new LineBorder(Color.white);

	public EmpInfoManagement() {
		
		//상하좌우 간격
		setLayout(new BorderLayout(200,50));
		add(mainPane);
		add("East", new JLabel());
		add("West", new JLabel());
		add("South", new JLabel());
		add("North", new JLabel());
		
		mainPane.setLayout(new BorderLayout(0,10));
		//사원정보 수정
		mainPane.add(topPane);
			topPane.setBackground(Color.white);
			topPane.setBorder(lineBorder);
			topPane.add(lblPane);
				lblPane.add(empNoLbl); 			lblPane.add(empNoTf);
				lblPane.add(emp_nameLbl); 		lblPane.add(emp_nameTf);
				lblPane.add(emp_birthLbl); 		lblPane.add(emp_birthTf);
				lblPane.add(emp_genderLbl);	 	lblPane.add(emp_genderTf);
				lblPane.add(emp_telLbl); 		lblPane.add(emp_telTf);
				lblPane.add(emp_emailLbl);		lblPane.add(emp_emailTf);
				lblPane.add(emp_deptLbl);		lblPane.add(emp_deptTf);
				lblPane.add(emp_jobLbl);		lblPane.add(emp_jobTf);
				lblPane.add(emp_passNoLbl);		lblPane.add(emp_passNoTf);
				lblPane.add(emp_addrLbl);		lblPane.add(emp_addrTf);	
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
				//	tfPane.add(searchLbl);
				//		searchLbl.setFont(fnt);
				//		searchLbl.setForeground(new Color(70,70,70));
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
		//사원정보 조회
		mainPane.add("South", tablePane);
			model = new DefaultTableModel(title, 0);
			empTable = new JTable(model);
			sp = new JScrollPane(empTable);
			tablePane.add(sp);

		//패널 설정	
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		//이벤트 등록
		empTable.addMouseListener(new MouseAdapter() {
			String empNo;
			public void mouseReleased(MouseEvent me) {
				if(me.getButton()==1) {
					int row = empTable.getSelectedRow();		//열 개수 가져오기 (열 모형에 있는 열 수를 반환)
					empNo = (String)model.getValueAt(row, 0);	//선택한 행의 사원번호 가져오기
					selectedEmp(empNo);
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
					empSearch();
				}
			}
		});
		
		getEmpAll();
		
	}
	
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("조회")) {
			empSearch();
		}else if(eventBtn.equals("등록")) {
			setEmpInsert();
		}else if(eventBtn.equals("수정")) {
			setEmpUpdate();
		}else if(eventBtn.equals("삭제")) {
			setEmpDelete();
		}else if(eventBtn.equals("전체")) {
			getEmpAll();
		}else if(eventBtn.equals("초기화")) {
			setFormClear();
		}
	}
	
	public void getEmpAll() {	//사원 전체목록
		EmpInfoDAO dao = new EmpInfoDAO();
		List<EmpInfoVO> lst = dao.empAllSelect();
		setNewTable(lst);
		setFormClear();
		searchTf.setText("이름 또는 사원번호");
	}
	
	public void empSearch() {	//사원 검색
		String searchWord = searchTf.getText();
		if(searchWord.equals("")) {
			JOptionPane.showMessageDialog(this, "검색어를 입력해 주시기 바랍니다.");
		}else {
			EmpInfoDAO dao = new EmpInfoDAO();
			List<EmpInfoVO> searchList = dao.getSearchRecord(searchWord);
			if(searchList.size()==0) {
				JOptionPane.showMessageDialog(this, "'"+searchWord+"'의 검색결과가 존재하지 않습니다.");
			}else {
				setNewTable(searchList);
			}
			searchTf.setText("");
		}
	}
		
	public void setNewTable(List<EmpInfoVO> lst) {	//JTable에 사원 목록 띄우기
		model.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			EmpInfoVO vo = lst.get(i);
			Object[] data = {vo.getEmpNo(), vo.getEmp_name(), vo.getEmp_birth(),
					vo.getEmp_tel(), vo.getEmp_email(), vo.getEmp_dept()};
			model.addRow(data);
		}
	}
	
	////////사원관리 JTable에서 클릭 -> JTextField에 나타내기////////
	public void selectedEmp(String empNo) {
		EmpInfoDAO dao = new EmpInfoDAO();
		List<EmpInfoVO> lst = dao.empAllInfo(empNo);
		EmpInfoVO vo = lst.get(0);
		Object[] data = {vo.getEmpNo(), vo.getEmp_name(), vo.getEmp_birth(),
				vo.getEmp_gender(), vo.getEmp_tel(), vo.getEmp_email(),
				vo.getEmp_dept(), vo.getEmp_job(), vo.getEmp_passNo(), vo.getEmp_addr()};
		for(int i=0; i<data.length; i++) {
			tf[i].setText((String)data[i]);
			tf[0].setEditable(false);	//사원번호 변경 불가
		}
	}
	
	//*******입력 제한 조건 재확인*******
	public void setEmpInsert() {		//사원 등록
		EmpInfoVO vo = new EmpInfoVO(tf[0].getText(), tf[1].getText(),
				tf[2].getText(), tf[3].getText(), tf[4].getText(), tf[5].getText(),
				tf[6].getText(), tf[7].getText(), tf[8].getText(), tf[9].getText());
		if(vo.getEmp_name().equals("") || vo.getEmp_tel().equals("")) {
			JOptionPane.showMessageDialog(this, "이름과 연락처를 반드시 입력하여야 합니다.");
		}else {
			EmpInfoDAO dao = new EmpInfoDAO();
			int result = dao.empInsert(vo);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "등록이 완료되었습니다.");
				getEmpAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "등록을 실패하였습니다.");
			}
		}
	}
	
	public void setFormClear() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setText("");
			tf[0].setEditable(true);	//사원번호란 입력 가능하도록 변경
			searchTf.setText("이름 또는 사원번호");
		}
	}
	
	public void setEmpUpdate() {
		EmpInfoVO vo = new EmpInfoVO();
		vo.setEmpNo(tf[0].getText());
		vo.setEmp_name(tf[1].getText());
		vo.setEmp_birth(tf[2].getText());
		vo.setEmp_gender(tf[3].getText());
		vo.setEmp_tel(tf[4].getText());
		vo.setEmp_email(tf[5].getText());
		vo.setEmp_dept(tf[6].getText());
		vo.setEmp_job(tf[7].getText());
		vo.setEmp_passNo(tf[8].getText());
		vo.setEmp_addr(tf[9].getText());
		EmpInfoDAO dao = new EmpInfoDAO();
		int result = dao.empUpdate(vo);
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "수정할 항목을 선택해 주시기 바랍니다.");
		}else {
			if(result>0) {
				JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
				getEmpAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "수정에 실패하였습니다.");
			}
		}
	}
	
	public void setEmpDelete() {
		String empNum = tf[0].getText();
		EmpInfoDAO dao = new EmpInfoDAO();
		int result = dao.empDelete(empNum);
		if(tf[0].getText().equals("")) {
			JOptionPane.showMessageDialog(this, "삭제할 항목을 선택해 주시기 바랍니다.");
		}else {	
			if(result>0) {
				JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
				getEmpAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "삭제에 실패하였습니다.");
			}
		}
	}
	

}
