package project;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Operations {
	// Set class variables
	Database database;
	Login login;
	Boolean on = true;

	// Operations constructor
	public Operations(Database database, Login login) {
		this.database = database;
		this.login = login;
	}

	// Method to list operations for a normal user
	public void list_of_operations() throws Exception {
		// Set operation_choice
		int operation_choice = 0;
		Scanner input = new Scanner(System.in);
		// Do While Loop for Boolean on
		do {
			// Check if the current_user is an admin, if not, continue with the if statement
			if (login.current_user.admin == false) {
				System.out.println(
						"\nChoose Operation:\n1. Display balance for your team\n2. Claim Cashback\n3. History\n4. Log Out of the System");
				// Try catch for operation choice
				try {
					operation_choice = input.nextInt();
					input.nextLine();

					switch (operation_choice) {
					// If 1. Display_balance for the team of the current user
					case 1:
						System.out.println("£" + database.teams[(login.current_user.team_number)].display_balance());
						break;
					// If 2. Call the cashback method for the team of the current user
					case 2:
						database.teams[(login.current_user.team_number)].cashback();
						break;
					// If 3. Call the history method for the team of the current user
					case 3:
						database.teams[(login.current_user.team_number)].history();
						break;
					// If 4. Log out, and call the login method again to relog
					case 4:
						login.login();
						break;
					// Default, continue and list the operation choice again
					default:
						continue;
					}
				}
				// For any errors with InputMismatchException in the list_of_operations method
				// it will display this error message and call list_of_operations again
				catch (InputMismatchException e) {
					System.out.println("Entered value is not a number");
					list_of_operations();
				}
			}
			// If the current user is an admin call the list_for_admins method
			else if (login.current_user.admin == true) {
				list_for_admins();
			}

		}
		// While on is true it will always loop back to display either
		// list_of_operations or list_for_admins
		// This can only be disabled from the admin options
		while (on == true);

	}

	// Method list_for_admin lists the admin options
	public void list_for_admins() throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println("Which team do you want to select? ");
		// Print out all the teams available
		for (int number = 1; number < database.teams.length; number++) {
			System.out.println("Team " + number);
		}
		System.out.println("Enter 0 to log out");
		System.out.println("Enter -1 to SHUTDOWN and RESET the whole system");
		// Try catch for admin operation choice
		try {
			// Select a team to modify
			int team_number_choice = input.nextInt();
			input.nextLine();
			// Enter 0 to log out and call the login method
			if (team_number_choice == 0) {
				login.login();
				// Enter -1 to shutdown the system, this is the only way to shutdown the whole
				// system.
			} else if (team_number_choice == -1) {
				// Ask and enter confirmation to shutdown the system
				System.out.println("Are you SURE you want to SHUTDOWN and RESET THE WHOLE SYSTEM\n1.Yes\n2.No");
				int confirm = input.nextInt();
				input.nextLine();
				switch (confirm) {
				// If Shutdown confirmed, set Boolean on to false. End the list_for_admin
				// method, return to the list_of_operations method, which will not loop because
				// Boolean on is now false, ending the program
				case 1:
					on = false;
					break;
				case 2:
					// Cancel calls list_for_admins
					list_for_admins();
					break;
				default:
					// Anything which isn't 1 calls list_for_admins
					list_for_admins();
					break;
				}
			}
			// If team_number_choice is valid it will call the show_options method with
			// team_number_choice as a parameter
			else if (!((team_number_choice) < 1 || (team_number_choice) > (database.teams.length))) {
				show_options(team_number_choice);
			}
			// else, team_number_choice wasn't valid, display error message
			else {
				System.out.println("Select a valid team");
				list_for_admins();
			}
		}
		// Catch statement, if InputMismatchException anywhere in list_for_admins, show
		// error message
		catch (InputMismatchException e) {
			System.out.println("Entered value is not a number");
		}

	}

	// show_options method shows the operations after selecting a team to view
	public void show_options(int team_number_choice) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"\nChoose Operation:\n1. List users for the team\n2. Display Team's balance \n3. Modify Team Members \n4. Modify Team's balance \n5. Show history \n6. Add a new user to this team \n7. Go Back");
		// Operation choice select
		int operation_choice = input.nextInt();
		input.nextLine();
		switch (operation_choice) {
		// Calls list_users for the selected team
		case 1:
			list_users(team_number_choice);
			show_options(team_number_choice);
			break;
		// Calls display_teams_balance for the selected team
		case 2:
			display_teams_balance(team_number_choice);
			show_options(team_number_choice);
			break;
		// Calls modify_teams_members for the selected team
		case 3:
			modify_team_members(team_number_choice);
			show_options(team_number_choice);
			break;
		// Calls modify_teams_balnce for the selected team
		case 4:
			modify_teams_balance(team_number_choice);
			show_options(team_number_choice);
			break;
		// Calls list_history for the selected team
		case 5:
			list_history(team_number_choice);
			show_options(team_number_choice);
			break;
		// Adds a new user to the selected team
		case 6:
			// Enter the new users username, password, first name and last name.
			System.out.println("Enter Username ");
			String username = input.nextLine();
			System.out.println("Enter Password ");
			String password = input.nextLine();
			System.out.println("Enter First Name");
			String firstname = input.nextLine();
			System.out.println("Enter Last Name");
			String lastname = input.nextLine();
			User newuser = new User(username, password, false, team_number_choice, firstname, lastname);
			// Adds the new user to the Users Database to the Teams database
			database.users.add(newuser);
			database.teams[team_number_choice].users_in_team.add(newuser);
			break;

		case 7:
			// Goes back to list_for_admins
			list_for_admins();
			break;
		}
	}

	// list_users prints out all the users in the selected team
	public void list_users(int choice) {
		System.out.println("");
		for (int counter = 0; counter < database.teams[choice].users_in_team.size(); counter++) {
			System.out.println((counter + 1) + ": " + database.teams[choice].users_in_team.get(counter).username + ": "
					+ database.teams[choice].users_in_team.get(counter).first_name + " "
					+ database.teams[choice].users_in_team.get(counter).last_name);
		}
	}

	// display_teams_balance displays the balance for the selected team
	public void display_teams_balance(int choice) {
		System.out.println("£" + database.teams[choice].display_balance());
	}

	// modify_teams_balance allows modifying the selected teams balance
	public void modify_teams_balance(int choice) {
		Scanner input = new Scanner(System.in);
		System.out.println(
				"\nHow much money do you want to add to this team's balance. (To take money away, just enter the amount with \"-\" infront");
		// Enter the amount to add
		int amount = input.nextInt();
		input.nextLine();
		// change the balance to balance += amount
		database.teams[choice].balance += amount;

	}

	// modify_team_members allows modifying a team member from the selected team
	public void modify_team_members(int choice) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nWhich team member do you want to modify? ");
		// List all the users in this team
		list_users(choice);
		// Select which user you want to modify
		int user_choice = input.nextInt();
		input.nextLine();
		// Check if user_choice is valid
		if (!(user_choice < 1 || user_choice > database.teams[choice].users_in_team.size())) {
			Boolean menu = true;
			do {
				System.out.println(
						"\nChoose Operation:\n1. Change User Name\n2. Change Password \n3. Change Team number \n4. Change First Name \n5. Change Last Name \n6. Delete User \n7. Cancel");

				int operation_choice = input.nextInt();
				input.nextLine();

				switch (operation_choice) {
				// Change Username
				case 1:
					System.out.println("Enter new username ");
					String new_username = input.nextLine();
					Boolean taken = false;
					// Cycle through all usernames
					for (int counter = 0; counter < database.users.size(); counter++) {

						// IF a user name exists in the Users database, set taken to true;
						if (new_username.equals(database.users.get(counter).username)) {
							taken = true;
							System.out.println("This Username is already taken! ");
						}
					}
					// Only change the user name if that user name isn't taken
					if (taken == false) {
						database.teams[choice].users_in_team.get((user_choice - 1)).username = new_username;
						System.out.println("Username: "
								+ database.teams[choice].users_in_team.get((user_choice - 1)).username + " set");
					}
					break;

				// Change Password
				case 2:
					System.out.println("Enter new password ");
					String new_password = input.nextLine();
					database.teams[choice].users_in_team.get((user_choice - 1)).password = new_password;
					System.out.println("Password: "
							+ database.teams[choice].users_in_team.get((user_choice - 1)).password + " set");
					break;

				// Change Team Number
				case 3:
					System.out.println("Enter new team number ");
					int new_team_number = input.nextInt();
					input.nextLine();
					database.teams[choice].users_in_team.get((user_choice - 1)).team_number = new_team_number;
					database.teams[new_team_number].users_in_team
							.add(database.teams[choice].users_in_team.get((user_choice - 1)));
					System.out.println("Team Number : " + database.teams[new_team_number].users_in_team
							.get(database.teams[new_team_number].users_in_team.indexOf(
									database.teams[new_team_number].users_in_team.get((user_choice - 1)))).team_number
							+ " set");
					database.teams[choice].users_in_team.remove((user_choice) - 1);
					menu = false;
					break;

				// Change First Name
				case 4:
					System.out.println("Enter new First Name ");
					String new_first_name = input.nextLine();
					database.teams[choice].users_in_team.get((user_choice - 1)).first_name = new_first_name;
					System.out.println("First Name : "
							+ database.teams[choice].users_in_team.get((user_choice - 1)).first_name + " set");

					break;

				// Change Last Name
				case 5:
					System.out.println("Enter new Last Name ");
					String new_last_name = input.nextLine();
					database.teams[choice].users_in_team.get((user_choice - 1)).last_name = new_last_name;
					System.out.println("Last Name : "
							+ database.teams[choice].users_in_team.get((user_choice - 1)).last_name + " set");
					break;

				// Delete User
				case 6:
					System.out.println("Are you SURE you want to DELETE this user\n1.Yes\n2.No");
					int confirm = input.nextInt();
					input.nextLine();
					// Get confirmation to delete
					switch (confirm) {
					case 1:
						System.out.println("User: "
								+ database.teams[choice].users_in_team.get((user_choice - 1)).username + " deleted");
						database.users.remove(database.teams[choice].users_in_team.get((user_choice - 1)));
						database.teams[choice].users_in_team.remove((user_choice) - 1);
						break;
					case 2:
						break;

					default:
						break;
					}

					// Go back
				case 7:
					menu = false;
					break;

				// Default, list options again
				default:
					break;

				}

			}
			// while statement to stay in the modify_team_members loop
			while (menu == true);

		}
		// else statement if team member choice is invalid
		else {
			System.out.println("Please Select a vaid team member ");
			modify_team_members(choice);
		}
	}

	// Method to get history for the current team
	public void list_history(int choice) {
		database.teams[(choice)].history();

	}

}
