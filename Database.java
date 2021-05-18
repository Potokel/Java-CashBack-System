package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Database {
	// Create ArrayList of users of type User
	ArrayList<User> users = new ArrayList<>();
	// Create Array of teams of type Team
	Team[] teams;

	// database method creates the database from the data text file
	public void database() throws Exception {

		// Set the ArrayList line to be the ArrayList where the file was read
		ArrayList<String> lines = lines();
		// For each element of the ArrayList (So for each line in the text file)
		// add that user to the users database
		for (String element : lines) {
			users.add(makeUser(element));

		}

		int max = 0;
		// Get the largest team number
		for (int counter = 0; counter < this.users.size(); counter++) {
			if ((this.users.get(counter).team_number) > max) {
				// Sets largest team number as max
				max = (this.users.get(counter).team_number);
			}
		}
		// Create the teams database to be the size of the amount of teams
		this.teams = new Team[max + 1];
		this.makeTeam();
	}

	public ArrayList<String> lines() throws Exception {
		// ArrayList for Strings
		ArrayList<String> temp = new ArrayList<>();
		// Read the data text file with BufferedReader
		try (BufferedReader reader = new BufferedReader(new FileReader("./data/data.txt"))) {
			String words;
			// While there are entities in a line, add them to the String ArrayList
			while ((words = reader.readLine()) != null) {
				temp.add(words);
			}
		}
		// Return the whole ArrayList
		return temp;
	}

	// Create User Object from the data text file
	public User makeUser(String line) {
		// Spilt the data from the text file by "\\s", (spacebar)
		String[] tab = line.split("\\s");
		// Create User Object with elements of the data from the data text file being
		// used as the constructor
		User user = new User(tab[0], tab[1], Boolean.parseBoolean(tab[2]), Integer.parseInt(tab[3]), tab[4], tab[5]);
		return user;
	}

	// Create Team in the Teams database
	public void makeTeam() {
		// Cycle through Users and get their team_number
		for (int i = 0; i < this.users.size(); i++) {
			User a = this.users.get(i);
			int team_number = a.team_number;

			// If team_number is 0, skip because 0 is reserved for admins, and we don't want
			// to make a team 0
			if (team_number == 0) {
				// Nothing
			}
			// Check the teams database if there is a team object at the index of the
			// team_number for this user
			// If there isn't a team object there, create one
			else if (this.teams[team_number] == null) {
				// Create Team object in the Teams Array
				Team t = new Team();

				teams[team_number] = t;
				// Add the current user from the for loop to this team
				t.users_in_team.add(a);
				// Add £50 to the teams balance
				t.balance += 50;
			}
			// If a team object is there, add the current user from the for loop to that
			// team
			else {
				Team t = this.teams[team_number];
				// Add user to team, and add £50 to that team
				t.users_in_team.add(a);
				t.balance += 50;
			}
		}
	}
}
