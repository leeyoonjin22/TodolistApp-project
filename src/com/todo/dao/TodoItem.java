package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String due_date;//직접 입력해야하는 날짜 yyyy/mm/dd


    public TodoItem(String title, String desc){
        
    	this.category = category; //카테고리 필드 생성자
    	this.title=title;
        this.desc=desc;
        
        SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd kk:mm:ss");
        this.current_date=f.format(new Date());
        this.due_date= due_date;
    }

    public String category() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    
    
    public String getTitle() {
        return title;
    }
    

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrent_date() {
        return current_date;
    }

    public void setCurrent_date(String current_date) {
        this.current_date = current_date;
    }
    
    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
    
    @Override
    public String toString() {
		return  " [" + category +"] "+title+" - "+ desc+" - "+due_date+" - "+ current_date;
    	
    }
    
    public String toSaveString() {
    	return category+"##"+title +"##"+desc+"##"+due_date+"##"+current_date+"\n";
    }
}
