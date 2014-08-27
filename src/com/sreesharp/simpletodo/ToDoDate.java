package com.sreesharp.simpletodo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ToDoDate implements Serializable {
	
	private int year;
	private int month;
	private int day;
	
	/*
	 * Date string in "mm/dd/yyyy" format
	 */
	public ToDoDate(String strDate)
	{
		String[] parts = strDate.split("/");
		month = Integer.parseInt(parts[0])	;
		day = Integer.parseInt(parts[1]);
		year = Integer.parseInt(parts[2]);
	}
	
	public ToDoDate (int y, int m, int d)
	{
		year = y;
		month = m;
		day = d;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return month + "/" + day + "/" + year;
	}
	
	public int getYear()
	{
		return year;
	}
	public int getMonth()
	{
		return month;
	}
	public int getDay()
	{
		return day;
	}
	

}
