package dbAll;

import java.util.ArrayList;
import java.util.List;

public class EmpSalesDAO extends DBConn {

	public EmpSalesDAO() {
		
	}
	
	public List<EmpSalesVO>EmpSalesAllselect(){
		List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
		try {
			getConn();
//			sql = "select to_char(brdDate, 'YYYY/MM/DD'), f.flightno, dep, des , TO_CHAR(fare*0.4, 'L9,999,999'), TO_CHAR(fare, 'L9,999,999')  ,TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익 "
//					+ "from ac_flight f join ac_reservation r on f.flightno=r.flightno";
			
			sql = "select to_char(brdDate, 'YYYY/MM/DD'), f.flightno, dep, des , TO_CHAR(fare*0.4), TO_CHAR(fare)  ,TO_CHAR(fare-(fare*0.4)) 영업이익 "
					+ "from ac_flight f join ac_reservation r on f.flightno=r.flightno";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpSalesVO vo = new EmpSalesVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				
				lst.add(vo);
			}
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}
		
		
		return lst;
	}
	
	// 전체 검색 조건?
	public  List<EmpSalesVO> getdateSearch(String dateWord){
		List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
		try{
			getConn();
//			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
//					+ "TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_user u on u.user_passNO=r.user_passNO where brdDate between TO_DATE('21/01/01','YY-MM-DD') and TO_DATE('21/01/31','YY-MM-DD') "
//					+ "union all "
//					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
//					+ "TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_company c on c.resNo=r.resNo where brdDate between TO_DATE('21/01/01','YY-MM-DD') and TO_DATE('21/01/31','YY-MM-DD')";
			
			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
					+ "TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_user u on u.user_passNO=r.user_passNO where brdDate between TO_DATE('21/01/01','YY-MM-DD') and TO_DATE('21/01/31','YY-MM-DD') "
					+ "union all "
					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
					+ "TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_company c on c.resNo=r.resNo where brdDate between TO_DATE('21/01/01','YY-MM-DD') and TO_DATE('21/01/31','YY-MM-DD')";
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, "%"+dateWord+"%"); //  시작일
			pstmt.setString(2, "%"+dateWord+"%"); //  종료일
			pstmt.setString(3, "%"+dateWord+"%"); //
			pstmt.setString(4, "%"+dateWord+"%"); //
			pstmt.setString(5, "%"+dateWord+"%"); //
			pstmt.setString(6, "%"+dateWord+"%"); // 
			pstmt.setString(7, "%"+dateWord+"%"); //
			
			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpSalesVO vo = new EmpSalesVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				
				lst.add(vo);
			}
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}	
		return lst;
	}
	//날짜
