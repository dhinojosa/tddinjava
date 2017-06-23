package com.xyzcorp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Runner {
    public static void main(String[] args) {
        InputStream inputStream = Runner.class.getResourceAsStream("/library.txt");
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
