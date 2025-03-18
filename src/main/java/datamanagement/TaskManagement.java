package datamanagement;




import java.io.IOException;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jdbc.DatabaseConnection;
import login.LoginSystem;
import usermanagement.User;


public class TaskManagement {
	
	private static final Logger logger = LogManager.getLogger(TaskManagement.class);
	static Connection connection = DatabaseConnection.getDataBase().getConnection();
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;
	LocalDateTime dateTime = LocalDateTime.now();

//	public static String[] categoryList =  {"Work & Professional","Personal Development","Home & Household","Health & Fitness","Finance & Budgeting","Social & Relationships","Errands & Appointments","Travel & Leisure","Creative & Hobbies","Maintenance & Miscellaneous"};
	public TaskManagement() {
		
	}

//	private static ObjectMapper objectMapper = new ObjectMapper();
//	private static List<Tasks> taskArray = getTaskArray();
//	private static final File taskFile = new File("tasks.json");

//	public static List<Tasks> getTaskArray() {
//		try {
//			
//			List<Tasks> tasks = objectMapper.readValue(taskFile,
//					objectMapper.getTypeFactory().constructCollectionType(List.class,Tasks.class));
//			return tasks;
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	

//	public static boolean showTasks(User userObj) {
//		byte temp = 0;
//		boolean output = true;
//		List<Tasks> tasksArray = TaskManagement.getTaskArray();
//		for (int i = 0; i < TaskManagement.getTaskArray().size(); i++) {
//
//			if (tasksArray.get(i) != null && tasksArray.get(i).getUserOfTask().equals(userObj.getUserName())) {
//				tasksArray.get(i).toString();
//				temp++;
//			}
//		}
//		if(temp == (byte)0) {
//			System.err.println("You don't have any tasks");
//			output = false;
//		}
//		return output;
//	}
	
