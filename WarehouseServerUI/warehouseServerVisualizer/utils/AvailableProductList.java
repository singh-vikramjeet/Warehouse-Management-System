package warehouseServerVisualizer.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AvailableProductList {
	private static AvailableProductList instance = null;

	
	private HashMap<String, Integer> availableProductList = new HashMap<String, Integer>();

	public HashMap<String, Integer> getAvailableProductList() {
		return availableProductList;
	}

	public static AvailableProductList getInstance() {
		if (instance == null)
			instance = new AvailableProductList();
		

		return instance;
	}

	private AvailableProductList() {
		//findAvailableProductsAndQuantities();
	}

	public HashMap<String, Integer> findAvailableProductsAndQuantities() {
		
		// Here we query the Product DB and we get the product names or the product IDs

		availableProductList.clear();
		
		// Here we query the Product DB and we get the product names or the product IDs
		
		availableProductList.put("Product 1", 200);
		availableProductList.put("Product 2", 350);
		availableProductList.put("Product 3", 400);
		availableProductList.put("Product 4", 150);
		availableProductList.put("Product 5", 100);
		
		return availableProductList;

	}



}
