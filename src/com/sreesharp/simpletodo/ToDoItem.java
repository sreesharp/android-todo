package com.sreesharp.simpletodo;

import java.io.Serializable;


@SuppressWarnings("serial")
public class ToDoItem implements Serializable  {
	
	private ToDoDate dueDate;
	private String title;
	private Priority priority;
	
	public ToDoItem(String title, ToDoDate date, Priority priority )
	{
		this.title = title;
		this.dueDate = date;
		this.priority = priority;
	}
	
	public ToDoDate getDueDate()
	{
		return dueDate;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public Priority getPriority()
	{
		return priority;
	}
		
}


