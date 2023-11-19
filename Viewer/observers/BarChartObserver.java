package observers;

import orderModule.IOrderState;

//The OBSERVER PATTERN
// If OrderState is OrderFulfilled, update both BarChart and Text Area
// Else update only the Text Area 

public class BarChartObserver implements IServerUIObserver {
	public void draw(IOrderState oneState) {
		// Call createBar method here from MainServerUI
		if(oneState.getClass().getSimpleName().equalsIgnoreCase("OrderFulfilled")) {
			System.out.println("Inside Bar Chart Observer :: Bar chart is updated");
		}
		
		
	}
	

}
