package com.salesTaxCalculator.salesTaxCalculator;

import com.salesTaxCalculator.salesTaxCalculator.Basket.Item;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SalesTaxCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SalesTaxCalculatorApplication.class, args);
		List<List<Item>> baskets = new ArrayList<List<Item>>();
		System.out.println("###INPUT");
		System.out.println("Input 1: ");
		Item item1 = new Item(1, 2, "book", 12.49, false);
		System.out.println(item1.getQuantity() + " " + item1.getName() + " at " + item1.getPrice());
		Item item2 = new Item(1, 1, "CD", 14.99, false);
		System.out.println(item2.getQuantity() + " " + item2.getName() + " at " + item2.getPrice());
		Item item3 = new Item(1, 2, "chocolate bar", 0.85, false);
		System.out.println(item3.getQuantity() + " " + item3.getName() + " at " + item3.getPrice()+ "\n");
		List<Item> firstBasket = new ArrayList<Item>(Arrays.asList(item1, item2, item3));
		baskets.add(firstBasket);

		System.out.println("Input 2: ");
		Item item4 = new Item(1, 2,"imported box of chocolates", 10.0, true);
		System.out.println(item4.getQuantity() + " " + item4.getName() + " at " + item4.getPrice());
		Item item5 = new Item(1, 1, "imported bottle of perfume", 47.50, true);
		System.out.println(item5.getQuantity() + " " + item5.getName() + " at " + item5.getPrice() + "\n");
		List<Item> secondBasket = new ArrayList<Item>(Arrays.asList(item4, item5));
		baskets.add(secondBasket);

		System.out.println("Input 3: ");
		Item item6 = new Item(1, 1,"imported bottle of perfume", 27.99, true);
		System.out.println(item6.getQuantity() + " " + item6.getName() + " at " + item6.getPrice());
		Item item7 = new Item(1, 1,"bottle of perfume", 18.99, false);
		System.out.println(item7.getQuantity() + " " + item7.getName() + " at " + item7.getPrice());
		Item item8 = new Item(1, 2, "packet of headache pills", 9.75, false);
		System.out.println(item8.getQuantity() + " " + item8.getName() + " at " + item8.getPrice());
		Item item9 = new Item(1, 2, "box of imported chocolates", 11.25, true);
		System.out.println(item9.getQuantity() + " " + item9.getName() + " at " + item9.getPrice()+ "\n");
		List<Item> thirdBasket = new ArrayList<Item>(Arrays.asList(item6, item7, item8, item9));
		baskets.add(thirdBasket);

		System.out.println("###OUTPUT");
		int counter = 0;
		for(int i = 0; i<baskets.size(); i++) {
			counter++;
			System.out.println("Output " + counter + ":" + "\n");
			Double totalSales = 0.0;
			Double totalOldSales = 0.0;
			for(int j = 0; j< baskets.get(i).size(); j++) {
				BigDecimal itemPriceDecimal = new BigDecimal(baskets.get(i).get(j).getPrice());
				itemPriceDecimal = itemPriceDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);   //using 0.05 round up to the closest digit
				totalOldSales += itemPriceDecimal.doubleValue();
				Item recalculatedItem = recalculateOrderPrice(baskets.get(i).get(j));
				totalSales += recalculatedItem.getPrice();
			}
			BigDecimal saleTaxesRounded = new BigDecimal(totalSales - totalOldSales); // calculating tax difference from base prise
			BigDecimal totalSaleTaxRounded = new BigDecimal(totalSales);  // total sales
			System.out.println("Sales Taxes: " + saleTaxesRounded.setScale(2, BigDecimal.ROUND_HALF_UP));
			System.out.println("Total: " + totalSaleTaxRounded.setScale(2, BigDecimal.ROUND_HALF_UP) + "\n");
		}

	}

	public static Item recalculateOrderPrice(Item item) {
		BigDecimal decimalPrice = null;
		if(item.getTaxCategory() != null && item.getTaxCategory() == 1 && item.getImporting() != false) {  // this is the category of item that includes 10% and additional 5% of taxes
			decimalPrice = new BigDecimal(item.getPrice() + (item.getPrice()*15/100));
			decimalPrice = decimalPrice.setScale(2, BigDecimal.ROUND_HALF_UP); //using 0.05 round up to the closest digit
			item.setPrice(decimalPrice.doubleValue());
		} else if (item.getImporting() != false && item.getTaxCategory() == 2) { // this is the category of item that is being exempted and is only included as imported item with 5%
			decimalPrice = new BigDecimal(item.getPrice() + (item.getPrice()*5/100));
			decimalPrice = decimalPrice.setScale(2, BigDecimal.ROUND_HALF_UP); //using 0.05 round up to the closest digit
			item.setPrice(decimalPrice.doubleValue());
		} else if (item.getImporting() != true && item.getTaxCategory() != null && item.getTaxCategory() == 1) { // this is for the rest of the items that have only 10% basic tax
			decimalPrice = new BigDecimal(item.getPrice() + (item.getPrice()*10/100));
			decimalPrice = decimalPrice.setScale(2, BigDecimal.ROUND_HALF_UP); //using 0.05 round up to the closest digit
			item.setPrice(decimalPrice.doubleValue());
		}

		Double totalPrice = item.getQuantity() * item.getPrice();
		System.out.println(item.getQuantity() + " " + item.getName() + ": " + totalPrice);
		return item;
	}


}
