package quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class DatabaseServiceImpl{
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private Connection conn;    private PreparedStatement pstmt;
	private ResultSet rs;    String user,pw;
	static { 
		try {  Class.forName(DRIVER);  } 
		catch(Exception e) { e.printStackTrace(); } 
	} 
	public DatabaseServiceImpl() { 	user = "dbwls";   pw = "dbwls9874";	} 

	public ArrayList<Inquiry> selectSQL() {
		String sql = "select * from travelinto";  //select로 모든정보를 가져온다
		ArrayList<Inquiry> list = new ArrayList<>();
		try {
			conn=DriverManager.getConnection(URL, user, pw);  //연결하고
			pstmt = conn.prepareStatement(sql);  //연결된 객체 이용해서 명령어 전송할 수 있는 전송객체 만든다
			rs = pstmt.executeQuery();  //그런다음 결과값 rs 얻어온다
			while(rs.next()) {
				Inquiry into = new Inquiry();  into.setAge(rs.getInt("age"));
				into.setGender(rs.getInt("gender"));  into.setTravel(rs.getInt("travel"));
				list.add(into); //결과값 얻어온걸 list에 저장해라
			}
		} catch (Exception e) {	e.printStackTrace(); }
		finally {
			try {
				if(rs != null ) rs.close(); //닫아줄땐 거꾸로 닫히게 한다
				if(pstmt != null ) pstmt.close();
				if(conn != null ) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		return list;
	}
	

	public void insertSQL(Inquiry into) {
		String INSERTSQL = "INSERT INTO travelinto(age, gender,travel) VALUES(?,?,?)";  //ResultServiceImpl에서 넘어와서 insert로 데이터 저장
		try {
			conn = DriverManager.getConnection(URL, user, pw);
			pstmt = conn.prepareStatement(INSERTSQL);
			pstmt.setInt(1, into.getAge());    pstmt.setInt(2, into.getGender()); //?에 숫자1,2,3을 넣고
			pstmt.setInt(3, into.getTravel());  pstmt.executeUpdate();       //pstmt.executeUpdate(): 실행해라
		} catch (SQLException e) {    e.printStackTrace(); 	}
		finally {
				try {
					if(pstmt != null ) pstmt.close();
					if(conn != null ) conn.close();
				} catch (SQLException e) { e.printStackTrace(); }
		}
	}

}

