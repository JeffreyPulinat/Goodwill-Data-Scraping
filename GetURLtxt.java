import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class GetURLtxt {
	static int lineCount;
	
	/**
	 * reads html, htm and txt files and counts each line
	 * saves and writes it to a file
	 * 
	 * @param line - string of url
	 * @param url
	 * @throws IOException
	 */
	public static void fetch(String line, URL url) throws IOException {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			lineCount = 0;
			while ((inputLine = in.readLine()) != null){
				BuildString.urltxt(inputLine);
				lineCount++;
			}
			in.close();
			String filefriendly = line.replaceAll("[^\\w\\s]","");
			writeFile(filefriendly);
			BuildString.output(line+ ": read in "+ lineCount + " lines.");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
    }
	/**Writes to a file
	 * 
	 * @param txtfile - name of saved file
	 */
	public static void writeFile(String txtfile) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(txtfile);
			bw = new BufferedWriter(fw);
			bw.write(BuildString.urltxt);	//writes to string

			//System.out.println("Done");
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	

}
