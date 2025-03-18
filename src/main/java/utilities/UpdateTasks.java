package utilities;


import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import datamanagement.TaskManagement;
import datamanagement.Tasks;
import jdbc.DatabaseConnection;

public class UpdateTasks {
	static Connection connection = DatabaseConnection.getDataBase().getConnection();
	static PreparedStatement statement = null;
	static ResultSet resultSet = null;

	static Scanner inputs = new Scanner(System.in);
	public static void updateTask(Tasks taskObj) throws IOException, SQLException {
		if(taskObj != null) {
		byte option = 0;
		String titleString;
		String descrString;
		String priorityString;
		int division;
		LocalDateTime temp;
        String endingDate;
		loop: while(true) {
			System.out.println("What field you want to edit\n1.Title of the task\n2.Description of the task\n3.Priority Level of task\n4.Category of the task\n5.Date of the task");
			try {
				option = inputs.nextByte();
				inputs.nextLine();
			} catch (InputMismatchException e) {
				System.err.println("Invalid Input..! Enter a valid number");
				inputs.nextLine();
				
			}
			switch (option) {
			case 1-> {
				System.out.println("Enter new Title");
				 titleString = inputs.nextLine();
				 if(TaskManagement.updateTitle(taskObj,titleString)) {
					 System.out.println("Task Updated Successfully");
				 }
				 else {
					 System.err.println("Task cannot be updated");
				 }
				 break loop;
			}
			case 2->{
				System.out.println("Enter the Description");
                descrString = inputs.nextLine();
                TaskManagement.updateDescription(taskObj, descrString);
                break loop;

			}
			case 3->{
				 innerloop6: while (true) {
                     option = 0;
                     System.out.println("Enter the Priority Level\n1.High\n2.Medium\n3.Low");
                     try {
                         option = inputs.nextByte();
                     } catch (InputMismatchException e) {
                         System.err.println("Invalid input! Please enter a valid number for priority.");
                         inputs.nextLine(); 
                         continue innerloop6;
                     }
                     switch (option) {
                         case 1 -> {
                        	 priorityString = "High";
                             break innerloop6;
                         }
                         case 2 -> {
                        	 priorityString = "Medium";
                             break innerloop6;
                         }
                         case 3 -> {
                             priorityString = "Low";
                             break innerloop6;
                         }
                         default -> {
                             System.err.println("Enter a valid option");
                             continue innerloop6;
                         }
                         
                         
                     }
                     
                     
                 }
				 String updateQuery = "UPDATE Tasks SET priority = ? WHERE title = ?";
        			statement = connection.prepareStatement(updateQuery);
        		    statement.setString(1, priorityString); 
        			statement.setString(2, taskObj.getTitle());
        			statement.executeUpdate();
				 break loop;
                 
			}
			case 4 -> {
				innerloop5: while (true) {
                    option = 0;
                 List<String> arr = TaskManagement.categoryMenu();
                    try {
                        option = inputs.nextByte();
                    } catch (InputMismatchException e) {
                        System.err.println("Invalid input! Please enter a valid number for category.");
                        inputs.nextLine(); 
                        continue innerloop5;
                    }

                    if (option <= arr.size() || option > 0) {
                        division = option-1;
                        String updateQuery = "UPDATE Tasks SET category = ? WHERE title = ?";
               			statement = connection.prepareStatement(updateQuery);
               		    statement.setInt(1, division); 
               			statement.setString(2, taskObj.getTitle());
               			statement.executeUpdate();
                        
                        break innerloop5;
                    } else {
                        System.err.println("Enter a valid Input");
                        continue innerloop5;
                    }
                }
				break loop;
				
			}
			case 5->{
				dateLoop:while(true) {
               	 System.out.println("Enter the date and time (DD-MM-YYYY hh:mm)");
               	 try {
               		 endingDate = inputs.nextLine();
               		 temp = TaskManagement.dateFormattor(endingDate);
               		 if(!TaskManagement.checkDate(temp)) {
                   		 System.err.println("Please enter a valid date");
                   		 continue dateLoop;
                   	 }
               		 else {
               			String updateQuery = "UPDATE Tasks SET finishtime = ? WHERE title = ?";
               			statement = connection.prepareStatement(updateQuery);
               		    statement.setString(1, taskObj.getEndTime()); 
               			statement.setString(2, taskObj.getTitle());
               			statement.executeUpdate();
               			break dateLoop;
               		 }
						
					} catch (Exception e) {
						System.err.println("Please enter a valid date");
					}
               	 
				}
				break loop;
                     
			}
			default-> {
				System.err.println("Enter a valid Input");
				continue loop;
			}
		}
		}
	}
		else {
			System.err.println("You don't have any task in such title");
		}
	}
	
}
