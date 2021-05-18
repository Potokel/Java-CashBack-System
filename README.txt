//
//	Version 9.0 Final
//	Date last edited 25.04
//	SID 2008692
//	Assignment Element 011
//	Previously in Team "Money Makers"
//
//	
//	This is sort of like an brief instruction manual, so that everthing works fine.
//
//	If for some reason the Jar file doesn't work, the java code files are in the /CODEFILES folder. 
//
//	The ZIP folder contains a Jar file as well as a folder called /data with the file "data.txt" in it. 
//	The Jar file MUST be in the same location as the /data/ folder. Outwise the "data.txt" may not be read
//	correctly.
//	
//	To run the jar, a CMD must be opened in the jar location, and "java -jar Test.jar" should run it.
//	Or use the .bat file provided
//
//	To use the system, the user must first log in, with given credentials. Seeing as I didn't know who would use
//	the system, I created sample users.
//	
//	It is possible to log in as any of the users in the file
//	
// 	A sample team member to log in as is:
//
//	user password false 1 GenericFirstName GenericLastName
//	Username: user
//	Password: password
//
//	Each line is a user, with the first word being the user name, and the second being the password
//	
//	The data is split in the following way
//	username password admin team_number firstname lastname
//
//	
//	To login as an admin, an admin account must be used
//	These are accounts where admin == true and team_number is 0
//
//	Admin accounts provided are:
//
//	admin admin true 0 adminFN adminLN
//	Username: admin
//	Password: admin
//
//	and 
//	root toor true 0 Linux SuperUser
//	Username: root
//	Password: toor
//
//
//
//
//	I designed the system in a way, where it isn't supposted to be shutdown once started,
//	unless someone wants to reset the whole system
//
//	This is why, it shouldn't be possible, to exit the app, unless you're an admin user,
//    and specificly select the shutdown option and confirm the choice.
//
//	Because of this, all changes can be done on the internal database, without needing to modify external files.
//
//
//	I hope that everything works correctly, and that using the system will be a pleasant experience.
//	SID 2008692
//
//
//
