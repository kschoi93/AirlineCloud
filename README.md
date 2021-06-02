# AirlineCloud

#### 프로젝트 개요
- 자바 스윙 ( java Swing )을 이용한 프로그램 제작

#### 프로젝트 주제
- 항공사 예약 프로그램

#### 개발인원
- 4명

#### 개발환경
- Eclipse, Java, Git, Google Drive, Oracle Database


#### 담당 프로세스
- 로그인 화면
- 회원가입
- 고객 메인페이지
- 예약하기1, 예약하기2, 예약하기3, 예약하기4, 예약하기5, 예약하기6
- 마이페이지, 회원정보수정, 회원탈퇴
<br/>
  위 작성된 페이지를 만들었으며 프로젝트 진행에 앞서 자발적으로 기본적인 레이아웃의 크기와 같은 기초 그리고 고객 페이지 / 관리자 페이지에 사용될 상 중 하단 프레임 제작으로 뼈대를 만들어 공유하여 프로젝트 진행방향을 제시함으로서 프로젝트의 틀을 정립하고 부족한 부분을 채워나가는 역할을 수행했습니다.

--------------------------------------------------
#### 대분류 : 로그인 페이지, 고객 페이지, 관리자 페이지
> Database

![210209_DB모델링_v9](https://user-images.githubusercontent.com/70927354/120403703-b3383400-c37f-11eb-8f79-009f1ea02a78.PNG)  
<br/>

#### 1. 메인(로그인 화면)
1)로그인시 아이디, 비밀번호 1차적인 오류 제한 기능 구현
 - 아이디, 비밀번호 미입력시 dialog message
 - 잘못된 아이디 비밀번호 입력시 dialog message
2) 비밀번호 까지 작성하고 enter key 입력 시 로그인 기능 수행
3) 상단 구름과 Thread를 통해 우에서 좌로 움직이는 비행기 구현하여 항공사 프로그램이라는 것을 어필
   ![슬라이드2](https://user-images.githubusercontent.com/70927354/120391189-a6a8e100-c369-11eb-996c-edd839414f65.JPG)
<details>
    <summary>자세히</summary>

### AirlineMoving	
``` ruby
public class AirlineMoving extends JPanel implements Runnable{
	// 캔버스와 이미지 정의
	private MyCanvas mc = new MyCanvas();
	private Image airplane = Toolkit.getDefaultToolkit().getImage("img/airplane.png");
	private Image cloud1 = Toolkit.getDefaultToolkit().getImage("img/cloud1.png");
	private Image cloud2 = Toolkit.getDefaultToolkit().getImage("img/cloud2.png");
	private int airplaneX, airplaneY;// 항공기 x,y 좌표
	private Random ran = new Random();
	private int[][] r = new int[30][2]; // 구름 위치 랜덤
	private int cloudMotion;// 구름 움직임,
	private int change=0, change1 =0; // 구름 움직이는 기준
	private Dimension frameSize; // 프레임 사이즈와 풀스크린 사이즈 구하기
	public AirlineMoving() {
		setLayout(new BorderLayout());
		setBackground(Color.white);
		add(mc);
	}
	// 스레드 런 메소드
	public void run() {
		while(true) {
			// 스레드 0.1초
			try {Thread.sleep(100);}catch(Exception e) {e.printStackTrace();}
			
			// 비행기 위치가 -100 일 때 첫 위치로 이동
			if(airplaneX==-100) airplaneX = ( (int)frameSize.getWidth()/10 )*10;
			// 비행기는 서쪽으로 쭉 이동
			airplaneX-=5;
			/////////////// 구름모션////////////			
			change1++;
			if(change1==10) {
				// 구름 모션 +1 , -1
				if(change==0) {
					cloudMotion = 1;
					change = 1;
				} else if(change == 1 ) {
					cloudMotion = -1;
					change = 0;
				}
				change1=0;
			}
			/////////////////////////////////
			mc.repaint();
		}
	}
	///////////////// 캔버스///////////////
	class MyCanvas extends Canvas{
		public void paint(Graphics g) {
			g.drawImage(airplane, airplaneX, airplaneY, 100, 100, this);
			for(int i=0; i<r.length;i++) {
				if(i<(r.length/2)) g.drawImage(cloud1, r[i][0]+cloudMotion, r[i][1], 40, 20, this);
				else if(i>=(r.length/2)) g.drawImage(cloud2, r[i][0]+cloudMotion, r[i][1], 40, 20, this);
			}
		}
	}
	
	////////////////// 프레임 사이즈 구하기///////////////
	public void getFrameSize() {
		frameSize = getSize();
		airplaneSize();
		cloudSize();
	}
	
	//// 항공기 사이즈////////
	public void airplaneSize() {
		airplaneX = ( (int)frameSize.getWidth()/10 )*10;
		airplaneY = (( (int)frameSize.getHeight()/2 - 50) /10 ) *10;

	}
	
	///////// 구름 위치//////////
	public void cloudSize(){
		for(int i =0; i<r.length;i++) {
			r[i][0] = ran.nextInt(((int)frameSize.getWidth()/10))*10;
			r[i][1] = ran.nextInt(((int)frameSize.getHeight()/10))*10;
		}
	}
}
```

### AirlineLoginDAO					      
``` ruby
public class AirlineLoginDAO extends DBConn{

	public AirlineLoginDAO() {}
	public List<AirlineLoginVO> LoginAllSelect(){
		List<AirlineLoginVO> lst = new ArrayList<AirlineLoginVO>();
		try {
			getConn();
			sql = "select user_id, user_pwd from ac_user";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AirlineLoginVO vo = new AirlineLoginVO(rs.getString(0),rs.getString(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	public int getLogin(String user_id,String user_pwd){
		List<AirlineLoginVO> lst = new ArrayList<AirlineLoginVO>();
		
		int state = 0;
		try {
			getConn();
			sql = "select user_id, user_pwd from ac_user where user_id = ? and user_pwd = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pwd);
			
			rs = pstmt.executeQuery();
			if(rs.next()) state = 1;
			
		} catch(Exception e) {
			System.out.println("DB 아이디 비밀번호 확인에러"+e.getMessage());
		} finally {
			dbClose();
		}
		return state;
	}
	
}					      
```					     
					      
					      
</details>

#### 2. 회원가입
1) 아이디 중복확인 후 회원가입 가능 기능
2) 아이디 길이 및 사용되는 특수문제 제한 기능
3) 비밀번호 길이 및 사용되는 특수문자 제한 기능
4) 비밀번호 일치여부 확인 기능
5) 이메일 @과. 확인하여 제한 기능

// 고객 페이지, 관리자 페이지로 나뉘어 있으며 frame을 만들어 두어 가운데 change 패널만 변경되는 방식을 이용했다.

![슬라이드3](https://user-images.githubusercontent.com/70927354/120391306-c8a26380-c369-11eb-83a0-8cb7601ec994.JPG)

<details>
    <summary>자세히</summary>

### AirlineSignUp  
``` ruby
public class AirlineSignUp extends JFrame implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,32);
	JLabel titleLbl = new JLabel("회 원 가 입");
	JLabel idLbl = new JLabel("아이디");
	JTextField idField = new JTextField(30);
	JButton duplicateBtn = new JButton("중복 확인");
	JLabel pwdLbl = new JLabel("비밀번호");
	JPasswordField pwdField = new JPasswordField(30);
	JLabel pwdcheckLbl = new JLabel("비밀번호 확인");
	JPasswordField pwdcheckField = new JPasswordField(30);
	JLabel nameHanLbl = new JLabel("이름(한글)");
	JTextField nameHanField = new JTextField(30);
	JLabel nameEngLbl = new JLabel("이름(영문)");
	JTextField nameEngField = new JTextField(30);
	JLabel telLbl = new JLabel("연락처");
	JTextField telField = new JTextField(30);
	JLabel emailLbl = new JLabel("이메일");
	JTextField emailField = new JTextField(30);
	JLabel genderLbl = new JLabel("성별");
	String gender[] = {"F","M"};
	DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(gender);
	JComboBox<String> genderCombo = new JComboBox<String>(model);
	JButton signUpBtn = new JButton("회원가입");
	JButton cancelBtn = new JButton("취소");
	AirlineSignUpDAO dao = new AirlineSignUpDAO();
	public AirlineSignUp() {
		setLayout(null);
		int x=300;
		int x1=410;
		add(titleLbl).setBounds(450,100,200,30);
		titleLbl.setFont(titleFnt);
		
		add(idLbl).setBounds(x,200,100,30); add(idField).setBounds(x1,200,250,30); add(duplicateBtn).setBounds(670,200,110,30);
		idLbl.setFont(fnt);
		idField.setFont(fnt);
		duplicateBtn.setFont(fnt);
		duplicateBtn.setBackground(new Color(0,130,255));
		duplicateBtn.setForeground(Color.white);
		
		add(pwdLbl).setBounds(x,250,100,30); add(pwdField).setBounds(x1,250,250,30); 
		pwdLbl.setFont(fnt);
		pwdField.setFont(fnt);
		
		add(pwdcheckLbl).setBounds(x,300,100,30); add(pwdcheckField).setBounds(x1, 300, 250, 30); 
		pwdcheckLbl.setFont(fnt);
		pwdcheckField.setFont(fnt);
		
		add(nameHanLbl).setBounds(x,350,100,30); add(nameHanField).setBounds(x1,350,250,30);
		nameHanLbl.setFont(fnt);
		nameHanField.setFont(fnt);
		
		add(nameEngLbl).setBounds(x,400,100,30); add(nameEngField).setBounds(x1,400,250,30);
		nameEngLbl.setFont(fnt);
		nameEngField.setFont(fnt);
		
		add(telLbl).setBounds(x,450,100,30); add(telField).setBounds(x1,450,250,30);
		telLbl.setFont(fnt);
		telField.setFont(fnt);
		
		add(emailLbl).setBounds(x,500,100,30); add(emailField).setBounds(x1,500,250,30);
		emailLbl.setFont(fnt);
		emailField.setFont(fnt);
		
		add(genderLbl).setBounds(x,550,100,30); add(genderCombo).setBounds(x1,550,250,30);
		genderLbl.setFont(fnt);
		genderCombo.setFont(fnt);
		genderCombo.setBackground(Color.white);
		
		
		add(signUpBtn).setBounds(400,630,100,30); add(cancelBtn).setBounds(530,630,100,30);
		signUpBtn.setFont(fnt);
		signUpBtn.setBackground(new Color(0,130,255));
		signUpBtn.setForeground(Color.white);
		signUpBtn.setEnabled(false);
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(0,130,255));
		cancelBtn.setForeground(Color.white);
		
		getContentPane().setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		duplicateBtn.addActionListener(this);
		signUpBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if(btn.equals("회원가입")) {
				String id = idField.getText();
				String password = pwdField.getText();
				String pwdCheck = pwdcheckField.getText();
				if(id.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
				} else if(password.equals("")) {
					JOptionPane.showMessageDialog(this, "비밀번호를 입력하셔야 합니다");
				} else if(password.length()<6 || password.length()>20) {    
					JOptionPane.showMessageDialog(this, "비밀번호는 6자리 이상, 20자리 이하만 가능 합니다.");
				} else if(pwdCheck.equals("")) {
					JOptionPane.showMessageDialog(this, "비교할 비밀번호를 입력해 주시기 바랍니다");
				} else if(!password.equals(pwdCheck)) {
					JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다");
				} else if(checkPWDMethod(password)==1) {
					JOptionPane.showMessageDialog(this, "비밀번호 특수문자는 !@#만 포함 가능 합니다");
				} else if(nameHanField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이름(한글)을 입력하셔야 합니다");
				} else if(nameEngField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "이름(영문)을 입력하셔야 합니다");
				} else if(telField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "연락처를 입력하셔야 합니다");
				} else if(emailField.getText().equals("")){
					JOptionPane.showMessageDialog(this, "이메일을 입력하셔야 합니다");
				} else if(emailCheck(emailField.getText())==1){
					JOptionPane.showMessageDialog(this, "잘못된 이메일을 입력하셨습니다");					
				} else {
					AirlineSignUpVO vo = new AirlineSignUpVO(idField.getText(),pwdField.getText(),
							nameHanField.getText(),nameEngField.getText().toUpperCase(),
							telField.getText(),emailField.getText(), (String)genderCombo.getSelectedItem());
									
					int result = dao.SignUpInsert(vo);
					if(result>0) { // 회원등록 성공함
						JOptionPane.showMessageDialog(this, "회원가입에 성공하였습니다\n원활한 이용을 위하여\n로그인 해주시기 바랍니다");
					} else { //회원등록 실패함
						JOptionPane.showMessageDialog(this, "회원가입에 실패하였습니다\n 관리자에게 문의해 주시기 바랍니다");
					}
					dispose();
					new AirlineMain();
				}
			} else if(btn.equals("취소")) {
				dispose();
				new AirlineMain();
			} else if(btn.equals("중복 확인")) {
				String idSearch = idField.getText();
				System.out.println(idSearch.length());
				if(idSearch.equals("")) {
					JOptionPane.showMessageDialog(this, "아이디를 입력하셔야 합니다");
					//id 특수문자 포함 확인
				} else if(idSearch.length() < 6 || idSearch.length() > 15) {
					JOptionPane.showMessageDialog(this, "아이디는 6자리 이상, 15자리 이하만 가능 합니다.");
				} else if(checkIDMethod(idSearch)==1){
					JOptionPane.showMessageDialog(this, "아이디는 특수문자 포함이 불가능합니다");
				} else {
					List<AirlineSignUpVO> result = dao.getidCheck(idSearch);
					if(result.size()==0) {
						JOptionPane.showMessageDialog(this, "사용 가능한 아이디 입니다");
						signUpBtn.setEnabled(true);
						idField.setEnabled(false);
					} else {
						JOptionPane.showMessageDialog(this, "등록되어 있는 아이디 입니다");
					}
						
				} 
			}
		}
	}
	
	public int checkIDMethod(String id) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<id.length(); i++) {
			alpha = id.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
			}
		}
		return check;
	}
	
	public int checkPWDMethod(String pwd) {
		int check= 0;
		char alpha;
		int code;
		for(int i=0; i<pwd.length(); i++) {
			alpha = pwd.charAt(i);
			code = (int)alpha;
			if(code>=0 && code<=32 || code>=36 && code<=47 || code>=58 && code<=63 || code>=91 && code <=96 || code>=123 && code <= 127) {
				check = 1;
				
			}
		}
		return check;
	}
	
	public int emailCheck(String email) {
		int emailCheck =0;
		// email에 @ 가 있는가? email에 .이 올바르게 있나 ? email에 특수문자가 있나?
		if(email.indexOf("@")== -1 || period(email)==true ||specialCharacter(email)==0) {
			emailCheck = 1;
		}
		// 0이면 이메일 체크결과 이상 없다, 1이면 이상 있다
		return emailCheck;
	}
	
	public Boolean period(String email) {
		String spl[] = email.split("@");
		// split은 @ 기준으로 앞뒤로 나눈다
		// spl[0] = id,  spl[1] = email주소
		int num = spl[1].lastIndexOf(".") - spl[1].indexOf("."); // last와 index의 값차이를 검사
		
		boolean clear = false;
		
		// "@"의 앞단 "." 있는지 확인
		if(spl[0].indexOf(".")==-1);
		else clear=true;
		
		// "@"의 뒷단 도메인 부분의 "." 들의 간격이 4칸이상 떨어지면 이메일이 아니다
		if(num <4);
		else clear = true;
		
		// 이메일에 .이 잘못되었을 경우 true를 보낸다
		return clear;
	}
	
	public int specialCharacter(String email) {
		String text[] = { "#", "!","$","%","^","&","*","(",")","-", "_","+","=",",","[","]","{","}",":",";","'","?","<",">"};
		
		int result = 0;
		for(int i=0; i<text.length; i++) {
			if(email.indexOf(text[i]) == -1) {
				result = 1;
			} else {
				break;
			}
		}
		// 이메일 검사했을때 특수문자가 있으면 1을 보내고 없으면 0을 보낸다
		return result;
	}
	
}
```

### AirlineSignUpDAO															   
``` ruby
public class AirlineSignUpDAO extends DBConn{

	public AirlineSignUpDAO() {}
	// 회원가입 db insert
	public int SignUpInsert(AirlineSignUpVO vo) {
		int result = 0;
		try{
			getConn();
			sql = "insert into ac_user(userNo, user_passNo, user_id, "
					+ " user_pwd,user_name,user_ename,user_tel, user_email,user_gender,mileage,grade) "
					+ " values(user_sq.nextval,pass_sq.nextval,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUser_id());
			pstmt.setString(2, vo.getUser_pwd());
			pstmt.setString(3, vo.getUser_name());
			pstmt.setString(4, vo.getUser_ename());
			pstmt.setString(5, vo.getUser_tel());
			pstmt.setString(6, vo.getUser_email());
			pstmt.setString(7, vo.getUser_gender());
			pstmt.setInt(8, 0);
			pstmt.setString(9, "White");
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	// 회원 아이디 비밀번호 검색, 회원 유무 확인
	public List<AirlineSignUpVO> getidCheck(String user_id){
		List<AirlineSignUpVO> lst = new ArrayList<AirlineSignUpVO>();		
		try {
			getConn();
			sql = "select user_id from ac_user where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AirlineSignUpVO vo = new AirlineSignUpVO();
				vo.setUser_id(rs.getString(1));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
}																	   
																	   
```																	   
                                                                                                                               
</details>

#### 3. 고객페이지 항공일정 (로그인 후 화면)
1) 상단 패널 로그인한 사람 이름 반영, 메뉴 마우스 클릭을 통해 중단 패널 화면 전환
   마이페이지 메뉴는 마우스를 올려놓을 경우 팝업 메뉴 출력
