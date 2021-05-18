package project;

import java.util.ArrayList;
import java.util.Scanner;

public class Team {
	// Define the class variables

	// Create an ArrayList of Objects of type User, as the users in the team
	ArrayList<User> users_in_team = new ArrayList<>();
	// Create an ArrayList of Strings, as the history of spending for this team
	ArrayList<String> history_of_spending = new ArrayList<>();

	String temp;
	int balance;

	public Team() {

	}

	// Method that returns the balance of the team
	public int display_balance() {
		return balance;
	}

	// Method for requesting cashback by a user for the team
	public void cashback() {
		Scanner input = new Scanner(System.in);
		System.out.println("How much do you want to claim ");
		// Input the amount of money requested
		int money_requested = input.nextInt();
		input.nextLine();
		// Check whether requested amount doesn't exceed the amount remaining for the
		// team. If not enough, print error message
		if (money_requested > balance) {
			System.out.println("Not enough money in the account");

		}
		// Request has to be >= 0
		else if (money_requested < 0) {
			System.out.println("We don't do that here ");
		}
		// Else change the balance to balance - money_requested
		else {
			balance = balance - money_requested;
			// Create the history of the transaction
			temp = Integer.toString(money_requested);
			System.out.println("What did you spend that money on?");
			String dscrp = input.nextLine();
			// Create the description
			dscrp = ("£" + temp + ": " + dscrp);
			// Show the user the finalised transaction, with the transaction description
			System.out.println(dscrp);
			// Add the record to the history_of_spending ArrayList
			history_of_spending.add(dscrp);
		}
	}

	// Method for viewing transaction history
	public void history() {
		// For loop to list all records in the ArrayList for this team
		for (int counter = 0; counter < history_of_spending.size(); counter++) {
			System.out.println(history_of_spending.get(counter));
		}
	}
}
