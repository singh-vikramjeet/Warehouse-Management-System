/*************************************************
 * FALL 2022
 * EECS 3311 GUI SAMPLE CODE
 * ONLT AS A REFERENCE TO SEE THE USE OF THE jFree FRAMEWORK
 * THE CODE BELOW DOES NOT DEPICT THE DESIGN TO BE FOLLOWED 
 */

package warehouseClientVisualizer.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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

import com.google.gson.JsonObject;

import httpClient.clientCaller;


public class MainClientUI extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JComboBox<String> productList;
	private static JComboBox<String> quantityList;
	private static String theProduct;
	private static String theQuantity;
	private JTextArea orderDetails;
	private static String productReport = null;
	private static String quantityReport = null;
	private static String timeReport = null; 
	private JLabel serverResponseLabel;
	

	private static MainClientUI instance;

	public static MainClientUI getInstance() {
		if (instance == null)
			instance = new MainClientUI();

		return instance;
	}

	private MainClientUI() {
		// Set window title
		super("Product Ordering Client");

		// Set top bar
		JLabel step1 = new JLabel("Step1 Choose Product");
		JLabel step2 = new JLabel("Step2 Choose Quantity");
		
		// Add Response Label for Server Response
		serverResponseLabel = new JLabel("Order Status");
		serverResponseLabel.setHorizontalAlignment(SwingConstants.CENTER);
		serverResponseLabel.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel chooseProductLabel = new JLabel(": ");
		Vector<String> productNames = new Vector<String>();
		productList = new JComboBox<String>(productNames);
		productNames.add("Product1");
		productNames.add("Product2");
		productNames.add("Product3");
		productNames.add("Product4");
		productNames.add("Product5");
		productNames.sort(null);

		JButton addProduct = new JButton("Choose");
		addProduct.setActionCommand("addProduct");
		addProduct.addActionListener(this);

		JLabel qty = new JLabel(": ");
		// JLabel to = new JLabel("To");
		Vector<String> quantity = new Vector<String>();
		for (int i = 0; i <= 1000; i = i + 50) {
			quantity.add("" + i);
		}
		quantity.remove(0);
		quantity.add(0, "1");
		quantityList = new JComboBox<String>(quantity);
		JButton addQuantity = new JButton("Choose");
		addQuantity.setActionCommand("addQuantity");
		addQuantity.addActionListener(this);

		quantityList.setActionCommand("addQuantity");

		JPanel north = new JPanel();
		north.add(step1);
		north.add(chooseProductLabel);
		north.add(productList);
		north.add(addProduct);
		north.add(step2);
		north.add(qty);
		north.add(quantityList);
		north.add(addQuantity);


		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		//createCharts(west);
		

		JLabel orderDetailsLabel = new JLabel("Order Details: ");
		orderDetails = new JTextArea(30, 60);
		JScrollPane orderDetailsScrollPane = new JScrollPane(orderDetails);
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(orderDetailsLabel);
		east.add(orderDetailsScrollPane);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(west, BorderLayout.WEST);
		
		// Add ServerResponseLabel to the Bottom of UI
		getContentPane().add(serverResponseLabel, BorderLayout.SOUTH);
	}

	private void createCharts(JPanel west) {
		createReport(west);

	}

	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = "Product " + theProduct + "\n" + 
				"Quantity " + theQuantity + "\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}
	
	// Method to update the text of the JLabel
    public void updateResponseLabelText(String newText) {
        serverResponseLabel.setText(newText);
    }

	
	public static void main(String[] args) {

		JFrame frame = MainClientUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);

	}
	private static void addDelay(int seconds) {
        try {
            // Sleep for the specified number of seconds
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		System.out.print(command);
		
		
		if ("addProduct".equals(command)) {
			/*
			 * stats.removeAll(); DataVisualizationCreator creator = new
			 * DataVisualizationCreator(); creator.createCharts();
			 */
			theProduct = productList.getSelectedItem().toString();
			productReport = "Product : " + theProduct + "\n";
			
		} else if ("addQuantity".equals(command)) {
			// selectedList.add(cryptoList.getSelectedItem().toString());
			theQuantity = quantityList.getSelectedItem().toString();
			quantityReport = "Quantity : " + theQuantity + "\n";
			timeReport = "Client Time Stamp : " +  java.time.LocalDateTime.now().toString() + "\n";
			
			orderDetails.setText(productReport + quantityReport + timeReport + "\n");
			
			// Send HttpRequest to Server with all the order details
			clientCaller cc = new clientCaller();
			// localhost/sendorder handler
			
			
			// Accepting String Response from Client
			String ResponseForClient = cc.sendHttpRequest(theProduct,theQuantity,java.time.LocalDateTime.now().toString());
			System.out.println("Response on ClientUI: " + ResponseForClient);
			updateResponseLabelText(ResponseForClient);
			
			

		}
	}
}