package ir.mahan.train.view;

public class Utils {
	public static String getFileExtention (String name) {
		int pointIndex = name.indexOf(".");
		if (pointIndex == -1) {
			return null;
		}
		if (pointIndex == name.length()-1) {
			return null;
		}
		return name.substring(pointIndex + 1, name.length());
	}
}
