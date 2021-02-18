
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;


public class CustomReservation4 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font fnt2 = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
		JPanel centerPane = new JPanel();
			JLabel titleLbl = new JLabel("좌석을 선택하세요");
			JPanel seatPane = new JPanel();
			JScrollPane sp = new JScrollPane(seatPane);
				JPanel leftSeatPane = new JPanel();
					JPanel abcPane = new JPanel();
				JPanel rightSeatPane = new JPanel();
					JPanel defPane = new JPanel();
		JPanel southPane = new JPanel();
			JPanel wrapPane = new JPanel();
				JPanel pane1 = new JPanel();
					JPanel numPane = new JPanel();
						JLabel name = new JLabel("탑승자");
						JLabel num = new JLabel("좌석번호");
					JPanel comboPane = new JPanel();
					String test[] = {"Test","Test2"};
					DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(test);
					JComboBox<String> combo = new JComboBox<String>(model);
			JScrollPane southSp = new JScrollPane(wrapPane);
			JPanel btnPane = new JPanel();
				JButton cancelBtn = new JButton("예약취소");
				JButton nextBtn = new JButton("다음단계");
	JButton A1 = new JButton(); JButton B1 = new JButton(); JButton C1 = new JButton(); JButton D1 = new JButton(); JButton E1 = new JButton(); JButton F1 = new JButton();
	JButton A2 = new JButton(); JButton B2 = new JButton(); JButton C2 = new JButton(); JButton D2 = new JButton(); JButton E2 = new JButton(); JButton F2 = new JButton();
	JButton A3 = new JButton(); JButton B3 = new JButton(); JButton C3 = new JButton(); JButton D3 = new JButton(); JButton E3 = new JButton(); JButton F3 = new JButton();
	JButton A4 = new JButton(); JButton B4 = new JButton(); JButton C4 = new JButton(); JButton D4 = new JButton(); JButton E4 = new JButton(); JButton F4 = new JButton();
	JButton A5 = new JButton(); JButton B5 = new JButton(); JButton C5 = new JButton(); JButton D5 = new JButton(); JButton E5 = new JButton(); JButton F5 = new JButton();
	JButton A6 = new JButton(); JButton B6 = new JButton(); JButton C6 = new JButton(); JButton D6 = new JButton(); JButton E6 = new JButton(); JButton F6 = new JButton();
	JButton A7 = new JButton(); JButton B7 = new JButton(); JButton C7 = new JButton(); JButton D7 = new JButton(); JButton E7 = new JButton(); JButton F7 = new JButton();
	JButton A8 = new JButton(); JButton B8 = new JButton(); JButton C8 = new JButton(); JButton D8 = new JButton(); JButton E8 = new JButton(); JButton F8 = new JButton();
	JButton A9 = new JButton(); JButton B9 = new JButton(); JButton C9 = new JButton(); JButton D9 = new JButton(); JButton E9 = new JButton(); JButton F9 = new JButton();
	JButton A10 = new JButton(); JButton B10 = new JButton(); JButton C10 = new JButton(); JButton D10 = new JButton(); JButton E10 = new JButton(); JButton F10 = new JButton();
	JButton A11 = new JButton(); JButton B11 = new JButton(); JButton C11 = new JButton(); JButton D11 = new JButton(); JButton E11 = new JButton(); JButton F11 = new JButton();
	JButton A12 = new JButton(); JButton B12 = new JButton(); JButton C12 = new JButton(); JButton D12 = new JButton(); JButton E12 = new JButton(); JButton F12 = new JButton();
	JButton A13 = new JButton(); JButton B13 = new JButton(); JButton C13 = new JButton(); JButton D13 = new JButton(); JButton E13 = new JButton(); JButton F13 = new JButton();
	JButton A14 = new JButton(); JButton B14 = new JButton(); JButton C14 = new JButton(); JButton D14 = new JButton(); JButton E14 = new JButton(); JButton F14 = new JButton();
	JButton A15 = new JButton(); JButton B15 = new JButton(); JButton C15 = new JButton(); JButton D15 = new JButton(); JButton E15 = new JButton(); JButton F15 = new JButton();
	JButton A16 = new JButton(); JButton B16 = new JButton(); JButton C16 = new JButton(); JButton D16 = new JButton(); JButton E16 = new JButton(); JButton F16 = new JButton();
	JButton A17 = new JButton(); JButton B17 = new JButton(); JButton C17 = new JButton(); JButton D17 = new JButton(); JButton E17 = new JButton(); JButton F17 = new JButton();
	JButton A18 = new JButton(); JButton B18 = new JButton(); JButton C18 = new JButton(); JButton D18 = new JButton(); JButton E18 = new JButton(); JButton F18 = new JButton();
	JButton A19 = new JButton(); JButton B19 = new JButton(); JButton C19 = new JButton(); JButton D19 = new JButton(); JButton E19 = new JButton(); JButton F19 = new JButton();
	JButton A20 = new JButton(); JButton B20 = new JButton(); JButton C20 = new JButton(); JButton D20 = new JButton(); JButton E20 = new JButton(); JButton F20 = new JButton();
	JButton A21 = new JButton(); JButton B21 = new JButton(); JButton C21 = new JButton(); JButton D21 = new JButton(); JButton E21 = new JButton(); JButton F21 = new JButton();
	JButton A22 = new JButton(); JButton B22 = new JButton(); JButton C22 = new JButton(); JButton D22 = new JButton(); JButton E22 = new JButton(); JButton F22 = new JButton();
	JButton A23 = new JButton(); JButton B23 = new JButton(); JButton C23 = new JButton(); JButton D23 = new JButton(); JButton E23 = new JButton(); JButton F23 = new JButton();
	JButton A24 = new JButton(); JButton B24 = new JButton(); JButton C24 = new JButton(); JButton D24 = new JButton(); JButton E24 = new JButton(); JButton F24 = new JButton();
	JButton A25 = new JButton(); JButton B25 = new JButton(); JButton C25 = new JButton(); JButton D25 = new JButton(); JButton E25 = new JButton(); JButton F25 = new JButton();
	JButton A26 = new JButton(); JButton B26 = new JButton(); JButton C26 = new JButton(); JButton D26 = new JButton(); JButton E26 = new JButton(); JButton F26 = new JButton();
	JButton A27 = new JButton(); JButton B27 = new JButton(); JButton C27 = new JButton(); JButton D27 = new JButton(); JButton E27 = new JButton(); JButton F27 = new JButton();
	JButton A28 = new JButton(); JButton B28 = new JButton(); JButton C28 = new JButton(); JButton D28 = new JButton(); JButton E28 = new JButton(); JButton F28 = new JButton();
	JButton A29 = new JButton(); JButton B29 = new JButton(); JButton C29 = new JButton(); JButton D29 = new JButton(); JButton E29 = new JButton(); JButton F29 = new JButton();
	JButton A30 = new JButton(); JButton B30 = new JButton(); JButton C30 = new JButton(); JButton D30 = new JButton(); JButton E30 = new JButton(); JButton F30 = new JButton();
	JButton A31 = new JButton(); JButton B31 = new JButton(); JButton C31 = new JButton(); JButton D31 = new JButton(); JButton E31 = new JButton(); JButton F31 = new JButton();
	JButton A32 = new JButton(); JButton B32 = new JButton(); JButton C32 = new JButton(); JButton D32 = new JButton(); JButton E32 = new JButton(); JButton F32 = new JButton();
	
	JButton btnA[]= {A1,A2,A3,A4,A5,A6,A7,A8,A9,A10,A11,A12,A13,A14,A15,A16,A17,A18,A19,A20,A21,A22,A23,A24,A25,A26,A27,A28,A29,A30,A31,A32};
	JButton btnB[]= {B1,B2,B3,B4,B5,B6,B7,B8,B9,B10,B11,B12,B13,B14,B15,B16,B17,B18,B19,B20,B21,B22,B23,B24,B25,B26,B27,B28,B29,B30,B31,B32};
	JButton btnC[]= {C1,C2,C3,C4,C5,C6,C7,C8,C9,C10,C11,C12,C13,C14,C15,C16,C17,C18,C19,C20,C21,C22,C23,C24,C25,C26,C27,C28,C29,C30,C31,C32};
	JButton btnD[]= {D1,D2,D3,D4,D5,D6,D7,D8,D9,D10,D11,D12,D13,D14,D15,D16,D17,D18,D19,D20,D21,D22,D23,D24,D25,D26,D27,D28,D29,D30,D31,D32};
	JButton btnE[]= {E1,E2,E3,E4,E5,E6,E7,E8,E9,E10,E11,E12,E13,E14,E15,E16,E17,E18,E19,E20,E21,E22,E23,E24,E25,E26,E27,E28,E29,E30,E31,E32};
	JButton btnF[]= {F1,F2,F3,F4,F5,F6,F7,F8,F9,F10,F11,F12,F13,F14,F15,F16,F17,F18,F19,F20,F21,F22,F23,F24,F25,F26,F27,F28,F29,F30,F31,F32};
	
	GridLayout grid = new GridLayout(0,3);
	public CustomReservation4() {
		setLayout(new BorderLayout(200,50));
		setBackground(Color.white);
		add("North",new JLabel());
		add("West",new JLabel());
		add("East",new JLabel());
		add("South",new JLabel());
		
		add("Center",main);
		main.setLayout(new GridLayout(2,1));
		main.setBackground(Color.white);
			main.add("North",centerPane);
			centerPane.setBackground(Color.white);
			centerPane.setLayout(new BorderLayout());
				centerPane.add("North",titleLbl);
					titleLbl.setHorizontalAlignment(JLabel.CENTER);
					titleLbl.setFont(fnt2);
					titleLbl.setBackground(Color.white);
				centerPane.add("Center",sp);
					seatPane.setBackground(Color.white);
					seatPane.setLayout(new GridLayout(1,2));
						seatPane.add(leftSeatPane);
						leftSeatPane.setLayout(new BorderLayout(50,30));
						leftSeatPane.setBackground(Color.white);
						leftSeatPane.add("North", new JLabel());
						leftSeatPane.add("South",new JLabel());
						leftSeatPane.add("East", new JLabel());
						leftSeatPane.add("West",new JLabel());
							leftSeatPane.add(abcPane);
							grid.setVgap(5);
							grid.setHgap(15);
							abcPane.setLayout(grid);
							abcPane.setBackground(Color.white);
								for(int i=0; i<32; i++) {
									abcPane.add(btnA[i]);
									btnA[i].setText("A"+(i+1));
									btnA[i].setFont(fnt);
									btnA[i].setBackground(Color.white);
									btnA[i].setBorder(new LineBorder(Color.black,1));
									
									abcPane.add(btnB[i]);		
									btnB[i].setText("B"+(i+1));
									btnB[i].setFont(fnt);
									btnB[i].setBackground(Color.white);
									btnB[i].setBorder(new LineBorder(Color.black,2));
									
									abcPane.add(btnC[i]);
									btnC[i].setText("C"+(i+1));					
									btnC[i].setFont(fnt);
									btnC[i].setBackground(Color.white);
									btnC[i].setBorder(new LineBorder(Color.black,2));
									
									btnA[i].addActionListener(this);
									btnB[i].addActionListener(this);
									btnC[i].addActionListener(this);
								}
							
						seatPane.add(rightSeatPane);
						rightSeatPane.setLayout(new BorderLayout(50,30));
						rightSeatPane.setBackground(Color.white);
						rightSeatPane.add("North",new JLabel());
						rightSeatPane.add("South",new JLabel());
						rightSeatPane.add("East",new JLabel());
						rightSeatPane.add("West",new JLabel());
							rightSeatPane.add(defPane);
							defPane.setLayout(grid);
							defPane.setBackground(Color.white);
							
								for(int i=0; i<32; i++) {
									defPane.add(btnD[i]);
									btnD[i].setText("D"+(i+1));
									btnD[i].setFont(fnt);
									btnD[i].setBackground(Color.white);
									btnD[i].setBorder(new LineBorder(Color.black,2));
									
									defPane.add(btnE[i]);		
									btnE[i].setText("E"+(i+1));
									btnE[i].setFont(fnt);
									btnE[i].setBackground(Color.white);
									btnE[i].setBorder(new LineBorder(Color.black,2));
									
									defPane.add(btnF[i]);
									btnF[i].setText("F"+(i+1));
									btnF[i].setFont(fnt);
									btnF[i].setBackground(Color.white);
									btnF[i].setBorder(new LineBorder(Color.black,2));
									
									btnD[i].addActionListener(this);
									btnE[i].addActionListener(this);
									btnF[i].addActionListener(this);
								}
						
			// 탑승자 좌석 선택번호 표시, 기내식 선택
			main.add("Center",southPane);
			southPane.setLayout(new BorderLayout());
				southPane.add("Center",southSp);
				wrapPane.setLayout(new FlowLayout());
					wrapPane.add(numPane);
					wrapPane.setBackground(Color.white);
					numPane.setLayout(new FlowLayout(FlowLayout.CENTER,80,100));
					numPane.setBackground(Color.white);
						numPane.add(name);
						name.setBackground(Color.white);
							name.setFont(fnt);
						numPane.add(num);
						num.setBackground(Color.white);
							num.setFont(fnt);
						numPane.add(combo);
						combo.setBackground(Color.white);
							combo.setFont(fnt);
				southPane.add("South",btnPane);
				btnPane.setBackground(Color.white);
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER ,30,5));
					btnPane.add(cancelBtn);
						cancelBtn.setBackground(new Color(0,130,255));
						cancelBtn.setForeground(Color.white);
						cancelBtn.setFont(fnt);
					btnPane.add(nextBtn);
						nextBtn.setBackground(new Color(0,130,255));
						nextBtn.setForeground(Color.white);
						nextBtn.setFont(fnt);
		
		setSize(1000,800);
		setVisible(true);
		


		nextBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		
		if(obj instanceof JButton) {
			String seats = ae.getActionCommand();
			for(int i=0; i<btnA.length;i++) {
				if(btnA[i].getText().equals(seats)) {
					Color col = btnA[i].getBackground();
					if(col.getRed()==192) {
						btnA[i].setBackground(Color.white);
						break;
					}
					btnA[i].setBackground(Color.LIGHT_GRAY);
					break;
				} else if(btnB[i].getText().equals(seats)) {
					Color col = btnB[i].getBackground();
					if(col.getRed()==192) {
						btnB[i].setBackground(Color.white);
						break;
					}
					btnB[i].setBackground(Color.LIGHT_GRAY);
					break;
				} else if(btnC[i].getText().equals(seats)) {
					Color col = btnC[i].getBackground();
					if(col.getRed()==192) {
						btnC[i].setBackground(Color.white);
						break;
					}
					btnC[i].setBackground(Color.LIGHT_GRAY);
					break;
				} else if(btnD[i].getText().equals(seats)) {
					Color col = btnD[i].getBackground();
					if(col.getRed()==192) {
						btnD[i].setBackground(Color.white);
						break;
					}
					btnD[i].setBackground(Color.LIGHT_GRAY);
					break;
				} else if(btnE[i].getText().equals(seats)) {
					Color col = btnE[i].getBackground();
					if(col.getRed()==192) {
						btnE[i].setBackground(Color.white);
						break;
					}
					btnE[i].setBackground(Color.LIGHT_GRAY);
					break;
				} else if(btnF[i].getText().equals(seats)) {
					Color col = btnF[i].getBackground();
					if(col.getRed()==192) {
						btnF[i].setBackground(Color.white);
						break;
					}
					btnF[i].setBackground(Color.LIGHT_GRAY);
					break;
				}
			}
			
			if(seats.equals("다음단계")) {
//				this.setVisible(false);
//				CustomFrame.reservation4.setVisible(true);
//				CustomFrame.centerPane.add(CustomFrame.reservation4);
			}else if(seats.equals("예약취소")){
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			}
		}
	}
}