	public static boolean showTasks(User userObj) {
	    boolean output = true;
	    byte temp = 0;
	    
	    String query = "SELECT * FROM Tasks WHERE createdBy = ?";
	    
	    try (PreparedStatement statement = connection.prepareStatement(query)) {
	        
	        statement.setInt(1, LoginSystem.getUserId(userObj.getUserName()));
	        
	        ResultSet resultSet = statement.executeQuery();
	        
	        while (resultSet.next()) {
	            Tasks task = new Tasks();
	            task.setTaskId(resultSet.getInt("tId"));
	            task.setTitle(resultSet.getString("title"));
	            task.setDescription(resultSet.getString("description"));
	            task.setCategory(resultSet.getInt("category"));
	            task.setStatus(resultSet.getString("status"));
	            task.setEntryTime(resultSet.getString("entryTime"));
	            task.setEndTime(resultSet.getString("finishTime"));
	            task.setPriority(resultSet.getString("priority"));
	            task.setCreatedBy(resultSet.getInt("createdBy"));
	            task.setAssignedBy(resultSet.getInt("assignedBy"));

	            System.out.println(task.toString());
	            temp++;
	        }

	        if (temp == 0) {
	            System.err.println("You don't have any tasks.");
	            output = false;
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Show task",e);
	        System.out.println("Error fetching tasks from database: " + e.getMessage());
	        output = false;
	    }
	    
	    return output;
	}
	
	public static ArrayList<String> categoryMenu() {
		System.out.println("Enter the Category :");

		System.out.println("\n===============================");
		System.out.println("        Choose a Category       ");
		System.out.println("===============================");

//		for (int j = 0; j < TaskManagement.categoryList.length; j++) {
//			System.out.printf("  [%d] %-20s\n", j + 1, TaskManagement.categoryList[j]);
//		}
		
		
		 String query = "SELECT * FROM categories order by cID";
		 ArrayList<String> arr = new ArrayList<String>();

		    try{
		    	statement = connection.prepareStatement(query);
		       
		         if(statement.execute()) {
		        	  ResultSet resultSet = statement.getResultSet();
				        while (resultSet.next()) {
				            int categoryId = resultSet.getInt("cID");
				            String categoryName = resultSet.getString("cName");
				            arr.add(categoryName);
				            System.out.printf("  [%d] %-20s\n", categoryId, categoryName);
				     }
		         }
		         else {
		        	 System.err.println("No data found");
		         }
		         
		       

		    } catch (SQLException e) {
		    	logger.error("Error on TaskManagement class,category",e);
		         e.printStackTrace();
		    }

		System.out.println("\n===============================");
		System.out.println(" Please enter your choice: ");
		System.out.println("===============================");
		return arr;
	}

	public static Tasks getTask(String titleOfTask) {
	    String query = "SELECT * FROM Tasks WHERE title = ?";
	    try (
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, titleOfTask);
	        ResultSet resultSet = statement.executeQuery();
	        
	        if (resultSet.next()) {
	            Tasks task = new Tasks();
	            task.setTaskId(resultSet.getInt("tId"));
	            task.setTitle(resultSet.getString("title"));
	            task.setDescription(resultSet.getString("description"));
	            task.setCategory(resultSet.getInt("category"));
	            task.setEntryTime(resultSet.getString("entryTime"));
	            task.setEndTime(resultSet.getString("finishTime"));
	            task.setPriority(resultSet.getString("priority"));
	            task.setCreatedBy(resultSet.getInt("createdBy"));
	            task.setAssignedBy(resultSet.getInt("assignedBy"));
	            return task;
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,get task",e);
	        System.err.println("Error retrieving task: " + e.getMessage());
	    }
	    return null;
	}

	public static boolean showIncompleted(User userObj) {
	    boolean output = true;
	    String query = "SELECT * FROM Tasks WHERE createdBy = ? AND status = 'Incompleted'";
	    try (
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, LoginSystem.getUserId(userObj.getUserName()));
	        ResultSet resultSet = statement.executeQuery();
	        
	        int temp = 0;
	        while (resultSet.next()) {
	            Tasks task = new Tasks();
	            task.setTaskId(resultSet.getInt("tId"));
	            task.setTitle(resultSet.getString("title"));
	            task.setDescription(resultSet.getString("description"));
	            task.setCategory(resultSet.getInt("category"));
	            task.setEndTime(resultSet.getString("finishTime"));
	            task.setPriority(resultSet.getString("priority"));
	            task.setCreatedBy(resultSet.getInt("createdBy"));
	            task.setAssignedBy(resultSet.getInt("assignedBy"));
	            task.setStatus(resultSet.getString("status"));
	            System.out.println(task.toString());
	            temp++;
	        }
	        if (temp == 0) {
	            System.err.println("You don't have any tasks to complete.");
	            output = false;
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Show Incompleted",e);
	        System.err.println("Error retrieving tasks: " + e.getMessage());
	    }
	    return output;
	}

	public static void showTaskPriorityLevel(User userObj, String priorityLevel) {
	    String query = "SELECT * FROM Tasks WHERE createdBy = ? AND priority = ?";
	    try (
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, LoginSystem.getUserId(userObj.getUserName()));
	        statement.setString(2, priorityLevel);
	        ResultSet resultSet = statement.executeQuery();
	        
	        int temp = 0;
	        while (resultSet.next()) {
	            Tasks task = new Tasks();
	            task.setTaskId(resultSet.getInt("tId"));
	            task.setTitle(resultSet.getString("title"));
	            task.setDescription(resultSet.getString("description"));
	            task.setCategory(resultSet.getInt("category"));
	            task.setEndTime(resultSet.getString("finishTime"));
	            task.setPriority(resultSet.getString("priority"));
	            task.setCreatedBy(resultSet.getInt("createdBy"));
	            task.setAssignedBy(resultSet.getInt("assignedBy"));
	            task.setStatus(resultSet.getString("status"));
	            System.out.println(task.toString());
	            temp++;
	        }
	        if (temp == 0) {
	            System.err.println("You don't have any tasks in this priority level.");
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Show priority",e);
	        System.err.println("Error retrieving tasks: " + e.getMessage());
	    }
	}
	
	public static String getCategory(int id) throws SQLException {
		String query = "SELECT cName FROM categories WHERE cId = ? limit 1";
		statement.setInt(1,id);
		statement = connection.prepareStatement(query);
        ResultSet rs = statement.executeQuery();
		return rs.getString("cName");
		
	}
	public static void showTaskCategoryVise(User userObj, int categoryInp) {
	    String query = "SELECT * FROM Tasks WHERE assignedBy = ? AND category = ?";
	    try (
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setInt(1, LoginSystem.getUserId(userObj.getUserName()));
	        statement.setInt(2, categoryInp);
	        ResultSet resultSet = statement.executeQuery();
	        
	        int temp = 0;
	        while (resultSet.next()) {
	            Tasks task = new Tasks();
	            task.setTaskId(resultSet.getInt("tId"));
	            task.setTitle(resultSet.getString("title"));
	            task.setDescription(resultSet.getString("description"));
	            task.setCategory(resultSet.getInt("category"));
	            task.setEndTime(resultSet.getString("finishTime"));
	            task.setPriority(resultSet.getString("priority"));
	            task.setCreatedBy(resultSet.getInt("createdBy"));
	            task.setAssignedBy(resultSet.getInt("assignedBy"));
	            task.setStatus(resultSet.getString("status"));
	            System.out.println(task.toString());
	            temp++;
	        }
	        if (temp == 0) {
	            System.err.println("You don't have any tasks in this category.");
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Show category",e);
	        System.err.println("Error retrieving tasks: " + e.getMessage());
	    }
	}

	public static void addTask(Tasks getTasks) throws SQLException {
	    String query = "INSERT INTO Tasks (title, description, category, finishTime, priority, createdBy, assignedBy, status) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	    try(
	        PreparedStatement statement = connection.prepareStatement(query)){
	    	int id = LoginSystem.getUserId(getTasks.getUserOfTask());
	    	if(id < 0) {
	    		throw new SQLException("Invalid Id");
	    	}
	        statement.setString(1,getTasks.getTitle());
	        statement.setString(2, getTasks.getDescription());
	        statement.setInt(3, getTasks.getCategory());
	        statement.setString(4, getTasks.getEndTime());
	        statement.setString(5, getTasks.getPriority());
	        statement.setInt(6, id);
	        statement.setInt(7, id);
	        statement.setString(8, "Incompleted");
	        statement.executeUpdate();
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Add task",e);
	        System.err.println("Error adding task: " + e.getMessage());
	    }
	}
	public static void addTask(Tasks getTasks,String userName) throws SQLException {
	    String query = "INSERT INTO Tasks (title, description, category, finishTime, priority, createdBy, assignedBy,status) VALUES (?, ?, ?, ?, ?, ?, ?,?)";
	    try(
	        PreparedStatement statement = connection.prepareStatement(query)){
	    	int id =  LoginSystem.getUserId(getTasks.getUserOfTask());
	    	int id2 = LoginSystem.getUserId(userName);
	    	if(id < 0) {
	    		throw new SQLException("Invalid Id");
	    	}
	        statement.setString(1,getTasks.getTitle());
	        statement.setString(2, getTasks.getDescription());
	        statement.setInt(3, getTasks.getCategory());
	        statement.setString(4, getTasks.getEndTime());
	        statement.setString(5, getTasks.getPriority());
	        statement.setInt(6, id);
	        statement.setInt(7, id2);
	        statement.setString(8, "Incompleted");
	        statement.executeUpdate();
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Addtask2",e);
	        System.err.println("Error adding task: " + e.getMessage());
	    }
	}

	public static boolean removeTask(String titleOfTask) throws SQLException {
	    String query = "DELETE FROM Tasks WHERE title = ?";
	    try (
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, titleOfTask);
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Remove task",e);
	        System.err.println("Error removing task: " + e.getMessage());
	    }
	    return false;
	}