2) 중단에 있는 항공일정은 오늘의 ( 한국->외국 ) / ( 외국->한국 ) 항공 일정을 보여주고
   현재 시간을 기준으로 이전 일정은 제외하고 출력
3) 하단 패널은 세계 주요 도시 시간이 10초 단위로 전환 ( 국기 포함 )
![슬라이드12](https://user-images.githubusercontent.com/70927354/120391388-e8d22280-c369-11eb-9e2f-527170c5a835.JPG)

<details>
    <summary>자세히</summary>
  
### CustomFrame
```ruby
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
```

### CustomPlanDAO	
``` ruby
public class CustomPlanDAO extends DBConn{

	public CustomPlanDAO() {}
	
	public List<CustomPlanVO> getKoreaRecord() {
		List<CustomPlanVO> lst = new ArrayList<CustomPlanVO>();
		try {
			getConn();
			sql = "select flightNo,dep,des,depTime,destime,flight_state from ac_flight where dep in('ICN', 'GMP') "
					+ " and deptime > to_char(sysdate,'HH24mi') order by depTime";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomPlanVO vo = new CustomPlanVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}

	public List<CustomPlanVO> getNationRecord() {
		List<CustomPlanVO> lst = new ArrayList<CustomPlanVO>();
		try {
			getConn();
			sql = "select flightNo,dep,des,depTime,destime,flight_state from ac_flight where dep not in('ICN','GMP') "
					+ " and deptime > to_char(sysdate,'HH24mi') order by depTime";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomPlanVO vo = new CustomPlanVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}
	
}	
```	

### CustomFramDAO	
``` ruby
public class CustomFrameDAO extends DBConn{

	public CustomFrameDAO() {}
	
	public List<CustomFrameVO> getName(String user_id) {
		List<CustomFrameVO> lst = new ArrayList<CustomFrameVO>();
		try {
			getConn();
			sql = "select user_name from ac_user where user_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomFrameVO vo = new CustomFrameVO();
				vo.setUser_name(rs.getString(1));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		return lst;
	}

}	
```	
	
</details>
  
#### 4. 예약하기
1) 날짜 선택 후 다음 단계 이동 가능
2) 달력 아이콘 선택 시 현재 연도 / 월을 기준으로 달력 출력
   날짜 첫 번째 클릭시 출발 날짜, 두 번째 클릭시 도착 날짜 textField 입력 / 반복
3) 당일보다 이전 날짜를 선택할 경우 다음 단계 이동 불가
4) 예약은 본인 포함 동승자 4명까지만 예약 가능하도록 제한

