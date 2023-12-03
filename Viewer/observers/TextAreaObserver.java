package observers;

import java.awt.GridLayout;

import javax.swing.JPanel;

import orderModule.IOrderState;
import warehouseServerVisualizer.gui.MainServerUI;

//The OBSERVER PATTERN
// If OrderState is OrderFulfilled, update both BarChart and Text Area
// Else update only the Text Area 

public class TextAreaObserver implements IServerUIObserver {
	public void draw(IOrderState oneState) {
		
		// Get the JPanel for Text Area
		JPanel jp = MainServerUI.getInstance().getEast();
		// Update the Product Data list in the MainServerUI
		MainServerUI.updateChartData();
		// Call removeAll() on JPanel to remove the old components
		jp.removeAll();
		// Create new Report 
		MainServerUI.getInstance().createReport(jp);
		jp.invalidate();
		jp.validate();
		jp.repaint();
		
		// Call createReport method here from MainServerUI
			System.out.println("Inside Text Area Observer :: Text Area is updated");		
	}

}
