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
	
	public static void saveList(TodoList l, String filename) { //list의 내용을 파일에 저장
		/*FileWriter 사용*/
		
		try {
			FileWriter fw = new FileWriter(filename);
			//todoItem의 tosaveString 메소드 사용해서 파일에 저장
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
		/*BufferedReader, FileReader, String Tokenizer 사용*/
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
						
				TodoItem set = new TodoItem(title, desc);
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
	
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		String category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"+
		"You did press 'add' button"+"\n"
				+ "========== Create item Section\n"
				+ "카테고리 > ");
		category = sc.nextLine();
		
		System.out.println("제목 > ");
		title = sc.nextLine();
		
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.println("내용 > ");
		desc = sc.nextLine();
		
		System.out.println("마감일자 > ");
		due_date = sc.nextLine();
		
	
		TodoItem t = new TodoItem(title, desc);
		t.setTitle(title);
		t.setCategory(category);
		t.setDesc(desc);
		t.setDue_date(due_date);
		list.addItem(t);
	}

	public static void deleteItem(TodoList list) {
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("\n"+"[항목삭제]");
		System.out.print("삭제할 항목의 번호를 입력하세요.");
		//삭제할 번호 입력받기-------------------------
		int n=sc.nextInt();
		
		String answer;
		
		for (TodoItem item : list.getList()) {
				
				//삭제할 번호에 해당하는 list 원소값 보여주기----------------------
				if(list.indexOf(item)==n) {
					System.out.println(item);
				System.out.print("위 항목을 삭제하시겠습니까? (y/n) ");
				answer = sc.next();

				if(answer.equals("y")) {
				list.deleteItem(item); //해당 항목 삭제
				System.out.println("삭제되었습니다.");
				break;
				}
				}
			
				}
				
			}
		
	
		
		
	


	public static void updateItem(TodoList l) {
		String new_category;
		Scanner sc = new Scanner(System.in);
		int num;
		System.out.println("\n"+
				"[항목 수정]"+"\n");
		System.out.print("수정할 항목의 번호를 입력하시오 > ");
		num = sc.nextInt();
		if(l.length()>num-1||l.length()==num-1) {
		for (TodoItem item : l.getList()) {
			if(l.indexOf(item)==num-1) {
				System.out.println(item);
				
		//해당 항목의 리스트 원소 보여주기==================================
		System.out.println("새 카테고리 > ");
		new_category = sc.next().trim();
	
		
		System.out.println("새 제목 > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		System.out.println("새 내용 > ");
		String new_description = sc.next().trim();
		
		System.out.println("새 마감일자 > ");
		String new_due_date = sc.next().trim();
		
		
		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title, new_description);
		t.setTitle(new_title);
		t.setCategory(new_category);
		t.setDesc(new_description);
		t.setDue_date(new_due_date);
		l.addItem(t);
		
		System.out.println("수정되었습니다.");
		break;
			}
		}	
	}
	}
//"Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc()+item.getCurrent_date()
	public static void listAll(TodoList l) {
		int n= l.length();
	System.out.println("[전체목록, 총 "+n+"개]" );
	int i=1;
		for (TodoItem item : l.getList()) {
			System.out.println(i+". "+item.toString());
			i++;
		}
	}

	public static void find(TodoList l,String find) {//괄호안에 들어가있는 키워드가 목록에 포홤되어있읈시 모두 출력하기
	
		int i=1;
		int count=0;
		for (TodoItem item : l.getList()) {
			if((item.toString()).contains(find)) {
			System.out.println(i+". "+item.toString());
			count++;
			}
			i++;
		}
		System.out.println("총 "+count+"개의 항목을 찾았습니다.");
	
		
	}
}
