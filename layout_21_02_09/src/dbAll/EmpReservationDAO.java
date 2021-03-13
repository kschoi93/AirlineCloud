package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpReservationDAO extends DBConn{

	public EmpReservationDAO() {
		
	}
	
	public List<EmpReservationVO> EmpReservationAllselect(){
		List<EmpReservationVO> lst = new ArrayList<EmpReservationVO>();
		try {
			getConn();
			sql = "select resno, user_name,  to_char(user_birth, 'YYYY/MM/DD'), user_tel, dep, r.flightno "
					+ "from ac_reservation r join ac_user u on r.user_passNo = u.user_passNo "
					+ "join ac_flight f on r.flightNO= f.flightNO "
					+ "UNION all "
					+ "select r.resno, com_name, to_char(com_birth, 'YYYY/MM/DD'), com_tel,  dep, r.flightno "
					+ "from ac_reservation r join ac_company c on r.resNo = c.resno "
					+ "join ac_flight f on r.flightNO= f.flightNO order by resno" ; 
			pstmt = conn.prepareStatement(sql);
			
			rs =pstmt.executeQuery();
			
			while(rs.next()) {
				EmpReservationVO vo = new EmpReservationVO();
				vo.setResno(rs.getString(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_birth(rs.getString(3));
				vo.setUser_tel(rs.getString(4));
				vo.setDep(rs.getString(5));
				vo.setFlightno(rs.getString(6));
				
				lst.add(vo);
				
			}
			

		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			
			dbClose();
		}
		return lst;
	}
	
	
	// 예약번호 삭제
	public int empReservationDelete(String resNo) {
		int result = 0 ;
		
		try {
			getConn();
			sql = "delete from ac_reservation where resNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, resNo);
			
			result = pstmt.executeUpdate();
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
 		
		return result;
	}

	
	//예약번호 검색
	public List<EmpReservationVO>getreservationSearchRecord(String searchReservationWord){
		List<EmpReservationVO> lst = new ArrayList<EmpReservationVO>();
		try {
			
			getConn();
//			sql = "select resno from ac_reservation where resno like ? order by resno ";
			
			sql = "select resno, user_name,  to_char(user_birth, 'YYYY/MM/DD'), user_tel, dep, r.flightno "
			+ "from ac_reservation r join ac_user u on r.user_passNo = u.user_passNo "
			+ "join ac_flight f on r.flightNO= f.flightNO where upper(resno) like '%' || upper (?) ||'%'  "
			+ "UNION all "
			+ "select r.resno, com_name, to_char(com_birth, 'YYYY/MM/DD'), com_tel,  dep, r.flightno "
			+ "from ac_reservation r join ac_company c on r.resNo = c.resno "
			+ "join ac_flight f on r.flightNO= f.flightNO where upper(r.resno) like '%' || upper (?) ||'%' " ; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+searchReservationWord+"%"); // 첫번째 물음표 _ 유저 예약번호 검색
			pstmt.setString(2, "%"+searchReservationWord+"%"); // 두번쨰 물음표 _ 동행자 예약번호 검색
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				EmpReservationVO vo = new EmpReservationVO();

				vo.setResno(rs.getString(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_birth(rs.getString(3));
				vo.setUser_tel(rs.getString(4));
				vo.setDep(rs.getString(5));
				vo.setFlightno(rs.getString(6));
				
				lst.add(vo);
			}
			
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		
		return lst;
	}
	
	//출발지검색
	public List<EmpReservationVO>getDepSearchRecord(String depReservationWord){
		List<EmpReservationVO> lst = new ArrayList<EmpReservationVO>();
		try {
			
			getConn();
//			sql = "select resno from ac_reservation where resno like ? order by resno ";
			
			sql = "select resno, user_name,  to_char(user_birth, 'YYYY/MM/DD'), user_tel, dep, r.flightno "
			+ "from ac_reservation r join ac_user u on r.user_passNo = u.user_passNo "
			+ "join ac_flight f on r.flightNO= f.flightNO where upper(dep) like '%' || upper (?) ||'%'  "
			+ "UNION all "
			+ "select r.resno, com_name, to_char(com_birth, 'YYYY/MM/DD'), com_tel,  dep, r.flightno "
			+ "from ac_reservation r join ac_company c on r.resNo = c.resno "
			+ "join ac_flight f on r.flightNO= f.flightNO where upper(dep) like '%' || upper (?) ||'%' " ; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+depReservationWord+"%");
			pstmt.setString(2, "%"+depReservationWord+"%");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				EmpReservationVO vo = new EmpReservationVO();

				vo.setResno(rs.getString(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_birth(rs.getString(3));
				vo.setUser_tel(rs.getString(4));
				vo.setDep(rs.getString(5));
				vo.setFlightno(rs.getString(6));
				
				lst.add(vo);
			}
			
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		
		return lst;
	}
		
	//항공편 검색
	public List<EmpReservationVO>getflightNoSearch(String flightNoReservationWord){
		List<EmpReservationVO> lst = new ArrayList<EmpReservationVO>();
		try {
			
			getConn();
//			sql = "select resno from ac_reservation where resno like ? order by resno ";
			
			sql = "select resno, user_name,  to_char(user_birth, 'YYYY/MM/DD'), user_tel, dep, r.flightno "
			+ "from ac_reservation r join ac_user u on r.user_passNo = u.user_passNo "
			+ "join ac_flight f on r.flightNO= f.flightNO where upper(r.flightno) like '%' || upper (?) ||'%'  "
			+ "UNION all "
			+ "select r.resno, com_name, to_char(com_birth, 'YYYY/MM/DD'), com_tel,  dep, r.flightno "
			+ "from ac_reservation r join ac_company c on r.resNo = c.resno "
			+ "join ac_flight f on r.flightNO= f.flightNO where upper(r.flightno) like '%' || upper (?) ||'%' " ; 
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+flightNoReservationWord+"%");
			pstmt.setString(2, "%"+flightNoReservationWord+"%");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				EmpReservationVO vo = new EmpReservationVO();

				vo.setResno(rs.getString(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_birth(rs.getString(3));
				vo.setUser_tel(rs.getString(4));
				vo.setDep(rs.getString(5));
				vo.setFlightno(rs.getString(6));
				
				lst.add(vo);
			}
			
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		
		return lst;
	}
	
	//출발일 검색
		public List<EmpReservationVO>getdateSearch(String dateWord){
			List<EmpReservationVO> lst = new ArrayList<EmpReservationVO>();
			try {
				
				getConn();
//				sql = "select resno from ac_reservation where resno like ? order by resno ";
				
				sql = "select resno, user_name,  to_char(user_birth, 'YYYY/MM/DD'), user_tel, dep, r.flightno,  to_char(brdDate, 'YYYY/MM/DD') "
				+ "from ac_reservation r join ac_user u on r.user_passNo = u.user_passNo "
				+ "join ac_flight f on r.flightNO= f.flightNO where upper( brdDate) like '%' || upper (?) ||'%'  "
				+ "UNION all "
				+ "select r.resno, com_name, to_char(com_birth, 'YYYY/MM/DD'), com_tel,  dep, r.flightno, to_char(brdDate, 'YYYY/MM/DD') "
				+ "from ac_reservation r join ac_company c on r.resNo = c.resno "
				+ "join ac_flight f on r.flightNO= f.flightNO where upper( brdDate) like '%' || upper (?) ||'%' " ; 
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, "%"+dateWord+"%");
				pstmt.setString(2, "%"+dateWord+"%");
				
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					EmpReservationVO vo = new EmpReservationVO();

					vo.setResno(rs.getString(1));
					vo.setUser_name(rs.getString(2));
					vo.setUser_birth(rs.getString(3));
					vo.setUser_tel(rs.getString(4));
					vo.setDep(rs.getString(5));
					vo.setFlightno(rs.getString(6));
					
					lst.add(vo);
				}
				
				
			}catch(Exception ae) {
				ae.printStackTrace();
			}finally {
				dbClose();
			}
			
			
			
			return lst;
		}

	
	
}