//	public  List<EmpSalesVO> getonlydateSearch(String startDate, String EndDate){
	public  List<EmpSalesVO> getonlydateSearch(String startDate, String endDate){
		List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
		try{
			getConn();
//			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
//					+ "TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_user u on u.user_passNO=r.user_passNO where brdDate between TO_DATE(?,'YY-MM-DD') and TO_DATE(?,'YY-MM-DD') "
//					+ "union all "
//					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
//					+ "TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_company c on c.resNo=r.resNo where brdDate between TO_DATE(?,'YY-MM-DD') and TO_DATE(?,'YY-MM-DD')";
			
			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
					+ "TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_user u on u.user_passNO=r.user_passNO where brdDate between TO_DATE(?,'YY-MM-DD') and TO_DATE(?,'YY-MM-DD') "
					+ "union all "
					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, "
					+ "TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_company c on c.resNo=r.resNo where brdDate between TO_DATE(?,'YY-MM-DD') and TO_DATE(?,'YY-MM-DD')";
			
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, startDate); //  유저 시작일
			pstmt.setString(2, endDate);//  유저 종료일
			pstmt.setString(3, startDate); //  동행자 시작일
			pstmt.setString(4, endDate); //  동헹자 종료일

			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpSalesVO vo = new EmpSalesVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				
				lst.add(vo);
			}
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}	
		return lst;
	}
	
	//출도착지
	public  List<EmpSalesVO> getonlydepSearch(String startDep, String endDes){
		List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
		try{
			getConn();
//			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_user u on u.user_passNO=r.user_passNO where dep like ? and des like ? "
//					+ "union all "
//					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_company c on c.resNo=r.resNo where dep like ? and des like ? ";
		
			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_user u on u.user_passNO=r.user_passNO where dep like ? and des like ? "
					+ "union all "
					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_company c on c.resNo=r.resNo where dep like ? and des like ? ";

			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, startDep); // 출발지
			pstmt.setString(2, endDes);//  도착지
			pstmt.setString(3, startDep); //  출발지
			pstmt.setString(4, endDes); //  도착지

			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpSalesVO vo = new EmpSalesVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				
				lst.add(vo);
			}
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}	
		return lst;
	}
	


	public List<EmpSalesVO> getAllDateDepSearch(String startDate, String endDate, String startDep, String endDes) {
	
		List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
		try{
			getConn();
//			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_user u on u.user_passNO=r.user_passNO where brdDate between TO_DATE(?,'YY-MM-DD') and  TO_DATE(?,'YY-MM-DD') and dep like ? and des like ? "
//					+ "union all "
//					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4, 'L9,999,999') 비용, TO_CHAR(fare, 'L9,999,999') 매출, TO_CHAR(fare-(fare*0.4), 'L9,999,999') 영업이익, "
//					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
//					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
//					+ "join ac_company c on c.resNo=r.resNo where brdDate between TO_DATE(?,'YY-MM-DD') and  TO_DATE(?,'YY-MM-DD') and dep like ? and des like ? ";
			
			sql = "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(user_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "user_gender 성별 , user_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_user u on u.user_passNO=r.user_passNO where brdDate between TO_DATE(?,'YY-MM-DD') and  TO_DATE(?,'YY-MM-DD') and dep like ? and des like ? "
					+ "union all "
					+ "select to_char(brdDate, 'YYYY/MM/DD') 출발일, f.flightno 항공편 , dep 출발지 , des 도착지, TO_CHAR(fare*0.4) 비용, TO_CHAR(fare) 매출, TO_CHAR(fare-(fare*0.4)) 영업이익, "
					+ "trunc(months_between(trunc(sysdate), to_date(to_char(com_birth, 'YYYYMMDD'))) /12+1) 나이, "
					+ "com_gender 성별 , com_birth 생년월일 from ac_reservation r join ac_flight f on f.flightno=r.flightno "
					+ "join ac_company c on c.resNo=r.resNo where brdDate between TO_DATE(?,'YY-MM-DD') and  TO_DATE(?,'YY-MM-DD') and dep like ? and des like ? ";
			
			
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, startDate); // 출발지
			pstmt.setString(2, endDate);//  도착지
			pstmt.setString(3, startDep); //  출발지
			pstmt.setString(4, endDes); //  도착지
			pstmt.setString(5, startDate); // 출발지
			pstmt.setString(6, endDate);//  도착지
			pstmt.setString(7, startDep); //  출발지
			pstmt.setString(8, endDes); //  도착지

			
			
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				EmpSalesVO vo = new EmpSalesVO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7));
				
				lst.add(vo);
			}
			
		}catch(Exception ae) {
			ae.printStackTrace();
		}finally {
			dbClose();
		}	
		return lst;
	}
	//1.날짜 + 연령대별 이용률
		public List<EmpSalesVO> getAgeChartbyDate(String startDate, String endDate, String startAge, String endAge) {
			List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
			try {
				getConn();
				sql = "select ageGroup, count(ageGroup)"
						+ " from (select months_between(trunc(sysdate, 'year'), trunc(user_birth, 'year'))/12+1 age, ageGroup"
						+ " from ac_user u, ac_reservation r, ac_ageGroup a"
						+ " where u.user_passNo = r.user_passNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " and agegroup between ? and ?"
						+ " and months_between(trunc(sysdate, 'year'), trunc(user_birth, 'year'))/12+1 between startAge and endAge"
						+ " union all"
						+ " select months_between(trunc(sysdate, 'year'), trunc(com_birth, 'year'))/12+1 age, ageGroup"
						+ " from ac_company c, ac_reservation r, ac_ageGroup a"
						+ " where c.resNo = r.resNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " and agegroup between ? and ?"
						+ " and months_between(trunc(sysdate, 'year'), trunc(com_birth, 'year'))/12+1 between startAge and endAge)"
						+ " group by ageGroup order by ageGroup";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, startAge);
				pstmt.setString(4, endAge);
				pstmt.setString(5, startDate);
				pstmt.setString(6, endDate);
				pstmt.setString(7, startAge);
				pstmt.setString(8, endAge);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpSalesVO vo = new EmpSalesVO(rs.getString(1), rs.getString(2));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}return lst;
		}
		
		//2.구간 + 연령대별 이용률
		public List<EmpSalesVO> getAgeChartbyRoute(String dep, String des, String startAge, String endAge) {
			List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
			try {
				getConn();
				sql = "select ageGroup, count(ageGroup)"
						+ " from (select months_between(trunc(sysdate, 'year'), trunc(user_birth, 'year'))/12+1 age, ageGroup"
						+ " from ac_user u, ac_reservation r, ac_ageGroup a, ac_flight f"
						+ " where u.user_passNo = r.user_passNo"
						+ " and r.flightNo = f.flightNo"
						+ " and dep=? and des=?"
						+ " and agegroup between ? and ?"
						+ " and months_between(trunc(sysdate, 'year'), trunc(user_birth, 'year'))/12+1 between startAge and endAge"
						+ " union all"
						+ " select months_between(trunc(sysdate, 'year'), trunc(com_birth, 'year'))/12+1 age, ageGroup"
						+ " from ac_company c, ac_reservation r, ac_ageGroup a, ac_flight f"
						+ " where c.resNo = r.resNo"
						+ " and r.flightNo = f.flightNo"
						+ " and dep=? and des=?"
						+ " and agegroup between ? and ?"
						+ " and months_between(trunc(sysdate, 'year'), trunc(com_birth, 'year'))/12+1 between startAge and endAge)"
						+ " group by ageGroup order by ageGroup";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, dep);
				pstmt.setString(2, des);
				pstmt.setString(3, startAge);
				pstmt.setString(4, endAge);
				pstmt.setString(5, dep);
				pstmt.setString(6, des);
				pstmt.setString(7, startAge);
				pstmt.setString(8, endAge);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpSalesVO vo = new EmpSalesVO(rs.getString(1), rs.getString(2));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println("DAO에서의 lst.size()->"+lst.size());
				dbClose();
			}return lst;
		}
			
		//3.날짜 + 구간 + 연령대 이용률
		public List<EmpSalesVO> getAgeChartbyAll(String startDate, String endDate, String dep, String des, String startAge, String endAge) {
			List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
			try {
				getConn();
				sql = "select ageGroup, count(ageGroup)"
						+ " from (select months_between(trunc(sysdate, 'year'), trunc(user_birth, 'year'))/12+1 age, ageGroup"
						+ " from ac_user u, ac_reservation r, ac_ageGroup a, ac_flight f"
						+ " where u.user_passNo = r.user_passNo"
						+ " and r.flightNo = f.flightNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " and dep=? and des=?"
						+ " and agegroup between ? and ?"
						+ " and months_between(trunc(sysdate, 'year'), trunc(user_birth, 'year'))/12+1 between startAge and endAge"
						+ " union all"
						+ " select months_between(trunc(sysdate, 'year'), trunc(com_birth, 'year'))/12+1 age, ageGroup"
						+ " from ac_company c, ac_reservation r, ac_ageGroup a, ac_flight f"
						+ " where c.resNo = r.resNo"
						+ " and r.flightNo = f.flightNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " and dep=? and des=?"
						+ " and agegroup between ? and ?"
						+ " and months_between(trunc(sysdate, 'year'), trunc(com_birth, 'year'))/12+1 between startAge and endAge)"
						+ " group by ageGroup order by ageGroup";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, dep);
				pstmt.setString(4, des);
				pstmt.setString(5, startAge);
				pstmt.setString(6, endAge);
				pstmt.setString(7, startDate);
				pstmt.setString(8, endDate);
				pstmt.setString(9, dep);
				pstmt.setString(10, des);
				pstmt.setString(11, startAge);
				pstmt.setString(12, endAge);

				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpSalesVO vo = new EmpSalesVO(rs.getString(1), rs.getString(2));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				System.out.println("DAO에서의 lst.size()->"+lst.size());
				dbClose();
			}return lst;
		}
		
		//4.날짜 + 성별 이용률
		public List<EmpSalesVO> getGenderChartbyDate(String startDate, String endDate) {
			List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
			try {
				getConn();
				sql = "select user_gender, count(user_gender)"
						+ " from (select user_name, user_gender"
						+ " from ac_user u, ac_reservation r"
						+ " where u.user_passNo=r.user_passNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " union all"
						+ " select com_name, com_gender"
						+ " from ac_company c, ac_reservation r"
						+ " where c.resNo=r.resNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd'))"
						+ " group by user_gender order by user_gender";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, startDate);
				pstmt.setString(4, endDate);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpSalesVO vo = new EmpSalesVO(rs.getString(1), rs.getString(2));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}return lst;
		}
			
		//5.구간 + 성별 이용률
		public List<EmpSalesVO> getGenderChartbyRoute(String startDate, String endDate) {
			List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
			try {
				getConn();
				sql = "select user_gender, count(user_gender)"
						+ " from (select user_name, user_gender"
						+ " from ac_user u, ac_reservation r, ac_flight f"
						+ " where u.user_passNo=r.user_passNo"
						+ " and r.flightNo = f.flightNo"
						+ " and dep=? and des=?"
						+ " union all"
						+ " select com_name, com_gender"
						+ " from ac_company c, ac_reservation r, ac_flight f"
						+ " where c.resNo=r.resNo"
						+ " and r.flightNo = f.flightNo"
						+ " and dep=? and des=?)"
						+ " group by user_gender order by user_gender";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, startDate);
				pstmt.setString(4, endDate);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpSalesVO vo = new EmpSalesVO(rs.getString(1), rs.getString(2));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}return lst;
		}
		
		//5.날짜 + 구간 + 성별 이용률
		public List<EmpSalesVO> getGenderChartAll(String startDate, String endDate, String dep, String des) {
			List<EmpSalesVO> lst = new ArrayList<EmpSalesVO>();
			try {
				getConn();
				sql = "select user_gender, count(user_gender)"
						+ " from (select user_name, user_gender"
						+ " from ac_user u, ac_reservation r, ac_flight f"
						+ " where u.user_passNo=r.user_passNo"
						+ " and r.flightNo = f.flightNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " and dep=? and des=?"
						+ " union all"
						+ " select com_name, com_gender"
						+ " from ac_company c, ac_reservation r, ac_flight f"
						+ " where c.resNo=r.resNo"
						+ " and r.flightNo = f.flightNo"
						+ " and brdDate between to_date(?,'yymmdd') and to_date(?,'yymmdd')"
						+ " and dep=? and des=?)"
						+ " group by user_gender order by user_gender";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, startDate);
				pstmt.setString(2, endDate);
				pstmt.setString(3, dep);
				pstmt.setString(4, des);
				pstmt.setString(5, startDate);
				pstmt.setString(6, endDate);
				pstmt.setString(7, dep);
				pstmt.setString(8, des);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					EmpSalesVO vo = new EmpSalesVO(rs.getString(1), rs.getString(2));
					
					lst.add(vo);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				dbClose();
			}return lst;
		}
		
		
		
		
		
		
		

	
	
}
