package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void saveList(TodoList l, String filename) { //list�� ������ ���Ͽ� ����
		/*FileWriter ���*/
		
		try {
			FileWriter fw = new FileWriter(filename);
			//todoItem�� tosaveString �޼ҵ� ����ؼ� ���Ͽ� ����
			for(TodoItem item : l.getList()) {
			fw.write(item.toSaveString());
			}
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void loadList(TodoList l, String filename) throws IOException {
		/*BufferedReader, FileReader, String Tokenizer ���*/
		try {
			BufferedReader bf = new BufferedReader(new FileReader(filename));
			String line;
			StringTokenizer cut;
			while((line= bf.readLine())!=null) {
				cut = new StringTokenizer(line,"##");
				String category = cut.nextToken();
				String title = cut.nextToken();
				String desc = cut.nextToken();
				String due_date = cut.nextToken();
				String current_date = cut.nextToken();  
				
				TodoItem set = new TodoItem(title, desc,category,due_date);
				set.setCategory(category);
				set.setCurrent_date(current_date);
				set.setDesc(desc);
				set.setDue_date(due_date);
				set.setTitle(title);
				l.addItem(set);
			}
			bf.close();
		}catch(FileNotFoundException e ) {
			e.printStackTrace();
		}catch(IOException e ) {
			e.printStackTrace();
		}
		
	}
	
	
public static void createItem(TodoList l) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[할 일 추가]");
		
		System.out.print("카테고리 > ");
		category = sc.next().trim();
		
		System.out.print("제목 > ");
		title = sc.next().trim();
//		if (l.isDuplicate(title)) {
//			System.out.println("- 동일한 제목의 일이 있습니다 !");
//			return;
//		}
		
		System.out.print("내용 > ");
		desc = sd.nextLine().trim();
		
		System.out.print("마감일자 > ");
		due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		if (l.addItem(t) > 0) {
			System.out.println("주가되었습니다.");
		}
		else {
			System.out.println("추가되지 않았습니다.");
		}
	}
	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("[항목 삭제]\n"+ "삭제할 항목의 번호를 입력하시오 > ");
		int index = sc.nextInt();
		if(l.deleteItem(index)>0)
			System.out.println("삭제되었습니다.");
	}
				
			
		
	
		
		
	


public static void updateItem(TodoList l) {
		
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		Scanner sd = new Scanner(System.in);
		
		System.out.println("[할 일 수정]");
		
		System.out.print("수정할 일의 번호 > ");
		int index = sc.nextInt();
				
		System.out.print("새로운 일의 카테고리 > ");
		new_category = sc.next().trim();

		System.out.print("새로운 일의 제목 > ");
		new_title = sc.next().trim();
//		if (l.isDuplicate(new_title)) {
//			System.out.println("- 동일한 제목의 일이 있습니다 !");
//			return;
//		}
		
		System.out.print("새로운 일의 내용 > ");
		new_desc = sd.nextLine().trim();
		
		System.out.print("마감일자 > ");
		new_due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(new_category, new_title, new_desc, new_due_date);
		t.setId(index);
		if (l.updateItem(t) > 0) {
			System.out.println("수정되었습니다.");
		}
		else {
			System.out.println("수정되지 않았습니다.");
		}
	}
	public static void listAll(TodoList l) {
		System.out.printf("[할 일 목록, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 할 일, 총 %d개]\n", l.getCount());
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int isCompleted) {
		System.out.printf("[전체 완료한 일, 총 %d개]\n", l.getCount(isCompleted));
		for (TodoItem item : l.getList(isCompleted)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}

	public static void find(TodoList l,String find) {//��ȣ�ȿ� ���ִ� Ű���尡 ��Ͽ� ���c�Ǿ��֟��� ��� ����ϱ�
	
		int i=1;
		int count=0;
		for (TodoItem item : l.getList()) {
			if((item.toString()).contains(find)) {
			System.out.println(i+". "+item.toString());
			count++;
			}
			i++;
		}
		System.out.println("�� "+count+"���� �׸��� ã�ҽ��ϴ�.");
	
		
	}

	public static void findList(TodoList l, String keyword) {
		// TODO Auto-generated method stub
		int count =0;
		for(TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
			
		}
		System.out.printf("총 %d개의 항목을 찾았습니다. \n", count);
		
	}


	public static void findCateList(TodoList l, String cate) {
		// TODO Auto-generated method stub
		int count =0;
		for(TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count ++;
		}
		System.out.printf("\n 총 %d개의 항목을 찾았습니다. \n", count);
		
	}
}
