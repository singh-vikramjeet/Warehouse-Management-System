package httpServerComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import warehouseServerVisualizer.utils.*;
import java.time.LocalDateTime;
import orderDetails.Order;
import quantityLogic.QuantityComparison;
import productModule.IProduct;

import factoryMethod.StateFactory;
import orderModule.*;
import java.util.List;
import observers.*;
import subject.ModelDataSubject;



// The client calls http://localhost:8000/test1?p1=10&p2=20 (e.g. from a Web browser or a Java Client program)
// and gets back as a response "Hello World! P1 was: 10 and p2 was: 20"
// If the client calls http://http://localhost:8000/test2/?p3=1000
// it gets as a response "Hello New Brave World!  p3 was: 1000"

// Note that the server can respond by sending back a Json or XML string
// which is interpreted by the client appropriately as per the logic of the client

public class Server {
	public void startServer() throws Exception {
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/test1", new MyHandler1());
		server.createContext("/sendorder", new OrderDetailsHandler());
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
	}
	
	
	static class MyHandler1 implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			String response = "Hello Brave World! " + "P1 was: " + parms.get("p1") + " and P2 was: " + parms.get("p2");
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			try {
				wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response = " FOO";
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os2 = exchange.getResponseBody();
			os2 = exchange.getResponseBody();
			os2.write(response.getBytes());
			os.close();
			os2.close();
		}
	}
	
	static class OrderDetailsHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			// Update the Server UI here by creating an instance of LastOrder
			LastOrder.getInstance();
			LastOrder.setProductName(parms.get("p1"));
			LastOrder.setQuantity(1000);
			//LocalDateTime.parse(parms.get("p3"))
			LastOrder.setDate(LocalDateTime.parse(parms.get("p3")));
			// Pass the order details to the Model
			int quantityP2 = Integer.parseInt(parms.get("p2"));
			// Order Details from the client
			Order anOrder = new Order(parms.get("p1"),quantityP2,LocalDateTime.parse(parms.get("p3")));
			// Product Details from the Database
			//(ProductID, currentStockQuantity, maxStockQuantity)
			IProduct aProduct = new IProduct(1,200,400);
			//QuantityComparison qc = new QuantityComparison(anOrder, aProduct);
			QuantityComparison qc = new QuantityComparison(anOrder);
			qc.setCurrentStockQuantity(200);
			qc.setMaxStockQuantity(400);
			
			qc.determineOrderState();
	
			int OrderState = qc.getOrderState();
			
			// Create Factory Instance
			StateFactory sf = StateFactory.getInstance();
			// Pass the order state to the factory method to create the desired order state
			
			IOrderState aState = sf.create(OrderState);
			
			// Get response from the Order State and send it to the client
			String stateResponse = aState.response();
			// Create instances of 2 observers
			IServerUIObserver bcObserver = new BarChartObserver();
			IServerUIObserver taObserver = new TextAreaObserver();
			// Create a list of observers
			List<IServerUIObserver> observersList = new ArrayList<IServerUIObserver>();
			// Add the observers to the observersList
			observersList.add(bcObserver);
			observersList.add(taObserver);
			// Pass Order State to the Model Data Subject 
			ModelDataSubject mds = new ModelDataSubject(observersList,aState);
			
			// Model Data Subject will notify the observers and update the server UI according to the State
			mds.notifyObservers(aState);
			
			String response = stateResponse;
			System.out.println(response);
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
						
			
		}
}

	public static Map<String, String> queryToMap(String query){

	    Map<String, String> result = new HashMap<String, String>();
	    for (String param : query.split("&")) {
	        String pair[] = param.split("=");
	        if (pair.length>1) {
	            result.put(pair[0], pair[1]);
	        }else{
	            result.put(pair[0], "");
	        }
	    }
	    return result;
	  }
}