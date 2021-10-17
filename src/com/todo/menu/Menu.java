package com.todo.menu;
public class Menu {

    public static void displaymenu()
    { 
        System.out.println();
        System.out.println("------ ToDoList ------");
        System.out.println("Add a new item ( add )추가 ");
        System.out.println("Delete an existing item ( del )삭제 ");
        System.out.println("Update an item  ( edit 수정 ");
        System.out.println("List all items ( ls )목록 ");
        System.out.println("List all category ( ls_cate )카테고리 목록 ");
        System.out.println("List all completed item ( ls_comp )완료된 것 목록 ");
        System.out.println("List all items by title (ls_name_asc)타이틀 오름차순 ");
        System.out.println("List all items by title reversely (ls_name_desc)타이틀 역순 ");
        System.out.println("List all items by date (ls_date)마감일 날짜빠른순 ");
        System.out.println("make complete mode the item (comp)완료체크하기 ");
        System.out.println("make complete del mode the item (del_comp)완료체크 해제하기 " );
        System.out.println("List all items by date reversely (ls_date_desc)마감일 날짜느린");
        System.out.println("Find items that include <keyword> in the title, content (find Keyword)키워드찾기_내용 ");
        System.out.println("Find items that include <keyword> in the category (find_cate Keyword)키워드찾기_카테고리 ");
        System.out.println("Make multi Delete the items (mulDel) 한꺼번에 삭제  ");
        System.out.println("Make multi complete check items (mulcom) 한꺼번에 완료체크 ");
        System.out.println("Make multi complete uncheck items (mulcomDel) 한꺼번에 완료체크해제 ");
        System.out.println("Load Json (com_json) JSon불러오기  ");
        System.out.println("Save File to Json (go_json) Json형식으로 저장하기 " );

        System.out.println("The end (exit)");
    }
    
    public static void prompt() {
    	System.out.print("\n (도움말_help를 입력하세요) \n Command > ");
    }
}
