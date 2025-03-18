package todoapplication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import datamanagement.TaskManagement;
import jdbc.DatabaseConnection;
import usermanagement.MyColor;
public class Menu {
	
	static Connection connection = DatabaseConnection.getDataBase().getConnection();
	static PreparedStatement statement = null;
	
	
	public static void signUpMenu() {
		System.out.println(MyColor.BG_BLACK + "==============================================" + MyColor.RESET);
		System.out.println(MyColor.BG_CYAN + "           Welcome to To-Do World             " + MyColor.RESET);
		System.out.println(MyColor.BG_BLACK + "==============================================" + MyColor.RESET);
		System.out.println("1. SignUp (New User)");
		System.out.println("2. Login");
		System.out.println("3. Exit App");
		System.out.println(MyColor.BG_BLACK + "==============================================" + MyColor.RESET);
	}
	
	
	public static void loginMenu() {
		System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
        System.out.printf("|%20s%-40s|\n", "", MyColor.YELLOW + "Todo World" + MyColor.RESET); // Centered title
        System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
        System.out.printf("| %-58s |\n", "1. " + MyColor.YELLOW + "Login" + MyColor.RESET);
        System.out.printf("| %-58s |\n", "2. " + MyColor.YELLOW + "Log Out" + MyColor.RESET);
        System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);

	}
	public static void adminMenu() {
		System.out.println("==============================================");
        System.out.println("               To-Do Menu                    ");
        System.out.println("==============================================");
        System.out.println("1. Add User");
        System.out.println("2. Assign Task");
        System.out.println("3. Show Tasks");
        System.out.println("4. Remove Tasks");
        System.out.println("5. Users Info");
        System.out.println("6. Exit Menu");
        System.out.println("==============================================");
	}
	
	public static void viewtaskmenu() {
		 System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
         System.out.printf("|%30s%-20s|\n", "",MyColor.YELLOW + "View Task" + MyColor.RESET); // Centered title
         System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
         System.out.printf("| %-50s |\n", "1. " + MyColor.YELLOW + "Show All" + MyColor.RESET);
         System.out.printf("| %-50s |\n", "2. " + MyColor.YELLOW + "Priority Wise" + MyColor.RESET);
         System.out.printf("| %-50s |\n", "3. " + MyColor.YELLOW + "Category Wise" + MyColor.RESET);
         System.out.printf("| %-50s |\n", "4. " + MyColor.YELLOW + "Back" + MyColor.RESET);
         System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
	}
	
	public static void signUpforUser() {
		  System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
	        System.out.printf("|%21s%-39s|\n", "", MyColor.YELLOW + "Sign Up" + MyColor.RESET); // Centered title
	        System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
	        System.out.printf("| %-58s |\n", "1. " + MyColor.YELLOW + "Admin Signup" + MyColor.RESET);
	        System.out.printf("| %-58s |\n", "2. " + MyColor.YELLOW + "User Signup" + MyColor.RESET);
	        System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
	}
	
	
	public static void todo() {
		 System.out.printf(MyColor.CYAN + "==============================================\n" + MyColor.RESET);
	        System.out.printf("|%15s%-38s|\n", "", MyColor.YELLOW + "To-Do Menu" + MyColor.RESET); // Centered title
	        System.out.printf(MyColor.CYAN + "==============================================\n" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "1. " + MyColor.YELLOW + "Add Task" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "2. " + MyColor.YELLOW + "Assign task" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "3. " + MyColor.YELLOW + "Show Task" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "4. " + MyColor.YELLOW + "Update Task" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "5. " + MyColor.YELLOW + "Remove Task" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "6. " + MyColor.YELLOW + "User Info" + MyColor.RESET);
	        System.out.printf("| %-51s |\n", "7. " + MyColor.YELLOW + "Exit Menu" + MyColor.RESET);
	        System.out.printf(MyColor.CYAN + "==============================================\n" + MyColor.RESET);
	}
	

	
	public static void prioritymenu() {
		 System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
	        System.out.printf("|%21s%-39s|\n", "", MyColor.YELLOW + "Enter Priority Level" + MyColor.RESET); // Centered title
	        System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
	        System.out.printf("| %-58s |\n", "1. " + MyColor.GREEN + "High" + MyColor.RESET);
	        System.out.printf("| %-58s |\n", "2. " + MyColor.YELLOW + "Medium" + MyColor.RESET);
	        System.out.printf("| %-58s |\n", "3. " + MyColor.RED+ "Low" + MyColor.RESET);
	        System.out.printf(MyColor.CYAN + "=====================================================\n" + MyColor.RESET);
		
	}
	
//	public static void categorymenu() {
//		 System.out.printf(MyColor.CYAN + "==============================================\n" + MyColor.RESET);
//	        System.out.printf("|%20s%-22s|\n", "", MyColor.YELLOW + "Enter the category" + MyColor.RESET); // Centered title
//	        System.out.printf(MyColor.CYAN + "==============================================\n" + MyColor.RESET);
////	        for (int j = 0; j < TaskManagement.categoryList.length; j++) {
////	            System.out.printf("| %-40s |\n", (j + 1) + ". " + MyColor.YELLOW + TaskManagement.categoryList[j] + MyColor.RESET);
////	        }
//	        String query = "SELECT * FROM categories order by cID";
//
//		    try{
//		    	statement = connection.prepareStatement(query);
//		         ResultSet resultSet = statement.executeQuery();
//
//		        
//		        while (resultSet.next()) {
//		            int categoryId = resultSet.getInt("cID");
//		            String categoryName = resultSet.getString("cName");
//		            
//		            System.out.printf("  [%d] %-20s\n", categoryId, categoryName);
//		        }
//
//		    } catch (SQLException e) {
//		        System.err.println("Error fetching categories: " + e.getMessage());
//		    }
//	        
//	        System.out.printf(MyColor.CYAN + "==============================================\n" + MyColor.RESET);
//	        System.out.printf(MyColor.GREEN + "Enter your choice: " + MyColor.RESET);
//	}
}
