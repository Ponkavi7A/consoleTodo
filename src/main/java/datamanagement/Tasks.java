package datamanagement;

import datamanagement.TaskManagement;


import java.awt.Color;
import java.io.File;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import usermanagement.MyColor;
import usermanagement.User;


@SuppressWarnings("unused")
public class Tasks{
	private int taskId;
	private String tId;
	private String title;
	private String description;
	private String priority;
	private int category;
	private String status;
	private String userOfTask;
	private String sTime;
	private String entryTime;
	private String endTime;
	private int createdBy;
	private int assignedBy;
	
	
	public Tasks() {
		
	}
	
	public Tasks(String title,String description, String priority,int category,String userName,String endTime) {
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.category = category;
		this.userOfTask = userName;
		this.status = "Incompleted";
		this.endTime = endTime;
		
	}

    
	
	
	private static  String getTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm"));
	}
	
	public String toString() {
		System.out.println("+----------------------+----------------------------------------------------------+");
		System.out.println("|    Task Details      |                         Information                      |");
		System.out.println("+----------------------+----------------------------------------------------------+");
		System.out.printf("| %-20s | %-50s       |\n", "Title", title);
		System.out.printf("| %-20s | %-50s       |\n", "Task Category", category);
		System.out.printf("| %-20s | %-50s       |\n", "Task", description);
		System.out.printf("| %-20s | %-50s       |\n", "Priority Level", priority);
		
		if(status.equals("Incompleted")) {
		System.out.printf("| %-20s | %-59s       |\n", "Task Status", MyColor.RED+status+MyColor.RESET );
		}
		else {
		System.out.printf("| %-20s | %-59s       |\n", "Task Status", MyColor.GREEN+status+MyColor.RESET );	
		}
		System.out.printf("| %-20s | %-50s       |\n", "Start Date", entryTime);
		System.out.printf("| %-20s | %-50s       |\n", "End Date", endTime);
		System.out.println("+----------------------+----------------------------------------------------------+");

	        return ""; 
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int i) {
		this.category = i;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public void setUserOfTask(String userName) {
		this.userOfTask = userName;
	}
	public String getUserOfTask() {
		return userOfTask;
	}
	
	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String string) {
		this.entryTime = string;
	}
	
	public void setEndTime(String date) {
		this.endTime = date;
	}
	public String getEndTime() {
		return this.endTime;
	}
	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public String getsTime() {
		return sTime;
	}

	public void setsTime(String sTime) {
		this.sTime = sTime;
	}
	
	public String gettId() {
		return tId;
	}

	public void settId(String tId) {
		this.tId = tId;
	}
	
	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public int getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(int assignedBy) {
		this.assignedBy = assignedBy;
	}

 
	

}
