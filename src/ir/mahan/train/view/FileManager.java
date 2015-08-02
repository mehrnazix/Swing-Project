package ir.mahan.train.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
	JFileChooser fileChooser;
	PersonFileFilter personFileFilter;
	
	public FileManager() {
		this.fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		personFileFilter = new PersonFileFilter();
//		fileChooser.setFileFilter(personFileFilter);
		fileChooser.addChoosableFileFilter(personFileFilter);
		

	}

	public <T> void exportToFile(List<T> output) throws IOException {
		try {
			int result = fileChooser.showSaveDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				FileOutputStream fileStream = new FileOutputStream(selectedFile + ".per");
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(output);
				os.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public <T> List<T> importFromFile() throws IOException {
		try {
			int result = fileChooser.showOpenDialog(null);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = fileChooser.getSelectedFile();
				FileInputStream fileStream = new FileInputStream(selectedFile);
				ObjectInputStream i = new ObjectInputStream(fileStream);
				List<T> e = (List<T>) i.readObject();
				i.close();
				return e;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return null;
	}	

}
