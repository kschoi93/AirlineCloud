
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import dbAll.CustomPlanDAO;
import dbAll.CustomPlanVO;

public class CustomPlan extends JPanel{
	Font fnt = new Font("굴림체",Font.BOLD,24);	
	JPanel main = new JPanel();
	JPanel northPane = new JPanel();
		JLabel dateLbl = new JLabel("___월___일 항공 일정");
		
	JPanel centerPane = new JPanel();
		JPanel koreaPane = new JPanel();
			String koreaName[] = {"항공편","출발지","도착지","출발시간","도착시간","상태"};
			DefaultTableModel koreaModel = new DefaultTableModel(koreaName,0);
			JTable koreaTbl = new JTable(koreaModel);
			JScrollPane koreaSp = new JScrollPane(koreaTbl);
			
		JPanel nationPane = new JPanel();
			String nationName [] = {"항공편","출발지","도착지","출발시간","도착시간","상태"};
			DefaultTableModel nationModel = new DefaultTableModel(nationName,0);
			JTable nationTbl = new JTable(nationModel);
			JScrollPane nationSp = new JScrollPane(nationTbl);
		

	public CustomPlan() {
		setLayout(new BorderLayout(0,50));
		add("East", new JLabel());
		add("West", new JLabel());
		add("North", new JLabel());
		add("South", new JLabel());
		add("Center", main);
		main.setBackground(Color.white);
		main.setLayout(new BorderLayout());
		main.add("North",northPane);
			northPane.setBackground(Color.white);
			northPane.setLayout(new FlowLayout(FlowLayout.CENTER,0,30));
			northPane.add(dateLbl);
			dateLbl.setBackground(Color.white);
			dateLbl.setFont(fnt);
			Date today = new Date();
			SimpleDateFormat format = new SimpleDateFormat("MM월 dd일 항공일정");
			String date = format.format(today);
			dateLbl.setText(date);
		main.add("Center",centerPane);
			centerPane.setLayout(new GridLayout(1,2));
			centerPane.add(koreaPane);
				koreaPane.setBackground(Color.white);
				koreaPane.add(koreaSp);
			centerPane.add(nationPane);
				nationPane.setBackground(Color.white);
				nationPane.add(nationSp);
			
		
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		getCustomKoreaPrint();
		getCustomNationPrint();
	}
	
	public void getCustomKoreaPrint() {
		CustomPlanDAO dao = new CustomPlanDAO();
		List<CustomPlanVO> lst = dao.getKoreaRecord();
		koreaModel.setRowCount(0);
		for(int i=0;i<lst.size();i++) {
			CustomPlanVO vo = lst.get(i);
			Object[] data = {vo.getFlightNo(),vo.getDep(),vo.getDes(),vo.getDepTime(),vo.getDesTime(),vo.getFlight_state()};
			koreaModel.addRow(data);
		}	
	}
	
	public void getCustomNationPrint() {
		CustomPlanDAO dao = new CustomPlanDAO();
		List<CustomPlanVO> lst = dao.getNationRecord();
		nationModel.setRowCount(0);
		for(int i=0;i<lst.size();i++) {
			CustomPlanVO vo = lst.get(i);
			Object[] data = {vo.getFlightNo(),vo.getDep(),vo.getDes(),vo.getDepTime(),vo.getDesTime(),vo.getFlight_state()};
			nationModel.addRow(data);
		}	
	}
	
}
