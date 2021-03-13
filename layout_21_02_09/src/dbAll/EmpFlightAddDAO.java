package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpFlightAddDAO extends DBConn{

	public EmpFlightAddDAO() {
		
	}
	public int flightInsert(EmpFlightAddVO vo) {
		int result = 0;
		try {
			getConn();
			
			sql = "insert into ac_flight(regno, flightno, dep, des, deptime, destime, fare) values(?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getRegNo());
			pstmt.setString(2, "AC"+vo.getFlightno());
			pstmt.setString(3, vo.getDep());
			pstmt.setString(4, vo.getDes());
			pstmt.setString(5, vo.getDepTime());
			pstmt.setString(6, vo.getDesTime());
			pstmt.setString(7, vo.getFare());
			
			result = pstmt.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	public List<EmpFlightAddVO> getDepDes(String check){//콤보박스에 출발지 넣기
		List<EmpFlightAddVO> lst = new ArrayList<EmpFlightAddVO>();
		try {
			getConn();
			sql = "select distinct dep, crew from ac_flight order by dep";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightAddVO vo = new EmpFlightAddVO(rs.getString(1), rs.getString(2));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightAddVO> getRegNo(){//콤보박스에 등록번호 넣기
		List<EmpFlightAddVO> lst = new ArrayList<EmpFlightAddVO>();
		try {
			getConn();
			sql = "select regNo from ac_aircraft";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightAddVO vo = new EmpFlightAddVO(rs.getString(1));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightAddVO> getFlightNameCheck(String flightName){
		List<EmpFlightAddVO> lst = new ArrayList<EmpFlightAddVO>();
		try {
			getConn();
			sql = "select flightno from ac_flight where flightno=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, flightName);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightAddVO vo = new EmpFlightAddVO(rs.getString(1));
				
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	public List<EmpFlightAddVO> getDepDesCheck(String dep, String des){//출발지, 도착지 중복확인
		List<EmpFlightAddVO> lst = new ArrayList<EmpFlightAddVO>();
		try {
			getConn();
			sql = "select dep, des from ac_flight where dep=? AND des=? order by dep";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dep);
			pstmt.setString(2, des);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpFlightAddVO vo = new EmpFlightAddVO(rs.getString(1), rs.getString(2));
				
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
