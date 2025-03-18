package todoapplication;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
//import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

import java.sql.SQLException;
import java.time.LocalDateTime;

import datamanagement.TaskManagement;
import datamanagement.Tasks;
import login.LoginSystem;
import usermanagement.MyColor;
import usermanagement.User;
import utilities.UpdateTasks;

//import usermanagement.Person;

public class Main {
	private static final Logger logger =  LogManager.getLogger(Main.class);
	public static void main(String[] args) throws IOException, SQLException {
		System.out.println("Welcome to the Todo..! Time for emphasize your task..!");
		
		boolean loggedIn = false;
		Scanner inputs = new Scanner(System.in);
		byte option = 0;

		outerLoop: while (true) {
			User userObject;
			String nameOfUser;
			String email;
			String passKey;
			int role;

			if (!loggedIn) {
				//Menu with sign up if user not sign up
				
				Menu.signUpMenu();

				try {
					option = inputs.nextByte();
				} catch (InputMismatchException e) {
					logger.error("Error on Main class,signUp",e);
					System.err.println("Invalid input! Please enter a valid number.");
					inputs.nextLine();
					continue;
				}
			} else {
//              Menu for logged in user
				Menu.loginMenu();
				try {
					option = inputs.nextByte();
				} catch (InputMismatchException e) {
					logger.error("Error on Main class,login",e);
					System.err.println("Invalid input! Please enter a valid number.");
					inputs.nextLine();
					continue;
				}

				if (option == (byte) 1) {
					option = 2;
				} else if (option == (byte) 2) {
					option = 3;
				} else {
					System.out.println("Enter a valid option");
				}
			}
			switch (option) {
			case 1 -> {

//				inputs.nextLine();
//				byte temp = 0;
//				loginLoop: while (true) {
//					// Choosing the sign Up
//					Menu.signUpforUser();
//					try {
//						temp = inputs.nextByte();
//					} catch (InputMismatchException e) {
//						// TODO: handle exception
//						System.err.println("Enter a valid input");
//					}
//					switch (temp) {
//					case 1 -> {
//						role = 2;
//						break loginLoop;
//					}
//					case 2 -> {
//						role = 1;
//						break loginLoop;
//					}
//					default -> {
//						System.err.println("Enter a valid input");
//						continue loginLoop;
//					}
//					}
//				}
				inputs.nextLine();
				System.out.println("Enter your name :");
				String name = inputs.nextLine();
				innerLoop2: while (true) {
					System.out.println("Enter your User name :");
					nameOfUser = inputs.nextLine();
					if (LoginSystem.userCheck(nameOfUser)) {
						break innerLoop2;
					} else {
						System.err.println("User name already existed. Enter another user name.");
					}
				}

				checkForPassWord: while (true) {
					System.out.println("Enter Password :");
					passKey = inputs.nextLine();
					if (LoginSystem.passWordCheck(passKey)) {
						break checkForPassWord;
					} else {
						System.err.println("Enter a valid Password format");
					}
				}

				innerLoop3: while (true) {
					System.out.println("Enter your Email ID :");
					email = inputs.nextLine();
					if (LoginSystem.emailCheck(email)) {
						break innerLoop3;
					}
				}

				try {
					User userObj = new User(name, nameOfUser, passKey, email, 1);
					userObject = LoginSystem.signUp(userObj);
				} catch (SQLException e) {
					logger.error("Error on Main class,signUp",e);
					e.printStackTrace();
				}
				System.out.println(MyColor.GREEN + "You are signed in" + MyColor.RESET);
				loggedIn = true;
				break;
			}

			case 2 -> {
				inputs.nextLine();
				System.out.println("Enter your Email ID or User Name :");
				email = inputs.nextLine();
				System.out.println("Enter PassWord");
				passKey = inputs.nextLine();
				userObject = LoginSystem.login(email, passKey);
				if (userObject == null) {
					System.err.println("Invalid Credentials");
				} else {
					System.out.println(MyColor.GREEN + "You are Logged In" + MyColor.RESET);
					if (userObject.getRole() ==  1) {
						option = 0;
						innerLoop4: while (true) {
							int division;
							String priorityLevel;
							// To do menu
							Menu.todo();
							
							
							try {
								option = inputs.nextByte();
							} catch (InputMismatchException e) {
								logger.error("Error on Main class,IO",e);
								System.err.println("Invalid input! Please enter a valid number.");
								inputs.nextLine();
								continue;
							}
							switch (option) {
							case 1 -> {
								inputs.nextLine();
								System.out.println("How many task you want to add");

								byte count = 0;
								try {
									count = inputs.nextByte();
								} catch (InputMismatchException e) {
									logger.error("Error on Main class,IO",e);
									System.err.println("Invalid input! Please enter a valid number for task count.");
									inputs.nextLine();
									continue innerLoop4;
								}

								for (int i = 0; i < count; i++) {
									inputs.nextLine();
									System.out.println("Enter the Task title : ");
									String titleOfTask = inputs.nextLine();
									System.out.println("Enter the Description");
									String descriptions = inputs.nextLine();

									innerloop5: while (true) {
										option = 0;
									List<String> arr = TaskManagement.categoryMenu();
										try {
											option = inputs.nextByte();
										} catch (InputMismatchException e) {
											System.err.println(
													"Invalid input! Please enter a valid number for category.");
											inputs.nextLine();
											continue innerloop5;
										}

										if (option <= arr.size() || option > 0) {
											division = option;
											break innerloop5;
										} else {
											System.err.println("Enter a valid Input");
											continue innerloop5;
										}
										
										
									}

									innerloop6: while (true) {
										option = 0;
//										System.out.println("Enter the Priority Level\n1.High\n2.Medium\n3.Low");
// priority menu
										Menu.prioritymenu();
										try {
											option = inputs.nextByte();
										} catch (InputMismatchException e) {
											logger.error("Error on Main class,IO",e);
											System.err.println(
													"Invalid input! Please enter a valid number for priority.");
											inputs.nextLine();
											continue innerloop6;
										}
										switch (option) {
										case 1 -> {
											priorityLevel = "High";
											break innerloop6;
										}
										case 2 -> {
											priorityLevel = "Medium";
											break innerloop6;
										}
										case 3 -> {
											priorityLevel = "Low";
											break innerloop6;
										}
										default -> {
											System.err.println("Enter a valid option");
											continue innerloop6;
										}
										}
									}
									inputs.nextLine();
									LocalDateTime temp;
									String endingDate;
									dateLoop: while (true) {
										System.out.println("Enter the date and time (yyyy-MM-dd HH:mm)");
										try {
											endingDate = inputs.nextLine();
											temp = TaskManagement.dateFormattor(endingDate);
											if (!TaskManagement.checkDate(temp)) {
												System.err.println("Please enter the date coming soon...");
												continue dateLoop;
											} else {
												break dateLoop;
											}

										} catch (Exception e) {
											logger.error("Error on Main class,Date",e);
											System.err.println("Please enter a valid date");
										}

									}

									Tasks tasksObj = new Tasks(titleOfTask, descriptions, priorityLevel, division,
											userObject.getUserName(), endingDate);
									TaskManagement.addTask(tasksObj);
								}
								System.out.println(MyColor.GREEN + "Task added Successfully..!" + MyColor.RESET);
								continue innerLoop4;
							}
							case 2->{
								inputs.nextLine();
								System.out.println("Enter the user name for assign task:");
								String name = inputs.nextLine();
								System.out.println("How many task you want to add");

								byte count = 0;
								try {
									count = inputs.nextByte();
								} catch (InputMismatchException e) {
									logger.error("Error on Main class,IO",e);
									System.err.println("Invalid input! Please enter a valid number for task count.");
									inputs.nextLine();
									continue innerLoop4;
								}

								for (int i = 0; i < count; i++) {
									inputs.nextLine();
									System.out.println("Enter the Task title : ");
									String titleOfTask = inputs.nextLine();
									System.out.println("Enter the Description");
									String descriptions = inputs.nextLine();

									division = 1;

									innerloop6: while (true) {
										option = 0;
//										System.out.println("Enter the Priority Level\n1.High\n2.Medium\n3.Low");
// priority menu
										Menu.prioritymenu();
										try {
											option = inputs.nextByte();
										} catch (InputMismatchException e) {
											logger.error("Error on Main class,IO",e);
											System.err.println(
													"Invalid input! Please enter a valid number for priority.");
											inputs.nextLine();
											continue innerloop6;
										}
										switch (option) {
										case 1 -> {
											priorityLevel = "High";
											break innerloop6;
										}
										case 2 -> {
											priorityLevel = "Medium";
											break innerloop6;
										}
										case 3 -> {
											priorityLevel = "Low";
											break innerloop6;
										}
										default -> {
											System.err.println("Enter a valid option");
											continue innerloop6;
										}
										}
									}
									inputs.nextLine();
									LocalDateTime temp;
									String endingDate;
									dateLoop: while (true) {
										System.out.println("Enter the date and time (yyyy-MM-dd HH:mm)");
										try {
											endingDate = inputs.nextLine();
											temp = TaskManagement.dateFormattor(endingDate);
											if (!TaskManagement.checkDate(temp)) {
												System.err.println("Please enter the date coming soon...");
												continue dateLoop;
											} else {
												break dateLoop;
											}

										} catch (Exception e) {
											logger.error("Error on Main class,Date ",e);
											System.err.println("Please enter a valid date");
										}

									}

									Tasks tasksObj = new Tasks(titleOfTask, descriptions, priorityLevel, division,
											userObject.getUserName(), endingDate);
									TaskManagement.addTask(tasksObj,name);
								}
								System.out.println(MyColor.GREEN + "Task assigned Successfully..!" + MyColor.RESET);
								continue innerLoop4;
							}

							case 3 -> {
								option = 0;
								innerLoop8: while (true) {
//                                        System.out.println("1.Show all\n2.Priority vise\n3.Category vise\n4.Back");
									// vie task choosing
									Menu.viewtaskmenu();
									try {
										option = inputs.nextByte();
									} catch (InputMismatchException e) {
										logger.error("Error on Main class,IO",e);
										System.err.println("Invalid input! Please enter a valid number.");
										inputs.nextLine();
										continue innerLoop8;
									}

									switch (option) {
									case 1 -> {
										TaskManagement.showTasks(userObject);
										continue innerLoop4;
									}
									case 2 -> {
										innerLoop9: while (true) {
											option = 0;
											Menu.prioritymenu();
											try {
												option = inputs.nextByte();
											} catch (InputMismatchException e) {
												logger.error("Error on Main class,IO",e);
												System.err.println("Invalid input! Please enter a valid number.");
												inputs.nextLine();
												continue innerLoop9;
											}

											switch (option) {
											case 1 -> {
												TaskManagement.showTaskPriorityLevel(userObject, "High");
												continue innerLoop8;
											}
											case 2 -> {
												TaskManagement.showTaskPriorityLevel(userObject, "Medium");
												continue innerLoop8;
											}
											case 3 -> {
												TaskManagement.showTaskPriorityLevel(userObject, "Low");
												continue innerLoop8;
											}
											case 4 -> {
												continue innerLoop8;
											}
											default -> {
												System.err.println("Enter a valid input");
											}
											}
										}
									}
									case 3 -> {
										option = 0;
										innerLoop10: while (true) {
											List<String> arr = TaskManagement.categoryMenu();
											try {
												option = inputs.nextByte();
											} catch (InputMismatchException e) {
												logger.error("Error on Main class,IO",e);
												System.err.println("Invalid input! Please enter a valid number.");
												inputs.nextLine();
												continue innerLoop10;
											}
											
											if (option <= arr.size()|| option > 0) {
												TaskManagement.showTaskCategoryVise(userObject,
														(option - 1));
												break innerLoop10;
											} else {
												System.err.println("Enter a valid output");
												continue innerLoop10;
											}
										}
									}
									case 4 -> {
										continue innerLoop4;
									}
									default -> {
										System.err.println("Enter a valid input");
									}
									}
								}
							}

							case 4 -> {
								innerLoop7: while (true) {
									if (TaskManagement.showIncompleted(userObject)) {
										inputs.nextLine();
										System.out.println("Enter the task title ");
										String titleforUpdate = inputs.nextLine();
										System.out.println("1.Mark as Completed\n2.Edit Task");
										try {
											option = inputs.nextByte();
										} catch (InputMismatchException e) {
											logger.error("Error on Main class,IO",e);
											System.err.println("Invalid input! Please enter a valid number.");
											inputs.nextLine();
											continue innerLoop7;
										}

										switch (option) {
										case 1 -> {
											if (TaskManagement.updateTask(titleforUpdate, "Completed")) {
												System.out.println(
														MyColor.GREEN + "Task Updated successfully" + MyColor.RESET);
											} else {
												System.err.println("Task cannot be updated");
											}
											continue innerLoop4;
										}
										case 2 -> {
											Tasks tasks = TaskManagement.getTask(titleforUpdate,userObject.getUserName());
											if(tasks != null) {
											UpdateTasks.updateTask(tasks);
											break innerLoop7;
											}
											else {
												System.err.println("No task Available");
											}

										}
										default -> {
											System.err.println("Enter a valid input");
										}
										}
									} else {
										continue innerLoop4;
									}
								}
							}

							case 5 -> {
								inputs.nextLine();
								if (TaskManagement.showIncompleted(userObject)) {
									System.out.println("Enter the task title of the task you want to remove :");
									String title = inputs.nextLine();
									if (TaskManagement.removeTask(title)) {
										System.out.println(MyColor.GREEN + "Task removed successfully" + MyColor.RESET);
									} else {
										System.err.println("You don't have any tasks in such title");
									}
								}
								continue innerLoop4;

							}
							case 6 -> {
								userObject.userInfo();
								continue innerLoop4;
							}
							case 7 -> {
								loggedIn = true;
								continue outerLoop;
							}

							default -> {
								System.err.println("Enter a valid option");
								continue innerLoop4;
							}
							}
						}
					} 

				}
			}

			case 3 -> {
				inputs.close();
				System.out.println(MyColor.GREEN + "Exiting....." + MyColor.RESET);
				break outerLoop;
			}
			default -> {
				System.err.println("Unexpected Value");
				logger.info("Unexpected value");
			}
			}
		}
	}

}
