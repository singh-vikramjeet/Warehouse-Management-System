package observers;



import orderDetails.Order;
import orderDetails.Queue;
import orderModule.IOrderState;
import warehouseServerVisualizer.gui.MainServerUI;

public class ResponseLabelObserver implements IServerUIObserver {

	public void draw(IOrderState oneState) {
		String result = "Result";

//		Order anOrder = Queue.getFirstOrder();
//		String productName = anOrder.getProductName();
//		int productQuantity = anOrder.getProductQuantity();
		
		if(oneState.getClass().getSimpleName().equalsIgnoreCase("OrderRejected")) {
			result = "Order exceeds the max quantity set for this product and cannot be processed";
		}
		else if(oneState.getClass().getSimpleName().equalsIgnoreCase("OrderPending")) {
			//result = String.format("Order for %s and Quantity %d is pending – order exceeds available quantity", productName,productQuantity);
			result = "Order for Product X and Quantity Y is pending - order exceeds available quantity";
			
		}
		else if(oneState.getClass().getSimpleName().equalsIgnoreCase("OrderFulfilled")) {
			result = "Order is finalized for Product X and Quantity Y with total price Z";
			//result = String.format("Order is finalized for %s and Quantity %d with total price Z", productName, productQuantity);
		}
		
		// Update the text in the JLabel
		MainServerUI.getInstance().updateResponseLabelText(result);
		
	}

}
