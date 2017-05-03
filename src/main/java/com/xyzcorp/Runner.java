package com.xyzcorp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Runner {
   public static void main(String[] args) {
	   InputStream is = Runner.class.getResourceAsStream("/library.txt");
	   InputStreamReader reader = new InputStreamReader(is);
	   BufferedReader buffReader = new BufferedReader(reader);
	   
	   CheckoutParser parser = new CheckoutParser("~");
	   PenaltyCalculator calculator = new PenaltyCalculator(LocalDate::now, 5);

	   String result = buffReader.lines()
	      .map(parser::parse)
	      .sorted(Comparator.comparing(Checkout::getCheckoutDate)).limit(5)
          .map(checkout -> checkout.getName() + " " + calculator.calculate(checkout.getCheckoutDate()))
                 .collect(Collectors.joining(","));

       System.out.println("result = " + result);
   }
}
