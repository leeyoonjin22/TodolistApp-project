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
		
		boolean quit = false;
		String keyword;
		int index;
		Menu.displaymenu();
		
		do {
			
			Menu.prompt();
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
			case "comp" :
				index = sc.nextInt();
				TodoUtil.completeItem(l,index);
				break;
				
			case "del_comp":
				index = sc.nextInt();
				TodoUtil.completeDelItem(l,index);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "ls_comp":
				TodoUtil.listAll(l,1);
				break;
				
			case "ls_uncomp":
				TodoUtil.listAll(l,0);
				break;
				

			case "ls_name_asc":
				TodoUtil.listAll(l,"title",1);
				System.out.println("제목순으로 정렬완료 ");
				break;

			case "ls_name_desc":
				TodoUtil.listAll(l,"title",0);
				System.out.println("제목역순 으로 정렬완료 ");
				break;
				
			case "ls_date":
				TodoUtil.listAll(l,"due_date",1);
				System.out.println("날짜순으로 정렬완료 ");
				break;
				
			case "ls_date_desc":
				TodoUtil.listAll(l,"due_date",0);
				System.out.println("날짜역순으로 정렬완료 ");
				break;
			
			case "find":
				keyword = sc.next().trim();
				TodoUtil.findList(l,keyword);
				break;
				
			case "find_cate":
				keyword = sc.next();
				TodoUtil.findCateList(l,keyword);
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
		//TodoUtil.saveList(l, "todolist.txt");
	}
}
