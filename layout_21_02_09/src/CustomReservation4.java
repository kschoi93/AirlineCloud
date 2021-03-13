
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import dbAll.CustomReservation4DAO;
import dbAll.CustomReservation4VO;


public class CustomReservation4 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font fnt2 = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
		JPanel centerPane = new JPanel();
			JLabel titleLbl = new JLabel("출발편 좌석을 선택하세요");
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
//						JLabel name = new JLabel("탑승자");
						JLabel num = new JLabel("좌석번호");
					JPanel comboPane = new JPanel();
						String food[] = {"한식","양식","중식"};
						DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(food);
						JComboBox<String> combo = new JComboBox<String>(model);
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
	
	// 1 ~ 5번째 사람의 좌석번호를 적는공간
	JLabel a = new JLabel("");
	JLabel b = new JLabel("");
	JLabel c = new JLabel("");
	JLabel d = new JLabel("");
	JLabel e = new JLabel("");
	int count ; // 좌석 선택이 몇번 가능한지 확인하기 위해..
	int oneNext ; // 편도에서 다음단계 눌렀을때 구분하기 위한 변수
	int roundNext ; // 왕복에서 다음단계 눌렀을때 구분하기 위한 변수
	// 편도 데이터 저장 (출발지에서 출발하는 데이터)
	// 데이터 저장순서 1) 기내식 종류 , 2) 예약자 좌석  , 3) 동행자1 좌석 ......
	static List<CustomReservation4VO> onelst = new ArrayList<CustomReservation4VO>();
	CustomReservation4VO vo;
	// 왕복 데이터 저장 (도착지에서 출발하는 데이터)
	static List<CustomReservation4VO> roundlst = new ArrayList<CustomReservation4VO>();
	
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
				southPane.add("Center",wrapPane);
				JPanel onePane = new JPanel();
				JPanel twoPane = new JPanel();
				JPanel thrPane = new JPanel();
				JPanel fouPane = new JPanel();
				JPanel fivPane = new JPanel();
				wrapPane.setLayout(new FlowLayout());
					wrapPane.add(numPane);
					wrapPane.setBackground(Color.white);
					numPane.setLayout(new FlowLayout(FlowLayout.CENTER,40,100));
					numPane.setBackground(Color.white);
