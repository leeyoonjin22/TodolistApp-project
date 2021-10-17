package com.todo.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.DbConnect;
import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> l;
	Connection conn;
	

	public TodoList() {
		this.conn = DbConnect.getConnection();
		this.l = new ArrayList<TodoItem>();
	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, desc, category, current_date, due_date, place, power)"
				+ " values (?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count = 0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date()); 
			pstmt.setString(5, t.getDue_date());
			pstmt.setString(6, t.getPlace());
			pstmt.setString(7, t.getPower());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		String sql = "delete from list where id=?;";
		
		int count =0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, index);
			count = pstmt.executeUpdate();
			pstmt.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, desc=?, category=?, current_date=?, due_date=?,is_completed=?, place=?, power=? "
				+ "where id =?;";
		PreparedStatement pstmt;
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, t.getTitle());
			pstmt.setString(2, t.getDesc());
			pstmt.setString(3, t.getCategory());
			pstmt.setString(4, t.getCurrent_date());
			pstmt.setString(5, t.getDue_date());
			pstmt.setInt(6, t.getIs_completed());
			pstmt.setString(7, t.getPlace());
			pstmt.setString(8, t.getPower());
			pstmt.setInt(9, t.getId());
			count = pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public void mulcompleteItem(TodoList l,int count) {
		//여러개 한꺼번에 완료체크하기
	Scanner sc = new Scanner(System.in);
		int arr[] = new int[count];
		System.out.println("\n 완료체크할 아이디 입력 : ");
		for(int i=0; i<count; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int j=0; j<count; j++) {
			for(TodoItem t : l.getList()) {
				if(t.getId()==arr[j]) {
					t.setIs_completed(1);
					break;
				}
			}
		}
		
		System.out.println("모두 완료 체크하였습니다.");
		
	}
	
	public int completeItem(int index) {
		String sql = "update list set is_completed =1"
				+" where id = ?;";
		PreparedStatement pstmt;
		int count =0;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, index);
		count = pstmt.executeUpdate();
		pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;		
	}
	
	public void mulcompleteDelItem(TodoList l, int count) {
		Scanner sc = new Scanner(System.in);
		int arr[] = new int[count];
		System.out.println("\n 완료체크 해제 할 아이디 입력 : ");
		for(int i=0; i<count; i++) {
			arr[i] = sc.nextInt();
		}
		
		for(int j=0; j<count; j++) {
			for(TodoItem t : l.getList()) {
				if(t.getId()==arr[j]) {
					t.setIs_completed(0);
					break;
				}
			}
		}
		
		System.out.println("모두 완료 체크하였습니다.");
	}
	
	public int completeDelItem(int index) {
		String sql = "update list set is_completed =0"
				+" where id = ?;";
		PreparedStatement pstmt;
		int count =0;
		
		try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, index);
		count = pstmt.executeUpdate();
		pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;		
	}
	
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("Id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String place = rs.getString("place");
				String power = rs.getString("power");
				
				
				TodoItem t = new TodoItem(category, title, desc, due_date, current_date,is_completed,place,power);
				
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<TodoItem> getList(String keyword) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
		try {
			String sql = "SELECT * FROM list WHERE title like ? OR desc like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setString(2, keyword);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int id = rs.getInt("Id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String place = rs.getString("place");
				String power = rs.getString("power");
				TodoItem t = new TodoItem(category, title, desc, due_date, current_date,is_completed, place, power);
				t.setId(id);
				list.add(t);
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getList(int is_completed) {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list WHERE is_completed = " + is_completed;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				String place = rs.getString("place");
				String power = rs.getString("power");
				TodoItem t = new TodoItem(category, title, desc, due_date, current_date,is_completed, place, power);
				t.setId(id);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<String> getCategories() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String category = rs.getString("category");
				list.add(category);
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ArrayList<TodoItem> getListCategory(String keyword) {
		// TODO Auto-generated method stub
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String place = rs.getString("place");
				String power = rs.getString("power");
				TodoItem t = new TodoItem(category, title, description, due_date, current_date,is_completed, place, power);
				t.setId(id);
				list.add(t);
			}
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public ArrayList<TodoItem> getOrderedList(String orderby, int ordering) {
		ArrayList<TodoItem> list = new ArrayList<>();
		String sql = "SELECT * FROM list ORDER BY "+ orderby;
		if (ordering == 0) {
			sql += " desc";
		}
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql+";");
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String desc = rs.getString("desc");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int is_completed = rs.getInt("is_completed");
				String place = rs.getString("place");
				String power = rs.getString("power");
				TodoItem t = new TodoItem(category, title, desc, due_date, current_date,is_completed, place, power);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getCount() {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getCount(int getIs_completed) {
		Statement stmt;
		int count = 0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list WHERE is_completed = " + getIs_completed + ";";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	
	public Boolean isDuplicate(String title) {
		for (TodoItem item : getList()) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}


	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list (title,desc,category,current_date,due_date,place,power)"
					+ "values(?,?,?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine())!=null) {
				StringTokenizer st = new StringTokenizer(line,"##");
				String category = st.nextToken();
				String title = st.nextToken();
				String desc = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				String place = st.nextToken();
				String power=st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, desc);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				pstmt.setString(6, place);
				pstmt.setString(7, power);
				int count = pstmt.executeUpdate();
				if(count>0) records++;
				pstmt.close();
			}
			System.out.println(records + " records read!");
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
				
		}
	}

	
	}
