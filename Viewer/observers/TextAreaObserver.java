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
		
		
		//MainServerUI.getInstance().createReport(east);
		
		// Call createReport method here from MainServerUI
			System.out.println("Inside Text Area Observer :: Text Area is updated");		
	}

}
