package httpServerComponent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import java.util.Map;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import facade.OrderHandlerFacade;
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
import sqLiteDB.ProductDB;
import orderDetails.Queue;

import com.google.gson.Gson;
import com.google.gson.JsonObject;



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

	private static void addDelay(int seconds) {
		try {
			// Sleep for the specified number of seconds
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	static class OrderDetailsHandler implements HttpHandler {
		public void handle(HttpExchange exchange) throws IOException {
			Map<String, String> parms = queryToMap(exchange.getRequestURI().getQuery());


			// Pass the order details to the Model
			int quantityP2 = Integer.parseInt(parms.get("p2"));
			// Order Details from the client
			Order anOrder = new Order(parms.get("p1"),quantityP2,LocalDateTime.parse(parms.get("p3")));

			OrderHandlerFacade.getInstance();
			String response = OrderHandlerFacade.HandleOrder(anOrder);
			System.out.println(response);

			// Send Response back to Client
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