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
import sqLiteDB.ProductDB;
import productModule.IProduct;

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
	
		List<IProduct> lp = ProductDB.getProductList();
		int[] quantity = new int[5];
		for(int i = 0; i < 5;i++) {
			quantity[i] = lp.get(i).getCurrentStockQuantity();
		}
		
		//int r1 = lp.get(0).getCurrentStockQuantity();
		//int r2 = lp.get(1).getCurrentStockQuantity();

		availableProductList.clear();
		
		// Here we query the Product DB and we get the product names or the product IDs
		
		availableProductList.put("Product 1", quantity[0]);
		availableProductList.put("Product 2", quantity[1]);
		availableProductList.put("Product 3", quantity[2]);
		availableProductList.put("Product 4", quantity[3]);
		availableProductList.put("Product 5", quantity[4]);
		
		return availableProductList;

	}



}
