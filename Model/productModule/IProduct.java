package productModule;

// This class will create a Product object from the data obtained through JDBC 

public class IProduct {
	private int productID;
	private String productName;
	private int currentStockQuantity;
	private int unitPrice;
	private int maxStockQuantity;
	private int minStockQuantity;
	private int restockSchedule;
	private String discountStrategyID;
	
	// Constructor
	public IProduct(int productID, String productName, int currentStockQuantity, int unitPrice, int maxStockQuantity,
			int minStockQuantity, int restockSchedule, String discountStrategyID) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.currentStockQuantity = currentStockQuantity;
		this.unitPrice = unitPrice;
		this.maxStockQuantity = maxStockQuantity;
		this.minStockQuantity = minStockQuantity;
		this.restockSchedule = restockSchedule;
		this.discountStrategyID = discountStrategyID;
	}
	public IProduct(int id, int currentStockQuantity, int maxStockQuantity) {
		this.productID = id;
		this.currentStockQuantity = currentStockQuantity;
	}
	
	

	public IProduct() {
		// TODO Auto-generated constructor stub
	}
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getCurrentStockQuantity() {
		return currentStockQuantity;
	}

	public void setCurrentStockQuantity(int currentStockQuantity) {
		this.currentStockQuantity = currentStockQuantity;
	}

	public int getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(int unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getMaxStockQuantity() {
		return maxStockQuantity;
	}

	public void setMaxStockQuantity(int maxStockQuantity) {
		this.maxStockQuantity = maxStockQuantity;
	}

	public int getMinStockQuantity() {
		return minStockQuantity;
	}

	public void setMinStockQuantity(int minStockQuantity) {
		this.minStockQuantity = minStockQuantity;
	}

	public int getRestockSchedule() {
		return restockSchedule;
	}

	public void setRestockSchedule(int restockSchedule) {
		this.restockSchedule = restockSchedule;
	}

	public String getDiscountStrategyID() {
		return discountStrategyID;
	}

	public void setDiscountStrategyID(String discountStrategyID) {
		this.discountStrategyID = discountStrategyID;
	}
	
	
	
	
	

}
