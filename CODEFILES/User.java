package project;

public class User {
	// Define Class Variables: username, password, whether the user is an admin,
	// team number, first name and last name
	String username;
	String password;
	Boolean admin;
	int team_number;
	String first_name;
	String last_name;

	// Constructor for User
	public User(String username, String password, Boolean admin, int team_number, String first_name, String last_name) {
		// Set variables
		this.username = username;
		this.password = password;
		this.admin = admin;
		this.team_number = team_number;
		this.first_name = first_name;
		this.last_name = last_name;
	}
}