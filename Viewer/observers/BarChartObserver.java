package observers;

import java.awt.GridLayout;

import javax.swing.JPanel;

import orderModule.IOrderState;
import warehouseServerVisualizer.gui.MainServerUI;

//The OBSERVER PATTERN
// If OrderState is OrderFulfilled, update both BarChart and Text Area
// Else update only the Text Area 

public class BarChartObserver implements IServerUIObserver {
	public void draw(IOrderState oneState) {

		
		// Call createBar method here from MainServerUI
		if(oneState.getClass().getSimpleName().equalsIgnoreCase("OrderFulfilled")) {
			//System.out.println("Inside Bar Chart Observer :: Bar chart is updated");
			// Get west JPanel
			JPanel jp = MainServerUI.getInstance().getWest();
			// Update the chart data in the MainServerUI
			MainServerUI.updateChartData();
			// Remove the old Bar chart from JPanel
			jp.removeAll();
			// Create new Bar chart 
			MainServerUI.getInstance().createBar(jp);
			//jp.removeAll();
			//jp.invalidate();
//			jp.validate();
//			jp.repaint();
			
			
		}
	}
	

}
