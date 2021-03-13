package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpInfoDAO extends DBConn {

	public EmpInfoDAO() {}
	
	public List<EmpInfoVO> empAllSelect() {
		List<EmpInfoVO> lst = new ArrayList<EmpInfoVO>();
		try{
			getConn();
			sql = "select empNo, emp_name, to_char(emp_birth, 'YYYY-MM-DD'),"
					+ " emp_tel, emp_email, emp_dept from ac_employee order by empNo";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpInfoVO vo = new EmpInfoVO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
				lst.add(vo);
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return lst;
	}
	
	public List<EmpInfoVO> getSearchRecord(String searchWord) {
		List<EmpInfoVO> lst = new ArrayList<EmpInfoVO>();
		try {
			getConn();
			sql = "select empNo, emp_name, to_char(emp_birth, 'YYYY-MM-DD'),"
					+ " emp_tel, emp_email, emp_dept from ac_employee"
					+ " where empNo like ? or emp_name like ? order by empNo";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchWord+"%");
			pstmt.setString(2, "%"+searchWord+"%");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpInfoVO vo = new EmpInfoVO();
				vo.setEmpNo(rs.getString(1));
				vo.setEmp_name(rs.getString(2));
				vo.setEmp_birth(rs.getString(3));
				vo.setEmp_tel(rs.getString(4));
				vo.setEmp_email(rs.getString(5));
				vo.setEmp_dept(rs.getString(6));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}
	
	public List<EmpInfoVO> empAllInfo(String empNo) {
		List<EmpInfoVO> lst = new ArrayList<EmpInfoVO>();
		try {
			getConn();
			sql = "select empNo, emp_name, to_char(emp_birth, 'YYYY-MM-DD'),"
					+ " emp_gender, emp_tel, emp_email, emp_dept, emp_job,"
					+ " emp_passNo, emp_addr from ac_employee where empNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpInfoVO vo = new EmpInfoVO(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
						rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10));
				lst.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}return lst;
	}	
	
	public int empInsert(EmpInfoVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "insert into ac_employee(empNo, emp_name, emp_birth, emp_gender, emp_tel,"
					+ " emp_email, emp_dept, emp_job, emp_passNo, emp_addr)"
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmpNo());
			pstmt.setString(2, vo.getEmp_name());
			pstmt.setString(3, vo.getEmp_birth());
			pstmt.setString(4, vo.getEmp_gender());
			pstmt.setString(5, vo.getEmp_tel());
			pstmt.setString(6, vo.getEmp_email());
			pstmt.setString(7, vo.getEmp_dept());
			pstmt.setString(8, vo.getEmp_job());
			pstmt.setString(9, vo.getEmp_passNo());
			pstmt.setString(10, vo.getEmp_addr());
			
			result = pstmt.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	public int empUpdate(EmpInfoVO vo) {
		int result = 0;
		try {
			getConn();
			sql = "update ac_employee set empNo=?, emp_name=?, emp_birth=?,"
					+ " emp_gender=?, emp_tel=?, emp_email=?, emp_dept=?,"
					+ " emp_job=?, emp_passNo=?, emp_addr=? where empNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmpNo());
			pstmt.setString(2, vo.getEmp_name());
			pstmt.setString(3, vo.getEmp_birth());
			pstmt.setString(4, vo.getEmp_gender());
			pstmt.setString(5, vo.getEmp_tel());
			pstmt.setString(6, vo.getEmp_email());
			pstmt.setString(7, vo.getEmp_dept());
			pstmt.setString(8, vo.getEmp_job());
			pstmt.setString(9, vo.getEmp_passNo());
			pstmt.setString(10, vo.getEmp_addr());
			pstmt.setString(11, vo.getEmpNo());
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();
		}
		return result;
	}
	
	public int empDelete(String empNo) {
		int result = 0;
		try {
			getConn();
			sql = "delete from ac_employee where empNo=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, empNo);
			
			result = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			dbClose();	
		}
		return result;
	}


}


