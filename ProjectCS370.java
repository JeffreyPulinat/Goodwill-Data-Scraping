import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


public class ProjectCS370 {
	
	static String input="";
	static String output="";
	static String path="";
	static String log="";
	static String result="";

	public static void main(String[] args) throws IOException{
		flagParam(args);	//assign all flag parameters
		
        fileChooserTxt("i"); 	//gets input and output text file
		fileChooserTxt("o");
		
		AdvSearch.launch();
		
	}

	/**
	 * Find flag parameters and gets next position on args for argument
	 * @param args
	 */
	private static void flagParam(String[] args) {
		for(int i = 0; i < args.length; i++) {
			try{
				if(args[i].equals("-i")||args[i].equals("-I")){
					input= args[i+1];
					continue;
				}
				if(args[i].equals("-o")||args[i].equals("-O")){
					output=args[i+1];
					continue;
				}
				if(args[i].equals("-d")||args[i].equals("-D")){
					path=args[i+1];
					continue;
				}
				if(args[i].equals("-l")||args[i].equals("-L")){
					log=args[i+1];
					continue;
				}
				if(args[i].equals("-v")||args[i].equals("-V")){
					result=args[i+1];
					continue;
				}
			}catch(Exception e){
				//IndexOutOfBound args? 
				//if input or output file doesn't exist JChooser is used instead.
			}
		}
	}


	
	/**
	 * Choose file to read from and write to
	 * @param io - mode input or output
	 */
	public static void fileChooserTxt(String io){
		File f = new File("");
		if(io.equals("i"))  f = (new File(input));
		if(io.equals("o"))  f = (new File(output));
		//if file exists in current directory
		if(f.exists()){
			try{
				//System.out.println(io+" file Exists");
				if(io.equals("i"))readInputFileURL(input);
				if(io.equals("o"))writeOutputFile(output, BuildString.outputtxt);
			}catch (Exception a){
				System.err.println("Not a valid file");
			}
			
		//file doesn't exist
		}else{	
			boolean ValidDir = false;
			File dir = null;
			
			//check directory
			try{
				File directory = new File(path);
				if(directory.isDirectory()){	//see if directory is valid
					ValidDir = true;
					dir=directory;
				}	
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Not a valid directory. \nReverting to current directory.");
			}
		
			FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT file", "txt"); //can only choose .txt
			JFileChooser chooser = new JFileChooser();
			chooser.setFileFilter(filter);
			if(ValidDir)chooser.setCurrentDirectory(dir);  //GUI opens at valid directory
			else chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));	 //GUI opens at current directory
			
			String title = "";
			if(io.equals("i"))  title = "Choose An Input File";
			if(io.equals("o"))  title = "Choose An Output File";
			chooser.setDialogTitle(title);
			
			int result = chooser.showOpenDialog(chooser);
			if (result == JFileChooser.APPROVE_OPTION) {
				File selectedFile = chooser.getSelectedFile();
				System.out.println("Selected file: " + selectedFile.getAbsolutePath());		
				if(io.equals("i"))readInputFileURL(selectedFile+"");
				if(io.equals("o"))writeOutputFile(selectedFile+"", BuildString.outputtxt);
			}
		}
	}
	
	/**
	 * reads file containing urls line by line
	 * sends url to be scraped for info
	 * @param txtfile
	 */
	public static void readInputFileURL(String txtfile){
		
		FileInputStream fis = null;
		BufferedReader br = null;
		try{
			fis = new FileInputStream(txtfile);
			br = new BufferedReader(new InputStreamReader(fis));
			
			String line = br.readLine();
			while(line != null){
				GetURL.masterController(line);
				line = br.readLine();
			}
			br.close();
            fis.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Writes a string to a file
	 * @param txtfile
	 */
	public static void writeOutputFile(String txtfile, String outputtxt) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(txtfile);
			bw = new BufferedWriter(fw);
			bw.write(outputtxt);	//gets output text
			//System.out.println(BuildString.outputtxt);

			//System.out.println("Done");
			bw.close();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String getStringFile(String txtfile){
		String source ="";
		FileInputStream fis = null;
		BufferedReader br = null;
		try{
			fis = new FileInputStream(txtfile);
			br = new BufferedReader(new InputStreamReader(fis));
			
			String line = br.readLine();
			while(line != null){
				source = source + line;
				line = br.readLine();
				
			}
			br.close();
            fis.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
        return source;
	}
	
	
}
	

