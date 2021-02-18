package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpUserInfoDAO extends DBConn {

	public EmpUserInfoDAO() {}
	
	public List<EmpUserInfoVO> userAllSelect() {
		List<EmpUserInfoVO> lst = new ArrayList<EmpUserInfoVO>();
		try {
			getConn();
			sql = "select userNo, user_name, user_gender, user_passNo, user_tel, user_email"
					+ " from ac_user order by userNo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpUserInfoVO vo = new EmpUserInfoVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
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
			sql = "update ac_user set user_name=?, user_birth=?, user_ename=?, user_gender=?,"
					+ " user_tel=?, user_passNo=?, user_exDate=?, user_email=?, mileage=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName_kor());
			pstmt.setString(2, vo.getUser_birth());
			pstmt.setString(3, vo.getName_eng());
			pstmt.setString(4, vo.getUser_gender());
			pstmt.setString(5, vo.getUser_tel());
			pstmt.setString(6, vo.getPassportNo());
			pstmt.setString(7, vo.getExpireDate());
			pstmt.setString(8, vo.getUser_email());
			pstmt.setString(9, vo.getMileage());
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	public int userDelete(int userNo) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_user where userNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			result = pstmt.executeUpdate();		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	public List<EmpUserInfoVO> getSearchRecord(String searchWord) {
		List<EmpUserInfoVO> lst = new ArrayList<EmpUserInfoVO>();
		try {
			getConn();
			sql = "select userNo, name_kor, user_gender, passportNo, user_tel, user_email"
					+ " from ac_user where user_kor like ? or user_tel like ? order by userNo";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");
			pstmt.setString(2, "010-%"+searchWord+"%");
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpUserInfoVO vo = new EmpUserInfoVO();
				vo.setUserNo(rs.getInt(1));
				vo.setName_kor(rs.getString(2));
				vo.setUser_gender(rs.getString(3));
				vo.setPassportNo(rs.getString(4));
				vo.setUser_tel(rs.getString(5));
				vo.setUser_email(rs.getString(6));
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}

}

