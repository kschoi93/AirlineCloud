
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbAll.CustomPlanDAO;
import dbAll.CustomPlanVO;
import dbAll.CustomReservationDAO;
import dbAll.CustomReservationVO;

public class CustomReservation2 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	JPanel changePane = new JPanel();
		JPanel centerPane = new JPanel();
			JPanel northPane = new JPanel();
				JPanel titlePane = new JPanel();
					JLabel titleLbl = new JLabel("항공편을 선택하세요");
				JPanel countryPane = new JPanel();
					JLabel startCountry = new JLabel("출발지");
					ImageIcon icon = new ImageIcon("img/arrow.png");
					Image im = icon.getImage();
					Image im2 = im.getScaledInstance(50, 30, Image.SCALE_DEFAULT);
					ImageIcon icon2 = new ImageIcon(im2);
					JLabel arriveCountry = new JLabel("도착지");
				JPanel datePane = new JPanel();
					JLabel startDate = new JLabel("2021/02/02");
					JLabel arriveDate = new JLabel("2021/02/08");
					
			JPanel wrpTblPane = new JPanel();
			
				JPanel startTablePane = new JPanel();
					String startModelTitle[] = {"비행편명","출발시간","도착시간","총 비행시간","예약 상태","운임"};
					DefaultTableModel startModel = new DefaultTableModel(startModelTitle,0);
					JTable startTbl = new JTable(startModel);
					JScrollPane startSp = new JScrollPane(startTbl);
					
				JPanel arriveTablePane = new JPanel();
					String arriveModelTitle[] = {"비행편명","출발시간","도착시간","총 비행시간","예약상태","운임"};
					DefaultTableModel arriveModel = new DefaultTableModel(arriveModelTitle,0);
					JTable arriveTbl = new JTable(arriveModel);
					JScrollPane arriveSp = new JScrollPane(arriveTbl);
					
		JPanel btnPane = new JPanel();
			JButton cancelBtn = new JButton("예약취소");
			JButton nextBtn = new JButton("다음단계");
		JPanel test = new JPanel();
	
		CustomReservation3 reservation3 = new CustomReservation3();
		CustomReservation reservation = new CustomReservation();
	
	public CustomReservation2() {}
	public CustomReservation2(String start,String arrive) {
		setLayout(new BorderLayout());
		
		// 전환되는 패널
		add("North",changePane);
		changePane.setLayout(new BorderLayout(0,30));
		changePane.setBackground(Color.white);
		changePane.add("North",new JLabel());
		changePane.add("East",new JLabel());
		changePane.add("West",new JLabel());
		changePane.add("South",new JLabel());
		changePane.add("Center",centerPane);
			centerPane.setLayout(new BorderLayout());
			centerPane.setBackground(Color.white);
			centerPane.add("North",northPane);
				northPane.setLayout(new GridLayout(3,1));
				northPane.setBackground(Color.white);
					// 타이틀 라벨
					northPane.add(titleLbl);
						titleLbl.setHorizontalAlignment(JLabel.CENTER);
						titleLbl.setFont(new Font("굴림체",Font.BOLD,24));
						titleLbl.setBackground(Color.white);
					// 도시표시 패널
					northPane.add(countryPane);
						countryPane.setLayout(new FlowLayout(FlowLayout.CENTER,30,10));
						//출발지
						countryPane.add(startCountry);
						countryPane.setBackground(Color.white);
							startCountry.setFont(fnt);
							startCountry.setBackground(Color.white);
						//화살표
						countryPane.add(new JLabel(icon2));
						//도착지
						countryPane.add(arriveCountry);
							arriveCountry.setFont(fnt);
							arriveCountry.setBackground(Color.white);
					//날짜패널
					northPane.add(datePane);
					datePane.setBackground(Color.white);
						datePane.setLayout(new FlowLayout(FlowLayout.CENTER,80,0));
						datePane.setBackground(Color.white);
						// 출발 날짜
						datePane.add(startDate);
							startDate.setText(start);
							startDate.setFont(fnt);
							startDate.setBackground(Color.white);
							// 복귀 날짜
						datePane.add(arriveDate);
							arriveDate.setText(arrive);
							arriveDate.setFont(fnt);
							arriveDate.setBackground(Color.white);
							
							
			//테이블 패널
			centerPane.add("Center",wrpTblPane);
				wrpTblPane.setLayout(new GridLayout(1,2));
				wrpTblPane.add(startTablePane);
					startTablePane.setBackground(Color.white);
					startTablePane.add(startSp);
					startSp.setFont(fnt);
				wrpTblPane.add(arriveTablePane);
					arriveTablePane.setBackground(Color.white);
					arriveTablePane.add(arriveSp);
					arriveSp.setFont(fnt);
			
			centerPane.add("South",btnPane);
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
				btnPane.setBackground(Color.white);
				btnPane.add(cancelBtn);
					cancelBtn.setFont(fnt);
					cancelBtn.setBackground(new Color(0,130,255));
					cancelBtn.setForeground(Color.white);
					
				btnPane.add(nextBtn);
					nextBtn.setFont(fnt);
					nextBtn.setBackground(new Color(0,130,255));
					nextBtn.setForeground(Color.white);
					
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent ae){
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(str.equals("다음단계")) {
				this.setVisible(false);
				CustomFrame.reservation3.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.reservation3);
			}
		}
	}
	
}
