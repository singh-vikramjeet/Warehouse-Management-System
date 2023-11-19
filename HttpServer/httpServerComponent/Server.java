package httpServerComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
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
		//server.createContext("/test2", new MyHandler2());
		server.createContext("/sendorder", new OrderDetailsHandler());
		server.createContext("/test3", new OrderProductHandler());
		// Create a new fixed thread pool executor
		//ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)Executors.newFixedThreadPool(10);
		//server.setExecutor(threadPoolExecutor);
		server.setExecutor(Executors.newCachedThreadPool());
		server.start();
	}
	
	
	static class OrderProductHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			String response = "Hello World! " + " p2 was: " + parms.get("p2");
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.flush();
			os.close();
		}
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
	
	
	static class MyHandler2 implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			String response = "Hello! " + " p3 was: " + parms.get("p3");
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
		}
	}
	
	static class OrderDetailsHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());
			// Update the Server UI here by creating an instance of LastOrder
			LastOrder lo = LastOrder.getInstance();
			lo.setProductName(parms.get("p1"));
			lo.setQuantity(1000);
			//LocalDateTime.parse(parms.get("p3"))
			lo.setDate(LocalDateTime.parse(parms.get("p3")));
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
			
			
			// Need to implement FACADE DESIGN PATTERN for the steps below
			// Check Inventory
			//qc.compareMaxStockQuantity();
			//qc.compareCurrentStockQuantity();
			qc.determineOrderState();
	
			int OrderState = qc.getOrderState();
			//System.out.println("Order State: " + OrderState);
			
			// Create Factory Instance
			StateFactory sf = StateFactory.getInstance();
			// Pass the order state to the factory method to create the desired order state
			
			IOrderState aState = sf.create(OrderState);
			
			// Get response from the Order State and send it to the client
			String stateResponse = aState.response();
			
			// Pass Order State to the Model Data Subject 
			
			
			// Model Data Subject will notify the observers and update the server UI according to the State
			
			
			String response = stateResponse;
			System.out.println(response);
			exchange.sendResponseHeaders(200, response.length());
			OutputStream os = exchange.getResponseBody();
			os.write(response.getBytes());
			os.close();
			
			
			
			// The program terminates after the response is sent to the client
			//String response = "Hello World! " + "P1 was: " + parms.get("p1") + " and p2 was: " + parms.get("p2") + " and p3 was: " + parms.get("p3");
			
			
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