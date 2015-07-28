package ir.mahan.train.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class FileManager {
	JFileChooser fileChooser;

	public FileManager() {
		this.fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new PersonFileFilter());
	}

	public void exportToFile(String str) throws IOException {
		try {
			File selectedFile = fileChooser.getSelectedFile();
			FileOutputStream fileStream = new FileOutputStream(selectedFile);
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(str);
			os.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
