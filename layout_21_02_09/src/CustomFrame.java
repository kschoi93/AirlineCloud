
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.LineBorder;

import dbAll.CustomFrameDAO;
import dbAll.CustomFrameVO;

public class CustomFrame extends JFrame implements ActionListener, MouseListener ,Runnable{
	// 상단 패널
	JPanel northPane = new JPanel();
		JPanel northNPane = new JPanel();
		JPanel northCPane = new JPanel();
		JPanel northSPane = new JPanel();
		static JLabel hiLbl = new JLabel("______님 어서오세요");
		JLabel resLbl = new JLabel("예 약 하 기");
		JLabel searchLbl = new JLabel("예 약 조 회");
		JLabel mypageLbl = new JLabel("마 이 페 이 지");
		JLabel logoutLbl = new JLabel("로 그 아 웃");
		//팝업메뉴
		JPopupMenu myPop = new JPopupMenu();
			JMenuItem my_page = new JMenuItem("마이페이지");
			JMenuItem mileageItem = new JMenuItem("마일리지 사용내역");
			JMenuItem memberInformationItem = new JMenuItem("회원정보 수정");
	String title[] = {"_______님 어서오세요","예약하기","예약조회","마이페이지","로그아웃"};
	
	Font fnt = new Font("굴림체",Font.BOLD,14);
	
	//// 중앙 패널
	static JPanel centerPane = new JPanel();
		static CustomPlan plan = new CustomPlan(); //항공일정
		static CustomReservation reservation = new CustomReservation(); // 예약하기
			static CustomReservation2 reservation2 = new CustomReservation2();
			static CustomReservation3 reservation3 = new CustomReservation3();
			static CustomReservation4 reservation4 = new CustomReservation4();
			static CustomReservation5 reservation5 = new CustomReservation5();
			static CustomReservation6 reservation6 = new CustomReservation6();
			static CustomBookingCancel bookingCancel = new CustomBookingCancel();
			static CustomBookingChange1 bookingChange1 = new CustomBookingChange1();
			static CustomBookingChange2 bookingChange2 = new CustomBookingChange2();
			static CustomBookingChange3 bookingChange3 = new CustomBookingChange3();
			static CustomBookingChange4 bookingChange4 = new CustomBookingChange4();
		static CustomMyPage mypage = new CustomMyPage(); // 마이페이지
			static CustomMileage mileage = new CustomMileage(); // 마이페이지 - 마일리지
			static CustomRevise revise = new CustomRevise(); // 마이페이지 - 회원정보 수정
	static JPanel visiblePane[] = {plan,reservation,reservation2,reservation3,mypage,mileage,revise,reservation4
			,reservation5,reservation6,bookingCancel,bookingChange1,bookingChange2,bookingChange3,bookingChange4};
			
	// 하단패널
	JPanel clockPane = new JPanel(new BorderLayout());
		JPanel reClock = new JPanel();
			JLabel koreaLbl = new JLabel("한국 :");
		JPanel clock = new JPanel();
			JLabel nationLbl = new JLabel();
	/////////////////////// 디지털 시계 /////////////////////
	DigitalClock koreaClock = new DigitalClock("Asia/Seoul");
	DigitalClock londonClock = new DigitalClock("Europe/London");
	DigitalClock parisClock = new DigitalClock("Europe/Paris");
	DigitalClock moscowClock = new DigitalClock("Europe/Moscow");
	DigitalClock dubaiClock = new DigitalClock("Asia/Dubai");
	DigitalClock jakartaClock = new DigitalClock("Asia/Jakarta");
	DigitalClock shanghaiClock = new DigitalClock("Asia/Shanghai");
	DigitalClock sydneyClock = new DigitalClock("Australia/Sydney");
	DigitalClock vancouverClock = new DigitalClock("America/Vancouver");
	DigitalClock losangelesClock = new DigitalClock("America/Los_Angeles");
	DigitalClock mexicocityClock = new DigitalClock("America/Mexico_City");
	DigitalClock newyorkClock = new DigitalClock("America/New_York");
	DigitalClock saopauloClock = new DigitalClock("America/Sao_Paulo");
	/////////////////////////////////////////////////////////
	//////////////////////시계패널//////////////////////////////