![슬라이드13](https://user-images.githubusercontent.com/70927354/120391436-fa1b2f00-c369-11eb-83c4-8afba82a6342.JPG)

<details>
    <summary>자세히</summary>
  
### CustomReservation	
``` ruby
public class CustomReservation extends JPanel implements ActionListener {
	Font fnt = new Font("굴림체",Font.BOLD,14);
	
	JPanel changePane = new JPanel();
	JLabel startLbl = new JLabel("출발지");
		static String start[] = {"인천","런던","바르셀로나","발리","방콕","부산","시드니","싱가포르","서울/김포"
				,"제주","파리","호놀룰루"};
		static DefaultComboBoxModel<String> startModel = new DefaultComboBoxModel<String>(start);
		static JComboBox<String> startCombo = new JComboBox<String>(startModel);
	
	JLabel arriveLbl = new JLabel("도착지");
		static String arrive[] = {"런던","바르셀로나","발리","방콕","부산","시드니","싱가포르","서울/김포","인천"
				,"제주","파리","호놀룰루"};
		static DefaultComboBoxModel<String> arriveModel = new DefaultComboBoxModel<String>(arrive);
		static JComboBox<String> arriveCombo = new JComboBox<String>(arriveModel);

	JLabel startDateLbl = new JLabel("출발 날짜");
		static JTextField startDateField = new JTextField();
		ImageIcon icon = new ImageIcon("img/calendar.png");
		Image im = icon.getImage();
		Image im2 = im.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon icon2 = new ImageIcon(im2);
		JLabel startCalendar = new JLabel(icon2);
	JLabel arriveDateLbl = new JLabel("도착 날짜");
		static JTextField arriveDateField = new JTextField();
	JLabel roundDateLbl = new JLabel("종류");
		JRadioButton roundBtn = new JRadioButton("왕복");
		JRadioButton onewayBtn = new JRadioButton("편도");	
		ButtonGroup groupRd = new ButtonGroup();
		static String rdb ="왕복"; // 라디오버튼 눌린게 무엇인지 구별
		
	JLabel humanLbl = new JLabel("인원수");
		JLabel audultLbl = new JLabel("어른");
		static Integer audult[] = {1,2,3,4,5};
		static DefaultComboBoxModel<Integer> adultModel = new DefaultComboBoxModel<Integer>(audult);
		static JComboBox<Integer> audultAge = new JComboBox<Integer>(adultModel);
		JLabel babyLbl = new JLabel("소아");
		static Integer baby[] = {0,1,2,3,4,5};
		static DefaultComboBoxModel<Integer> babyModel = new DefaultComboBoxModel<Integer>(baby);
		static JComboBox<Integer> babyAge = new JComboBox<Integer>(babyModel);
		
		
	
	JButton cancelBtn = new JButton("예약취소");
	JButton nextBtn = new JButton("다음단계");
	
	int calendarWindowTest = 0; // calendar가 켜졌을때는 한번 더 open되지 않도록 제한사항을 부여
	int clickCheck = 0; // 달력에서 클릭한 횟수를 통해 출발날짜, 도착날짜가 체크되는 순서를 절한다.
	static int humanCount; // 사람 명수
	public CustomReservation() {
		setLayout(new BorderLayout());
		
		
		
		// 전환 페이지
		add("Center",changePane);
		changePane.setLayout(null);
		changePane.setBackground(Color.white);
		int x=300;
		int x1=380;
		changePane.add(startLbl).setBounds(x,150,70,30); changePane.add(startCombo).setBounds(x1,150,150,30); 
		startLbl.setFont(fnt);
		startCombo.setFont(fnt);
		startCombo.setBackground(Color.white);
		
		changePane.add(arriveLbl).setBounds(x,200,70,30); changePane.add(arriveCombo).setBounds(x1,200,150,30); 
		arriveLbl.setFont(fnt);
		arriveCombo.setFont(fnt);
		arriveCombo.setBackground(Color.white);
		
		changePane.add(startDateLbl).setBounds(x,250,70,30); changePane.add(startDateField).setBounds(x1, 250, 150, 30); changePane.add(startCalendar).setBounds(550,250,30,30);
		startDateLbl.setFont(fnt);
		startDateField.setFont(fnt);
		
		changePane.add(arriveDateLbl).setBounds(x,300,70,30); changePane.add(arriveDateField).setBounds(x1,300,150,30); 
		arriveDateLbl.setFont(fnt);
		arriveDateField.setFont(fnt);
		
		changePane.add(roundDateLbl).setBounds(x,350,70,30); changePane.add(roundBtn).setBounds(x1,350,70,30); changePane.add(onewayBtn).setBounds(520,350,70,30);
		roundDateLbl.setFont(fnt);
		roundBtn.setSelected(true);
		roundBtn.setBackground(Color.white);
		onewayBtn.setBackground(Color.white);
		// 라디오 버튼 그룹화
		groupRd.add(roundBtn);
		groupRd.add(onewayBtn);
		
		changePane.add(humanLbl).setBounds(x,400,70,30); changePane.add(audultLbl).setBounds(x1,400,50,30); changePane.add(audultAge).setBounds(450,400,70,30); changePane.add(babyLbl).setBounds(550,400,50,30);
		changePane.add(babyAge).setBounds(610,400,70,30);
		audultAge.setBackground(Color.white);
		babyAge.setBackground(Color.white);
		humanLbl.setFont(fnt);
		audultLbl.setFont(fnt);
		audultAge.setFont(fnt);
		babyLbl.setFont(fnt);
		babyAge.setFont(fnt);
		
		changePane.add(cancelBtn).setBounds(400,500,100,30); changePane.add(nextBtn).setBounds(530,500,100,30); 
		cancelBtn.setFont(fnt);
		cancelBtn.setBackground(new Color(0,130,255));
		cancelBtn.setForeground(Color.white);
		nextBtn.setFont(fnt);
		nextBtn.setBackground(new Color(0,130,255));
		nextBtn.setForeground(Color.white);
			
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);
		
		startCalendar.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent me) {
				me.getSource(); 
				if(calendarWindowTest==0) {
					 new CustomCalendar();
					 calendarWindowTest=1;
				 }
			}
		});
		roundBtn.addActionListener(this);
		onewayBtn.addActionListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	
	
	
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();

		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			
			
			if(btn.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(btn.equals("다음단계")) {
				if(startCombo.getSelectedItem().equals(arriveCombo.getSelectedItem())) {
					JOptionPane.showMessageDialog(this, "동일 지역이 선택 될 수 없습니다");
				} else if(startDateField.getText().equals("") || arriveDateField.getText().equals("")) {
					JOptionPane.showMessageDialog(this, "날짜를 선택해 주시기바랍니다");
				} else if(startDateField.getText().equals(arriveDateField.getText())) {
					JOptionPane.showMessageDialog(this, "같은 날짜 선택은 불가능합니다.");
				} else if(errorCheck() ==1) {
					JOptionPane.showMessageDialog(this, "당일보다 이전일은 항공일정이 없습니다");
				} else if(dayMinusCheck()==1) {
					JOptionPane.showMessageDialog(this, "출발 날짜보다 도착 날짜가 이전일 수는 없습니다");
				} else if(humanCount() == 1){
					JOptionPane.showMessageDialog(this, "본인을 포함, 5명을 초과하여 예약 할 수 없습니다 \n단체 예약을 원 할경우 관리자에게 문의하시기 바랍니다.");
				}else {
					this.setVisible(false);
					CustomFrame.reservation2.setVisible(true);
					CustomFrame.centerPane.add(CustomFrame.reservation2);
				}
			}
		} else if(obj instanceof JRadioButton) {
			rdb = (String)ae.getActionCommand();
		}
	}
	public int humanCount() {
		int result = 0;
		humanCount = (int) audultAge.getSelectedItem() +  (int)babyAge.getSelectedItem();
		if(humanCount >5) result = 1;
		
		return result;
	}
	
	
	/// 출발 날짜가 도착 날짜보다 뒤로 설정해보는 엉뚱한 사람을 체크해라!
	public int dayMinusCheck() {
		int result = 0;
		int start = Integer.valueOf(startDateField.getText().replace("/", ""));
		int arrive = Integer.valueOf(arriveDateField.getText().replace("/", ""));
		int minusCheck = arrive-start;
		if(minusCheck<0) {
			result = 1;
		}
		return result;
	}
	// 출발날짜 선택을 당일보다 전일로 설정 할 경우 걸러낸다
	public int errorCheck() {
		int result = 0;
		int start = Integer.valueOf(startDateField.getText().replace("/",""));
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		int fmt = Integer.valueOf(format.format(date));
		int dateCheck = fmt - start;
		if(dateCheck > 0){
			result = 1;
		}
		
		return result;
	}
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	//////////////////            달                   력      ////////////////////////////////
	class CustomCalendar extends JFrame implements ActionListener, WindowListener{
		// 상단 지역
		JPanel bar = new JPanel();
			JButton lastMonth = new JButton("◀");
		
			JComboBox<Integer> yearCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
			
			JLabel yLbl = new JLabel("년 ");
			
			JComboBox<Integer> monthCombo = new JComboBox<Integer>(); 
				DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
			JLabel mLbl = new JLabel("월");
			JButton nextMonth = new JButton("▶");
		// 중앙 지역
		JPanel center = new JPanel(new BorderLayout());
			// 중앙 상단 지역
			JPanel cntNorth = new JPanel(new GridLayout(0,7));
			// 중앙 중앙 지역
			JPanel cntCenter = new JPanel(new GridLayout(0,7));
		
		// 요일 입력
		String dw[] = {"일","월","화","수","목","금","토"};
		
		
		Calendar now = Calendar.getInstance();
		int year, month, date;
		
		public CustomCalendar() {
			year = now.get(Calendar.YEAR);// 2021년
			month = now.get(Calendar.MONTH)+1; // 0월 == 1월
			date = now.get(Calendar.DATE);
			for(int i=year; i<=year+50; i++){yearModel.addElement(i);}
			for(int i=1; i<=12; i++) { monthModel.addElement(i); }
			//////////////////////////프레임///////////////////////////////////////////
			// 상단 지역
			add("North", bar);
				bar.setLayout(new FlowLayout());
				bar.setSize(300,400);
				bar.add(lastMonth);
				//////////////////////////달력/////////////////////////////////////////////
				bar.add(yearCombo);
					yearCombo.setModel(yearModel);
					yearCombo.setSelectedItem(year);

				bar.add(yLbl);
				bar.add(monthCombo);
					monthCombo.setModel(monthModel);
					monthCombo.setSelectedItem(month);

				bar.add(mLbl);
				bar.add(nextMonth);
				bar.setBackground(new Color(0,210,180));

			// 중앙 지역
			add("Center", center);
				// 중앙 상단 지역
				center.add("North",cntNorth);
				for(int i=0; i<dw.length; i++) {
					JLabel dayOfWeek = new JLabel(dw[i],JLabel.CENTER);
					if(i==0) dayOfWeek.setForeground(Color.red);
					else if(i==6) dayOfWeek.setForeground(Color.blue);
					cntNorth.add(dayOfWeek);
				}

				// 중앙 중앙 지역
				center.add("Center",cntCenter);
				dayPrint(year,month);
				
				
			// 이벤트
			yearCombo.addActionListener(this);
			monthCombo.addActionListener(this);
			lastMonth.addActionListener(this);
			nextMonth.addActionListener(this);
			addWindowListener(this);
			
			// frame 기본 셋팅
			setSize(400,300);
			setVisible(true);
			setResizable(false);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		}	


		// 이벤트 처리
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if(obj instanceof JButton){
				JButton eventBtn = (JButton)obj;
				int yy = (Integer)yearCombo.getSelectedItem();
				int mm = (Integer)monthCombo.getSelectedItem();
				if(eventBtn.equals(lastMonth)){	//전달
					if(mm == 1 && yy == year ) {
					} else if(mm == 1){
						yy--; mm = 12;
					} else {
						mm--;
					}
				}else if(eventBtn.equals(nextMonth)){	//다음달
					if(mm == 12){
						yy++; mm = 1;
					}else{
						mm++;
					}
				}
				yearCombo.setSelectedItem(yy);
				monthCombo.setSelectedItem(mm);
			}else if(obj instanceof JComboBox){	//콤보박스 이벤트 발생시
				createDayStart();
			}
		}


		private void createDayStart() {
			cntCenter.setVisible(false);	//패널 숨기기
			cntCenter.removeAll();	//날짜 출력한 라벨 지우기
			dayPrint((Integer)yearCombo.getSelectedItem(), (Integer)monthCombo.getSelectedItem());
			cntCenter.setVisible(true);	//패널 재출력	
		}


		// 날짜 출력
		public void dayPrint(int y,int m) {
			Calendar cal = Calendar.getInstance();
			cal.set(y, m-1, 1);
			int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
			int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
			for(int i =1; i<week; i++) { // 1월 1일 전까지 공백을 표시해라
				cntCenter.add(new JLabel(""));
			}
			
			for(int i =0;i<=lastDate-1;i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
				JLabel day = new JLabel();
				day.setHorizontalAlignment(JLabel.CENTER);
				if((week+i)%7==0) {
					cntCenter.add(day).setForeground(Color.blue);
					day.setText(1+i+"");
				} else if((week+i)%7==1) {
					cntCenter.add(day).setForeground(Color.red);
					day.setText(1+i+"");
				} else {
					cntCenter.add(day);
					day.setText(1+i+"");
				}
				day.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent me) {
						JLabel mouseClick = (JLabel)me.getSource();
						String str= mouseClick.getText();
						String y = ""+yearCombo.getSelectedItem();
						String m = ""+monthCombo.getSelectedItem();
						
						// 받은 "요일"이 1자리면 0을 붙여라
						if(str.equals("")) ;
						else if(str.length()==1) str = "0"+str; 
						
						// 받은 "월"이 1자리면 0을 붙여라
						if(m.length()==1) m = "0"+m;
						
						if(clickCheck==0) {
							startDateField.setText(y+"/"+m+"/"+str);
							startDateField.setEnabled(false);
							clickCheck++;
						} else if(clickCheck==1) {
							arriveDateField.setText(y+"/"+m+"/"+str);
							arriveDateField.setEnabled(false);
							clickCheck--;
						}
					}
				});
			}
			

			
		}

		public void windowOpened(WindowEvent e) {
			calendarWindowTest = 1;
		}
		public void windowClosing(WindowEvent e) {
			calendarWindowTest = 0;
		}	
		public void windowClosed(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowActivated(WindowEvent e) {}
		public void windowDeactivated(WindowEvent e) {}
	}
}
```

### CustomReservaionDAO						       
``` ruby
public class CustomReservationDAO extends DBConn{

	public CustomReservationDAO() {	}
	public List<CustomReservationVO> getStartPlan(String start){
		List<CustomReservationVO> lst = new ArrayList<CustomReservationVO>();
		try {
			getConn();
			sql = "select flightno,deptime,destime,flighttime,flight_state,fare from ac_flight "
					+ " where deptime>to_char(sysdate,'HH24mi') order by deptime";
			
			pstmt = conn.prepareStatement(sql);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return lst;
	}
	
	public List<CustomReservationVO> setDate(String arrive) {
		List<CustomReservationVO> lst = new ArrayList<CustomReservationVO>();
		CustomReservationVO vo = new CustomReservationVO();
		
		
		
		return lst;
	}

}						       
```
						       
</details>                                     
                                    

#### 5. 예약하기 2
1) 항공편 선택 화면, 편도 선택 시 출발할 때의 일정만 출력
2) 예약자 정보 입력 화면, 정보 입력
3) 예약자 정보입력 후 정확히 입력하였는지 확인하도록 체크 생성, 클릭 후 다음 단계 이동 가능
4) 동승자의 모든 정보를 입력

