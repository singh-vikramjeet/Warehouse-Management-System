package sqLiteDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import productModule.IProduct;

public class ProductDB {
	// List of products
	private static List<IProduct> productList = new ArrayList<IProduct>();
	
	
	public static List<IProduct> getProductList() {
		return productList;
	}

	public static void setProductList(List<IProduct> productList) {
		ProductDB.productList = productList;
	}

	public static void connect() {
		Connection conn = null;
		try {
			// create a database connection
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/vikramjeetsingh/DBBrowser/ProductDB.db");
			Statement statement = conn.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.


			ResultSet rs = statement.executeQuery("select * from products");
			 while(rs.next())
		      {
		        // read the result set
				// Printing the result set
//		        System.out.println("Product Id = " + rs.getInt("Id"));
//		        System.out.println("Product Name = " + rs.getString("Name"));
//		        System.out.println("Current Stock Quantity = " + rs.getInt("CurrentStockQuantity"));
//		        System.out.println("Unit Price= " + rs.getInt("UnitPrice"));
//		        System.out.println("Max Stock Quantity = " + rs.getInt("MaxStockQuantity"));
//		        System.out.println("Restock Schedule= " + rs.getInt("RestockSchedule"));
//		        System.out.println("Discount Strategy= " + rs.getString("DiscountStrategy"));
		        
		        IProduct aProduct = new IProduct();
		        aProduct.setProductID(rs.getInt("Id"));
		        aProduct.setProductName(rs.getString("Name"));
		        aProduct.setCurrentStockQuantity(rs.getInt("CurrentStockQuantity"));
		        aProduct.setUnitPrice(rs.getInt("UnitPrice"));
		        aProduct.setMaxStockQuantity(rs.getInt("MaxStockQuantity"));
		        aProduct.setRestockSchedule(rs.getInt("RestockSchedule"));
		        aProduct.setMinStockQuantity(rs.getInt("MinStockQuantity"));
		        aProduct.setDiscountStrategyID(rs.getString("DiscountStrategy"));
		        productList.add(aProduct);
		        
		      }

		} catch(SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.close();
					//System.out.println("Connection to Product DB closed");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}

	}
	
	
	
	// Method to update Current Stock Quantity
	// UPDATE products
	// SET  CurrentStockQuantity = 500
	// WHERE Name="Product5";
	
	public static void updateCurrentStockQuantity(String productName, int productQuantity) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/vikramjeetsingh/DBBrowser/ProductDB.db");
			//System.out.println("Connection to SQLite has been established");
			
			// SQL to update the Current Stock Quantity
			String updateStockSql = "UPDATE products SET CurrentStockQuantity=? WHERE Name=?";
			PreparedStatement pstmt = conn.prepareStatement(updateStockSql);
			pstmt.setString(2,productName);
			pstmt.setInt(1, productQuantity);
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (conn != null) {
					conn.close();
					//System.out.println("Connection to Product DB closed");
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
	
	// Restock Product
	// UPDATE products
	// SET CurrentStockQuantity = CurrentStockQuantity + 30 (30 is the restocking schedule)
	// WHERE Name = "Product3"
	
	public static void restockProduct(String productName, int restockSchedule) {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:/Users/vikramjeetsingh/DBBrowser/ProductDB.db");
			//System.out.println("Connection to SQLite has been established for Restock Operation");
			
			// SQL to update the Current Stock Quantity
			String updateStockSql = "UPDATE products SET CurrentStockQuantity = CurrentStockQuantity + ? WHERE Name=?";
			PreparedStatement pstmt = conn.prepareStatement(updateStockSql);
			pstmt.setString(2,productName);
			pstmt.setInt(1, restockSchedule);
			pstmt.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if (conn != null) {
					conn.close();
					//System.out.println("Connection to Product DB closed");
				}
			} catch (SQLException ex) {
				//System.out.println(ex.getMessage());
			}
		}
	}
	

}
