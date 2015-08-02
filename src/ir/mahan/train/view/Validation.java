package ir.mahan.train.view;

public class Validation {
	static boolean userValidation(User user) {
		if (user.firstName.isEmpty()) {
			return false;
		}
		
		if (user.lastName.isEmpty()) {
			return false;
		}		
		return true;
	}
}