![슬라이드18](https://user-images.githubusercontent.com/70927354/120391456-00a9a680-c36a-11eb-91b5-187c443a34ef.JPG)
![슬라이드19](https://user-images.githubusercontent.com/70927354/120391487-099a7800-c36a-11eb-97b9-c3314d921c49.JPG)

<details>
    <summary>자세히</summary>

### CustomReservation2	
  ``` ruby
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
					JLabel startDate = new JLabel("2020/02/02");
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
					
		JPanel wrpPane = new JPanel();
			JPanel selectTextPane = new JPanel();	
				JLabel finalStartText = new JLabel(" ");
				JLabel finalArriveText = new JLabel(" ");
			JPanel btnPane = new JPanel();
				JButton cancelBtn = new JButton("예약취소");
				JButton nextBtn = new JButton("다음단계");
		
		CustomReservation3 reservation3 = new CustomReservation3();
		
		String startD ; // start 날짜 데이터 저장용
		String arriveD ; // arrive 날짜 데이터 저장용
		String start ; // start 위치 데이터 저장용
		String arrive ; // arrive 위치 데이터 저장용
		String radioButton ="왕복"; // CustomReservation의 라디오 버튼이 왕복버튼인지 편도인지 확인용
		String selectStart =" "; // start 테이블 선택시
		String selectArrive = " "; // arrive 테이블 선택시
		int clickCount = 0; // 항공편 출발지 도착지 선택여부 확인용
		int radioCheck = 0; // 왕복인지 여부에 따라 clickCount 갯수를 세서 "다음"버튼을 풀어줄건지 말건지 용.
		static String arriveSelect;
		static String startSelect;
		static String startTime;
		static String startEndTime;
		static String arriveTime;
		static String arriveendTime;
		static int startFare;
		static int arriveFare;
	public CustomReservation2() {
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
							startDate.setText(CustomReservation.startDateField.getText()); // reservation에서 받아온다
							startDate.setFont(fnt);
							startDate.setBackground(Color.white);
							// 복귀 날짜
						datePane.add(arriveDate);
							arriveDate.setText(CustomReservation.arriveDateField.getText()); // reservation에서 받아온다
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
			
			
			centerPane.add("South",wrpPane);
				wrpPane.setLayout(new BorderLayout());
				wrpPane.add("Center",selectTextPane);
				wrpPane.setBackground(Color.white);
				JPanel sPane = new JPanel();
				JPanel aPane = new JPanel();
				selectTextPane.setBackground(Color.white);
				selectTextPane.setLayout(new GridLayout(1,2));
					selectTextPane.add(sPane);
						sPane.add(finalStartText);
						sPane.setBackground(Color.white);
						finalStartText.setFont(fnt);
						
					selectTextPane.add(aPane);
						aPane.add(finalArriveText);
						aPane.setBackground(Color.white);
						finalArriveText.setFont(fnt);
						
				wrpPane.add("South",btnPane);
				btnPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,5));
				btnPane.setBackground(Color.white);
				btnPane.add(cancelBtn);
					cancelBtn.setFont(fnt);
					cancelBtn.setBackground(new Color(0,130,255));
					cancelBtn.setForeground(Color.white);
					
				btnPane.add(nextBtn);
					nextBtn.setFont(fnt);
					nextBtn.setBackground(new Color(0,130,255));
					nextBtn.setForeground(Color.white);
					nextBtn.setEnabled(false);
					
		setBackground(Color.white);
		setSize(1000,800);
		setVisible(false);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
		
		// 마우스 움직일때마다 예약하기 1번화면에서 최신화된 값을 불러온다
		addMouseListener(new MouseAdapter() { 
		public void mouseEntered(MouseEvent e) {
			// start 날짜
			startD = CustomReservation.startDateField.getText();
			// arrive 날짜
			arriveD = CustomReservation.arriveDateField.getText();
			// start 위치
			start = (String)CustomReservation.startCombo.getSelectedItem();
			// arrive 위치
			arrive = (String)CustomReservation.arriveCombo.getSelectedItem();
			// 왕복여부 확인
			if(!CustomReservation.rdb.equals("왕복")) {
				radioButton = "편도";
				//편도이면 arriveModel을 초기화 해줘야 한다.
				arriveModel.setRowCount(0);
				//편도일 경우 clickCount도 편도만큼만 하게 설정할 수 있는 ->   radioCheck = 1
				radioCheck = 1;
				//편도이면 final arrive text가 초기화된다
				finalArriveText.setText(" ");
			} else if(CustomReservation.rdb.equals("왕복")) {
					radioButton = "왕복";
					startModel.setRowCount(0);
					radioCheck = 0;
			}
			// 데이터 넣기
			startDate.setText(startD);
			arriveDate.setText(arriveD);
			startCountry.setText(start);
			arriveCountry.setText(arrive);
			// table 최신화
			tblPrint();
		}
		});
		
		// 스타트 테이블에서 적힌것을 받아와야 한다.
		startTbl.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==1) {
					int row = startTbl.getSelectedRow();
					int col = startTbl.getColumnCount();
					startSelect = (String)startModel.getValueAt(row, 0);
					startTime =(String)startModel.getValueAt(row, 1);
					startEndTime = (String)startModel.getValueAt(row, 2);
					startFare = (int)startModel.getValueAt(row, 5);
					selectStart = "출발지 선택편명 [ "+startSelect+" ]";
					finalStartText.setText(selectStart);
				}
				if(radioCheck==0) { // radio check가 0이면 왕복이라는것
					clickCount = 1 ;
				} else { // radio check가 0이 아니면 넥스트 버튼을 풀어줘라
					nextBtn.setEnabled(true);
				}
			}
		});
		
		// 복귀 테이블에서 적힌것을 받아와야 한다.
		arriveTbl.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==1) {
					int row = arriveTbl.getSelectedRow();
					int col = arriveTbl.getColumnCount();
					arriveSelect = (String)arriveModel.getValueAt(row, 0);
					arriveTime =(String)startModel.getValueAt(row, 1);
					arriveendTime = (String)startModel.getValueAt(row, 2);
					arriveFare = (int)startModel.getValueAt(row, 5);
					selectArrive = "도착지 선택편명 [ "+arriveSelect+" ]";
					finalArriveText.setText(selectArrive);
				}
				if(clickCount == 1) {
					nextBtn.setEnabled(true);
				}
			}
		});
		
	}
	
	// 액션 이벤트
	public void actionPerformed(ActionEvent ae){
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String str = ae.getActionCommand();
			if(str.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(str.equals("다음단계")) {
				this.setVisible(false);
				// 만약.... 리저베이션에서 넥스트 체크가 휴먼카운트보다 작다면.... 리플레이스 카운트를 실행해라
				if(CustomFrame.reservation3.nextCheck>=CustomFrame.reservation.humanCount) {
					CustomFrame.reservation3.replaceCount();
				}
				CustomFrame.reservation3.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.reservation3);
			}
		} 
	}
	
	// 테이블 최신화
	public void tblPrint() {
		// sql에 start 값, arrive값 최신화된 값을 넣어야함
		String sqlStart = start;
		String sqlArrive = arrive;
		switch(start) {
			case "인천":  sqlStart="ICN"; break;
			case "호놀룰루": sqlStart="HNL"; break;
			case "시드니": sqlStart="SYD"; break;
			case "런던": sqlStart="LHR"; break;
			case "파리": sqlStart="CDG"; break;
			case "바르셀로나": sqlStart="BCN"; break;
			case "방콕": sqlStart="BKK"; break;
			case "발리": sqlStart="DPS"; break;
			case "싱가포르": sqlStart="SIN"; break;
			case "제주": sqlStart="CJU"; break;
			case "서울/김포": sqlStart="GMP"; break;
			case "부산": sqlStart="PUS"; break;
		}
		switch(arrive) {
		case "인천":  sqlArrive="ICN"; break;
		case "호놀룰루": sqlArrive="HNL"; break;
		case "시드니": sqlArrive="SYD"; break;
		case "런던": sqlArrive="LHR"; break;
		case "파리": sqlArrive="CDG"; break;
		case "바르셀로나": sqlArrive="BCN"; break;
		case "방콕": sqlArrive="BKK"; break;
		case "발리": sqlArrive="DPS"; break;
		case "싱가포르": sqlArrive="SIN"; break;
		case "제주": sqlArrive="CJU"; break;
		case "서울/김포": sqlArrive="GMP"; break;
		case "부산": sqlArrive="PUS"; break;
		}
		// 시작 테이블 최신화
		CustomReservation2DAO dao = new CustomReservation2DAO();
		List<CustomReservation2VO> startlst = dao.getStartPlan(sqlStart, sqlArrive);
		startModel.setRowCount(0);
		for(int i=0; i<startlst.size(); i++) {
			CustomReservation2VO vo = startlst.get(i);
			Object[] data = {vo.getFlightno(),vo.getDeptime(),vo.getDestime(),vo.getFlighttime(),vo.getFlight_state(),vo.getFare()};
			startModel.addRow(data);
		}
		
		// 복귀 테이블 최신화
		if(radioButton.equals("왕복")) {
			CustomReservation2DAO dao2 = new CustomReservation2DAO();
			List<CustomReservation2VO> arrivelst = dao2.getarrivePlan(sqlStart, sqlArrive);
			arriveModel.setRowCount(0);
			for(int i=0; i<arrivelst.size();i++) {
				CustomReservation2VO vo = arrivelst.get(i);
				Object[] data = {vo.getFlightno(),vo.getDeptime(),vo.getDestime(),vo.getFlighttime(),vo.getFlight_state(),vo.getFare()};
				arriveModel.addRow(data);
			}
		}
		
		
	}
	
}
                                           
    ```                                       

### CustomReservation3							     
  ``` ruby
public class CustomReservation3 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체",Font.BOLD,14);
	Font titleFnt = new Font("굴림체",Font.BOLD,24);
	JPanel main = new JPanel();
	JPanel wrapPane = new JPanel();
		JPanel lblPane= new JPanel();
			JLabel titleLbl = new JLabel("예약자 정보입력");
			JPanel hanPane = new JPanel();
				JLabel hanLbl = new JLabel("예약자 성명(한) ex) 홍길동");
				JTextField hanField = new JTextField(30);
			JPanel engPane = new JPanel();
				JLabel engLbl = new JLabel("예약자 성명(영어) ex) Hong GilDong");
				JTextField engField = new JTextField(30);
			JPanel passportPane = new JPanel();
				JLabel	passportLbl = new JLabel("여권번호 ex) M986541");
				JTextField passportField = new JTextField(30);
			JPanel passEndPane = new JPanel();
				JLabel passEndLbl = new JLabel("여권만료일 ex) 20230204");
				JTextField passEndField = new JTextField(30);
			JPanel birthPane = new JPanel();
				JLabel birthLbl = new JLabel("생년월일 ex) 19950807");
				JTextField birthField = new JTextField(30);
			JPanel genderPane = new JPanel();
				JLabel genderLbl = new JLabel("성별");
				String gender[] = {"F","M"};
				DefaultComboBoxModel<String> genderModel = new DefaultComboBoxModel<String>(gender);
				JComboBox<String> genderCombo = new JComboBox<String>(genderModel);
			JPanel nationPane = new JPanel();
				JLabel nationLbl = new JLabel("여권 발행국가");
				String nation[] = {"KOREA","AUSTRALIA","ARGENTINA","BRAZIL","CANADA","CHINA","FRANCE","GERMANY","ITALY","INDIA","INDONESIA",
						"JAPAN","MEXICO","RUSSIA","RSA","SAUDIARABIA","TURKEY","USA","UK"};
				DefaultComboBoxModel<String> nationModel = new DefaultComboBoxModel<String>(nation);
				JComboBox<String> nationCombo = new JComboBox<String>(nationModel);
			JPanel telPane = new JPanel();
				JLabel telLbl = new JLabel("연락처 ex) 010-1234-5678");
				JTextField telField = new JTextField(30);
			JPanel emailPane = new JPanel();
				JLabel emailLbl = new JLabel("이메일 ex) hong123@naver.com");
				JTextField emailField = new JTextField(30);
				
			JPanel checkPane = new JPanel();
				JCheckBox checkBox = new JCheckBox();
				JLabel checkStr = new JLabel("해당 정보를 맞게 입력하셨나요? 위 내용은 예약 완료 후 변경이 불가합니다");
				JLabel checkStr2 = new JLabel("정보 변경을 희망할 경우 예약취소를 하시고 다시 시도해주시기 바랍니다");
				int c = 0;
			JPanel buttonPane = new JPanel();
				JButton nextBtn = new JButton("다음단계");
				JButton cancelBtn = new JButton("예약취소");
		JPanel[] pane = {hanPane,engPane,passportPane,passEndPane,birthPane,telPane,emailPane};
		JLabel[] lbl = {hanLbl,engLbl,passportLbl,passEndLbl,birthLbl,telLbl,emailLbl};
		JTextField[] tf = {hanField,engField,passportField,passEndField,birthField,telField,emailField};
		
		static int nextCheck = 0; // 동승자 예약까지 하기위해 next버튼 누른 횟수를 저장한다.
		int count ;
		
		static List<CustomReservation3FellowVO> fellowLst = new ArrayList<CustomReservation3FellowVO>();
		static List<CustomReservation3VO> lst = new ArrayList<CustomReservation3VO>();
	public CustomReservation3() {
		setLayout(new BorderLayout());
		add(main);
		main.setLayout(new BorderLayout(200,0));
		main.add("North",new JLabel());
		main.add("East",new JLabel());
		main.add("West",new JLabel());
		main.add("South",new JLabel());
		main.setBackground(Color.white);
		main.add(wrapPane);
		wrapPane.setLayout(new GridLayout(12,1));
		wrapPane.setBackground(Color.white);
		wrapPane.add(titleLbl);
			titleLbl.setHorizontalAlignment(JLabel.CENTER);
			titleLbl.setOpaque(true);
			titleLbl.setBackground(Color.white);
			titleLbl.setFont(titleFnt);
		
		for(int i=0; i<pane.length;i++) {
			wrapPane.add(pane[i]);
			pane[i].setLayout(new BorderLayout(0,15));
			pane[i].setBackground(Color.white);
			pane[i].add("North",new JLabel());
			pane[i].add("South",new JLabel());
			pane[i].add("West", lbl[i]);
			pane[i].add("East", tf[i]);
			lbl[i].setOpaque(true);
			lbl[i].setFont(fnt);
			lbl[i].setBackground(Color.white);
			lbl[i].setHorizontalAlignment(JLabel.CENTER);
			tf[i].setFont(fnt);
		}
		wrapPane.add(nationPane);
		nationPane.setBackground(Color.white);
		nationPane.setLayout(new BorderLayout(0,15));
		nationPane.add("South",new JLabel());
			nationPane.add("West",nationLbl);
				nationLbl.setFont(fnt);
			nationPane.add("East", nationCombo);
				nationCombo.setFont(fnt);
				nationCombo.setBackground(Color.white);
		
		wrapPane.add(genderPane);
		genderPane.setLayout(new BorderLayout(0,15));
		genderPane.setBackground(Color.white);
		genderPane.add("South",new JLabel());
			genderPane.add("West",genderLbl);
				genderLbl.setFont(fnt);
			genderPane.add("East",genderCombo);
				genderCombo.setFont(fnt);
				genderCombo.setBackground(Color.white);
		
		wrapPane.add(checkPane);
			checkPane.setBorder(new LineBorder(Color.lightGray,1));
			checkPane.setBackground(Color.white);
			checkPane.setLayout(new BorderLayout(5,0));
			checkPane.add("West",checkBox);
				checkBox.setBackground(Color.white);
			JPanel checkLblPane = new JPanel();
			checkPane.add("Center",checkLblPane);
				checkLblPane.setBackground(Color.white);
				checkLblPane.add(checkStr);
				checkStr.setHorizontalAlignment(JLabel.RIGHT);
				checkStr.setBackground(Color.white);
				checkStr.setFont(fnt);
				checkLblPane.add(checkStr2);
				checkStr2.setHorizontalAlignment(JLabel.RIGHT);
				checkStr2.setBackground(Color.white);
				checkStr2.setFont(fnt);
		
		wrapPane.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER,50,10));
			buttonPane.setBackground(Color.white);
				buttonPane.add(cancelBtn);
					cancelBtn.setFont(fnt);
					cancelBtn.setForeground(Color.white);
					cancelBtn.setBackground(new Color(0,130,255));
				buttonPane.add(nextBtn);
					nextBtn.setEnabled(false);
					nextBtn.setFont(fnt);
					nextBtn.setForeground(Color.white);
					nextBtn.setBackground(new Color(0,130,255));
		

		setBackground(Color.white);
		setSize(1000,800);
		setVisible(true);

		checkBox.addActionListener(this);
		cancelBtn.addActionListener(this);
		nextBtn.addActionListener(this);
	}
	// 이벤트 등록
	public void actionPerformed(ActionEvent ae) {
		Object obj = ae.getSource();
		if(obj instanceof JButton) {
			String btn = ae.getActionCommand();
			if(btn.equals("예약취소")) {
				// 전부 초기화
				for(int i=0; i<tf.length; i++) {
					tf[i].setText("");
				}
				setTrue();
				lst.removeAll(lst);
				fellowLst.removeAll(fellowLst);
				titleLbl.setText("예약자 정보입력");
				hanLbl.setText("예약자 이름(한글) ex) 홍길동");
				engLbl.setText("예약자 이름(영어) ex) Hong GilDong");
				checkBox.setSelected(false);
				nextBtn.setEnabled(false);
				c=0;
				nextCheck=0;
				
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
			} else if(btn.equals("다음단계")) {
				count = CustomReservation.humanCount;
				if(nextCheck==0 && nextCheck < count) {
					// 다음 눌렀느데 count가 인원수가 nextcheck랑 동일하면 다음화면 이동
					if( (nextCheck +1) == count) {
						
						lst.removeAll(lst);
						bookingRegist(lst);	
						
						setFalse();
						this.setVisible(false);
						// 다음을 누르면 reservation4를 세팅한다
						CustomFrame.reservation4.setStartSeatPaint();
						
						CustomFrame.reservation4.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation4);
					} else { // 아니라면 ++;
						// 리스트에 담는다
					
						bookingRegist(lst);
						// 전부 초기화
						for(int i=0; i<tf.length; i++) {
							tf[i].setText("");
						}
						checkBox.setSelected(false);
						nextBtn.setEnabled(false);
						c=0;
						titleLbl.setText("동승자 정보입력");
						hanLbl.setText("동승자 이름(한글) ex) 홍길동");
						engLbl.setText("동승자 이름(영어) ex) Hong GilDong");
						nextCheck++;
					}
					
				} else if(nextCheck < count) {
					if((nextCheck+1) == count) {
						
						if(!(fellowLst.size()==(count-1))) {
							bookingFellowRegist(fellowLst);	
						}
						
						setFalse();
						this.setVisible(false);
						// 다음을 누르면 reservation4를 세팅한다
						CustomFrame.reservation4.setStartSeatPaint();
						
						CustomFrame.reservation4.setVisible(true);
						CustomFrame.centerPane.add(CustomFrame.reservation4);
						
					} else {
						bookingFellowRegist(fellowLst);
						for(int i=0; i<tf.length; i++) {
							tf[i].setText("");
						}
						setTrue();
						checkBox.setSelected(false);
						nextBtn.setEnabled(false);
						c=0; 
						nextCheck++;
					}
				} 

				
			}
		}
		if(obj instanceof JCheckBox) {
			if(c==0) {
				nextBtn.setEnabled(true);
				c++;
			} else if(c==1) {
				nextBtn.setEnabled(false);
				c--;
			}
		}
	}
	// 예약자 정보 입력
	public void bookingRegist(List<CustomReservation3VO> lst) {
		String han = hanField.getText();
		String eng = engField.getText();
		String pas = passportField.getText();
		String pasE = passEndField.getText();
		String bir = birthField.getText();
		String tel = telField.getText();
		String email = emailField.getText();
		String nation = (String) nationCombo.getSelectedItem();
		String gender = (String) genderCombo.getSelectedItem();
		CustomReservation3VO vo = new CustomReservation3VO(han,eng,pas,pasE,bir,tel,email,nation,gender);
		lst.add(vo);
	}
	
	// 동승자 정보 입력
	public void bookingFellowRegist(List<CustomReservation3FellowVO> fellowlst) {
		String han = hanField.getText();
		String eng = engField.getText();
		String pas = passportField.getText();
		String pasE = passEndField.getText();
		String bir = birthField.getText();
		String tel = telField.getText();
		String email = emailField.getText();
		String nation = (String) nationCombo.getSelectedItem();
		String gender = (String) genderCombo.getSelectedItem();
		CustomReservation3FellowVO vo = new CustomReservation3FellowVO(han,eng,pas,pasE,bir,tel,email,nation,gender);
		fellowlst.add(vo);
	}
	// 만약, 인원수가 변동이 있어서 줄어든다? 그러면 모두다 초기화하고 다시 받아라
	public void replaceCount() {
		setTrue();
		// 전부 초기화
		for(int i=0; i<tf.length; i++) {
			tf[i].setText("");
		}
		lst.removeAll(lst);
		fellowLst.removeAll(fellowLst);
		titleLbl.setText("예약자 정보입력");
		hanLbl.setText("예약자 이름(한글) ex) 홍길동");
		engLbl.setText("예약자 이름(영어) ex) Hong GilDong");
		checkBox.setSelected(false);
		nextBtn.setEnabled(false);
		c=0;
		nextCheck = 0;
	}
	
	// 모든 필드, 콤보를 트루로 다시 바꾼다
	public void setTrue() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setEnabled(true);
		}
		nationCombo.setEnabled(true);
		genderCombo.setEnabled(true);
	}
	
	// 모든 필드, 콤보를 펄스로 다시 바꾼다
	public void setFalse() {
		for(int i=0; i<tf.length; i++) {
			tf[i].setEnabled(false);
		}
		nationCombo.setEnabled(false);
		genderCombo.setEnabled(false);
	}
}
```
					  
### CustomReservation2DAO
``` ruby
public class CustomReservation2DAO extends DBConn{

	public CustomReservation2DAO() {	}
	public List<CustomReservation2VO> getStartPlan(String start,String arrive){
		List<CustomReservation2VO> lst = new ArrayList<CustomReservation2VO>();
		try {
			getConn();
			sql = "select flightno, deptime, destime, flighttime, flight_state, fare from ac_flight where dep =? AND des = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, start);
			pstmt.setString(2, arrive);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReservation2VO vo = new CustomReservation2VO();
				vo.setFlightno(rs.getString(1));
				vo.setDeptime(rs.getString(2));
				vo.setDestime(rs.getString(3));
				vo.setFlighttime(rs.getString(4));
				vo.setFlight_state(rs.getString(5));
				vo.setFare(rs.getInt(6));
				
				lst.add(vo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	
	public List<CustomReservation2VO> getarrivePlan(String start,String arrive){
		List<CustomReservation2VO> lst = new ArrayList<CustomReservation2VO>();
		try {
			getConn();
			sql = "select flightno, deptime, destime, flighttime, flight_state, fare from ac_flight where dep =? AND des = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arrive);
			pstmt.setString(2, start);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomReservation2VO vo = new CustomReservation2VO();
				vo.setFlightno(rs.getString(1));
				vo.setDeptime(rs.getString(2));
				vo.setDestime(rs.getString(3));
				vo.setFlighttime(rs.getString(4));
				vo.setFlight_state(rs.getString(5));
				vo.setFare(rs.getInt(6));
				
				lst.add(vo);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return lst;
	}
	
	

}					  
```					  
					  
### CustomReservation3DAO			  
``` ruby
public class CustomReservation3DAO extends DBConn{

	public CustomReservation3DAO() {}
	
	public List<CustomReservation3VO> setCustomInfo(String passno, String user_id) {
		List<CustomReservation3VO> result = new ArrayList<CustomReservation3VO>();
		try {
			getConn();
			sql = "";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passno);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
}					  
					  
```
					  
</details>

#### 6. 예약하기 3
1) 출발 편에 예약되어 있는 좌석번호는 검은색으로 출력 및 enabled
2) 내가 선택한 좌석은 회색으로 출력 및 좌석번호 우측에 출력
3) 한번 더 클릭할 경우 다시 하얀색으로 표시 및 체크한 좌석번호도 제거

