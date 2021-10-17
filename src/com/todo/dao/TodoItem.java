package com.todo.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoItem {
	
	private String place; //장소필드 추가
	private String power; //난이도 필드 추가(1~5)
	private String category;
    private String title;
    private String desc;
    private String current_date;
    private String due_date;// 
    private int id;
    private int is_completed;

    public TodoItem(String cate, String title, String desc, String due_date){
    	this.category=cate;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.current_date=simpleDateFormat.format(new Date());
    }
    
    public TodoItem(String cate, String title, String desc, String due_date, String current_date,int is_completed, String place, String power){
    	this.category=cate;
        this.title=title;
        this.desc=desc;
        this.due_date=due_date;
        this.current_date=current_date;
        this.is_completed = is_completed;
        this.place = place;
        this.power = power;
        
    }

    public String getPlace() {
    	return place;
    }
    
    public void setPlace(String place) {
    	this.place = place;
    
    }
    
    public String getPower() {
    	return power;
    }
    
    public void setPower(String power) {
    	this.power = power;
    }
    
    public String getCategory() {
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
    public void setId(int id) {
    	this.id = id;
    }
    
   
    
    @Override
    public String toString() {
    	if(is_completed==1) {
		return id +" [" + category +"] "+title+" [V] - "+ desc+" - "+due_date+" - "+ current_date+" - "+place+" - " + power;
    	}
    	else {
    		return id +" [" + category +"] "+title+" - "+ desc+" - "+due_date+" - "+ current_date +" - " +place+" - "+ power;
    	}
    	
    }
    
    public String toSaveString() {
    	return category+"##"+title +"##"+desc+"##"+due_date+"##"+current_date+"##"+is_completed+"##"+place+"##"+power+"\n";
    }

	public int getIs_completed() {
		return is_completed;
	}

	public void setIs_completed(int is_completed) {
		// TODO Auto-generated method stub
		this.is_completed = is_completed;
	}

	
}
