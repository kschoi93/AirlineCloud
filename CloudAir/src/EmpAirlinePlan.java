//관리자페이지 로그인하고 난뒤 메인 페이지

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EmpAirlinePlan extends JPanel{
	Font fnt = new Font("굴림체",Font.BOLD,24);
		String columName[] = {"출발지","도착지","출발시간","도착시간","항공편","상태"};
		Object str[][] = {
				{"x1","x2","x3","x4","x5","x6"}
		};
	DefaultTableModel model = new DefaultTableModel(str,columName);
	JTable tbl = new JTable(model);
	JScrollPane sp = new JScrollPane(tbl);
		
	JLabel dateLbl = new JLabel("___월___일 항공 일정");
	public EmpAirlinePlan() {
		setLayout(new GridBagLayout());
		GridBagConstraints g = new GridBagConstraints();
		g.fill = GridBagConstraints.BOTH;
		
		g.gridwidth = 1;
		g.gridheight = 1;
		g.gridx = 0;
		g.gridy = 0;
		dateLbl.setHorizontalAlignment(JLabel.CENTER);
		dateLbl.setFont(fnt);
		add(dateLbl,g);
		
		g.gridwidth= 1;
		g.gridheight= 1;
		g.gridx = 0;
		g.gridy = 1;
		tbl.getParent().setBackground(Color.white);
		add(sp,g);
		
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
	}
}
