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
			sql = "select userNo, b_name_kor, b_name_eng, b_passportNo,"
					+ " b_gender, b_reason from ac_blacklist order by b_user_kor";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpBlacklistVO vo = new EmpBlacklistVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public int BlacklistDelete(String b_passportNo) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_blacklist where b_passportNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b_passportNo);
			
			result = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return result;
	}

}

