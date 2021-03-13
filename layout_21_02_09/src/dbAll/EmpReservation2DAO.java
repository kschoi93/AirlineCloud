package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpReservation2DAO extends DBConn{

	public EmpReservation2DAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public int getMileage(String passNo) {
		//현재 누적 마일리지 가져오기
		int mileage = 0;
		try {
			getConn();
			sql = "select mileage from ac_user where user_passno=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mileage = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return mileage;
	}
	public int mileageUpdate(EmpReservation2VO vo) {
		int result = 0;
		try {
			getConn();
			//								((누적-초기)+변경후)
			sql = "update ac_user set mileage=((?-?)+?) where user_passno=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getMileageTotal());
			pstmt.setInt(2, vo.getMileageOld());
			pstmt.setInt(3, vo.getMileageNew());
			pstmt.setString(4, vo.getUserPassNo());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	public int bookingUpdate(EmpReservation2VO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update ac_reservation set flightno=? where resno=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getNewFlightNum());
			pstmt.setString(2, vo.getResNo());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	
	public List <EmpReservation2VO> EmpReservation2Allselect(String depfare, String fare){
		List<EmpReservation2VO> lst = new ArrayList<EmpReservation2VO>();
		
		try {
			getConn();
			sql = "select flightno, fare "
					+ "from ( "
					+ "select flightno, fare, "
					+ "    LAG(flightno) over (order by flightno) 모르겠다, "
					+ "    LAG(fare) over (order by fare) 음"
					+ "    from ac_flight where flightno in(?, ?) "
					+ "    order by flightno ) "
					+ "    where flightno in( ? , ?)";
			
			
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,depfare);
			pstmt.setString(2, fare);
			pstmt.setString(3,depfare);
			pstmt.setString(4, fare);
			
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				EmpReservation2VO vo = new EmpReservation2VO(
						rs.getString(1),
						rs.getString(2)
//						rs.getString(3),
//						rs.getString(4)
						
						);
				
				lst.add(vo);
				
			}
			
		}catch(Exception ae) {
			 ae.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpReservation2VO> getTable3(String newFlight1, String resNo1){
		List<EmpReservation2VO> lst = new ArrayList<EmpReservation2VO>();
		try {
			getConn();
			sql = "select dep,  brddate, deptime, destime, f.flightno, seatno, fare from ac_flight f, ac_reservation r, ac_seat s "
					+ "where f.flightno=? AND r.resno=? AND s.resno=? order by seatno";
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, newFlight1);
			pstmt.setString(2, resNo1);
			pstmt.setString(3, resNo1);
		
			rs = pstmt.executeQuery();
			System.out.println("쿼리문"+newFlight1);
			
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담는 작업
				EmpReservation2VO vo = new EmpReservation2VO();
				
				vo.setDep(rs.getString(1));
				vo.setBrdDate(rs.getString(2));
				vo.setDepTime(rs.getString(3));
				vo.setDesTime(rs.getString(4));
				vo.setFlightNo(rs.getString(5));
				vo.setSeatNo(rs.getString(6));
				vo.setFare2(rs.getString(7));
	
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	public List<EmpReservation2VO> getTable4(String resNo1){
		List<EmpReservation2VO> lst = new ArrayList<EmpReservation2VO>();
		try {
			getConn();
			sql = "select user_gender, user_name, user_ename, r.user_passno, r.user_exdate, user_nation, user_birth, user_tel, user_email "
					+ "from ac_user u join ac_reservation r on u.user_passno=r.user_passno where r.resno=? "
					+ "union all "
					+ "select com_gender, com_name, com_ename, com_passno, com_exdate, com_nation, com_birth, com_tel, com_email "
					+ "from ac_company where resno=?"; 
					
			pstmt = conn.prepareStatement(sql);
	
			pstmt.setString(1, resNo1);
			pstmt.setString(2, resNo1);
		
			rs = pstmt.executeQuery();
			System.out.println("쿼리문2"+resNo1);
			
			while(rs.next()) {
				//레코드를 VO에 담고 VO를 List에 담는 작업
				EmpReservation2VO vo = new EmpReservation2VO();
				vo.setGender(rs.getString(1));
				vo.setUserName(rs.getString(2));
				vo.setUserEname(rs.getString(3));
				vo.setUserPassNo(rs.getString(4));
				vo.setUserExdate(rs.getString(5));
				vo.setUserNation(rs.getString(6));
				vo.setUserBirth(rs.getString(7));
				vo.setUserTel(rs.getString(8));
				vo.setUserEmail(rs.getString(9));
				
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
