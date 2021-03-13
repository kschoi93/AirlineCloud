import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import dbAll.EmpBlacklistDAO;
import dbAll.EmpBlacklistVO;
import dbAll.EmpUserInfoVO;

public class EmpBlacklistManagement extends JPanel implements ActionListener {
	JPanel mainPane = new JPanel();
		//블랙리스트 등록 패널
		JPanel topPane = new JPanel(new BorderLayout());
			JPanel lblPane = new JPanel(new GridLayout(4,4));
				JLabel black_nameLbl = new JLabel("이름(한글)");
				JLabel black_enameLbl = new JLabel("이름(영문)");
				JLabel black_passNoLbl = new JLabel("여권번호");	
				JLabel black_genderLbl = new JLabel("성별");
				JLabel black_birthLbl = new JLabel("생년월일");
				JLabel reasonLbl = new JLabel("사유");
				JLabel userNoLbl = new JLabel("회원번호");
				JLabel regDateLbl = new JLabel("등록일");
				JTextField black_nameTf = new JTextField(30);
				JTextField black_enameTf = new JTextField(30);
				JTextField black_passNoTf = new JTextField(30);
				JTextField black_genderTf = new JTextField(30);
				JTextField black_birthTf = new JTextField(30);
				JTextField reasonTf = new JTextField(30);
				JTextField userNoTf = new JTextField(30);
				JTextField regDateTf = new JTextField(30);
	JLabel[] lbl = {black_nameLbl, black_enameLbl, black_passNoLbl,
			black_genderLbl, black_birthLbl, reasonLbl, userNoLbl, regDateLbl};
	JTextField[] tf = {black_nameTf, black_enameTf, black_passNoTf,
			black_genderTf, black_birthTf, reasonTf, userNoTf, regDateTf};
			JPanel btnPane = new JPanel();
				String[] btnLbl = {"블랙리스트 등록", "블랙리스트 해제", "전체보기", "초기화"};
		//블랙리스트 목록 패널	
		JPanel listPane = new JPanel(new BorderLayout());
			JLabel listLbl = new JLabel("블랙리스트 목록");
			JTable listTable;
				String[] title = {"이름(한글)", "이름(영문)", "여권번호", "성별", "사유", "회원번호"};
			DefaultTableModel model;
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
				lblPane.add(black_nameLbl);		lblPane.add(black_nameTf);
				lblPane.add(black_enameLbl);	lblPane.add(black_enameTf);
				lblPane.add(black_passNoLbl);	lblPane.add(black_passNoTf);
				lblPane.add(black_genderLbl);	lblPane.add(black_genderTf);
				lblPane.add(black_birthLbl);	lblPane.add(black_birthTf);
				lblPane.add(reasonLbl);			lblPane.add(reasonTf);
				lblPane.add(userNoLbl);			lblPane.add(userNoTf);
				lblPane.add(regDateLbl);		lblPane.add(regDateTf);
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
				
		//블랙리스트 목록
		mainPane.add("South", listPane);
			listPane.add("North", listLbl);
			listLbl.setHorizontalAlignment(JLabel.CENTER);
			listLbl.setFont(new Font("맑은 고딕", Font.BOLD, 18));
			listLbl.setForeground(new Color(70,70,70));
			model = new DefaultTableModel(title, 0);
			listTable = new JTable(model);
			sp = new JScrollPane(listTable);
			listPane.add(sp);
		
		//패널 설정
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		//이벤트 등록
		listTable.addMouseListener(new MouseAdapter() {
			String userNo, passNo;
			public void mouseReleased(MouseEvent me) {
				if(me.getButton()==1) {
					int row = listTable.getSelectedRow();
					userNo = String.valueOf((Integer)model.getValueAt(row, 5));
					if(userNo==null) {
						userNo = "0";
					}
					passNo = (String)model.getValueAt(row, 2);
					selectedBlacklist(userNo, passNo);
				}
			}
		});
		
