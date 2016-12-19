package pagedemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import pagedemo.pojo.User;

public class DataUtils {

	 @Test
	 public void test() {
		 //getPage(21,5);
		 getUsers(1);
	 }

	private static final int PAGE_SIZE = 5;
	private static String URL = "jdbc:mysql://localhost:3306/pagedemo";
	private static String NAME = "root";
	private static String PASSWORD = "root";

	public static List<User> getUsers(int page) {

		String sql = "select * from users limit ?,?";
		Connection conn = null;
		//Statement stat = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		List<User> users = new ArrayList<User>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*PAGE_SIZE);
			ps.setInt(2, PAGE_SIZE);
			result = ps.executeQuery();

			while (result.next()) {
				User user = new User();
				user.setId(result.getInt(1));
				user.setName(result.getString(2));
				user.setSex(result.getString(3));
				user.setAge(result.getInt(4));
				user.setBirthday(result.getDate(5));
				user.setOccupation(result.getString(6));
				user.setAddress(result.getString(7));
				user.setEmail(result.getString(8));
				user.setCellPhone(result.getString(9));
				users.add(user);
				System.out.println("id: " + result.getInt(1));
				System.out.println("name: " + result.getString(2));
				System.out.println("sex: " + result.getString(3));
				System.out.println("age: " + result.getInt(4));
				System.out.println("birthday: " + result.getDate(5));
				System.out.println("occupation: " + result.getString(6));
				System.out.println("address: " + result.getString(7));
				System.out.println("email: " + result.getString(8));
				System.out.println("cellphone: " + result.getString(9));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static int getPageSize()
	{
		return PAGE_SIZE;
	}

	public static int getUsersCount() {
		String sql = "select count(*) from users";
		Connection conn = null;
		Statement stat = null;
		ResultSet result = null;
		int count = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, NAME, PASSWORD);
			stat = conn.createStatement();
			result = stat.executeQuery(sql);

			while (result.next()) {
               count = result.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(count);
		return count;
	}
	
	public static int getPage(int count, int pageSize) {
		int page = 1;
		if (count < pageSize) {
			page = 1;
		} else if (count % pageSize == 0) {
			page = count / pageSize;
		} else if (count % pageSize != 0) {
			page = count / pageSize + 1;
		}
		System.out.println(page);
		return page;
	}
}
