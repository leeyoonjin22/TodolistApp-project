package com.todo.menu;
public class Menu {

    public static void displaymenu()
    { 
        System.out.println();
        System.out.println("ToDoList ------");
        System.out.println("Add a new item ( add )");
        System.out.println("Delete an existing item ( del )");
        System.out.println("Update an item  ( edit )");
        System.out.println("List all items ( ls )");
        System.out.println("List all category ( ls_cate )");
        System.out.println("List all completed item ( ls_comp )");
        System.out.println("List all uncompleted item ( ls_uncomp )");
        System.out.println("List all items by title (ls_name_asc)");
        System.out.println("List all items by title reversely (ls_name_desc)");
        System.out.println("List all items by date (ls_date)");
        System.out.println("List all items by date reversely (ls_date_desc)");
        System.out.println("Find items that include <keyword> in the title, content (find Keyword)");
        System.out.println("Find items that include <keyword> in the category (find_cate Keyword)");
        System.out.println("The end (exit)");
    }
    
    public static void prompt() {
    	System.out.print("\n Command > ");
    }
}