![슬라이드22](https://user-images.githubusercontent.com/70927354/120391534-1ae38480-c36a-11eb-8578-c43c6d1e26bb.JPG)

<details>
    <summary>자세히</summary>

### CustomReservation4
  ``` ruby
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
```

### CustomReservation4DAO					    
``` ruby
	public class CustomReservation4DAO extends DBConn{

	public CustomReservation4DAO() {}
	
	public List<CustomReservation4VO> startSeat(String startFlightno, String startDate) {
		
		List<CustomReservation4VO> lst = new ArrayList<CustomReservation4VO>();
		try {
			getConn();
			sql = "select seatno from (select * from ac_reservation where flightno = ? and brddate = ?) r, ac_seat s where r.resno=s.resno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, startFlightno);
			pstmt.setString(2, startDate);
			

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomReservation4VO vo = new CustomReservation4VO();
				vo.setSeatNo(rs.getString(1));
				
				lst.add(vo);
			} 
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
	
	public List<CustomReservation4VO> arriveSeat(String arriveFlightno, String arriveDate) {
		
		List<CustomReservation4VO> lst = new ArrayList<CustomReservation4VO>();
		try {
			getConn();
			sql = "select seatno from (select * from ac_reservation where flightno = ? and brddate = ?) r, ac_seat s where r.resno=s.resno";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, arriveFlightno);
			pstmt.setString(2, arriveDate);
			

			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CustomReservation4VO vo = new CustomReservation4VO();
				vo.setSeatNo(rs.getString(1));
				
				lst.add(vo);
			}
		} catch(Exception e ) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return lst;
	}
}				    
```					    

</details>  
  
#### 7. 예약하기 4
1) 선택 항공 내역과 탑승자 정보 출력
2) 모든 사항 체크 후 결제하기 가능
3) jpg 파일로 예약 확인서 저장됨
4) 마지막 예약 확인된 내용 출력
5) 정보 입력을 잘못할 경우 모든 내용 초기화 후 다시 입력

