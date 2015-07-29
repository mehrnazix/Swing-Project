package ir.mahan.train.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class TextPanel extends JPanel {

	private JTextArea textArea;

	public JTextArea getTextArea() {
		return textArea;
	}

	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		Dimension dim = getPreferredSize();
		dim.width = 384;
		setPreferredSize(dim);
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		textArea.setFont(new Font(Font.SERIF,Font.PLAIN,15));
	}

	public void setText(String str) {
		this.textArea.append(str + "\n");
	}

}
