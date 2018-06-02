package com.xyzcorp;

import java.time.LocalDate;
import java.util.stream.Stream;

public class LibraryParser {

    public static Checkout parseLine(String data) {
        String[] tokens = data.split("~");
        return new Checkout(tokens[0], tokens[1], LocalDate.parse(tokens[2]));
    }


    public static Stream<Checkout> parseStream(Stream<String> stringStream) {
        return stringStream.map(LibraryParser::parseLine);
    }
}
