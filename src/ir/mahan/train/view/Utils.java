package ir.mahan.train.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;

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

	public static Icon createIcon(String path) {
		URL url = System.class.getResource(path);
		if (url == null) {
			System.err.println("uabnle to load" + path);
		}
		ImageIcon icon = new ImageIcon(url);
		return icon;
		
//		Font font = null;
//		
//		try {
//			font = font.createFont(Font.TRUETYPE_FONT, url.openStream());
//		} catch (FontFormatException e) {
//			// TODO: handle exception
//		}
	}
	
	public static Font createFont(String path) {
		return null;
		
	}
}