//						numPane.add(name);
//						name.setBackground(Color.white);
//							name.setFont(fnt);
						
						numPane.add(combo);
							combo.setBackground(Color.white);
							combo.setFont(fnt);
						numPane.add(num);
							num.setBackground(Color.white);
							num.setFont(fnt);
							// 좌석 라벨추가
						numPane.add(a);
							a.setBackground(Color.white);
							a.setFont(fnt);
						numPane.add(b);
							b.setBackground(Color.white);
							b.setFont(fnt);
						numPane.add(c);
							c.setBackground(Color.white);
							c.setFont(fnt);
						numPane.add(d);
							d.setBackground(Color.white);
							d.setFont(fnt);
						numPane.add(e);
							e.setBackground(Color.white);
							e.setFont(fnt);
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
				
				if(count <CustomReservation.humanCount) {
					if(count ==0) {
						countcheck(seats,a);
					} else if(count ==1) {
						countcheck(seats,b);
					} else if(count ==2) {
						countcheck(seats,c);
					} else if(count ==3) {
						countcheck(seats,d);
					} else if(count ==4) {
						countcheck(seats,e);
					}
					
				} else if(count == CustomReservation.humanCount) {
					if(count==5) {
						btnMinusCheck(e,seats);
					} else if(count == 4) {
						btnMinusCheck(d,seats);
					} else if(count ==3 ) {
						btnMinusCheck(c,seats);
					} else if(count == 2) {
						btnMinusCheck(b,seats);
					} else if(count ==1) {
						btnMinusCheck(a,seats);
					}
					

				}else if(count > CustomReservation.humanCount) {
					setTrue();
				}
				
				
						
				if(seats.equals("다음단계")) {
					// 편도일때는 저장하고 바로~ 넘어간다
					if(CustomReservation.rdb.equals("편도")) {
						if(oneNext==0) {
							// count에 따라서 좌석수를 저장하는 수가 달라진다
							if(count==1) {
								vo = new CustomReservation4VO((String)combo.getSelectedItem(), a.getText());	
							} else if(count==2) {
								vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText());
							}else if(count==3) {
								vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText());
							}else if(count==4) {
								vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText(),d.getText());
							}else if(count==5) {
								vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText(),d.getText(),e.getText());
							}
							onelst.add(vo);
							oneNext++;
						}
						
						this.setVisible(false);
						CustomFrame.reservation5.getData();
						CustomFrame.reservation5.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation5);
					} else if(CustomReservation.rdb.equals("왕복") && roundNext ==0) { // 왕복이면 0번째는 출발지에서 출발하는 좌석 선택
						// count에 따라서 좌석수를 저장하는 수가 달라진다
						if(count==1) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(), a.getText());	
						} else if(count==2) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText());
						}else if(count==3) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText());
						}else if(count==4) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText(),d.getText());
						}else if(count==5) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText(),d.getText(),e.getText());
						}
						onelst.add(vo);
						roundNext++;
						//settrue 시키고, 도착지 정보 세팅시켜서 넘긴다
						setTrue();
						titleLbl.setText("복귀편 좌석을 선택하세요");
						setArriveSeatPrint();
					} else if(CustomReservation.rdb.equals("왕복") && roundNext ==1) { // 1번째는 도착지에서 출발하는 좌석 선택
						// count에 따라서 좌석수를 저장하는 수가 달라진다
						if(count==1) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(), a.getText());	
						} else if(count==2) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText());
						}else if(count==3) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText());
						}else if(count==4) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText(),d.getText());
						}else if(count==5) {
							vo = new CustomReservation4VO((String)combo.getSelectedItem(),a.getText(),b.getText(),c.getText(),d.getText(),e.getText());
						}
						roundlst.add(vo);
						roundNext++;
						this.setVisible(false);
						CustomFrame.reservation5.getData();
						CustomFrame.reservation5.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation5);
					} else if(CustomReservation.rdb.equals("왕복") && roundNext ==2) { // 2번째는 더이상 저장하지 않는다.
						this.setVisible(false);
						CustomFrame.reservation5.getData();
						CustomFrame.reservation5.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation5);
					}
					
					
				}else if(seats.equals("예약취소")){
					this.setVisible(false);
					CustomFrame.plan.setVisible(true);
					setTrue();
					onelst.removeAll(onelst);
					roundlst.removeAll(roundlst);
					
				}
			}
		
	}
	///////////////// btn minus 추가
	public void btnMinusCheck(JLabel num,String seats) {
		btnMinus(btnA,num,seats);
		btnMinus(btnB,num,seats);
		btnMinus(btnC,num,seats);
		btnMinus(btnD,num,seats);
		btnMinus(btnE,num,seats);
		btnMinus(btnF,num,seats);
	}
	
	////////////////////////// count check위해      1번
	public void countcheck(String seats, JLabel num) {
		for(int i=0; i<btnA.length; i++) {
			if(btnA[i].getText().equals(seats)) {
				setText(seats,num);
			} else if(btnB[i].getText().equals(seats)) {
				setText(seats,num);
			} else if(btnC[i].getText().equals(seats)) {
				setText(seats,num);
			} else if(btnD[i].getText().equals(seats)) {
				setText(seats,num);
			} else if(btnE[i].getText().equals(seats)) {
				setText(seats,num);
			} else if(btnF[i].getText().equals(seats)) {
				setText(seats,num);
			}
		}
	}
	
	// 시트 버튼 눌렀을때 변화 및 좌석번호         2번
		public void setText(String seats, JLabel num) {
			if(count <CustomReservation.humanCount) {
				btnPrint(btnA,num,seats);
				btnPrint(btnB,num,seats);
				btnPrint(btnC,num,seats);
				btnPrint(btnD,num,seats);
				btnPrint(btnE,num,seats);
				btnPrint(btnF,num,seats);
				
			} else if(count == CustomReservation.humanCount) {
				
				btnMinus(btnA,num,seats);
				btnMinus(btnB,num,seats);
				btnMinus(btnC,num,seats);
				btnMinus(btnD,num,seats);
				btnMinus(btnE,num,seats);
				btnMinus(btnF,num,seats);
			}
		}
		//////////////////////////// 3-1번
		public void btnPrint(JButton btn[], JLabel num, String seats) {
			for(int i=0; i<btnA.length; i++) {
				if(btn[i].getText().equals(seats)) {
					Color col = btn[i].getBackground();
					if(col.getRed()==192) {
						btn[i].setBackground(Color.white);
						num.setText("");
						count--;
						break;
					}
					btn[i].setBackground(Color.LIGHT_GRAY);
					num.setText(btn[i].getText());
					
					if(count < CustomReservation.humanCount) {
						count++;
					}
					break;
					
				}
			}
		}
		////////////////////////// 3-2번
		public void btnMinus(JButton btn[], JLabel num, String seats) {
			for(int i=0; i<btnA.length; i++) {
				if(btn[i].getText().equals(seats)) {
					Color col = btn[i].getBackground();
					if(col.getRed()==192) {
						btn[i].setBackground(Color.white);
						num.setText("");
						count--;
						break;
					}
				}
			}
		}
		
	// 출발 시트 선택위한...
	public void setStartSeatPaint() {
		// start 날짜
		String startD = CustomReservation.startDateField.getText().substring(2,10);
		System.out.println("출발하는 날짜 고른거->"+startD);
		String startFlightno = CustomReservation2.startSelect;
		System.out.println("출발하느날짜 비행편->"+startFlightno);
		CustomReservation4DAO dao = new CustomReservation4DAO();
		List<CustomReservation4VO> lst = dao.startSeat(startFlightno, startD);
		for(int i = 0; i<lst.size(); i++) {
			CustomReservation4VO vo = lst.get(i);
			System.out.println("좌석이 어떤게 받아져왔나----->"+vo);
			for(int j=0; j<btnA.length; j++) {
				if(btnA[j].getText().equals(vo.getSeatNo())) {
					btnA[j].setBackground(Color.black);
					btnA[j].setEnabled(false);
				} else if(btnB[j].getText().equals(vo.getSeatNo())) {
					btnB[j].setBackground(Color.black);
					btnB[j].setEnabled(false);
				} else if(btnC[j].getText().equals(vo.getSeatNo())) {
					btnC[j].setBackground(Color.black);
					btnC[j].setEnabled(false);
				} else if(btnD[j].getText().equals(vo.getSeatNo())) {
					btnD[j].setBackground(Color.black);
					btnD[j].setEnabled(false);
				} else if(btnD[j].getText().equals(vo.getSeatNo())) {
					btnD[j].setBackground(Color.black);
					btnD[j].setEnabled(false);
				} else if(btnE[j].getText().equals(vo.getSeatNo())) {
					btnE[j].setBackground(Color.black);
					btnE[j].setEnabled(false);
				} else if(btnF[j].getText().equals(vo.getSeatNo())) {
					btnF[j].setBackground(Color.black);
					btnF[j].setEnabled(false);
				}
				
			}
		}
		System.out.println("???????????????????????????????????????????????????????????????????");
	}
	
	// 복귀 시트 선택
	public void setArriveSeatPrint() {
		// arrive 날짜
		String arriveD = CustomReservation.arriveDateField.getText().substring(2,10);
		String arriveFlightno = CustomReservation2.arriveSelect;
		CustomReservation4DAO dao = new CustomReservation4DAO();
		List<CustomReservation4VO> lst = dao.startSeat(arriveFlightno, arriveD);
		for(int i = 0; i<lst.size(); i++) {
			CustomReservation4VO vo = lst.get(i);
			for(int j=0; j<btnA.length; j++) {
				if(btnA[j].getText().equals(vo.getSeatNo())) {
					btnA[j].setBackground(Color.black);
					btnA[j].setEnabled(false);
				} else if(btnB[j].getText().equals(vo.getSeatNo())) {
					btnB[j].setBackground(Color.black);
					btnB[j].setEnabled(false);
				} else if(btnC[j].getText().equals(vo.getSeatNo())) {
					btnC[j].setBackground(Color.black);
					btnC[j].setEnabled(false);
				} else if(btnD[j].getText().equals(vo.getSeatNo())) {
					btnD[j].setBackground(Color.black);
					btnD[j].setEnabled(false);
				} else if(btnD[j].getText().equals(vo.getSeatNo())) {
					btnD[j].setBackground(Color.black);
					btnD[j].setEnabled(false);
				} else if(btnE[j].getText().equals(vo.getSeatNo())) {
					btnE[j].setBackground(Color.black);
					btnE[j].setEnabled(false);
				} else if(btnF[j].getText().equals(vo.getSeatNo())) {
					btnF[j].setBackground(Color.black);
					btnF[j].setEnabled(false);
				}
				
			}
		}
	}
	
	// 왕복일 경우 도착지 검색하기 위해 다시 원복귀 시킨다
	public void setTrue() {
		for(int j=0; j<btnA.length; j++) {
			btnA[j].setBackground(Color.white);
			btnA[j].setEnabled(true);
			btnB[j].setBackground(Color.white);
			btnB[j].setEnabled(true);
			btnC[j].setBackground(Color.white);
			btnC[j].setEnabled(true);
			btnD[j].setBackground(Color.white);
			btnD[j].setEnabled(true);
			btnD[j].setBackground(Color.white);
			btnD[j].setEnabled(true);
			btnE[j].setBackground(Color.white);
			btnE[j].setEnabled(true);
			btnF[j].setBackground(Color.white);
			btnF[j].setEnabled(true);
		}
		count = 0;
		a.setText("");
		b.setText("");
		c.setText("");
		d.setText("");
		e.setText("");
	}
	
	
}
