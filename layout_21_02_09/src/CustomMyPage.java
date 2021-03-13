
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dbAll.CustomFrameDAO;
import dbAll.CustomFrameVO;
import dbAll.CustomMypageDAO;
import dbAll.CustomMypageVO;

public class CustomMyPage extends JPanel implements ActionListener{
	Font fnt2 = new Font("굴림체",Font.BOLD,20);
	Font fnt1 = new Font("굴림체",Font.BOLD,14);
	JPanel main = new JPanel();
	JLabel memberLbl = new JLabel("@@@님");
	JLabel memberNumLbl = new JLabel("회원번호 0000000");
	JLabel listLbl = new JLabel("마일리지 현황");
	JLabel mymilLbl = new JLabel("나의 마일리지");
	JLabel boardingLbl = new JLabel("탑승횟수");
	JLabel mileNumLbl = new JLabel("00,000");
	JLabel boardingNumLbl = new JLabel("15회");
	JButton btn = new JButton("돌아가기");
	
	CustomPlan cp = new CustomPlan();
	static int mileage = 0;
	public CustomMyPage() {
		setLayout(new BorderLayout());
		main.setLayout(new GridBagLayout());
		add(main);
		
		GridBagConstraints g = new GridBagConstraints();
		
		g.fill = GridBagConstraints.NONE;
		
		//@@@님
		g.gridwidth = 2;
		g.gridheight = 1;
		g.gridx = 4;
		g.gridy = 0;
		memberLbl.setHorizontalAlignment(JLabel.CENTER);
		memberLbl.setFont(fnt2);
		main.add(memberLbl,g);
		
		//회원번호 000000
		g.gridwidth = 2;
		g.gridheight = 1;
		g.insets = new Insets(0, 0, 20, 0);
		g.gridx = 4;
		g.gridy = 1;
		memberNumLbl.setHorizontalAlignment(JLabel.CENTER);
		main.add(memberNumLbl,g);
		memberNumLbl.setFont(fnt1);
		
		// 마일리지 현황
		g.gridwidth=2;
		g.gridheight=1;
		g.gridx = 4;
		g.gridy = 4;
		listLbl.setFont(fnt2);
		listLbl.setHorizontalAlignment(JLabel.CENTER);
		main.add(listLbl,g);
		
		/// 나의 마일리지 , 38,000원
		g.gridwidth=2;
		g.gridheight=1;
		g.gridx = 2;
		g.gridy = 5;
		mymilLbl.setHorizontalAlignment(JLabel.CENTER);
		main.add(mymilLbl,g);
		mymilLbl.setFont(fnt1);
		
		g.gridwidth=2;
		g.gridheight=1;
		g.gridx=6;
		g.gridy=5;
		mileNumLbl.setHorizontalAlignment(JLabel.CENTER);
		main.add(mileNumLbl,g);
		boardingLbl.setFont(fnt1);
		
		//탑승횟수, 15회
		g.gridwidth=2;
		g.gridheight=1;
		g.gridx = 2;
		g.gridy = 6;
		main.add(boardingLbl,g);
		boardingLbl.setFont(fnt1);
		
		g.gridwidth =2;
		g.gridheight=1;
		g.gridx = 8;
		g.gridx = 6;
		g.insets = new Insets(0,0,30,0);
		main.add(boardingNumLbl,g);
		boardingNumLbl.setFont(fnt1);
		
		//돌아가기
		g.gridwidth=2;
		g.gridheight = 1;
		g.gridx = 4;
		g.gridy = 7;
		main.add(btn,g);
		btn.setForeground(Color.white);
		btn.setBackground(new Color(0,130,255));
		btn.setFont(fnt1);
		
		main.setBackground(Color.white);
		btn.addActionListener(this);
		
		getPrintInformation();
		setCountPrint();
	}
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("돌아가기")){
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			}
		}
	}
	
	public void getPrintInformation() {
		CustomMypageDAO dao = new CustomMypageDAO();
		String id = AirlineMain.idField.getText();
		System.out.println("아이디가 왜 안받아지냐->"+id);
		List<CustomMypageVO> name = dao.setMypage(id);
		for(int i=0; i<name.size(); i++) {
			CustomMypageVO vo = name.get(i);
			memberLbl.setText(vo.getUser_name()+"님");
			memberNumLbl.setText("회원번호 "+vo.getUserno());
			mileNumLbl.setText(vo.getMileage()+"점");
			mileage = vo.getMileage();
		}
		
	}
	
	public void setCountPrint() {
		CustomMypageDAO dao = new CustomMypageDAO();
		String id = AirlineMain.idField.getText();
		List<CustomMypageVO> lst = dao.setCount(id);
		for(int i=0; i<lst.size(); i++) {
			CustomMypageVO vo = lst.get(i);
			boardingNumLbl.setText(vo.getCount()+"회");
		}
	}

}
