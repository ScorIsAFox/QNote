import java.io.*;
import java.nio.charset.Charset;

import javax.swing.JTextArea;

public class NoteContent {
	
	private File file = new File("D:\\1-Project\\Java Projects\\QNote\\src\\NOTE.txt");
	
	public void fileCreate() {
		if (file.exists()) {
		} else {
			try {
				file.createNewFile();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void readOperation(JTextArea text) {
		try {
			//FileReader in = new FileReader(file);
			BufferedReader bufr = new BufferedReader(new InputStreamReader(new FileInputStream(file),
					Charset.forName("GBK")));
			String s = null;
			while ((s = bufr.readLine()) != null) {
				text.append(s + "\n");
			}
			bufr.close();
			//in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveOperation(JTextArea text) {
		try {
			//FileWriter out = new FileWriter(file);
			BufferedWriter bufw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file),
					Charset.forName("GBK")));
			bufw.write(text.getText());
			bufw.close();
			//out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
