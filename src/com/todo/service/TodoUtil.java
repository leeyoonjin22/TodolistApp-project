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
				+ "ī�װ� > ");
		category = sc.nextLine();
		
		System.out.println("���� > ");
		title = sc.nextLine();
		
		if (list.isDuplicate(title)) {
			System.out.printf("title can't be duplicate");
			return;
		}
		
		System.out.println("���� > ");
		desc = sc.nextLine();
		
		System.out.println("�������� > ");
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
		
		
		System.out.println("\n"+"[�׸����]");
		System.out.print("������ �׸��� ��ȣ�� �Է��ϼ���.");
		//������ ��ȣ �Է¹ޱ�-------------------------
		int n=sc.nextInt();
		
		String answer;
		
		for (TodoItem item : list.getList()) {
				
				//������ ��ȣ�� �ش��ϴ� list ���Ұ� �����ֱ�----------------------
				if(list.indexOf(item)==n) {
					System.out.println(item);
				System.out.print("�� �׸��� �����Ͻðڽ��ϱ�? (y/n) ");
				answer = sc.next();

				if(answer.equals("y")) {
				list.deleteItem(item); //�ش� �׸� ����
				System.out.println("�����Ǿ����ϴ�.");
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
				"[�׸� ����]"+"\n");
		System.out.print("������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		num = sc.nextInt();
		if(l.length()>num-1||l.length()==num-1) {
		for (TodoItem item : l.getList()) {
			if(l.indexOf(item)==num-1) {
				System.out.println(item);
				
		//�ش� �׸��� ����Ʈ ���� �����ֱ�==================================
		System.out.println("�� ī�װ� > ");
		new_category = sc.next().trim();
	
		
		System.out.println("�� ���� > ");
		String new_title = sc.next().trim();
		
		if (l.isDuplicate(new_title)) {
			System.out.println("title can't be duplicate");
			return;
		}
		System.out.println("�� ���� > ");
		String new_description = sc.next().trim();
		
		System.out.println("�� �������� > ");
		String new_due_date = sc.next().trim();
		
		
		l.deleteItem(item);
		TodoItem t = new TodoItem(new_title, new_description);
		t.setTitle(new_title);
		t.setCategory(new_category);
		t.setDesc(new_description);
		t.setDue_date(new_due_date);
		l.addItem(t);
		
		System.out.println("�����Ǿ����ϴ�.");
		break;
			}
		}	
	}
	}
//"Item Title: " + item.getTitle() + "  Item Description:  " + item.getDesc()+item.getCurrent_date()
	public static void listAll(TodoList l) {
		int n= l.length();
	System.out.println("[��ü���, �� "+n+"��]" );
	int i=1;
		for (TodoItem item : l.getList()) {
			System.out.println(i+". "+item.toString());
			i++;
		}
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
}
