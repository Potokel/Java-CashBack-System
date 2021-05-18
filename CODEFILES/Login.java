package project;

import java.util.Scanner;

public class Login {
	// correct_login has to be set to false at start
	Boolean correct_login = false;
	Database database;
	User current_user;

	// Login uses database, so it has to be in the constructor
	public Login(Database database) {
		this.database = database;
	}

	// Login function
	public void login() throws Exception {
		// Declare variables
		String username;
		String password;
		Scanner input = new Scanner(System.in);
		// Ask and enter Username and Password
		System.out.println("Username: ");
		username = input.nextLine();
		System.out.println("Password: ");
		password = input.nextLine();

		// Check does a user with this username exist in the Users database
		for (int counter = 0; counter < database.users.size(); counter++) {
			// Set correct_login to false
			correct_login = false;
			// If a user with this username is found, check whether the passwords match
			if (username.equals(database.users.get(counter).username)) {
				// Check whether the passwords match
				if (password.equals(database.users.get(counter).password)) {
					// If both username and password are correct, set correct_login to true
					this.correct_login = true;
					// Set current_user as the user who's credentials were entered
					current_user = database.users.get(counter);
					// break out of the login system
					break;
				} else {
					continue;
				}
			} else {
				continue;
			}
		}
		// Check correct_login status
		// If correct_login is true, print out welcome message
		if (correct_login == true) {
			System.out.println("Login valid, Welcome: " + username);
		}

		// If correct_login is false, repeat login process
		else {
			System.out.println("Invalid Login or Password!");
			login();

		}
	}
}
