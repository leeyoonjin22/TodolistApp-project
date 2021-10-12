package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String due_date;//���� �Է��ؾ��ϴ� ��¥ yyyy/mm/dd
    private int id;
    //private int is_completed;

    public TodoItem(String cate, String title, String desc, String due_date){
    	this.category=cate;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.current_date=simpleDateFormat.format(new Date());
    }
    
    public TodoItem(String cate, String title, String desc, String due_date, String current_date){
    	this.category=cate;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=current_date;
//        this.is_completed = is_completed;
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
    
    public int getId() {
    	return id;
    }
    
   
    
    @Override
    public String toString() {
		return  " [" + category +"] "+title+" - "+ desc+" - "+due_date+" - "+ current_date;
    	
    }
    
    public String toSaveString() {
    	return category+"##"+title +"##"+desc+"##"+due_date+"##"+current_date+"\n";
    }

	public String getCategory() {
		
		return category;
	}

	public void setId(int index) {
		// TODO Auto-generated method stub
		this.id = index;
	}
}
