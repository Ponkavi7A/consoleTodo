package usermanagement;



public class User extends Person{
	private String userName;
	private String passWord;
	private String email;
	private int role;
	private String assignedAdminId;
	
	public User() {
		
	}
	
	public User(String name, String userName, String passWord,String email,int role){
		super(name);	
		this.userName = userName;
		this.passWord = passWord;
		this.email = email;
		this.role = role;
		this.assignedAdminId = null;
	}
	public String getAssignedAdminId() {
		return assignedAdminId;
	}

	public void setAssignedAdminId(String assignedAdminId) {
		this.assignedAdminId = assignedAdminId;
	}

	public void userInfo() {
		System.out.println("+----------------------+----------------------------------------------------------+");
		System.out.println("|    User Details      |                         Information                      |");
		System.out.println("+----------------------+----------------------------------------------------------+");
		System.out.printf("| %-20s | %-50s       |\n", "Name" , this.getName());
		System.out.printf("| %-20s | %-50s       |\n", "User name", this.getUserName());
		System.out.printf("| %-20s | %-50s       |\n", "Email ID", this.getEmail());
		System.out.printf("| %-20s | %-50s       |\n", "Password", ("*").repeat(this.getPassWord().length()));
		System.out.println("+----------------------+----------------------------------------------------------+");

	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public void addUser(String userName) {
		
	}
}
