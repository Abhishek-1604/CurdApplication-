package CrudApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DbOperations {
	private static String host = "jdbc:mysql://localhost:3306/studentsdata";
	private static String userName = "root";
	private static String userPassword = "Abhishek@16";

	private static Connection toCreateConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection(host, userName, userPassword);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void toAddStudentData(StudentClass obj) {
		try {
			Connection con = toCreateConnection();
			String query = "Insert into student (studName, studEmailID, studGrade, studNumber) values (?,?,?,?)";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, obj.getStudName());
			stmt.setString(2, obj.getStudEmailID());
			stmt.setString(3, Character.toString(obj.getStudGrade()));
			stmt.setLong(4, obj.getStudNumber());

			stmt.executeUpdate();
			con.close();
			System.out.println("Thank you- Student creation successfully\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static HashMap<Integer, StudentClass> toDisplayStudent() {
		try {
			Connection con = toCreateConnection();
//			String query = "Select * from student";
			PreparedStatement stmt = con.prepareStatement("Select * from student");

			ResultSet rowData = stmt.executeQuery();
			HashMap<Integer, StudentClass> studentData = new HashMap<>();
			while (rowData.next()) {
				StudentClass obj = new StudentClass();
				obj.setStudId(rowData.getInt(1));
				obj.setStudName(rowData.getString(2));
				obj.setStudEmailID(rowData.getString(3));
				obj.setStudGrade(rowData.getString(4).charAt(0));
				obj.setStudNumber(rowData.getLong(5));
				studentData.put(obj.getStudId(), obj);
			}
			con.close();
			return studentData;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void toRemoveAStudentData(int id) {

		try {
			if (studExists(id)) {
				Connection con = toCreateConnection();
				PreparedStatement stmt = con.prepareStatement("Delete from student where studId = ?");
				stmt.setInt(1, id);
				stmt.executeUpdate();
				con.close();
				System.out.println("Student is Removed Successfully ");
			} else {
				System.out.println("Student does not exist with this id !!!!!!!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean studExists(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from student where studId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			return rowData.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static void toUpdateStudName(String name, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update student set studName = ? where studId = ?");
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateStudEmail(String email, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update student set studEmailID = ? where studId = ?");
			stmt.setString(1, email);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateStudGrade(char ch, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update student set studGrade = ? where studId = ?");
			stmt.setString(1, Character.toString(ch));
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void toUpdateStudNumber(long number, int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Update student set studNumber = ? where studId = ?");
			stmt.setLong(1, number);
			stmt.setInt(2, id);
			stmt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static StudentClass toGetStudData(int id) {
		try {
			Connection con = toCreateConnection();
			PreparedStatement stmt = con.prepareStatement("Select * from student where studId = ?");
			stmt.setInt(1, id);
			ResultSet rowData = stmt.executeQuery();
			rowData.next();
			StudentClass obj = new StudentClass();
			obj.setStudId(rowData.getInt(1));
			obj.setStudName(rowData.getString(2));
			obj.setStudEmailID(rowData.getString(3));
			obj.setStudGrade(rowData.getString(4).charAt(0));
			obj.setStudNumber(rowData.getLong(5));
			con.close();
			return obj;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
