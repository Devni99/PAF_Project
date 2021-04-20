package Model;

public class Product {

	int pID;
	String pCode;
	String pName;
	String description;
	String inventor;
	double price;
	int quantity;
	
	
	public Product() {
		
	}


	public Product(int pID, String pCode, String pName, String description, String inventor, double price, int quantity) {
		super();
		this.pID = pID;
		this.pCode = pCode;
		this.pName = pName;
		this.description = description;
		this.inventor = inventor;
		this.price = price;
		this.quantity = quantity;
	}


	public int getpID() {
		return pID;
	}


	public String getpCode() {
		return pCode;
	}


	public String getpName() {
		return pName;
	}


	public String getDescription() {
		return description;
	}


	public String getInventor() {
		return inventor;
	}


	public double getPrice() {
		return price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setpID(int pID) {
		this.pID = pID;
	}


	public void setpCode(String pCode) {
		this.pCode = pCode;
	}


	public void setpName(String pName) {
		this.pName = pName;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setInventor(String inventor) {
		this.inventor = inventor;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
	
	
	
	
}
