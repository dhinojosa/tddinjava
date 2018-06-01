package com.xyzcorp;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.util.List;

import static org.mockito.Mockito.mock;

public class ResultSet2StudentConverterTest {

    //H2, and HSQLDB

    @Test
    void testResultSetOf2ItemsToListStudents() {
        ResultSet resultSet = mock(ResultSet.class);
        //List<Student> studentList = ResultSet2StudentConverter.convert(resultSet);
    }
}
