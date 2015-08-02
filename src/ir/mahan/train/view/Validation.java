package ir.mahan.train.view;
import ir.mahan.train.model.User;

public class Validation {
	static boolean userValidation(User user) {
		if (user.FirstName.isEmpty()) {
			return false;
		}
		
		if (user.LastName.isEmpty()) {
			return false;
		}		
		return true;
	}
}
