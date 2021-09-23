package com.todo;

import java.io.IOException;
import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() throws IOException {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
		do {
			
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

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
				isList = true;
				break;

			case "ls_name_desc":
				System.out.println("\n"+"You did press 'ls_name_desc' button");
				l.sortByName();
				l.reverseList();
				System.out.println("���� �������� �����Ͽ����ϴ�.");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("��¥������ �����Ͽ����ϴ�.");
				isList = true;
				break;

			case "help":
				Menu.displaymenu();
				break;
			
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("��Ȯ�� ��ɾ �Է��ϼ���. (���� - help)");
				break;
			}
			
			if(isList) TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
