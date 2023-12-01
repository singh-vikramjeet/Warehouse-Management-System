package facade;

public class OrderHandlerFacade {
	
	public void HandleOrder() {
		// Step 1: Get first order from the Queue
		
		// Step 2: Get Product List from Product DB
		
		// Step 3: Extract the desired Product from the Product List
		
		// Step 4: Pass the Order object and Product object to the Controller (QuantityComparison class)
		
		// Step 5: Call determineOrderState() in QuantityComparison class
		
		// Step 6: Get Order state (1, 2 or 3) from the QuantityComparison (Controller)
		
		// Step 7: Use State Factory to create the order state
		
		// Step 8: Get Response from the Order State
		
		// Step 9: Convert the Response from String into JSON object
		
		// Step 10: Send the response back to Client
		
		// Step 11: Display the response on both ServerUI and ClientUI
	}

}
