//package login;
//
//import java.io.File;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import jdbc.DatabaseConnection;
//import usermanagement.MyColor;
//import usermanagement.User;
//import usermanagement.UserRole;
//
//public class LoginSystem{
//	static User currentUser;
//
////	static Connection connection = DatabaseConnection.getDataBase().getConnection();
//
//
//    private static ObjectMapper objectMapper = new ObjectMapper();
// 
// 
//	public static List<User> getUser() {
//		try {
//			List<User> users = objectMapper.readValue(new File("users.json"),
//					objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
//			return users;
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return null;
//	}
//
//	
//	public static boolean passWordCheck(String passKey) {
//		    passKey = passKey.trim();
//		    String passwordRegex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$";
//		    Pattern pattern = Pattern.compile(passwordRegex);
//		    Matcher matcher = pattern.matcher(passKey);
//		    return matcher.matches();
//	}
//	public static boolean userCheck(String userNameInp) {
//		return getUserObj(userNameInp) == null;
//	}
//
//	public static boolean emailCheck(String emailId) {
//		boolean output = true;
//		List<User> userList = getUser();
//		if ((emailId.endsWith("@zohomail.com") || emailId.endsWith("@gmail.com"))
//				&& emailId.equals(emailId.toLowerCase())) {
//			output = true;
//			for (User user : userList) {
//				if (user.getEmail().equals(emailId)) {
//					System.out.println(MyColor.RED+"This email ID is already exixts"+MyColor.RESET);
//					output = false;
//					break;
//				}
//			}                           
//		}
//
//		else {
//			output = false;
//			System.out.println("Invalid emailId");
//		}
//
//		return output;
//	}
//
//	private static File file = new File("users.json");
//	
//
//	public static User login(String emailID, String passWords) {
//		List<User> userList = getUser();
//		for (User user : userList) {
//			if (user.getEmail().equals(emailID) && user.getPassWord().equals(passWords)) {
//				currentUser = user;
//				break;
//			}
//		}
//		return currentUser;
//	}
//	
//	public static void writeUser(User userObj) {
//		try {
//			List<User> userList = getUser();
//			userList.add(userObj);
//			objectMapper.writeValue(file, userList);
//			
//		} catch (Exception e) {
//			System.err.println("User cannot be added");
//		}
//		
//		
//	}
//	public static User signUp(User userObj) throws SQLException {
//		List<User> userList = getUser();
//		userList.add(userObj);
//		
//		return userObj;
//	}
//	public static User getUserObj(String userNameInp) {
//		User output = null;
//		List<User> userList = getUser();
//		for (User user : userList) {
//			if (user.getUserName().equals(userNameInp)) {
//				output = user;
//				break;
//			}
//		}
//		return output;
//	}
//	
//	
//	
//	
////	😍😍😍
//	
//}