	DigitalClock[] country = {londonClock,parisClock,moscowClock,dubaiClock,jakartaClock,shanghaiClock
			,sydneyClock,vancouverClock,losangelesClock,mexicocityClock,newyorkClock,saopauloClock};
	String countryName[] = {"런던 :","파리 :","모스크바 :","두바이 :","자카르타 :","상하이 :","시드니 :","밴쿠버 :"
			,"로스앤젤레스 :","멕시코시티 :","뉴욕 :","상파울루 :"}; 
	String countryImg[] = {"flag/uk.png","flag/franch.png","flag/russia.png","flag/uae.png","flag/indonesia.png"
			, "flag/shanghai.png", "flag/sydney.png", "flag/canada.png","flag/america.png","flag/mexico.png"
			, "flag/america.png","flag/sangpaulo.png"};

	public CustomFrame() {
		// 기본화면 레이아웃
		setLayout(new BorderLayout());
		northPane.setLayout(new BorderLayout());
		northPane.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		northPane.add("North",northNPane);
		northNPane.setBackground(Color.white);
		
		northCPane.setLayout(new GridLayout(1,5));
		northPane.add("Center",northCPane);
		//북쪽 프레임
		northCPane.add(hiLbl);
			hiLbl.setOpaque(true);
			hiLbl.setBackground(Color.white);
			hiLbl.setFont(fnt);
			hiLbl.setHorizontalAlignment(JLabel.CENTER);
			hiLbl.setForeground(new Color(0,128,255));
			customNameChange(); // 이름 세팅하는 메소드
		northCPane.add(resLbl);
			resLbl.setOpaque(true);
			resLbl.setFont(fnt);
			resLbl.setBackground(Color.white);
			resLbl.setHorizontalAlignment(JLabel.CENTER);
			resLbl.setForeground(new Color(0,128,255));
		northCPane.add(searchLbl);
			searchLbl.setOpaque(true);
			searchLbl.setFont(fnt);
			searchLbl.setBackground(Color.white);
			searchLbl.setHorizontalAlignment(JLabel.CENTER);
			searchLbl.setForeground(new Color(0,128,255));
		northCPane.add(mypageLbl);
			mypageLbl.setOpaque(true);
			mypageLbl.setFont(fnt);
			mypageLbl.setBackground(Color.white);
			mypageLbl.setHorizontalAlignment(JLabel.CENTER);
			mypageLbl.setForeground(new Color(0,128,255));
		northCPane.add(logoutLbl);
			logoutLbl.setOpaque(true);
			logoutLbl.setFont(fnt);
			logoutLbl.setBackground(Color.white);
			logoutLbl.setHorizontalAlignment(JLabel.CENTER);
			logoutLbl.setForeground(new Color(0,0,0));
		
		northPane.add("South",northSPane);
		northSPane.setBackground(Color.white);
		
		//마이페이지 팝어메뉴 추가
		myPop.add(my_page);
		myPop.add(mileageItem);
		myPop.add(memberInformationItem);

		add("North",northPane);
		
		//중앙 프레임
		add("Center",centerPane);
		centerPane.setBackground(Color.white);
		centerPane.setLayout(new BorderLayout());
			centerPane.add(plan);
			
		//남쪽 프레임
		clockPane.setLayout(new GridLayout(0,2));
		clockPane.setBorder(new LineBorder(Color.lightGray, 1));
		clockPane.add("West",clock);
		clockPane.add("East",reClock);
		add("South",clockPane);
		
		// 프레임 기본 설정
		setTitle("CLOUD AIR");
		setResizable(false);
		setIconImage(AirlineMain.im);
		setSize(1000,800);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 이벤트 등록
		resLbl.addMouseListener(this);
		searchLbl.addMouseListener(this);
		mypageLbl.addMouseListener(this);
		logoutLbl.addMouseListener(this);
		mileageItem.addActionListener(this);
		memberInformationItem.addActionListener(this);
		my_page.addActionListener(this);
		
	}
	///////// 메소드 런
	public void run() {
		/////////////////시계패널///////////////////////////
		Thread t1 = new Thread(koreaClock);
		t1.start();
		/// 한국 패널
		ImageIcon icon = new ImageIcon("flag/korea.png");
		Image im = icon.getImage();
		Image korea2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(korea2);
		koreaLbl.setFont(fnt);
		JLabel kImg = new JLabel(icon2);
		kImg.setBorder(new LineBorder(Color.black,1));
		clock.add(kImg); clock.add(koreaLbl); clock.add(koreaClock);
		clock.setBackground(Color.white);
		koreaLbl.setBackground(Color.white);
		koreaClock.setBackground(Color.white);
		
		/////////////////////////////////////////////

		////////////////세계 주요도시 시간/////////////////////////
		for(int i=0; i<country.length;) {
			ImageIcon c1 = new ImageIcon(countryImg[i]);
			Image c2 = c1.getImage();
			Image c3 = c2.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
			ImageIcon c4 = new ImageIcon(c3);
			JLabel nImg = new JLabel(c4);
			nImg.setBorder(new LineBorder(Color.black,1));
			nationLbl.setText(countryName[i]);
			reClock.add(nImg);
			nationLbl.setFont(fnt);
			reClock.add(nationLbl);
			reClock.add(country[i]);
			reClock.setBackground(Color.white);
			nationLbl.setBackground(Color.white);
			country[i].setBackground(Color.white);
			Thread t2 = new Thread(country[i]);
			t2.start();
			try{Thread.sleep(10000);}catch(Exception e) {}
			reClock.removeAll();
			reClock.repaint();
			if(i==country.length-1) {
				i=0;
				continue;
			}
			i++;
		}
		/////////////// 시계 패널 끝..////////////

	}
	// _____님 어서오세요 라벨 변경
	public void customNameChange() {
		CustomFrameDAO dao = new CustomFrameDAO();
		String id = AirlineMain.idField.getText();
		List<CustomFrameVO> name = dao.getName(id);
		for(int i=0; i<name.size(); i++) {
			CustomFrameVO vo = name.get(i);
			hiLbl.setText(vo.getUser_name()+"님 어서오세요");
		}
	}
	
