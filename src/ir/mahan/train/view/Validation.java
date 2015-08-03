package ir.mahan.train.view;

public class Validation {
	static boolean userValidation(FormEvent formEvent) {
		if (formEvent.firstName.isEmpty()) {
			return false;
		}
		
		if (formEvent.lastName.isEmpty()) {
			return false;
		}		
		return true;
	}
}