	public static boolean updateTask(String titleOfTask, String statusOfTask) throws SQLException {
	    String query = "UPDATE Tasks SET status = ? WHERE title = ? AND status = 'Incompleted'";
	    try (
	         PreparedStatement statement = connection.prepareStatement(query)) {
	        statement.setString(1, statusOfTask);
	        statement.setString(2, titleOfTask);
	        
	        int rowsAffected = statement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	    	logger.error("Error on TaskManagement class,Update task",e);
	        System.err.println("Error updating task: " + e.getMessage());
	    }
	    return false;
	}

	public static LocalDateTime dateFormattor(String date) throws IOException {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	LocalDateTime dateTime = null;
		try {
			dateTime =  LocalDateTime.parse(date,dateFormatter);
		} catch (DateTimeParseException e) {
			logger.error("Error on TaskManagement class,date formattar",e);
			e.printStackTrace();
		}
		return dateTime;
	}
	
	public static boolean checkDate(LocalDateTime date) {
		return(date.isAfter(LocalDateTime.now()));
	}
	

	
	public static boolean updateTitle(Tasks taskObj, String titleString) throws IOException, SQLException {
		String updateQuery = "UPDATE Tasks SET title = ? WHERE title = ?";
			statement = connection.prepareStatement(updateQuery);
		    statement.setString(1, titleString); 
			statement.setString(2, taskObj.getTitle());
			
		if(statement.executeUpdate() > 0) {
			taskObj.setTitle(titleString);
			return true;
		}
		return false;
	}
	
	public static boolean updateDescription(Tasks taskObj, String desc) throws IOException, SQLException {
		String updateQuery = "UPDATE Tasks SET desciption = ? WHERE title = ?";
			statement = connection.prepareStatement(updateQuery);
		    statement.setString(1, desc); 
			statement.setString(2, taskObj.getTitle());
			statement.executeUpdate();
			if(statement.executeUpdate() > 0) {
				taskObj.setDescription(desc);
				return true;
			}
			return false;
	}
	
	public static Tasks getTask(String taskTitle, String name) throws SQLException {
		 int id = LoginSystem.getUserId(name);
		 System.out.println(taskTitle);
	     String query = "select * from Tasks where assignedBy = ? and title = ? limit 1";
	     statement = connection.prepareStatement(query);
	     statement.setInt(1, id);
	     statement.setString(2, taskTitle);
	    if(statement.execute()) {
	    	ResultSet resultSet = statement.getResultSet();
		     Tasks task = new Tasks();
		     while (resultSet.next()) {
		            task.setTaskId(resultSet.getInt("tId"));
		            task.setTitle(resultSet.getString("title"));
		            task.setDescription(resultSet.getString("description"));
		            task.setCategory(resultSet.getInt("category"));
		            task.setEndTime(resultSet.getString("finishTime"));
		            task.setEntryTime(resultSet.getString("entryTime"));
		            task.setPriority(resultSet.getString("priority"));
		            task.setCreatedBy(resultSet.getInt("createdBy"));
		            task.setAssignedBy(resultSet.getInt("assignedBy"));
		            task.setStatus(resultSet.getString("status"));
		            System.out.println(task.toString());
		        }
		     return task;
	    }
	     return null;
	     
	}

}
