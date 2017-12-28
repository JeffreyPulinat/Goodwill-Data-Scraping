import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;

public class GetURLImage {
	public static BufferedImage img = null;

	/**
	 * Gets image and saves it to current directory
	 * @param line - String of url
	 * @param url
	 */
	public static void fetch (String line, URL url) {
		try{
			url = new URL(line);
			//System.out.println("image detected");
			img = ImageIO.read(url);
			String filefriendly = line.replaceAll("[^\\w\\s]","");
			String extension = line.substring(line.lastIndexOf("."));	//ex .png .jpeg
			File file = new File(filefriendly+extension);
			if(!file.exists())file.createNewFile();
			ImageIO.write(img, extension.substring(1), file);  //extension.substing(1) removes "."
			BuildString.output(line+ " : Image URL");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
