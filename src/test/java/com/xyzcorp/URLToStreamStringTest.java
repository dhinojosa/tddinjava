package com.xyzcorp;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class URLToStreamStringTest {

    @Test
    void testURLToStreamString() throws IOException {
        String location = "https://raw.githubusercontent.com/dhinojosa/tddinjava/master/src/main/resources/library.txt";
        Stream<String> stream = URLToStreamString.convertFromURL(location);
        assertThat(stream).isNotNull();
    }
}
