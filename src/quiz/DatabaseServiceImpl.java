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
		String sql = "select * from travelinto"; 
		ArrayList<Inquiry> list = new ArrayList<>();
		try {
			conn=DriverManager.getConnection(URL, user, pw);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Inquiry into = new Inquiry();  into.setAge(rs.getInt("age"));
				into.setGender(rs.getInt("gender"));  into.setTravel(rs.getInt("travel"));
				list.add(into);
			}
		} catch (Exception e) {	e.printStackTrace(); }
		finally {
			try {
				if(rs != null ) rs.close();
				if(pstmt != null ) pstmt.close();
				if(conn != null ) conn.close();
			} catch (SQLException e) { e.printStackTrace(); }
		}
		return list;
	}
	

	public void insertSQL(Inquiry into) {
		String INSERTSQL = "INSERT INTO travelinto(age, gender,travel) VALUES(?,?,?)";
		try {
			conn = DriverManager.getConnection(URL, user, pw);
			pstmt = conn.prepareStatement(INSERTSQL);
			pstmt.setInt(1, into.getAge());    pstmt.setInt(2, into.getGender());
			pstmt.setInt(3, into.getTravel());  pstmt.executeUpdate();
		} catch (SQLException e) {    e.printStackTrace(); 	}
		finally {
				try {
					if(pstmt != null ) pstmt.close();
					if(conn != null ) conn.close();
				} catch (SQLException e) { e.printStackTrace(); }
		}
	}

}

