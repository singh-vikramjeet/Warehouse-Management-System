package authentication;

import java.util.ArrayList;
import java.util.List;

import sqLiteDB.AdminDB;

public class AuthServer {
	
	// Singleton Pattern
	private static AuthServer instance = null;
	
	private AuthServer AuthServer() {
		return new AuthServer();
	}
	
	public static AuthServer getInstance() {
		if(instance == null) {
			instance = new AuthServer();
		}
		return instance;
	}
	
	
	public static boolean verifyCredentials(String userName, String password) {
		
		boolean result = false;
		int passKey = Integer.parseInt(password);
		
		List<User> usersList = AdminDB.getUsersList();
		
		for(int i = 0; i < 5;i++) {
			if(usersList.get(i).getUsername().equalsIgnoreCase(userName) && usersList.get(i).getPassword() == passKey) {
				result = true;
			}
			
		}
		
		return result;
	}

	
}
