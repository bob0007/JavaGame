/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gravitygame.load;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Henry
 */
public class LoadingUtils {

    static String fileReader(String filePath) throws FileNotFoundException {
	StringBuilder output = new StringBuilder();
        InputStream fsStream = LoadingUtils.class.getClassLoader().getResourceAsStream(filePath);
	while (true) {
	    try {
                if (fsStream == null) {
                    throw new FileNotFoundException("The file path: " + filePath + " could not be opened.");
                }
		int value = fsStream.read();
		if (value == -1) {
		    break;
		}
		char ch;

		ch = (char) (value);
		output.append(ch);
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }

	}
	return output.toString();
    }
}
