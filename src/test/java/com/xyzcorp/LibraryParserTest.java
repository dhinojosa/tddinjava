package com.xyzcorp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LibraryParserTest {

    @Test
    void testParseOfNameASingleLine() {
        var data = "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12";
        Checkout checkout = LibraryParser.parseLine(data);
        assertThat(checkout.getName()).isEqualTo("Brian Sletten");
    }

    @Test
    void testParseOfTitleASingleLine() {
        var data = "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12";
        Checkout checkout = LibraryParser.parseLine(data);
        assertThat(checkout.getTitle()).isEqualTo("A Supposedly Fun Thing I Will Never Do Again");
    }

    @Test
    void testParseOfCheckoutDateASingleLine() {
        var data = "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12";
        Checkout checkout = LibraryParser.parseLine(data);
        assertThat(checkout.getDate()).isEqualTo(LocalDate.of(2015,2,12));
    }

    //Add a lot more!

    @Test
    void testParseFromStream() {


        String data = "Sarah Smith~To Kill a Mockingbird~2014-11-19\n" +
                "Mimansha Bhargav~Lost Symbol~2015-01-05\n" +
                "Daniel Hinojosa~Beautiful Flowers~2013-10-20\n";

        Stream<String> original = Arrays.stream(data.split("\n"));
        Stream<Checkout> stream = LibraryParser.parseStream(original);

        List<Checkout> list = stream.collect(Collectors.toList());

        assertThat(list.get(0).getName()).isEqualTo("Sarah Smith");
        assertThat(list.get(1).getTitle()).isEqualTo("Lost Symbol");
        assertThat(list.get(2).getDate()).isEqualTo(LocalDate.of(2013,10,20));
    }


    @Test
    void testIntegrationWithWebService() throws IOException {
        String location = "https://raw.githubusercontent.com/dhinojosa/tddinjava/master/src/main/resources/library.txt";
        Stream<String> stream = URLToStreamString.convertFromURL(location);
        Stream<Checkout> checkoutStream = LibraryParser.parseStream(stream);
        Checkout checkout = checkoutStream.sorted(Comparator.comparing
                (Checkout::getDate)).findFirst().orElseThrow(() ->
                        new IllegalStateException("No one found"));
        assertThat(checkout.getName()).isEqualTo("Vivek Nag");
    }

    @Test
    void testIntegrationWithFile() throws IOException {
        Stream<String> stream = URLToStreamString.convertFromInputStream(getClass().getResourceAsStream("/library.txt"));
        Stream<Checkout> checkoutStream = LibraryParser.parseStream(stream);
        Checkout checkout = checkoutStream.sorted(Comparator.comparing
                (Checkout::getDate)).findFirst().orElseThrow(() ->
                new IllegalStateException("No one found"));
        assertThat(checkout.getName()).isEqualTo("Vivek Nag");
    }


}
