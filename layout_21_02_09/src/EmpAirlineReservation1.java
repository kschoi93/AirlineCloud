import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dbAll.EmpReservation1DAO;
import dbAll.EmpReservation1VO;
import dbAll.EmpReservationVO;

public class EmpAirlineReservation1 extends JPanel implements ActionListener,MouseListener{
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
					JLabel arriveCountry = new JLabel("변경할 항공편");
				JPanel datePane = new JPanel();
					JLabel startDate = new JLabel("출발 시간");
					JLabel arriveDate = new JLabel("도착 시간");	
			JPanel tablePane = new JPanel();
					Object startModelTitle[] = {"비행편명","출발시간","도착시간","총 비행시간","예약 상태","운임"};
					DefaultTableModel startModel = new DefaultTableModel(startModelTitle,0);
					JTable startTbl = new JTable(startModel);
					JScrollPane startSp = new JScrollPane(startTbl);

				JPanel btnPane = new JPanel();
					JButton cancelBtn = new JButton("변경취소");
					JButton nextBtn = new JButton("다음단계");
			JPanel test = new JPanel();
				
				//이벤트용 변수
				int row = 0; //테이블에서 눌린 행이 1개가 아닌걸 확인하는 용도
				
				static String newFlightNum;
				
			
					
//			EmpAirlineReservation reservation = new EmpAirlineReservation();
	
	public EmpAirlineReservation1() {
		setLayout(new BorderLayout());
		
		// 전환되는 패널
		add("North",changePane);
		changePane.setLayout(new BorderLayout(0,0));
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
						startDate.setFont(fnt);
						startDate.setBackground(Color.white);
						// 복귀 날짜
					datePane.add(arriveDate);
						arriveDate.setFont(fnt);
						arriveDate.setBackground(Color.white);
		//테이블 패널
		centerPane.add("Center",tablePane);
		tablePane.setBackground(Color.white);
			tablePane.add(startSp);
			startSp.setFont(fnt);
		
		centerPane.add("South",btnPane);
			btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
			btnPane.setBackground(Color.white);
			btnPane.add(cancelBtn);
				cancelBtn.setFont(fnt);
				cancelBtn.setBackground(new Color(255,128,128));
				cancelBtn.setForeground(Color.white);
				
			btnPane.add(nextBtn);
				nextBtn.setFont(fnt);
				nextBtn.setBackground(new Color(255,128,128));
				nextBtn.setForeground(Color.white);
					
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		startTbl.addMouseListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae){
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("변경취소")) {
				this.setVisible(false);
				EmpMainFrame.plan.setVisible(true);
			} else if(str.equals("다음단계")) {
				if(row==0) {
					JOptionPane.showMessageDialog(this, "변경할 항공편을 선택하세요.");
					
					CustomFrame.bookingChange3.setVisible(true);
					
				}else {
				this.setVisible(false);
				EmpMainFrame.reservation2.setVisible(true);
				EmpMainFrame.reservation2.table1Print();
				EmpMainFrame.reservation2.table2Print();
				EmpMainFrame.reservation2.test3();
				EmpMainFrame.centerPane.add(EmpMainFrame.reservation2);
				}
			}
		}
	}
	
	public void add(String dep, String FlightNO) {

		EmpReservation1DAO dao = new EmpReservation1DAO();
		
		List<EmpReservation1VO> lst = dao.EmpReservation1Allselect(dep, FlightNO);

		
		setNewTableList(lst);
	}
	
	public void tablePrint() {
		String resNOtest =EmpAirlineReservation.resNOtest;  //예약번호
		String dep = EmpAirlineReservation.dep;
		String FlightNO =EmpAirlineReservation.fno;
		
		
		
//		startCountry.setText(dep);
		startCountry.setText(FlightNO);
		
		add(dep,FlightNO);
	}
	
	public void setNewTableList(List<EmpReservation1VO> lst) {
		startModel.setRowCount(0); //JTable의 모든 레코드 지우기
		for(int i = 0 ; i<lst.size() ; i++) {
			EmpReservation1VO vo =lst.get(i);  // i씩 꺼낼때 vo에 들어가있는데

			Object[] data1 = {vo.getFlightno(),vo.getDepTime(),vo.getDesTime(),
					vo.getFlightTime(),vo.getState(),vo.getFare(),
			};
			
			System.out.println("hhhhh"+vo.getFlightno());
			startModel.addRow(data1);

		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==1) {
			if(startTbl.getSelectedRowCount() !=1) {
				JOptionPane.showMessageDialog(this, "1개의 비행편 선택 가능합니다.");
			}else {
				row = startTbl.getSelectedRow()+1;
				System.out.println(row);
				
			}
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.getButton()==1) {
			int rowSelected = startTbl.getSelectedRow();
			arriveCountry.setText((String)startModel.getValueAt(rowSelected, 0));
			startDate.setText((String)startModel.getValueAt(rowSelected, 1));
			arriveDate.setText((String)startModel.getValueAt(rowSelected, 2));
			
			newFlightNum = (String)startModel.getValueAt(rowSelected, 0);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
