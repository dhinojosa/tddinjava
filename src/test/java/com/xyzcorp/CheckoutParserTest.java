package com.xyzcorp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CheckoutParserTest {

    //Choose the simplest thing first
    @Test
    public void testParsingOfEmptyStream() throws Exception {
        CheckoutParser checkoutParser = new CheckoutParser("~");
        Stream<Checkout> result = checkoutParser.parseStream(Stream.empty());
        assertThat(result).isEmpty();
    }

    @Test
    public void testParsingOfOneItemStreamWithName() throws Exception {
        CheckoutParser checkoutParser = new CheckoutParser("~");
        Stream<Checkout> result = checkoutParser.parseStream
                (Stream.of("Sarah Smith~To Kill a Mockingbird~2014-11-19"));
        assertThat(result.findFirst().get().getName()).isEqualTo("Sarah Smith");
    }

    @Test
    public void testParsingOfOneItemStreamWithDate() throws Exception {
        CheckoutParser checkoutParser = new CheckoutParser("~");
        Stream<Checkout> result = checkoutParser.parseStream
                (Stream.of("Sarah Smith~To Kill a Mockingbird~2014-11-19"));
        assertThat(result.findFirst().get().getCheckoutDate())
                .isEqualTo(LocalDate.of(2014, 11, 19));
    }

    @Test
    public void testParsingOfOneItemStreamWithNameAndDifferentDelimiter() throws Exception {
        CheckoutParser checkoutParser = new CheckoutParser("#");
        Stream<Checkout> result = checkoutParser.parseStream
                (Stream.of("Sarah Smith#To Kill a Mockingbird#2014-11-19"));
        assertThat(result.findFirst().get().getName()).isEqualTo("Sarah Smith");
    }

    @Test
    public void testParsingOfAnEmptyLine() throws Exception {
        CheckoutParser checkoutParser = new CheckoutParser("#");
        assertThatThrownBy(() -> checkoutParser.parseLine(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CheckoutParser.STRING_CANNOT_BE_EMPTY);
    }

    @Test
    public void testParsingOfALineThatHasAnInvalidDate() throws Exception {
        CheckoutParser checkoutParser = new CheckoutParser("#");
        assertThatThrownBy(() -> checkoutParser.parseLine("Sarah Smith#To Kill a Mockingbird#2014-120-30"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(CheckoutParser.INVALID_DATE)
                .hasCauseInstanceOf(DateTimeParseException.class);
    }

    //There is a lot more we should test!


    @Test
    public void testWebService() {
        String data = "Sarah Smith~To Kill a Mockingbird~2014-11-19\n" +
                "Mimansha Bhargav~Lost Symbol~2015-01-05\n" +
                "Daniel Hinojosa~Beautiful Flowers~2013-10-20\n" +
                "Venkat Subramaniam~Function Programming~2015-03-10\n" +
                "Brian Sletten~A Supposedly Fun Thing I Will Never Do Again~2015-02-12\n" +
                "Beth Brown~The Leftovers~2013-03-31\n" +
                "Janelle Klein~On Intelligence~2014-07-21\n" +
                "Jim Price~The Girl In The Spider's Web~2015-08-15\n" +
                "Hao Guan~Ender's Game~2012-02-12\n" +
                "Vivek Nag~Alchemist~2009-04-20\n" +
                "Anshuman Purohit~Harry Potter and the Prisoner of Azkaban~2013-10-19\n" +
                "Timothy Cheng~Hamlet~2015-12-15\n" +
                "Judy Hirsch~Crime and Punishment~2015-11-19\n" +
                "Jim Czenkusch~Warlock inspite of itself~2016-04-30\n" +
                "Amit Sharma~Effective Java~2016-05-31\n" +
                "Lloyd Moore~Paradise Lost~2016-01-10";

        CheckoutParser checkoutParser = new CheckoutParser("~");
        assertThat(checkoutParser.parseStream(Arrays.stream(data.split("\n"))).count()).isEqualTo(16);
    }
}
