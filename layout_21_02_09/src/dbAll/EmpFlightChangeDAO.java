package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpFlightChangeDAO extends DBConn{

	public EmpFlightChangeDAO() {
		
	}
	public int cancelUpdate(EmpFlightChangeVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update ac_flight set flight_state='CANCEL' where flightNo=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getFlightno_r());
			
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public int delayUpdate(EmpFlightChangeVO vo) {//지연상태 수정
		int result = 0;
		try {
			getConn();
			sql = "update ac_flight set depTime=?, desTime=?, flight_state='DELAY' where flightNo=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getDepTime());
			pstmt.setString(2, vo.getDesTime());
			pstmt.setString(3, vo.getFlightno_r());
			
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public List<EmpFlightChangeVO> flightAllSelect(){//초기 테이블 세팅
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO>();
		try {
			getConn();
			sql = "select distinct f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f join ac_reservation r "
			+ "on r.flightno=f.flightno where r.brdDate > to_char(sysdate,'YYYYMMDD') order by r.brddate";
			
			pstmt = conn.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getSearchOneRecord(String dateSearchWord, String acSearchWord, String depSearchWord, String desSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-1 1개만 입력
		try {
			getConn();
			sql = "select f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f, ac_reservation r "
					+ "where f.flightno=? OR dep=? OR des=? OR r.brddate > to_date(?, 'YYYYMMDD') order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "AC"+acSearchWord);
			pstmt.setString(2, depSearchWord);
			pstmt.setString(3, desSearchWord);
			pstmt.setString(4, dateSearchWord);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				lst.add(vo);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getDateFlightRecord(String dateSearchWord, String acSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-2 출발날짜&항공편
		try {
			getConn();
			sql = "select DISTINCT f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f, ac_reservation r "
					+ "where r.brddate > to_date(?, 'YYYYMMDD') AND f.flightno=? order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dateSearchWord);
			pstmt.setString(2, "AC"+acSearchWord);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				lst.add(vo);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getDateDepRecord(String dateSearchWord, String depSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-3 출발날짜&출발지
		try {
			getConn();
			sql = "select DISTINCT f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f, ac_reservation r "
					+ "where r.brddate > to_date(?, 'YYYYMMDD') AND dep=? order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dateSearchWord);
			pstmt.setString(2, depSearchWord);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				lst.add(vo);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getDateDesRecord(String dateSearchWord, String desSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-4 출발날짜&도착지
		try {
			getConn();
			sql = "select DISTINCT f.flightno, r.brdDate, dep, depTime, des, desTime, flight_state from ac_flight f, ac_reservation r "
					+ "where r.brddate > to_date(?, 'YYYYMMDD') AND des=? order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dateSearchWord);
			pstmt.setString(2, desSearchWord);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				lst.add(vo);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getFlightDepRecord(String acSearchWord, String depSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-5 항공편&출발지
		try {
			getConn();
			sql = "select DISTINCT f.flightno 항공편면, r.brdDate 출발날짜, dep 출발지, depTime 출발시간, des 도착지, desTime 도착시간, flight_state 상태 from ac_flight f, ac_reservation r "
					+ "where f.flightno=? AND dep=? order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "AC"+acSearchWord);
			pstmt.setString(2, depSearchWord);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				lst.add(vo);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getFlightDesRecord(String acSearchWord, String desSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-6 항공편&도착지
		try {
			getConn();
			sql = "select DISTINCT f.flightno 항공편면, r.brdDate 출발날짜, dep 출발지, depTime 출발시간, des 도착지, desTime 도착시간, flight_state 상태 from ac_flight f, ac_reservation r "
					+ "where f.flightno=? AND des=? order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "AC"+acSearchWord);
			pstmt.setString(2, desSearchWord);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
				lst.add(vo);				
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightChangeVO> getDepDesRecord(String depSearchWord, String desSearchWord){
		List<EmpFlightChangeVO> lst = new ArrayList<EmpFlightChangeVO> ();
		//2-7출발지&도착지
		try {
			getConn();
			sql = "select DISTINCT f.flightno 항공편면, r.brdDate 출발날짜, dep 출발지, depTime 출발시간, des 도착지, desTime 도착시간, flight_state 상태 from ac_flight f, ac_reservation r "
					+ "where dep=? AND des=? order by r.brddate";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, depSearchWord);
			pstmt.setString(2, desSearchWord);
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightChangeVO vo = new EmpFlightChangeVO(rs.getString(1),
						rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				
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
