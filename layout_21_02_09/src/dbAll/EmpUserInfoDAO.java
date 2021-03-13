package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpUserInfoDAO extends DBConn {

	public EmpUserInfoDAO() {}
	
	public List<EmpUserInfoVO> userAllSelect() {
		List<EmpUserInfoVO> lst = new ArrayList<EmpUserInfoVO>();
		try {
			getConn();
			sql = "select userNo, user_name, user_gender, to_char(user_birth, 'YYYY-MM-DD'),"
					+ " user_passNo, user_tel, user_email from ac_user order by userNo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpUserInfoVO vo = new EmpUserInfoVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;		
	}
	
	public List<EmpUserInfoVO> getSearchRecord(String searchWord) {
		List<EmpUserInfoVO> lst = new ArrayList<EmpUserInfoVO>();
		try {
			getConn();
			sql = "select userNo, user_name, user_gender, to_char(user_birth, 'YYYY-MM-DD'),"
					+ " user_passNo, user_tel, user_email from ac_user"
					+ " where userNo like ? or user_name like ? order by userNo";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");
			pstmt.setString(2, "%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpUserInfoVO vo = new EmpUserInfoVO();
				vo.setUserNo(rs.getInt(1));
				vo.setUser_name(rs.getString(2));
				vo.setUser_gender(rs.getString(3));
				vo.setUser_birth(rs.getString(4));
				vo.setUser_passNo(rs.getString(5));
				vo.setUser_tel(rs.getString(6));
				vo.setUser_email(rs.getString(7));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public List<EmpUserInfoVO> userAllInfo(String userNo){
		List<EmpUserInfoVO> lst = new ArrayList<EmpUserInfoVO>();
		try {
			getConn();
			sql = "select userNo, user_name, to_char(user_birth, 'YYYY-MM-DD'),"
					+ " user_ename, user_gender, user_tel, user_passNo,"
					+ " user_email, mileage, grade from ac_user where userNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpUserInfoVO vo = new EmpUserInfoVO(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				lst.add(vo);
				System.out.println("생년월일->"+rs.getString(3));
				System.out.println(vo.getUser_birth());
			}			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public int userUpdate(EmpUserInfoVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update ac_user set userNo=?, user_name=?, user_birth=? ,"
					+ " user_ename=?, user_gender=?, user_tel=?, user_passNo=?,"
					+ " user_email=?, mileage=?, grade=? where userNo=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getUserNo());
			pstmt.setString(2, vo.getUser_name());
			pstmt.setString(3, vo.getUser_birth());
			pstmt.setString(4, vo.getUser_ename());
			pstmt.setString(5, vo.getUser_gender());
			pstmt.setString(6, vo.getUser_tel());
			pstmt.setString(7, vo.getUser_passNo());
			pstmt.setString(8, vo.getUser_email());
			pstmt.setString(9, vo.getMileage());
			pstmt.setString(10, vo.getGrade());
			pstmt.setInt(11, vo.getUserNo());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	public int userDelete(int userNum) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_user where userNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	public int blacklistInsert(String passNo, String answer) {
		int result = 0;
		try {
			getConn();
			sql = "insert into ac_blacklist(user_PassNo, reason) values (?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passNo);
			pstmt.setString(2, answer);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	

	
	
	
	
	
	
	
	
	
	

}

