package com.xyzcorp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

public class WebServiceRunner {

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/dhinojosa/tddinjava/master/src/main/resources/library.txt");
        InputStreamReader reader = new InputStreamReader(url.openConnection().getInputStream());
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