	// 이벤트 ( 팝업메뉴아이템 )
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JMenuItem) {// JMenuItem 일 경우
			String click = ae.getActionCommand();
			if(click.equals("마이페이지")) {
				visibleMethod();
				mypage.setVisible(true);
				centerPane.add(mypage);
			}else if(click.equals("마일리지 사용내역")) {
				visibleMethod();
				mileage.setVisible(true);
				centerPane.add(mileage);
			} else if(click.equals("회원정보 수정")) {
				visibleMethod();
				revise.setVisible(true);
				centerPane.add(revise);
			} 
		}
	}
	// panel(들)을 visible(false)
	public static void visibleMethod() {
		for(int i=0; i<visiblePane.length;i++) {
			visiblePane[i].setVisible(false);
		}
		
	}
	// 마우스 이벤트 ( 라벨 )
	public void mouseClicked(MouseEvent me) {
		Object obj = me.getSource();
		if((JLabel)obj instanceof JLabel) { // JLabel 일 경우
			JLabel click = (JLabel)me.getSource();
			String menuStr = click.getText();
			if(click instanceof JLabel) {
				if(menuStr.equals("예 약 하 기")) {
					visibleMethod();
					reservation.setVisible(true);
					centerPane.add(reservation);
				} else if(menuStr.equals("예 약 조 회")) {
					visibleMethod();
					bookingChange1.setVisible(true);
					centerPane.add(bookingChange1);
				} else if(menuStr.equals("마 이 페 이 지")) {
					visibleMethod();
					mypage.setVisible(true);
					centerPane.add(mypage);
				} else if(menuStr.equals("로 그 아 웃")) {
					dispose();
					new AirlineMain(); 
				}
			} 
		}
	}
	
	public void mouseReleased(MouseEvent me) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseEntered(MouseEvent me) {
		Object obj = me.getSource();
		if((JLabel)obj instanceof JLabel){
			JLabel myLbl = (JLabel)me.getSource();
			String mymenu= myLbl.getText();
			if(mymenu.equals("마 이 페 이 지")) {
				myPop.setVisible(true);
				myPop.show(mypageLbl, me.getX(), me.getY());
			} else {
				myPop.setVisible(false);
			}
		}
	}
	public void mouseExited(MouseEvent me) {}
	
	public static void main(String[] args) {
		Thread t1 = new Thread(new CustomFrame());
		t1.start();
	}

}
