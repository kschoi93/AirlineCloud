package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpBlacklistDAO extends DBConn {

	public EmpBlacklistDAO() {}
	
	//회원, 비회원 전부 끌고 와야 함
	//비회원 블랙리스트는 회원번호 없으므로 이름 오름차순 정렬 (->JTable에 회원번호 노출할 지..?)
	public List<EmpBlacklistVO> BlacklistAllSelect() {
		List<EmpBlacklistVO> lst = new ArrayList<EmpBlacklistVO>();
		try {
			getConn();
			sql = "select user_name as name, user_ename, u.user_passNo, user_gender, reason, userNo"
					+ " from ac_user u join ac_blacklist b on u.user_passNo=b.user_passNo"
					+ " union all"
					+ " select black_name as name, black_ename, black_passNo,"
					+ " black_gender, reason, userNo from ac_blacklist2 order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpBlacklistVO vo = new EmpBlacklistVO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
				lst.add(vo);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public List<EmpUserInfoVO> blackAllInfo(String passNo){
		List<EmpUserInfoVO> lst = new ArrayList<EmpUserInfoVO>();
		try {
			getConn();
			sql = "select user_name, user_ename, u.user_passNo, user_gender,"
					+ " to_char(user_birth,'YYYY-MM-DD'), reason, userNo,"
					+ " to_char(regDate, 'YYYY-MM-DD') from ac_user u join ac_blacklist b"
					+ " on u.user_passNo=b.user_passNo where u.user_PassNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpUserInfoVO vo = new EmpUserInfoVO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public List<EmpBlacklistVO> blackAllInfo2(String passNo){
		List<EmpBlacklistVO> lst = new ArrayList<EmpBlacklistVO>();
		try {
			getConn();
			sql = "select black_name, black_ename, black_passNo, black_gender,"
					+ " to_char(black_birth, 'YYYY-MM-DD'), reason, userNo,"
					+ " to_char(regDate, 'YYYY-MM-DD') from ac_blacklist2 where black_passNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpBlacklistVO vo = new EmpBlacklistVO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7), rs.getString(8));
				lst.add(vo);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public int blacklistInsert(EmpBlacklistVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "insert into ac_blacklist2(black_name, black_ename, black_passNo,"
					+ " black_gender, black_birth, reason)"
					+ " values(?, ?, ?, ?, to_date(?, 'YYMMDD'), ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getBlack_name());
			pstmt.setString(2, vo.getBlack_ename());
			pstmt.setString(3, vo.getBlack_passNo());
			pstmt.setString(4, vo.getBlack_gender());
			pstmt.setString(5, vo.getBlack_birth());
			pstmt.setString(6, vo.getReason());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	public int blacklistDelete(String passNum) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_blacklist where user_passNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passNum);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}
	
	public int blacklistDelete2(String passNum) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_blacklist2 where black_passNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, passNum);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}

}