		getBlacklistAll();

	}
	
	public void actionPerformed(ActionEvent ae) {
		String eventBtn = ae.getActionCommand();
		if(eventBtn.equals("블랙리스트 등록")) {
			setBlacklistInsert();
		}else if(eventBtn.equals("블랙리스트 해제")) {
			setBlacklistDelete();
		}else if(eventBtn.equals("전체보기")) {
			getBlacklistAll();
		}else if(eventBtn.equals("초기화")) {
			setFormClear();
		}
	}	
	
	
	public void getBlacklistAll() {
		EmpBlacklistDAO dao = new EmpBlacklistDAO();
		List<EmpBlacklistVO> lst = dao.BlacklistAllSelect();
		setNewTable(lst);
		setFormClear();
	}
	
	public void setNewTable(List<EmpBlacklistVO> lst) {
		model.setRowCount(0);
		for(int i=0; i<lst.size(); i++) {
			EmpBlacklistVO vo = lst.get(i);
			Object[] data = {vo.getBlack_name(), vo.getBlack_ename(), vo.getBlack_passNo(),
					vo.getBlack_gender(), vo.getReason(), vo.getUserNo()};
			model.addRow(data);
		}
	}
	
	public void selectedBlacklist(String userNo, String passNo) {
		int num = Integer.parseInt(userNo);
		EmpBlacklistDAO dao = new EmpBlacklistDAO();
		if(userNo.equals("0")) {
			List<EmpBlacklistVO> lst = dao.blackAllInfo2(passNo);
			EmpBlacklistVO vo = lst.get(0);
			Object[] data = {vo.getBlack_name(), vo.getBlack_ename(),
					vo.getBlack_passNo(), vo.getBlack_gender(), vo.getBlack_birth(),
					vo.getReason(), vo.getUserNo(), vo.getRegDate()};
			for(int i=0; i<data.length; i++) {
				if(i==6){
					tf[i].setText(String.valueOf((Integer)data[i]));					
				}else {
				tf[i].setText((String)data[i]);
				}
			}
		}else {
			List<EmpUserInfoVO> lst = dao.blackAllInfo(passNo);
			EmpUserInfoVO vo = lst.get(0);
			Object [] data = {vo.getUser_name(), vo.getUser_ename(),
					vo.getUser_passNo(), vo.getUser_gender(), vo.getUser_birth(),
					vo.getReason(),	vo.getUserNo(), vo.getRegDate()};
			for(int i=0; i<data.length; i++) {
				if(i==6) {
					tf[i].setText(String.valueOf((Integer)data[i]));					
				}else {
					tf[i].setText((String)data[i]);										
				}
			}
		}
	}
	
	public void setFormClear() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setText("");
		}
	}
	
	public void setBlacklistInsert() {
		EmpBlacklistVO vo = new EmpBlacklistVO(tf[0].getText(), tf[1].getText(),
				tf[2].getText(), tf[3].getText(), tf[4].getText(), tf[5].getText());
		EmpBlacklistDAO dao = new EmpBlacklistDAO();
		int result = dao.blacklistInsert(vo);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "동록이 완료되었습니다.");
			getBlacklistAll();
			setFormClear();
		}else {
			JOptionPane.showMessageDialog(this, "등록에 실패하였습니다.");
		}		
	}
	
	public void setBlacklistDelete() {
		String passNum = tf[2].getText();
		int num = Integer.parseInt(tf[6].getText());
		int result;
		EmpBlacklistDAO dao = new EmpBlacklistDAO();
		if(num==0) {
			result = dao.blacklistDelete2(passNum);
			if(result>0) {
				JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
				getBlacklistAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "삭제에 실패하였습니다.");
			}
		}else {
			result = dao.blacklistDelete(passNum);
			if(result>0) {		
				JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.");
				getBlacklistAll();
				setFormClear();
			}else {
				JOptionPane.showMessageDialog(this, "삭제에 실패하였습니다.");
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
