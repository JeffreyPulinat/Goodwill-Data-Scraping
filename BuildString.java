public class BuildString {
	static String outputtxt="";
	static String urltxt="";
	
	//Builds Strings for all classes
	
    public static void output(String s){
    	outputtxt = outputtxt+s +"\n";
    }
    public static void urltxt(String s){
    	urltxt = urltxt+s + "\n";
    }
	public static void goodwillURLbuild(String search, String itemNum, String minPrice, String maxPrice, String mode) {
		// TODO Auto-generated method stub
		String item = "Item/" +itemNum;
		
		String base ="https://www.shopgoodwill.com/Listings?";
		String st ="st="+ search.replaceAll(" ", "%20").toLowerCase();
		String extra ="&sg=&c=&s=";
		int minprice =0;
		int maxprice =999999;
		try{
			minprice = Integer.parseInt(minPrice);
			maxprice = Integer.parseInt(maxPrice);
			if(minprice<0||minprice>999999)minprice=0;
			if(maxprice>999999||maxprice<0)maxprice=999999;
		}catch(Exception e){
		}
		
		String lp = "&lp="+minprice;
		String hp = "&hp="+maxprice;
		
		String extraEnd ="&sbn=false&spo=false&snpo=false&socs=false&sd=false&sca=false&caed=12/18/2017&cadb=7&scs=false&sis=false&col=1&p=1&ps=40&desc=false&ss=0&UseBuyerPrefs=true";
		String url = base+st+extra+lp+hp+minPrice+maxPrice+extraEnd;
		//System.out.println(url);
		try {
			//System.out.println(mode);
			GetURL.htmlSource(url,mode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
