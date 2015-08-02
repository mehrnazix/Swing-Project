package ir.mahan.train.view;
import java.io.File;
import javax.swing.filechooser.FileFilter;

public class PersonFileFilter extends FileFilter{

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		String extension = Utils.getFileExtention(name);
		
	    
		if (file.isDirectory()) {
			return true;
		}
		
		if (extension == null) {
			return false;
		}
		
		if (extension.equals("per"))
		{
			return true;
		}
		
		return false;
	}

	@Override
	public String getDescription() {
		return "Person Files";
	}
	
}
