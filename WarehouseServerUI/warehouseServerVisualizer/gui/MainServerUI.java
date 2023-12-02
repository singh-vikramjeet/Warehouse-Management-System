/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package warehouseServerVisualizer.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import warehouseServerVisualizer.utils.AvailableProductList;
import warehouseServerVisualizer.utils.LastOrder;

public class MainServerUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Map<String, Integer> productData;
	private static LastOrder theLastOrder;

	private static MainServerUI instance;
	
	private JPanel east; // Text Area
	private JLabel responseLabel; //
	private JPanel west; // Bar Chart

	public static MainServerUI getInstance() {
		if (instance == null)
			instance = new MainServerUI();

		return instance;
	}
	

	public MainServerUI() {
		// Set window title
		super("Warehouse Server UI");

		productData = AvailableProductList.getInstance().findAvailableProductsAndQuantities();
		theLastOrder = LastOrder.getInstance().findLastOrder();


		// Set charts region
		west = new JPanel();
		west.setLayout(new GridLayout(2, 0));

		east = new JPanel();
		east.setLayout(new GridLayout(2, 0));

		getContentPane().add(west, BorderLayout.WEST);
		getContentPane().add(east, BorderLayout.EAST);

		createCharts(west, east);
		
		
		// Add a panel for the bottom label
	    JPanel bottomPanel = new JPanel(new BorderLayout());
	    responseLabel = new JLabel("Result: Order is finalized for Product 1 and Quantity 1 with total price 500", SwingConstants.CENTER);
	    responseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	    responseLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
	    bottomPanel.add(responseLabel, BorderLayout.CENTER);

	    // Add the bottom panel to the main UI at the SOUTH position
	    getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		
		

	}

	public void createCharts(JPanel west, JPanel east) {

		createBar(west);
		createReport(east);

	}

	public void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage1, reportMessage2,reportMessage3;

		reportMessage1 = "Last Order\n" + "==========================\n" + "\t";
		reportMessage1 = reportMessage1 + "Product: " + theLastOrder.getProductName() + "\n" 
		                 + "\tQuantity:" +  theLastOrder.getQuantity() + "\n"
		                 + "\tTimeStamp:" +  theLastOrder.getDate() + "\n";
		

		reportMessage2 = "Current Product Quantity in Warehouse\n" + "==============================\n";
		

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			//System.out.println("IN LOOP " + entry.getKey() + " " + entry.getValue());
			reportMessage2 = reportMessage2 + entry.getKey();
			reportMessage2 = reportMessage2 + "\n \t Quantity ==> " + entry.getValue() + "\n";

		}


	

		report.setText(reportMessage1 + reportMessage2);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

	public void createBar(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Map.Entry<String, Integer> entry : productData.entrySet()) {
			dataset.setValue(entry.getValue(), entry.getKey(), "");

		}


		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setRenderer(1, barrenderer2);

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis

		JFreeChart barChart = new JFreeChart("Warehouse Product Monitor System",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);


		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}
	
	// Method to update the text of the JLabel
    public void updateResponseLabelText(String newText) {
        responseLabel.setText(newText);
    }
	
	public static void displayUI() {
		JFrame frame = MainServerUI.getInstance();
		frame.setSize(800, 600);
		// frame.pack();
		frame.setVisible(true);
	}


}