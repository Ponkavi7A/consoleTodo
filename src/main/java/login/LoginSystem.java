


package login;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import com.mysql.cj.callback.UsernameCallback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jdbc.DatabaseConnection;
import todoapplication.Main;
import usermanagement.MyColor;
import usermanagement.User;

public class LoginSystem {
	private static final Logger logger =  LogManager.getLogger(Main.class);
	static Connection connection = DatabaseConnection.getDataBase().getConnection();
	static PreparedStatement statement = null;

	public static boolean passWordCheck(String passKey) {
		passKey = passKey.trim();
		String passwordRegex = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$";
		Pattern pattern = Pattern.compile(passwordRegex);
		Matcher matcher = pattern.matcher(passKey);
		return matcher.matches();
	}

	public static boolean userCheck(String userNameInp) {
		ResultSet resultSet = null;
		boolean exists = false;
		try {
			String query = "SELECT * FROM Users WHERE userName = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, userNameInp);
			resultSet = statement.executeQuery();

			return !resultSet.next();
		} catch (SQLException e) {
			logger.error("Error on LoginSystem class,userCheck",e);
			e.printStackTrace();
		}
		return false;
	
	}

	public static boolean emailCheck(String emailId) {
		boolean output = true;
		ResultSet resultSet = null;
		try {
			String query = "SELECT COUNT(*) FROM Users WHERE email = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, emailId);
			resultSet = statement.executeQuery();

			if (resultSet.next() && resultSet.getInt(1) > 0) {
				output = false;
			}
		} catch (SQLException e) {
			logger.error("Error on LoginSystem class,emailCheck",e);
			e.printStackTrace();
		}

		return output;
	}

	public static User login(String username, String passWords) {
	    User currentUser = null;
	    ResultSet resultSet = null;

	    try {
	        String query = "SELECT * FROM Users WHERE userName = ? or email = ?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, username);
	        statement.setString(2, username);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String storedHashedPassword = resultSet.getString("password");
	            if (BCrypt.checkpw(passWords, storedHashedPassword)) {
	                currentUser = new User();
	                currentUser.setEmail(resultSet.getString("email"));
	                currentUser.setUserName(resultSet.getString("username"));
	                currentUser.setPassWord(storedHashedPassword);
	                currentUser.setRole(resultSet.getInt("role"));
	            }
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on LoginSystem class,login",e);
	        System.out.println(e.getMessage());
	    }
	    logger.info("Login Succeful,userCheck",username);
	    return currentUser;
	}
	
	public static User signUp(User userObj) throws SQLException {
	    try {
	        String query = "insert into Users (name,email,username,password,role) values (?,?,?,?,?)";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, userObj.getName());
	        statement.setString(2, userObj.getEmail());
	        String password = BCrypt.hashpw(userObj.getPassWord(), BCrypt.gensalt());
	        statement.setString(3, userObj.getUserName());
	        statement.setString(4, password);
	        statement.setInt(5, 1);
	        statement.executeUpdate();
	    } catch (SQLException e) {
	    	logger.error("Error on LoginSystem class,SignUps",e);
	        e.printStackTrace();
	        throw e;
	    }
	    logger.info("Sign Up succeful LoginSystem class,userCheck",userObj.getUserName());
	    return userObj;
	}
	
	public static User getUserObj(String userNameInp) {
	    User output = null;
	    ResultSet resultSet = null;

	    try {
	        String query = "SELECT * FROM Users WHERE userName = ?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, userNameInp);
	        resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            output = new User();
	            output.setUserName(resultSet.getString("userName"));
	            output.setEmail(resultSet.getString("email"));
	            output.setPassWord(resultSet.getString("password")); 
	        }
	    } catch (SQLException e) {
	    	logger.error("Error on LoginSystem class,getUserObj",e);
	        System.out.println(e.getMessage());
	    }
	    return output;
	}
	
	public static int getUserId(String userName) throws SQLException {
		System.out.println(userName);
		  String query = "select userId from Users where username = ?";
		  statement = connection.prepareStatement(query);
		  statement.setString(1, userName);
		  if(statement.execute()) {
			ResultSet resultSet = statement.getResultSet();
			  if (resultSet.next()) { // Move the cursor to the first row
				  System.out.println(resultSet.getInt("userId"));
		            return resultSet.getInt("userId"); // Retrieve userId
		        }
		  }
		  System.err.println("User does'nt Exist");
		  return -1;
	}

	

}