![슬라이드24](https://user-images.githubusercontent.com/70927354/120391559-2931a080-c36a-11eb-9169-f00d1022c634.JPG)

<details>
    <summary>자세히</summary>

### CustomReservation5 	
  ``` ruby
public class CustomReservation5 extends JPanel implements MouseListener, ItemListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel titleLbl1 = new JLabel("선택 항공 내역");
	String flightStr[] = {"출발지", "도착지", "출발일", "촐발시간", "도착시간", "항공편명", "좌석", "운임"};
	JTable table1;
	JScrollPane sp1;
	DefaultTableModel model1;
	JCheckBox check1 = new JCheckBox("", false);
	JLabel lbl1 = new JLabel("상기 내용을 확인하고 예약을 진행합니다.");
	
	JLabel titleLbl2 = new JLabel("탑승자 정보");
	String passengerStr[] = {"성명(한)", "성명(영)", "여권번호", "여권만료일", "발행국가", "생년월일", "연락처", "이메일"};
	JTable table2;
	JScrollPane sp2;
	DefaultTableModel model2;
	JCheckBox check2 = new JCheckBox("", false);
	JLabel lbl2 = new JLabel("해당 정보를 확인하세요. 위 내용은 예약 완료 후 변경이 불가합니다.");
	
	JLabel titleLbl3 = new JLabel("결제 내역");
	String paymentStr[] = {"결제예정금액"};
	JTable table3;
	JScrollPane sp3;
	DefaultTableModel model3;
	JCheckBox check3 = new JCheckBox("", false);
	JLabel lbl3 = new JLabel("상기 내용을 확인하고 결제를 진행합니다.");
	
	JCheckBox check4 = new JCheckBox("", false);
	JLabel bookingConfirmLbl = new JLabel("예약 변경 및 취소 규정을 확인해주세요.");
	
	JButton payBtn = new JButton("결제하기");
	JButton cancelBtn = new JButton("예약취소");
	
	//이벤트용 변수
	int allSelected = 0;
	int paymentWindowState = 0;
	int paymentState = 0;
	
	
	// 결제창에 넘길 값
	
	int result = 0;
	public CustomReservation5() {
		setLayout(null);
		this.setBackground(Color.white);
		
		//table1 선택항공내역
		add(titleLbl1).setBounds(390, 60, 230, 50);
			titleLbl1.setFont(titleFnt);
		model1 = new DefaultTableModel(flightStr, 0);
		table1 = new JTable(model1);
		sp1 = new JScrollPane(table1);
			sp1.getViewport().setBackground(Color.white);
		add(sp1).setBounds(100,110, 800,100);
		add(check1).setBounds(100, 217, 17, 17);
			check1.setBackground(Color.white);
		add(lbl1).setBounds(120, 215, 400, 25);
			lbl1.setFont(fnt);
		
		//table2 탑승자 정보
		add(titleLbl2).setBounds(410, 230, 200, 50);
			titleLbl2.setFont(titleFnt);
		model2  = new DefaultTableModel(passengerStr, 0);
		table2 = new JTable(model2);
		sp2 = new JScrollPane(table2);
		add(sp2).setBounds(100,280, 800, 100);
			sp2.getViewport().setBackground(Color.white);
		add(check2).setBounds(100, 387, 17,17);
			check2.setBackground(Color.white);
			check2.setEnabled(false);
		add(lbl2).setBounds(120, 385, 470, 25);
			lbl2.setFont(fnt);
		
		//table3 결제정보
		add(titleLbl3).setBounds(425, 410, 200, 50);
			titleLbl3.setFont(titleFnt);
		model3 = new DefaultTableModel(paymentStr,0);
		table3 = new JTable(model3);
		sp3 = new JScrollPane(table3);
		add(sp3).setBounds(300,460, 400, 100);
			sp3.getViewport().setBackground(Color.white);
		add(check3).setBounds(300, 566, 17, 17); //283
			check3.setBackground(Color.white);
			check3.setEnabled(false);
		add(lbl3).setBounds(320, 565, 400, 25); //310
			lbl3.setFont(fnt);
			
		add(check4).setBounds(300,580, 17, 45);
			check4.setEnabled(false); //예약 변경/취소 규정을 눌러서 확인해야 활성화
			check4.setBackground(Color.white);
		add(bookingConfirmLbl).setBounds(320,590, 420,27);
			bookingConfirmLbl.setFont(fnt);	
			
		//buttons
		add(payBtn).setBounds(370, 630, 100, 30);
			payBtn.setFont(fnt);
			payBtn.setBackground(new Color(0,130,255));
			payBtn.setForeground(Color.white);
			payBtn.setEnabled(false);
		add(cancelBtn).setBounds(530, 630, 100, 30);
			cancelBtn.setFont(fnt);
			cancelBtn.setBackground(new Color(0,130,255));
			cancelBtn.setForeground(Color.white);
		
		setSize(1000, 800);
		setVisible(true);
		
		//이벤트
		check1.addItemListener(this);
		check2.addItemListener(this);
		check3.addItemListener(this);
		check4.addItemListener(this);
		
		bookingConfirmLbl.addMouseListener(this);
		payBtn.addMouseListener(this);
			payBtn.setEnabled(false);
		cancelBtn.addMouseListener(this);
	}
		
	public void getData() {
		CustomReservation4VO vo4 = new CustomReservation4VO();
		CustomReservation3VO vo3 = new CustomReservation3VO();
		CustomReservation3FellowVO voFellow3 = new CustomReservation3FellowVO();
		
		// 출발지, 도착지, 출발일, 출발시간, 도착시간, 항공편명, 좌석, 운임
		String startCountry = (String) CustomReservation.startCombo.getSelectedItem();//출발지
		String startEndCountry = (String) CustomReservation.arriveCombo.getSelectedItem();//도착지
		String startDate = CustomReservation.startDateField.getText(); // 출발일
		String startTime = CustomReservation2.startTime; // 출발시간
		String startEndTime = CustomReservation2.startEndTime; // 도착시간
		String startResno = CustomReservation2.startSelect; // 출발 항공편명
		String startSeatNo = "";
		
		if(CustomReservation.humanCount==1) {
			for(int i=0; i<CustomReservation4.onelst.size(); i++) {
				vo4 = CustomReservation4.onelst.get(i); 
				startSeatNo = vo4.getSeatNo();	
			}
		} else if(CustomReservation.humanCount==2) {
			for(int i=0; i<CustomReservation4.onelst.size(); i++) {
				vo4 = CustomReservation4.onelst.get(i); 
				startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2();
			}
		} else if(CustomReservation.humanCount==3) {
			for(int i=0; i<CustomReservation4.onelst.size(); i++) {
				vo4 = CustomReservation4.onelst.get(i); 
				startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3();	
			}
		} else if(CustomReservation.humanCount==4) {
			for(int i=0; i<CustomReservation4.onelst.size(); i++) {
				vo4 = CustomReservation4.onelst.get(i); 
				startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
						", " +vo4.getSeatNo4();			
			}
		} else if(CustomReservation.humanCount==5) {
			for(int i=0; i<CustomReservation4.onelst.size(); i++) {
				vo4 = CustomReservation4.onelst.get(i); 
				startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
						", " +vo4.getSeatNo4() +", "+ vo4.getSeatNo5();
			}
		}
		
		int startFare = CustomReservation2.startFare;
		
		// 출발지, 도착지, 출발일, 출발시간, 도착시간, 항공편명, 좌석, 운임
		String arriveCountry = (String) CustomReservation.arriveCombo.getSelectedItem();//출발지
		String arriveEndCountry = (String) CustomReservation.startCombo.getSelectedItem();//도착지
		String arriveDate = CustomReservation.arriveDateField.getText(); // 도착지에서 출발일
		String arriveTime = CustomReservation2.arriveTime; // 도착지에서 출발시간
		String arriveEndTime = CustomReservation2.arriveendTime;
		String arriveResno = CustomReservation2.arriveSelect; // 출발 항공편명
		String arriveSeatNo = "";
		if(CustomReservation.humanCount==1) {
			for(int i=0; i<CustomReservation4.onelst.size(); i++) {
				vo4 = CustomReservation4.roundlst.get(i); 
				arriveSeatNo = vo4.getSeatNo();	
			}
		} else if(CustomReservation.humanCount==2) {
			for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
				vo4 = CustomReservation4.roundlst.get(i);
				arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2();
			}
		} else if(CustomReservation.humanCount==3) {
			for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
				vo4 = CustomReservation4.roundlst.get(i);
				arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3();
			}
		} else if(CustomReservation.humanCount==4) {
			for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
				vo4 = CustomReservation4.roundlst.get(i);
				arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
						", " +vo4.getSeatNo4();
			}
		} else if(CustomReservation.humanCount==5) {
			for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
				vo4 = CustomReservation4.roundlst.get(i);
				arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
						", " +vo4.getSeatNo4() +", "+ vo4.getSeatNo5();
			}
		}
		int arriveFare = CustomReservation2.arriveFare;
		Object startflightlst[] = {startCountry,startEndCountry,startDate,startTime,startEndTime,startResno,startSeatNo,startFare};
		Object arriveflightlst[] = {arriveCountry,arriveEndCountry,arriveDate,arriveTime,arriveEndTime,arriveResno,arriveSeatNo,arriveFare};
		model1.setRowCount(0);
		model1.addRow(startflightlst);
		model1.addRow(arriveflightlst);
		
		////////////////////////////////////// 예약정보/////////////////////////////////
		
		
		
		//성명(한), 성명(영), 여권번호, 여권만료일, 발행국가, 생년월일, 연락처, 이메일
		for(int i=0; i<CustomReservation3.lst.size();i++){
			vo3 = CustomReservation3.lst.get(i);
			Object Data1[] = {vo3.getUser_name(),vo3.getUser_ename(),vo3.getUser_passno(),vo3.getUser_exdate(),vo3.getUser_nation(),vo3.getUser_birth()
					,vo3.getUser_tel(),vo3.getUser_email()};
			model2.setRowCount(0);
			model2.addRow(Data1);
		}
		for(int i=0; i<CustomReservation3.fellowLst.size();i++) {
			voFellow3 = CustomReservation3.fellowLst.get(i);
			Object Data2[] = {voFellow3.getCom_name(),voFellow3.getCom_ename(),voFellow3.getCom_passno(),voFellow3.getCom_exdate(),voFellow3.getCom_nation()
					,voFellow3.getCom_birth(),voFellow3.getCom_tel(),voFellow3.getCom_email()};
			model2.addRow(Data2);
		}
		
		if(CustomReservation.rdb.equals("왕복")) {
			Object payResurt[] = {(startFare * Integer.valueOf(CustomReservation.humanCount)) +
				(arriveFare * Integer.valueOf(CustomReservation.humanCount))};
			model3.setRowCount(0);
			model3.addRow(payResurt);
			// 결제창에 넘겨줄 값
			result = (startFare * Integer.valueOf(CustomReservation.humanCount)) +
					(arriveFare * Integer.valueOf(CustomReservation.humanCount));
		} else if(CustomReservation.rdb.equals("편도")) {
			Object payResurt[] = {startFare * Integer.valueOf(CustomReservation.humanCount)};
			model3.setRowCount(0);
			model3.addRow(payResurt);
		}
		
		
		
	}
	
	
	
	@Override
	public void itemStateChanged(ItemEvent ie) {
		if(ie.getStateChange()==ItemEvent.SELECTED) {
			if(ie.getItem()==check1) {
				check2.setEnabled(true);
			}else if(ie.getItem()==check2) {
				check3.setEnabled(true);
			}else if(ie.getItem()==check4) {
				payBtn.setEnabled(true);
				allSelected = 1;
			}
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btnStr = ((JButton) obj).getText();
			if(btnStr.equals("결제하기")) {
				if(allSelected==0) {
					JOptionPane.showMessageDialog(this, "모든 내용을 확인해주세요");
				}else if(allSelected != 0) {
					if(paymentWindowState==0) {
						new CustomPayment();
						paymentWindowState = 1;
					}
				}
			}else if(btnStr.equals("예약취소")) {
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JLabel) {
			String lblName = ((JLabel) obj).getText();
			if(lblName.equals("예약 변경 및 취소 규정을 확인해주세요."));
			JOptionPane.showMessageDialog(this, "예약 변경/취소 규정을 확인했습니다. \n 체크박스를 선택해주세요.");
			check4.setEnabled(true);
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

	//////////////////////////////////////////////////////////////
	//결제창
	////////////////////////////////////////////////////////////////
	
	class CustomPayment extends JFrame implements ActionListener, MouseListener, WindowListener{
		Font fnt = new Font("굴림체", Font.BOLD, 14);
		
		JLabel amountLbl1 = new JLabel("총 운임");
		JLabel amountLbl2 = new JLabel("570000");
		JLabel wonLbl = new JLabel("원");
		
		JLabel paymentLbl = new JLabel("결제수단");
		String paymentStr[] = {"카드결제", "계좌이체"};
		JComboBox<String> paymentBox;
		
		JLabel cardNumLbl = new JLabel("카드번호");
		JTextField cardNumTf= new JTextField(20);
		JLabel bankLbl = new JLabel("우리은행");
		JLabel numLbl = new JLabel("0001-010-05-0002");
		
		JLabel mileageLbl1 = new JLabel("적립 마일리지");
		JLabel mileageLbl2 = new JLabel("570");
		JLabel mileageLbl3 = new JLabel("등급명");
		JLabel mileageLbl4 = new JLabel("보유 마일리지");
		JLabel mileageLbl5 = new JLabel("3800");
//		JButton mileageBtn = new JButton("사용");
		
//		JLabel finalPayLbl1 = new JLabel("최종 결제금액");
//		JLabel finalPayLbl2 = new JLabel("551200");
		
//		JLabel wonLbl2 = new JLabel("원");
		
		JButton payBtn = new JButton("결제");
		JButton cancelBtn = new JButton("취소");
		
		public CustomPayment() {
			setLayout(null);
			this.setBackground(Color.white);
			
			amountLbl2.setText(result+"");
			mileageLbl2.setText((int)(result*0.01)+"");
			mileageLbl5.setText(CustomMyPage.mileage+"");
			
			add(amountLbl1).setBounds(60,100, 80, 25); amountLbl1.setFont(fnt); 
			add(amountLbl2).setBounds(200, 100, 100, 25);amountLbl2.setFont(fnt); 
			add(wonLbl).setBounds(270,100, 20,25);	wonLbl.setFont(fnt);
				
			add(paymentLbl).setBounds(60, 130, 80, 25); paymentLbl.setFont(fnt);
			paymentBox = new JComboBox<String>(paymentStr);
			add(paymentBox).setBounds(200, 130, 100, 25); paymentBox.setFont(fnt);
				paymentBox.setBackground(Color.white);
				
			add(cardNumLbl).setBounds(60, 160, 80, 25); cardNumLbl.setFont(fnt);
			add(cardNumTf).setBounds(200,160, 160, 25);	cardNumTf.setFont(fnt); cardNumTf.setBackground(Color.white);
			add(bankLbl).setBounds(60, 160, 80, 25); bankLbl.setFont(fnt); bankLbl.setVisible(false);
			add(numLbl).setBounds(200,160, 160, 25);numLbl.setFont(fnt); numLbl.setVisible(false);
				
			add(mileageLbl1).setBounds(60,190, 100,25); mileageLbl1.setFont(fnt);
			add(mileageLbl2).setBounds(200,190, 60,25); mileageLbl2.setFont(fnt);
			//add(mileageLbl3).setBounds(270,190, 60, 25); mileageLbl3.setFont(fnt);
			add(mileageLbl4).setBounds(60,220, 100,25); mileageLbl4.setFont(fnt);
			add(mileageLbl5).setBounds(200,220, 80,25); mileageLbl5.setFont(fnt);
//			add(mileageBtn).setBounds(270,220, 65,25); mileageBtn.setFont(fnt);
//				mileageBtn.setBackground(new Color(0,130,255));
//				mileageBtn.setForeground(Color.white);
			
//			add(finalPayLbl1).setBounds(60,250, 100,25); finalPayLbl1.setFont(fnt);
//			add(finalPayLbl2).setBounds(200,250, 100,25); finalPayLbl2.setFont(fnt);
//			add(wonLbl2).setBounds(270,250, 20,25); wonLbl2.setFont(fnt);
			add(payBtn).setBounds(75,320, 100, 30); payBtn.setFont(fnt);
				payBtn.setBackground(new Color(0,130,255));
				payBtn.setForeground(Color.white);
			add(cancelBtn).setBounds(220,320, 100,30); cancelBtn.setFont(fnt);
				cancelBtn.setBackground(new Color(0,130,255));
				cancelBtn.setForeground(Color.white);
				
			setSize(400, 450);
			setVisible(true);
			setResizable(false);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			//이벤트
			paymentBox.addActionListener(this);
//			mileageBtn.addMouseListener(this);
			payBtn.addMouseListener(this);
			cancelBtn.addMouseListener(this);
			addWindowListener(this);
		}

		

		@Override
		public void actionPerformed(ActionEvent ae) {
			Object obj = ae.getSource();
			if(obj instanceof JComboBox) {
				String comboItem = (String)paymentBox.getSelectedItem();
				System.out.println(comboItem);
				
				if(comboItem.equals("계좌이체")) {
					cardNumLbl.setVisible(false);
					bankLbl.setVisible(true);
					cardNumTf.setVisible(false);
					numLbl.setVisible(true);
				}else if(comboItem.equals("카드결제")) {
					bankLbl.setVisible(false);
					cardNumLbl.setVisible(true);
					numLbl.setVisible(false);
					cardNumTf.setVisible(true);
				}
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent me) {
			Object obj = me.getSource();
			if(obj instanceof JButton) {
				String btnName = ((JButton) obj).getText();
				if(btnName.equals("결제")) {
					if(CustomFrame.reservation6.setData()==0) {
						JOptionPane.showMessageDialog(this, "정보입력이 잘못되었습니다 \n 올바른 정보를 입력후 다시 결제 하시기 바랍니다");
					} else {
						JOptionPane.showMessageDialog(this, "결제가 완료되었습니다");
						this.setVisible(false);
						CustomFrame.reservation5.setVisible(false);
						CustomFrame.reservation6.setVisible(true);
						CustomFrame.reservation6.getData();
						setMileage();
						CustomFrame.centerPane.add(CustomFrame.reservation6);
					}
				}else if(btnName.endsWith("취소")){
					JOptionPane.showMessageDialog(this, "결제를 취소했습니다");
					this.setVisible(false);
					
				}
//				else if(btnName.equals("사용")) {
//					int totalMinusMileage = (Integer.parseInt(finalPayLbl2.getText())
//							- (Integer.parseInt(mileageLbl5.getText())));
//					String finalPrice = (Integer.toString(totalMinusMileage));
//					finalPayLbl2.setText(finalPrice);
////					mileageBtn.setEnabled(false);
//					mileageLbl5.setText("0");
//				}
			}
			
		}
	//// 멈춤
		public void setMileage() {
			CustomReservaion6DAO dao = new CustomReservaion6DAO();
			dao.plusMileage(Integer.valueOf((int)(result*0.01)), AirlineMain.idField.getText());
		}

		
		@Override
		public void windowOpened(WindowEvent e) {
			paymentWindowState = 1;
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			paymentWindowState = 0;
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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

	
	

}
  ```

### CustomReservation6	
``` ruby
  public class CustomReservation6 extends JPanel implements ActionListener{
	Font fnt = new Font("굴림체", Font.BOLD, 14);
	Font titleFnt = new Font("굴림체", Font.BOLD, 32);
	
	JLabel ticketLbl = new JLabel("예약 확인서");
	JLabel bookingNumLbl1 = new JLabel("예약번호");
	JLabel bookingNumLbl2 = new JLabel("AAA123");
	JLabel bookingDateLbl1 = new JLabel("예약날짜");
	JLabel bookingDateLbl2 = new JLabel("2021-01-31");
	
	JLabel flightLbl = new JLabel("선택한 노선");
	String[] flightStr = {"출발지", "도착지", "출발일", "출발시간", "도착시간", "비행편", "좌석", "운임"};
	DefaultTableModel model1;
	JTable flightTable;
	JScrollPane sp1;
	
	JLabel passengerLbl = new JLabel("탑승자 정보");
	String[] passengerStr = {"성명(한)", "영문명", "여권번호", "여권만료일", "발행국가", "생년월일", "연락처", "이메일"};
	DefaultTableModel model2;
	JTable passengerTable;
	JScrollPane sp2;
	
	JButton saveBtn = new JButton("저장");
	JButton okBtn = new JButton("확인");
	
	public CustomReservation6() {
		setLayout(null);
		this.setBackground(Color.white);
		
		add(ticketLbl).setBounds(420, 60, 200, 50);
			ticketLbl.setFont(titleFnt);
		add(bookingNumLbl1).setBounds(380, 120, 70, 35);
			bookingNumLbl1.setFont(fnt);
		add(bookingNumLbl2).setBounds(385, 150, 150, 35);
			bookingNumLbl2.setFont(fnt);
			
		add(bookingDateLbl1).setBounds(580, 120, 70, 35);
			bookingDateLbl1.setFont(fnt);
		add(bookingDateLbl2).setBounds(575, 150, 100, 35);
			bookingDateLbl2.setFont(fnt);
			
		add(flightLbl).setBounds(420, 200, 200, 50);
			flightLbl.setFont(titleFnt);
		model1 = new DefaultTableModel(flightStr,0);
		flightTable = new JTable(model1);
		sp1 = new JScrollPane(flightTable);
		add(sp1).setBounds(200, 250, 600, 150);
			sp1.getViewport().setBackground(Color.white);
		
		add(passengerLbl).setBounds(420, 400, 200, 50);
			passengerLbl.setFont(titleFnt);
		model2 = new DefaultTableModel(passengerStr, 0);
		passengerTable = new JTable(model2);
		sp2 = new JScrollPane(passengerTable);
		add(sp2).setBounds(200, 450, 600, 150);
			sp2.getViewport().setBackground(Color.white);
			
		add(saveBtn).setBounds(350, 620, 100, 30);
			saveBtn.setFont(fnt);
			saveBtn.setBackground(new Color(0,130,255));
			saveBtn.setForeground(Color.white);
		add(okBtn).setBounds(550, 620, 100, 30);
			okBtn.setFont(fnt);
			okBtn.setBackground(new Color(0,130,255));
			okBtn.setForeground(Color.white);
		
			
		setSize(1000,800);
		setVisible(true);
		
		saveBtn.addActionListener(this);
		okBtn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj instanceof JButton) {
			String btn = ((JButton) obj).getText();
			if(btn.equals("저장")) {
				JOptionPane.showMessageDialog(this, "예약확인서가 저장되었습니다.");
				 try {
					 BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			         Graphics2D graphics2D = image.createGraphics();
			         CustomFrame.bookingChange4.paint(graphics2D);
			         ImageIO.write(image,"jpeg", new File("D://E-ticket.jpeg"));
			        }
			        catch(Exception exception)
			        {
			            //code
			        }
				//파일 쓰기
			}else if(btn.equals("확인")) {
				JOptionPane.showMessageDialog(this, "예약이 완료되었습니다.");
				
				this.setVisible(false);
				CustomFrame.plan.setVisible(true);
				CustomFrame.centerPane.add(CustomFrame.plan);
			}
		}
	}
	public void getData() {
			CustomReservation4VO vo4 = new CustomReservation4VO();
			CustomReservation3VO vo3 = new CustomReservation3VO();
			CustomReservation3FellowVO voFellow3 = new CustomReservation3FellowVO();
			String meal ="";
			String seatno ="";
			
			// 출발지, 도착지, 출발일, 출발시간, 도착시간, 항공편명, 좌석, 운임
			String startCountry = (String) CustomReservation.startCombo.getSelectedItem();//출발지
			String startEndCountry = (String) CustomReservation.arriveCombo.getSelectedItem();//도착지
			String startDate = CustomReservation.startDateField.getText(); // 출발일
			String startTime = CustomReservation2.startTime; // 출발시간
			String startEndTime = CustomReservation2.startEndTime; // 도착시간
			String startResno = CustomReservation2.startSelect; // 출발 항공편명
			String startSeatNo = "";
			
			if(CustomReservation.humanCount==1) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo();	
				}
			} else if(CustomReservation.humanCount==2) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2();
				}
			} else if(CustomReservation.humanCount==3) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3();	
				}
			} else if(CustomReservation.humanCount==4) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4();			
				}
			} else if(CustomReservation.humanCount==5) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.onelst.get(i); 
					startSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4() +", "+ vo4.getSeatNo5();
				}
			}
			
			int startFare = CustomReservation2.startFare;
			
			// 출발지, 도착지, 출발일, 출발시간, 도착시간, 항공편명, 좌석, 운임
			String arriveCountry = (String) CustomReservation.arriveCombo.getSelectedItem();//출발지
			String arriveEndCountry = (String) CustomReservation.startCombo.getSelectedItem();//도착지
			String arriveDate = CustomReservation.arriveDateField.getText(); // 도착지에서 출발일
			String arriveTime = CustomReservation2.arriveTime; // 도착지에서 출발시간
			String arriveEndTime = CustomReservation2.arriveendTime;
			String arriveResno = CustomReservation2.arriveSelect; // 출발 항공편명
			String arriveSeatNo = "";
			if(CustomReservation.humanCount==1) {
				for(int i=0; i<CustomReservation4.onelst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i); 
					arriveSeatNo = vo4.getSeatNo();	
				}
			} else if(CustomReservation.humanCount==2) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2();
				}
			} else if(CustomReservation.humanCount==3) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3();
				}
			} else if(CustomReservation.humanCount==4) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4();
				}
			} else if(CustomReservation.humanCount==5) {
				for(int i=0; i<CustomReservation4.roundlst.size(); i++) {
					vo4 = CustomReservation4.roundlst.get(i);
					arriveSeatNo = vo4.getSeatNo() + ", "+ vo4.getSeatNo2() +", "+ vo4.getSeatNo3() +
							", " +vo4.getSeatNo4() +", "+ vo4.getSeatNo5();
				}
			}
			int arriveFare = CustomReservation2.arriveFare;
			Object startflightlst[] = {startCountry,startEndCountry,startDate,startTime,startEndTime,startResno,startSeatNo,startFare};
			Object arriveflightlst[] = {arriveCountry,arriveEndCountry,arriveDate,arriveTime,arriveEndTime,arriveResno,arriveSeatNo,arriveFare};
			model1.addRow(startflightlst);
			model1.addRow(arriveflightlst);

			////////////////////////////////////// 예약정보/////////////////////////////////
			
			
			
			//성명(한), 성명(영), 여권번호, 여권만료일, 발행국가, 생년월일, 연락처, 이메일
			for(int i=0; i<CustomReservation3.lst.size();i++){
				vo3 = CustomReservation3.lst.get(i);
				Object Data1[] = {vo3.getUser_name(),vo3.getUser_ename(),vo3.getUser_passno(),vo3.getUser_exdate(),vo3.getUser_nation(),vo3.getUser_birth()
						,vo3.getUser_tel(),vo3.getUser_email()};
				model2.setRowCount(0);
				model2.addRow(Data1);
			}
			for(int i=0; i<CustomReservation3.fellowLst.size();i++) {
				voFellow3 = CustomReservation3.fellowLst.get(i);
				Object Data2[] = {voFellow3.getCom_name(),voFellow3.getCom_ename(),voFellow3.getCom_passno(),voFellow3.getCom_exdate(),voFellow3.getCom_nation()
						,voFellow3.getCom_birth(),voFellow3.getCom_tel(),voFellow3.getCom_email()};
				model2.addRow(Data2);
			}
			
			
		}
	
	
	/// 데이터 저장
	public int setData() {
		CustomReservaion6DAO dao = new CustomReservaion6DAO();
		
		String startDate = CustomReservation.startDateField.getText(); // 출발일
		String arriveDate = CustomReservation.arriveDateField.getText(); // 도착지에서 출발일
		String startResno = CustomReservation2.startSelect; // 출발 항공편명
		String arriveResno = CustomReservation2.arriveSelect; // 복귀 항공편명
		
		int result = dao.userUpdate(CustomReservation3.lst,AirlineMain.idField.getText());
		dao.reservationStartUpdate(startResno, startDate, CustomReservation3.lst);
		dao.reservationArriveUpdate(arriveResno, arriveDate, CustomReservation3.lst);
		String sResno = dao.reservationStartResnoCheck(AirlineMain.idField.getText());
		String aResno = dao.reservationArriveResnoCheck(AirlineMain.idField.getText());
		if(!(CustomReservation.humanCount == 1)) { // 명 수가 1이 아닐때는 동승자가 있기 때문에 실행한다.
			dao.companyStartUpdate(CustomReservation3.fellowLst);
			dao.companyArriveUpdate(CustomReservation3.fellowLst);
		}
		dao.seatStartUpdate(CustomReservation.humanCount, CustomReservation4.onelst);
		dao.seatArriveUpdate(CustomReservation.humanCount, CustomReservation4.roundlst);
		
		bookingNumLbl2.setText(sResno + ", " + aResno);
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		bookingDateLbl2.setText(year +"-"+month+"-"+date);
		
		return result;
		
	}

}
```

### CustomReservaion6DAO
``` ruby
public class CustomReservaion6DAO extends DBConn {
	public CustomReservaion6DAO() {	}

	
	String meal;
	String startResno;
	String arriveResno;
	String passno;
	String startDate;
	String arriveDate;
	

	
	// mileage 추가 쿼리문
	public void plusMileage(int mileage, String user_id) {
		try{
			getConn();
			sql = "update ac_user set mileage = mileage + ? where user_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mileage);
			pstmt.setString(2, user_id);
		
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		
	}
	
	
	//1번 ac_user 업데이트
	public int userUpdate(List<CustomReservation3VO> vo, String user_id) {
		int result =0;
		CustomReservation3VO vo3 = vo.get(0);
					
		String birth = vo3.getUser_birth().replace("/", "");
		passno = vo3.getUser_passno();
		try{
			getConn();
			sql = "update ac_user set user_name=?, user_ename=?, user_passno=?, user_birth=to_date(?,'YYMMDD')"
					+ " , user_tel=?, user_email=?, user_nation=?, user_gender=? where user_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo3.getUser_name());
			pstmt.setString(2, vo3.getUser_ename());
			pstmt.setString(3, vo3.getUser_passno());
			pstmt.setString(4, birth);
			pstmt.setString(5, vo3.getUser_tel());
			pstmt.setString(6, vo3.getUser_email());
			pstmt.setString(7, vo3.getUser_nation());
			pstmt.setString(8, vo3.getUser_gender());
			pstmt.setString(9, user_id);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return result;
	}
	
	// 2-1번 reservation Start 출발지 기준 생성
	public void reservationStartUpdate(String flightno, String start, List<CustomReservation3VO> vo) {
		CustomReservation3VO vo3 = vo.get(0);
		
		String date = start.replace("/", ""); // 출발일
		String exDate = vo3.getUser_exdate().replace("/", "");
		startDate = start.substring(2,10);
		try{
			getConn();
			sql = "insert into ac_reservation(resno, res_date, flightno, brddate, user_passno, user_exdate) "
					+ " values('A'||to_char(resno_sq.nextval), sysdate, ?, to_date(?,'YYMMDD'), ?, to_date(?,'YYMMDD'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flightno);// 편명
			pstmt.setString(2, date); // 탑승일
			pstmt.setString(3, vo3.getUser_passno()); // 여권번호
			pstmt.setString(4, exDate); // 여권만료일
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		
	}
	
	// 2-2번 reservation Arrive 도착지 기준 생성
	public void reservationArriveUpdate(String flightno,String arrive, List<CustomReservation3VO> vo) {
		CustomReservation3VO vo3 = vo.get(0);
		
		String date = arrive.replace("/", ""); // 도착지에서 출발일
		String exDate = vo3.getUser_exdate().replace("/", "");
		arriveDate = arrive.substring(2,10);
		try{
			getConn();
			sql = "insert into ac_reservation(resno,res_date,flightno,brddate,user_passno,user_exdate) "
					+ " values('A'||to_char(resno_sq.nextval), sysdate, ?, to_date(?,'YYMMDD'), ?, to_date(?,'YYMMDD'))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flightno);//편명
			pstmt.setString(2, date);
			pstmt.setString(3, vo3.getUser_passno());
			pstmt.setString(4, exDate);
			
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		
	}
	
	// 3-1번 reservation의 출발지 -> 도착지 기준의 resno를 가져온다
	public String reservationStartResnoCheck(String user_id) {
		try {
			getConn();
			sql ="select resno from ac_reservation where user_passno=(select user_passno from ac_user where user_id=?) and brddate=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, startDate);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				startResno = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return startResno;
	}
	
	// 3-2번 reservation의 도착지 -> 출발지 기준의 resno를 가져온다
	public String reservationArriveResnoCheck(String user_id) {
		try {
			getConn();
			sql ="select resno from ac_reservation where user_passno=(select user_passno from ac_user where user_id=?) and brddate=?";
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2, arriveDate);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				arriveResno = rs.getString(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return arriveResno;
	}
	

	//4- 1번 ( 출발지 -> 도착지 resno 기준  등록 )
	public void companyStartUpdate(List<CustomReservation3FellowVO> vo) {
		for(int i=0; i<vo.size();i++) {
			CustomReservation3FellowVO voFellow3 = vo.get(i);
			String exDate = voFellow3.getCom_exdate().replace("/", "");
			String birth = voFellow3.getCom_birth().replace("/", "");
			try{
				getConn();
				sql = "insert into ac_company(com_passno, com_name, com_ename, com_birth, com_gender,"
						+ "com_exdate, com_nation, com_tel, com_email, resno) "
						+ " values(?, ?, ?, to_date(?,'YYMMDD'), ?, to_date(?,'YYMMDD'), ?, ?, ?, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, voFellow3.getCom_passno());
				pstmt.setString(2, voFellow3.getCom_name());
				pstmt.setString(3, voFellow3.getCom_ename());
				pstmt.setString(4, birth);
				pstmt.setString(5, voFellow3.getCom_gender());
				pstmt.setString(6, exDate);
				pstmt.setString(7, voFellow3.getCom_nation());
				pstmt.setString(8, voFellow3.getCom_tel());
				pstmt.setString(9, voFellow3.getCom_email());
				pstmt.setString(10, startResno);
				
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally{
				dbClose();
			}
		}
	}
	
	//4-2번 ( 도착지 -> 출발지 resno 기준 등록 )
	public void companyArriveUpdate(List<CustomReservation3FellowVO> vo) {
		for(int i=0; i<vo.size();i++) {
			CustomReservation3FellowVO voFellow3 = vo.get(i);
			String exDate = voFellow3.getCom_exdate().replace("/", "");
			String birth = voFellow3.getCom_birth().replace("/", "");
			
			try{
				getConn();
				sql = "insert into ac_company(com_passno, com_name, com_ename, com_birth, com_gender,"
						+ "com_exdate, com_nation, com_tel, com_email,resno) "
						+ " values(?,?,?,to_date(?,'YYMMDD'),?,to_date(?,'YYMMDD'),?,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, voFellow3.getCom_passno());
				pstmt.setString(2, voFellow3.getCom_name());
				pstmt.setString(3, voFellow3.getCom_ename());
				pstmt.setString(4, birth);
				pstmt.setString(5, voFellow3.getCom_gender());
				pstmt.setString(6, exDate);
				pstmt.setString(7, voFellow3.getCom_nation());
				pstmt.setString(8, voFellow3.getCom_tel());
				pstmt.setString(9, voFellow3.getCom_email());
				pstmt.setString(10, arriveResno);
				
				pstmt.executeUpdate();
			} catch(Exception e) {
				e.printStackTrace();
			} finally{
				dbClose();
			}
		}
	}
		
	//5-1번 ac_seat 출발편 등록
	public void seatStartUpdate(int count, List<CustomReservation4VO> vo) {
		CustomReservation4VO vo4 = vo.get(0);
		meal = vo4.getMeal();
		try {
			getConn();
			for(int i=0; i<count; i++) {
				if(i==0) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, startResno);
					pstmt.setString(2, vo4.getSeatNo());
					pstmt.setString(3, meal);
				} else if(i==1) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, startResno);
					pstmt.setString(2, vo4.getSeatNo2());
					pstmt.setString(3, meal);
				} else if(i==2) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, startResno);
					pstmt.setString(2, vo4.getSeatNo3());
					pstmt.setString(3, meal);
				} else if(i==3) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, startResno);
					pstmt.setString(2, vo4.getSeatNo4());
					pstmt.setString(3, meal);
				} else if(i==4) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, startResno);
					pstmt.setString(2, vo4.getSeatNo5());
					pstmt.setString(3, meal);
				} 
				pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
			
	}
	
	//5-2번 ac_seat 복귀편 등록
	public void seatArriveUpdate(int count, List<CustomReservation4VO> vo) {
		CustomReservation4VO vo4 = vo.get(0);
		meal = vo4.getMeal();
		try {
			getConn();
			for(int i=0; i<count; i++) {
				if(i==0) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, arriveResno);
					pstmt.setString(2, vo4.getSeatNo());
					pstmt.setString(3, meal);
				} else if(i==1) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, arriveResno);
					pstmt.setString(2, vo4.getSeatNo2());
					pstmt.setString(3, meal);
				} else if(i==2) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, arriveResno);
					pstmt.setString(2, vo4.getSeatNo3());
					pstmt.setString(3, meal);
				} else if(i==3) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, arriveResno);
					pstmt.setString(2, vo4.getSeatNo4());
					pstmt.setString(3, meal);
				} else if(i==4) {
					sql = "insert into ac_seat(resno,seatno,meal) values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, arriveResno);
					pstmt.setString(2, vo4.getSeatNo5());
					pstmt.setString(3, meal);
				} 
				pstmt.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
	}
	
}	
```	
  
</details>

#### 8. 마이페이지
1) 마일리지 현황 및 탑승 횟수 확인 가능
2) 사용내역을 확인 가능
3) 회원정보 수정 가능, 공백으로 둘 경우 전에 입력되어 있던 데이터 그대로 저장됨
4) 회원 탈퇴 시 모든 데이터 삭제 가능

