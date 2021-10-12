package com.todo;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException {
	
//		Scanner sc = new Scanner(System.in);
//		TodoList l = new TodoList();
//		l.importData("todolist.txt");
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		
		boolean quit = false;
		String key;
		int index;
		//TodoUtil.loadList(l, "todolist.txt");
		do {
			
			Menu.prompt();
			String choice = sc.next();
			
			switch (choice) {
			
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l,keyword);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l,cate);
				break;

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				System.out.println("\n"+"You did press 'ls' button");
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc":
				System.out.println("\n"+"You did press 'ls_name_asc' button");
				l.sortByName();
				System.out.println("��������� �����Ͽ����ϴ�.");
				
				break;

			case "ls_name_desc":
				System.out.println("\n"+"You did press 'ls_name_desc' button");
				l.sortByName();
				l.reverseList();
				System.out.println("���� �������� �����Ͽ����ϴ�.");
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("��¥������ �����Ͽ����ϴ�.");
				break;
			
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;

			case "help":
				Menu.displaymenu();
				break;
			
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정확한 명령어를 입력해주세요. 명령어 - help)");
				break;
			}
			
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
