package com.xyzcorp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.stream.Stream;

public class URLToStreamString {
    public static Stream<String> convertFromURL(String location) throws IOException {
        URL url= new URL(location);
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.lines();
    }

    public static Stream<String> convertFromInputStream(InputStream is) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        return bufferedReader.lines();
    }
}
