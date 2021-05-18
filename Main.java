//
//	Version 9.0 Final
//	Date last edited 25.04
//	SID 2008692
//	Assignment Element 011
//	Previously in Team "Money Makers"
//	
//	Created in Eclipse (I chose Eclipse because of how enjoyable the environment was to work in, 
//	with implementing certain libraries, as well as ease of debugging and helpful tips to deal with errors
//
//	Formated with Eclipse formating
//	
package project;

//Main method is executed, runs, and initialises the creation of Database, Login, and Operations)
public class Main {
	public static void main(String[] args) throws Exception {
		Database database = new Database();
		database.database();
		Login login = new Login(database);
		login.login();
		Operations operations = new Operations(database, login);

		// If the correct_login Boolean operator in login is true, it will proceed with
		// listing the available operations.
		if (login.correct_login == true) {
			operations.list_of_operations();
		}
	}
}