![슬라이드39](https://user-images.githubusercontent.com/70927354/120391650-50886d80-c36a-11eb-9d92-4e88ec537607.JPG)

<details>
  <summary>자세히</summary>

``` ruby
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
```

### CustomMypageDAO
``` ruby
	public class CustomMypageDAO extends DBConn{

	public CustomMypageDAO() {}
	
	public List<CustomMypageVO> setMypage(String id) {
		List<CustomMypageVO> lst = new ArrayList<CustomMypageVO>();
		
		try {
			getConn();
			sql = "select user_name,userno,mileage from ac_user where user_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomMypageVO vo = new CustomMypageVO();
				
				vo.setUser_name(rs.getString(1));
				vo.setUserno(rs.getInt(2));
				vo.setMileage(rs.getInt(3));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}

	public List<CustomMypageVO> setCount(String id) {
		List<CustomMypageVO> lst = new ArrayList<CustomMypageVO>();
		
		try {
			getConn();
			sql = "select count(resno) from ac_reservation where user_passno=(select user_passno from ac_user where user_id=?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomMypageVO vo = new CustomMypageVO();
				
				vo.setCount(rs.getInt(1));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		
		return lst;
	}
}
```					   
                               
</details>


------------------------
### 마치며
#### 프로젝트 보완사항
본 프로젝트는 완성도측에서 항공사에 대한 데이터를 일자별로 관리자가 설정 할 수 있게 만들지 못한점, 사원에 대한 데이터를 구현하지 못하여 완벽하지 못해 아쉬웠습니다.
프로젝트 구현 전 팀원들과의 회의 때도 이 부분에 대해 토론 했지만 프로젝트 종료까지 구현하기에는 다소 시간이 부족할 것이라 판단하여 제외하고 진행했는데 
결국 옳았던 판단이였지만 프로그램 제작자로서 아쉬움이 남아있어 추후 파이널 프로젝트는 완성도 측면에서 좀 더 완벽 할 수 있도록 하고자 합니다.

#### 느낀점
 프로그래밍을 시작하고 처음으로 협업을 통해 제작한 토이 프로젝트이기에 다소 부족해 보인다 느껴지고 너무나도 거대한 Java 라는 언어에 대해서도 알면 알 수록 다양한 기능이 있어 아직까지 숙달이 부족하다고 생각합니다. 
   다만 본 프로젝틀를 진행하며 프로그래밍 언어의 활용법을 숙달하였고 새로운 기술에 대한 해결 능력, 다양한 방법을 시도하며 발전하는 모습, 내가 원하는대로 만들어 졌을 때 느껴지는 희열, 협업 중 공유의 중요성, 프로그래밍을 하는 재미를 느낄 수 있는, 추후에 개발하는데 있어 도움이 될 소중한 경험을 얻는 시간이었습니다.
 
 아래는 프로젝트를 진행함에 있어서 개인적으로 중점을 두고 진행한 부분에 대해 작성해 보았습니다.
 - 실제 서비스시 발생할 문제들에 대해 생각해보고 사전에 제약사항들을 적용해 보며 테스트를 진행함
 - 코드를 작성함에 있어 데이터의 중복이 일어나지 않고 객체지향을 최대한 활용하고자 함
 - 변수명을 짓는데 있어, 겹치거나 의미없지 않도록 하기 위해서 고민함
 - 주석을 적극 활용함으로써 팀원들은 물론이고 추후에 본인 또한 수정 및 분석함에 있어 편리하도록 하고자 함
 
생각은 했지만 적용함에 있어 부족했던 점이 있을텐데 최선을 다했다고 자부하기에 부끄럽지 않은 프로그램, 유익한 경험이었습니다.
<br/>
이상으로 글을 마치겠습니다.
