package com.xyzcorp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebServiceRunner {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://raw.githubusercontent.com/dhinojosa/tddinjava/master/src/main/resources/library.txt");
        InputStream inputStream = url.openConnection().getInputStream();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Stream<String> lines = bufferedReader.lines();

        CheckoutParser checkoutParser = new CheckoutParser("~");
        PenaltyCalculator penaltyCalculator = new PenaltyCalculator(10);

        String result = checkoutParser
                .parseStream(lines)
                .sorted(Comparator.comparing(Checkout::getCheckoutDate))
                .limit(5)
                .map(co -> String.format("Name: %s, Owed: %d", co.getName(),
                        penaltyCalculator.calculate(LocalDate.now(), co.getCheckoutDate())))
                .collect(Collectors.joining("\n"));

        System.out.println(result);
    }
}
