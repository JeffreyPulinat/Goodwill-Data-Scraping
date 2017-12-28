import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataHandling {
	private static final Pattern numOfResults = Pattern.compile("<p>Showing.*?of (.) results</p>");
	private static final Pattern image = Pattern.compile("<img class=\"lazy-load\" src=\"(.*?)\"");
	private static final Pattern itemNum = Pattern.compile("Product #: </span>(.*?)</div>");
	private static final Pattern title  = Pattern.compile("<div class=\"title\">\\s*(.*?)\\s{4,}<br>");
	private static final Pattern price  = Pattern.compile("<div class=\"product-price\">\\s*<div class=\"price\">(.*?)</div>");
	private static final Pattern timeLeft  = Pattern.compile("<div class=\"timer countdown product-countdown\" data-countdown=\"(.*?)\"></div>");
	private static Hashtable<Integer, Item> table = new Hashtable<Integer,Item>();
	private static String csv ="";
	
	/*
	 * Pattern matching/ storing data
	 */
	public static void regex(String source) {
		// TODO Auto-generated method stub
		Matcher m_numOfResults = numOfResults.matcher(source);
		Matcher m_itemNum = itemNum.matcher(source);
		Matcher m_Title = title.matcher(source);
		Matcher m_price = price.matcher(source);
		Matcher m_timeLeft = timeLeft.matcher(source);
		Matcher m_image = image.matcher(source);
		int num;
		if(m_numOfResults.find())num = Integer.parseInt(m_numOfResults.group(1));
		int key = 0;
		while(m_itemNum.find()&&m_Title.find()&&m_price.find()&&m_timeLeft.find()&&m_image.find()){
			Item data = new Item(m_itemNum.group(1),m_Title.group(1),m_price.group(1),m_timeLeft.group(1),m_image.group(1));
			key++;
			csv = csv +data.toString()+"\n";
			table.put(key,data);
		}
		System.out.println(csv);
		ProjectCS370.writeOutputFile("searchData.csv", csv);
		userDBchoice();
	}
	
	public static void userDBchoice(){
		BufferedReader user = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Do you want to launch database Gui? (Y/N)");
	    String input ="";
		try {
			input = user.readLine();
			if(input.equals("Y"))DatabaseGUI.launch();
			user.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}

	public static String getCSV() {
		return csv;
	}
	
}
