//package usermanagement;
//
//import java.util.List;
//
//import login.LoginSystem;
//
//public class AdminManagement{
//	public static void showAllUsers(User obj) {
//		try {
//			List<User> users = LoginSystem.getUser();
//			if(users.size() > 0) {
//				for(User user : users) {
//					if(user.getAssignedAdminId().equals(obj.getUserName())) {
//					user.userInfo();
//					}
//				}
//			}
//			
//		} catch (NullPointerException e) {
//			System.err.println("User not found");
//		}
//		
//	}
//	public static void addUser(String uName,String aId) {
//		User user = LoginSystem.getUserObj(uName);
//		if(user != null) {
//			user.setAssignedAdminId(aId);
//			LoginSystem.writeUser(user);
//		}
//	}
//	
//}