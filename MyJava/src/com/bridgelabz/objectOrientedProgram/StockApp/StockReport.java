/**
 * 
 */
package com.bridgelabz.objectOrientedProgram.StockApp;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * purpose
 * 
 * @author Chaithra-Shenoy
 * @version 1.0
 * @since 17-05-2018
 */
public class StockReport {
	static Scanner scan = new Scanner(System.in);
	static int sumStock = 0;
	static int totalStock = 0;

	public static void main(String[] args)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		StockPortfolio stockfolio = new StockPortfolio();
		ObjectMapper map = new ObjectMapper();
		System.out.println("Enter number of stock");
		int number = scan.nextInt();
		Stock stocknew = new Stock();
		for (int i = 0; i < number; i++) {
			stocknew = stockMethod();
			stockfolio.getStockList().add(stocknew);
		}
		map.writeValue(
				new File("/home/administrator/java/Task1/src/com/bridgelabz/objectOrientedProgram/StockApp/stock.json"),
				stockfolio);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader(
				"/home/administrator/java/Task1/src/com/bridgelabz/objectOrientedProgram/StockApp/stock.json"));
		JSONObject jsonObject = (JSONObject) obj;
		System.out.println(jsonObject);
		JSONArray array = (JSONArray) jsonObject.get("stockList");
		for (int i = 0; i < array.size(); i++) {
			JSONObject obstock = (JSONObject) (array.get(i));
			sumStock += ((long) obstock.get("stockNumber")) * ((long) obstock.get("stockPrice"));
			totalStock += (long) obstock.get("stockNumber");
		}
		System.out.println(sumStock);
		System.out.println(totalStock);
	}

	public static Stock stockMethod() {
		Stock stock = new Stock();
		System.out.println("Enter the stock name");
		String name = scan.next();
		stock.setStockName(name);
		System.out.println("Enter number of shares");
		int share = scan.nextInt();
		stock.setStockNumber(share);
		System.out.println("enter price per share");
		int price = scan.nextInt();
		stock.setStockPrice(price);
		return stock;
	}
}
