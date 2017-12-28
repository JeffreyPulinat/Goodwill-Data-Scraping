import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;

import javax.imageio.ImageIO;

public class GetURL {
	static String outputtxt="";
	static String other;
	
	/**
	 * Sorts what to do with different type of urls.
	 * 
	 * @param line - String of url
	 * @throws MalformedURLException
	 * @throws IOException
	 */
    public static void masterController(String line) 
            throws MalformedURLException, IOException
        {
    	URL url = new URL(line);
    	
    	if(line.endsWith(".html")||line.endsWith(".htm")||line.endsWith(".txt")){
    		GetURLtxt.fetch(line,url);
    		
    	}else if(ImageIO.read(url) != null){ //returns null if the file can not be read as an image.
    		GetURLImage.fetch(line,url);
    	}
    	else{
    		//System.out.println(url);
    		URLConnection connection = url.openConnection();
    		printinfo(connection);
    	}
    }
    
    
    /**Display the URL address, and information about it.
     * 
     * @param u
     * @throws IOException
     */
    public static void printinfo(URLConnection u) throws IOException {
        BuildString.output(u.getURL().toExternalForm() + " :");
        BuildString.output("  Content Type: " + u.getContentType());
        BuildString.output("  Content Length: " + u.getContentLength());
        BuildString.output("  Last Modified: " + new Date(u.getLastModified()));
        BuildString.output("  Expiration: " + u.getExpiration());
        BuildString.output("  Content Encoding: " + u.getContentEncoding());
    }
    
    public static void htmlSource(String urlString, String mode) throws Exception{
    	//Set URL
    	//System.out.println(mode);
 	    if(!mode.equals("offline")){ //online
 	    	URL url = new URL(urlString);
 	    	URLConnection fakebrowser = url.openConnection();
 	    	String source ="";
    	 
 	    	//look like a web browser
 	    	fakebrowser.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 5.5; Windows NT 5.0; H010818)" );
 	    	BufferedReader in = new BufferedReader(new InputStreamReader(fakebrowser.getInputStream()));
 	    	String strLine = "";
    	
    	 
 	    	//Loop through every line in the source
 	    	while ((strLine = in.readLine()) != null){
 	    		source = source + strLine+"\n";
 	    	}
 	    	DataHandling.regex(source);
 	    	ProjectCS370.writeOutputFile("sourceHTML.txt",source);
    		
    	}else{ //offline
 	    	System.out.println(mode);
 	    	FileInputStream fis = null;
 			BufferedReader br = null;
 			String source = "";
 			source = ProjectCS370.getStringFile("sourceHTML.txt");
 			DataHandling.regex(source);
 	    }
    }
    
}
