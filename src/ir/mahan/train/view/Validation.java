package ir.mahan.train.view;

public class Validation {
	static boolean userValidation(FormEvent formEvent) {
		if (formEvent.getFirstName().isEmpty()) {
			return false;
		}
		
		if (formEvent.getLastName().isEmpty()) {
			return false;
		}		
		return true;
	}
}
