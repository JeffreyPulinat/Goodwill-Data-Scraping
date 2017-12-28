import java.util.regex.Matcher;
/*
 * Object to capture results of search from goodwill
 */
public class Item {
	//numOfResults.matcher(source);
	String itemNum;
	String title;
	String price;
	String timeLeft;
	String image;
	
	public Item(String itemNum, String title, String price, String timeLeft, String image){
		this.itemNum=itemNum;
		this.title=title;
		this.price=price;
		this.timeLeft=timeLeft;
		this.image=image;
	}
	@Override
	public String toString(){
		return this.itemNum+ "|" + this.title + "|" + this.price + "|"+this.timeLeft+"|"+this.image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTimeLeft() {
		return timeLeft;
	}
	public void setTimeLeft(String timeLeft) {
		this.timeLeft = timeLeft;
	}
}
