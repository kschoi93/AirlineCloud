package dbAll;

import java.util.List;

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
